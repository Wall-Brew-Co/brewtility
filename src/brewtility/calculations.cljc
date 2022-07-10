(ns brewtility.calculations
  "Namespace for handling recipe calculations.
   This namespace assumes ingredients that conform to the common-beer-format."
  (:require [brewtility.color :as color]
            [brewtility.units :as units]
            [clojure.string :as str]))


(defn normalize-fermentable
  "Given a `common-beer-format` conforming `fermentable`, normalize it for color computation"
  [fermentable]
  (let [is-not-grain? (not= "grain" (str/lower-case (:type fermentable)))
        kg->lbs       (fn [w] (units/convert-weight w :kilogram :pound))] ;; MCU is calculated against pounds
    (cond-> fermentable
      true          (update :amount kg->lbs)
      is-not-grain? (update :color color/srm->lovibond)))) ;; Grain color is in Lovibond, all other fermentables use SRM

(defn calculate-malt-color-units
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the overall Malt Color Units for a recipe"
  [fermentables batch-size]
  (let [normalized-fermentables (map normalize-fermentable fermentables)
        reducing-fn             (fn [acc v] (+ acc (* (:amount v) (:color v))))
        color                   (reduce reducing-fn 0 normalized-fermentables)
        imperial-volume         (units/convert-volume batch-size :litre :american-gallon)]
    (/ color imperial-volume)))


(defn calculate-srm-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the SRM color for a recipe"
  [fermentables batch-size]
  (let [mcu-color (calculate-malt-color-units fermentables batch-size)]
    (* mcu-color 1.02349998)))


(def calculate-ebc-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the EBC color for a recipe"
  (comp color/srm->ebc calculate-srm-color))


(def calculate-lovibond-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the color in degrees Lovibond for a recipe"
  (comp color/srm->ebc calculate-srm-color))


(def calculate-rgba-color
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the RGBA color for a recipe"
  (comp color/srm->rgba calculate-srm-color))


(defn potential-gravity->gravity-points
  "Given the `potential-gravity` of a fermentable, and the `weight` of the fermentable, calculate gravity points"
  [potential-gravity weight]
  (let [weight-in-pounds (units/convert-weight weight :kilogram :pound)]
    (-> potential-gravity
        (* 1000)
        (- 1000)
        (* weight-in-pounds))))


(defn gravity-points->potential-gravity
  "Given the `gravity-points` of a recipe, and the `volume` of the batch, calculate the potential gravity"
  [gravity-points volume]
  (let [volume-in-gallons (units/convert-volume volume :litre :american-gallon)]
    (-> gravity-points
        (/ volume-in-gallons)
        (+ 1000)
        (/ 1000.0))))


(defn calculate-potential-gravity
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, calculate the potential original gravity."
  [fermentables batch-size]
  (let [reducing-fn (fn [acc ferm]
                      (let [maximum-gravity (potential-gravity->gravity-points (:potential ferm) (:amount ferm))]
                        (+ acc (* (:yield ferm) maximum-gravity))))
        total-gravity-points (reduce reducing-fn 0 fermentables)]
    (gravity-points->potential-gravity total-gravity-points batch-size)))


(def ^:const gravity->abv-multiplier 0.00135)
(def ^:const default-attenuation 0.75)


(defn calculate-potential-final-gravity
  "Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, estimate the Final Gravity.
   The primary fermentation yeast's `attenuation` may also be passed, otherwise 75% is assumed."
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
  "Calculate the percentage of alpha acid that a hop could release over `boil-duration` in a wort at a specific `gravity`
   Based on: http://howtobrew.com/book/section-1/hops/hop-bittering-calculations"
  [gravity boil-duration]
  (let [gravity-factor (* 1.65 (Math/pow 0.000125 (- gravity 1)))
        time-factor    (/ (- 1 (Math/pow Math/E (* -0.04 boil-duration))) 4.15)]
    (* gravity-factor time-factor)))


(defn calculate-alpha-acid-units
  "Calculate the maximum amount of alpha acid released by `weight` ounce of a hop at `percent` alpha acid"
  [weight alpha]
  (let [weight-in-ounces (units/convert-weight weight :kilogram :ounce)
        aau-normalization-factor 100]
    (* aau-normalization-factor weight-in-ounces alpha)))


(defn calculate-ibu-per-hop
  "Given a `common-beer-format` conforming `hop`, `batch-size`, and `potential-gravity`, calculate the amount of IBUs generated"
  [hop batch-size potential-gravity]
  (let [utilization       (calculate-hop-utilization potential-gravity (:time hop))
        alpha-acid-units  (calculate-alpha-acid-units (:amount hop) (:alpha hop))
        imperial-volume   (units/convert-volume batch-size :litre :american-gallon)
        conversion-factor 74.89]
    (/ (* alpha-acid-units utilization conversion-factor) imperial-volume)))


(defn calculate-recipe-ibus
  "Given a collection of `common-beer-format` conforming `hops`, `batch-size`, and `potential-gravity` calculate the amount of IBUs generated"
  [hops batch-size potential-gravity]
  (let [reducing-fn (fn [acc h] (+ acc (calculate-ibu-per-hop h batch-size potential-gravity)))]
    (reduce reducing-fn 0 hops)))
