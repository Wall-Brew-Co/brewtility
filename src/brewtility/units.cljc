(ns brewtility.units
  "Namespace for converting between different units of measure")


(def ^:const volume-measurement->litre
  {:teaspoon             0.00492892
   :tablespoon           0.0147868
   :imperial-fluid-ounce 0.0284131
   :american-fluid-ounce 0.0295735
   :cup                  0.236588
   :imperial-pint        0.568261
   :american-pint        0.473176
   :imperial-quart       1.13652
   :american-quart       0.946353
   :imperial-gallon      4.54609
   :american-gallon      3.78541
   :litre                1.0
   :liter                1.0   ;; To support regional spelling
   :millilitre           0.001
   :milliliter           0.001}) ;; To support regional spelling

(defn liter->volume-measurement
  [measurement-name]
  (/ 1.0 (get volume-measurement->litre measurement-name)))


(defn convert-volume
  "Given a `volume` in `source-measurement`, convert it to the `target-measurement`."
  [volume source-measurement target-measurement]
  (if (= source-measurement target-measurement)
    volume
    (let [source->litre-multiplier (volume-measurement->litre source-measurement)
          litre->target-multiplier (liter->volume-measurement target-measurement)]
      (* volume source->litre-multiplier litre->target-multiplier))))


(def ^:const weight-measurement->kilogram
  {:ounce     0.02834952
   :pound     0.45359237
   :milligram 0.000001
   :gram      0.001
   :kilogram  1.0})


(defn kilogram->weight-measurement
  [measurement-name]
  (/ 1.0 (get weight-measurement->kilogram measurement-name)))


(defn convert-weight
  "Given a `weight` in `source-measurement`, convert it to the `target-measurement`."
  [weight source-measurement target-measurement]
  (if (= source-measurement target-measurement)
    weight
    (let [source->kilogram-multiplier (weight-measurement->kilogram source-measurement)
          kilogram->target-multiplier (kilogram->weight-measurement target-measurement)]
      (* weight source->kilogram-multiplier kilogram->target-multiplier))))


(defn celsius->fahrenheit
  [temp]
  (+ 32 (* 1.8 temp)))


(defn celsius->kelvin
  [temp]
  (+ temp 273.15))


(defn fahrenheit->celsius
  [temp]
  (/ (* (- temp 32) 5) 9.0))


(defn kelvin->celsius
  [temp]
  (- temp 273.15))


(def ^:const temperature-measurement->celsius
  {:celsius    identity
   :c          identity
   :centigrade identity
   :fahrenheit fahrenheit->celsius
   :f          fahrenheit->celsius
   :kelvin     kelvin->celsius
   :k          kelvin->celsius})


(def ^:const celsius->temperature-measurement
  {:celsius    identity
   :c          identity
   :centigrade identity
   :fahrenheit celsius->fahrenheit
   :f          celsius->fahrenheit
   :kelvin     celsius->kelvin
   :k          celsius->kelvin})


(defn convert-temperature
  "Given a `temp` in `source-measurement`, convert it to the `target-measurement`."
  [temp source-measurement target-measurement]
  (if (= source-measurement target-measurement)
    temp
    (let [source->celsius-fn (temperature-measurement->celsius source-measurement)
          celsius->target-fn (celsius->temperature-measurement target-measurement)]
      (-> temp source->celsius-fn celsius->target-fn))))
