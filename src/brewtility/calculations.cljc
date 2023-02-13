(ns brewtility.calculations
  "Namespace for handling recipe calculations.
   This namespace assumes ingredients that conform to the common-beer-format."
  {:added "1.0"}
  (:require [brewtility.color :as color]
            [brewtility.predicates.fermentables :as fermentables]
            [brewtility.units :as units]))


(defn normalize-fermentable
  "Given a `common-beer-format` conforming `fermentable`, normalize it for color computation."
  {:added "1.0"}
  [fermentable]
  (let [is-not-grain? (not (fermentables/grain? fermentable))
        kg->lbs       (fn [w] (units/convert-weight w :kilogram :pound))] ; MCU is calculated against pounds
    (cond-> fermentable
      true          (update :amount kg->lbs)
      is-not-grain? (update :color color/srm->lovibond)))) ; Grain color is in Lovibond, all other fermentables use SRM

(defn calculate-malt-color-units
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the overall Malt Color Units for a recipe."
  {:added    "1.0"
   :see-also ["calculate-srm-color" "calculate-ebc-color" "calculate-lovibond-color" "calculate-rgba-color"]}
  [fermentables batch-size]
  (let [normalized-fermentables (map normalize-fermentable fermentables)
        reducing-fn             (fn [acc v] (+ acc (* (:amount v) (:color v))))
        color                   (reduce reducing-fn 0 normalized-fermentables)
        imperial-volume         (units/convert-volume batch-size :litre :american-gallon)]
    (/ color imperial-volume)))


(defn calculate-srm-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the SRM color for a recipe."
  {:added    "1.0"
   :see-also ["calculate-srm-color" "calculate-ebc-color" "calculate-lovibond-color" "calculate-rgba-color"]}
  [fermentables batch-size]
  (let [mcu-color (calculate-malt-color-units fermentables batch-size)]
    (* mcu-color 1.02349998)))


(defn calculate-ebc-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the EBC color for a recipe."
  {:added    "1.0"
   :see-also ["calculate-malt-color-units"
              "calculate-srm-color"
              "calculate-lovibond-color"
              "calculate-rgba-color"]}
  [fermentables batch-size]
  (-> fermentables
      (calculate-srm-color batch-size)
      color/srm->ebc))


(defn calculate-lovibond-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the color in degrees Lovibond for a recipe."
  {:added    "1.0"
   :see-also ["calculate-malt-color-units"
              "calculate-srm-color"
              "calculate-ebc-color"
              "calculate-rgba-color"]}
  [fermentables batch-size]
  (-> fermentables
      (calculate-srm-color batch-size)
      color/srm->lovibond))


(defn calculate-rgba-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the RGBA color for a recipe."
  {:added    "1.0"
   :see-also ["calculate-malt-color-units"
              "calculate-srm-color"
              "calculate-lovibond-color"
              "calculate-ebc-color"]}
  [fermentables batch-size]
  (-> fermentables
      (calculate-srm-color batch-size)
      color/srm->rgba))


(defn potential-gravity->gravity-points
  "Given the `potential-gravity` of a fermentable, and the `weight` of the fermentable, calculate gravity points."
  {:added    "1.0"
   :see-also ["gravity-points->potential-gravity"]}
  [potential-gravity weight]
  (let [weight-in-pounds (units/convert-weight weight :kilogram :pound)]
    (-> potential-gravity
        (* 1000)
        (- 1000)
        (* weight-in-pounds))))


(defn gravity-points->potential-gravity
  "Given the `gravity-points` of a recipe, and the `volume` of the batch, calculate the potential gravity."
  {:added    "1.0"
   :see-also ["potential-gravity->gravity-points"]}
  [gravity-points volume]
  (let [volume-in-gallons (units/convert-volume volume :litre :american-gallon)]
    (-> gravity-points
        (/ volume-in-gallons)
        (+ 1000)
        (/ 1000.0))))


(defn calculate-potential-gravity
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, calculate the potential original gravity."
  {:added    "1.0"
   :see-also ["calculate-potential-final-gravity"]}
  [fermentables batch-size]
  (let [reducing-fn (fn [acc ferm]
                      (let [maximum-gravity (potential-gravity->gravity-points (:potential ferm) (:amount ferm))]
                        (+ acc (* (:yield ferm) maximum-gravity))))
        total-gravity-points (reduce reducing-fn 0 fermentables)]
    (gravity-points->potential-gravity total-gravity-points batch-size)))


(def ^:const gravity->abv-multiplier
  "The multiplier used to convert gravity to ABV. 

   This is a constant, and is not configurable."
  0.00135)


(def ^:const default-attenuation
  "A default attenuation for yeast if none is provided.
   This represents a common level home brewers should expect from their yeast."
  0.75)


(defn calculate-potential-final-gravity
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, estimate the Final Gravity.
   The primary fermentation yeast's `attenuation` may also be passed, otherwise 75% is assumed."
  {:added    "1.1"
   :see-also ["calculate-potential-gravity"]}
  ([fermentables batch-size]
   (calculate-potential-final-gravity fermentables batch-size default-attenuation))

  ([fermentables batch-size attenuation]
   (let [gravity (calculate-potential-gravity fermentables batch-size)
         gravity-points (-> gravity
                            (* 1000)
                            (- 1000))
         attenuated-points (* gravity-points attenuation)]

     (-> gravity-points
         (- attenuated-points)
         (+ 1000)
         (/ 1000.0)))))


(defn calculate-potential-abv
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, estimate the ABV.
   The primary fermentation yeast's `attenuation` may also be passed, otherwise 75% is assumed."
  {:added "1.0"}
  ([fermentables batch-size]
   (calculate-potential-abv fermentables batch-size default-attenuation))

  ([fermentables batch-size attenuation]
   (let [gravity (calculate-potential-gravity fermentables batch-size)]
     (-> gravity
         (* 1000)
         (- 1000)
         (* attenuation)
         (* gravity->abv-multiplier)))))


(defn calculate-hop-utilization
  "Calculate the percentage of alpha acid that a hop could release over `boil-duration` in a wort at a specific `gravity`.
   Based on: http://howtobrew.com/book/section-1/hops/hop-bittering-calculations"
  [gravity boil-duration]
  (let [gravity-factor (* 1.65 (Math/pow 0.000125 (- gravity 1)))
        time-factor    (/ (- 1 (Math/pow Math/E (* -0.04 boil-duration))) 4.15)]
    (* gravity-factor time-factor)))


(defn calculate-alpha-acid-units
  "Calculate the maximum amount of alpha acid released by `weight` ounce of a hop at `percent` alpha acid."
  {:added "1.0"}
  [weight alpha]
  (let [weight-in-ounces (units/convert-weight weight :kilogram :ounce)
        aau-normalization-factor 100]
    (* aau-normalization-factor weight-in-ounces alpha)))


(defn calculate-ibu-per-hop
  "Given a `common-beer-format` conforming `hop`, `batch-size`, and `potential-gravity`, calculate the amount of IBUs generated."
  {:added "1.0"}
  [hop batch-size potential-gravity]
  (let [utilization       (calculate-hop-utilization potential-gravity (:time hop))
        alpha-acid-units  (calculate-alpha-acid-units (:amount hop) (:alpha hop))
        imperial-volume   (units/convert-volume batch-size :litre :american-gallon)
        conversion-factor 74.89]
    (/ (* alpha-acid-units utilization conversion-factor) imperial-volume)))


(defn calculate-recipe-ibus
  "Given a collection of `common-beer-format` conforming `hops`, `batch-size`, and `potential-gravity` calculate the amount of IBUs generated."
  {:added "1.0"}
  [hops batch-size potential-gravity]
  (let [reducing-fn (fn [acc h] (+ acc (calculate-ibu-per-hop h batch-size potential-gravity)))]
    (reduce reducing-fn 0 hops)))


(defn calculate-equipment-boil-volume
  "Given a `common-beer-format` conforming `equipment`, calculate the volume of the wort at the start of the boil.
   If insufficient data is provided, this function will throw an exception."
  {:added    "1.5"}
  [{:keys [batch-size top-up-water trub-chiller-loss boil-time evap-rate]}]
  (if (every? number? [batch-size top-up-water trub-chiller-loss boil-time evap-rate])
    (let [starting-water      (- batch-size top-up-water trub-chiller-loss)
          boil-time-in-hours  (/ boil-time 60.0)
          evaporation-decimal (/ evap-rate 100.0)
          evaporated-water    (+ 1 (* boil-time-in-hours evaporation-decimal))]
      (* starting-water evaporated-water))
    (throw (ex-info "Cannot calculate boil volume with non-numeric values" {:batch-size        batch-size
                                                                            :top-up-water      top-up-water
                                                                            :trub-chiller-loss trub-chiller-loss
                                                                            :boil-time         boil-time
                                                                            :evap-rate         evap-rate}))))
