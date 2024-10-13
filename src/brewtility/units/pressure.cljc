(ns brewtility.units.pressure
  "A namespace for converting between different units of pressure.

   In the BeerXML spec, pressure is measured in kilopascals (kPa).
   This namespace provides a way to convert between kilopascals and other units.

    Currently, brewtility supports the following types of pressure:
      - [pascal](https://en.wikipedia.org/wiki/Pascal_(unit)#Multiples_and_submultiples)
      - [kilopascal](https://en.wikipedia.org/wiki/Pascal_(unit)#Multiples_and_submultiples)
      - [bar](https://en.wikipedia.org/wiki/Bar_(unit))
      - [atmosphere](https://en.wikipedia.org/wiki/Atmosphere_(unit))
      - [torr](https://en.wikipedia.org/wiki/Torr)
      - [psi](https://en.wikipedia.org/wiki/Pound-force_per_square_inch)"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def measurements
  "The pressure measurements supported by brewtility."
  #{options/pascal
    options/kilopascal
    options/bar
    options/atmosphere
    options/torr
    options/psi})


(def measurements->display-name
  "A map of pressure measurements to their display names."
  {options/pascal     {options/full  "pascal"
                       options/short "pa"}
   options/kilopascal {options/full  "kilopascals"
                       options/short "kpa"}
   options/bar        {options/full  "bar"
                       options/short "bar"}
   options/atmosphere {options/full  "atmosphere"
                       options/short "atm"}
   options/torr       {options/full  "torr"
                       options/short "torr"}
   options/psi        {options/full  "pounds per square inch"
                       options/short "psi"}})


(defn- pascal->kilopascal
  "An implementation to convert `pressure` from pascal to kilopascal."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (/ pressure 1000.0))


(defn- bar->kilopascal
  "An implementation to convert `pressure` from bar to kilopascal."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (* pressure 100.0))


(defn- atmosphere->kilopascal
  "An implementation to convert `pressure` from atmosphere to kilopascal."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (* pressure 101.325))


(defn- torr->kilopascal
  "An implementation to convert `pressure` from torr to kilopascal."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (* pressure 0.133322))


(defn- psi->kilopascal
  "An implementation to convert `pressure` from psi to kilopascal."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (* pressure 6.894757))


(defn- kilopascal->pascal
  "An implementation to convert `pressure` from kilopascal to pascal."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (* pressure 1000.0))


(defn- kilopascal->bar
  "An implementation to convert `pressure` from kilopascal to bar."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (/ pressure 100.0))


(defn- kilopascal->atmosphere
  "An implementation to convert `pressure` from kilopascal to atmosphere."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (/ pressure 101.325))


(defn- kilopascal->torr
  "An implementation to convert `pressure` from kilopascal to torr."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (/ pressure 0.133322))


(defn- kilopascal->psi
  "An implementation to convert `pressure` from kilopascal to psi."
  {:no-doc true
   :added  "2.0"}
  [pressure]
  (/ pressure 6.894757))


(def measurement->kilopascal
  "A map of pressure measurements to functions that convert to kilopascals."
  {options/pascal     pascal->kilopascal
   options/kilopascal identity
   options/bar        bar->kilopascal
   options/atmosphere atmosphere->kilopascal
   options/torr       torr->kilopascal
   options/psi        psi->kilopascal})


(def kilopascal->measurement
  "A map of pressure measurements to functions that convert from kilopascals."
  {options/pascal     kilopascal->pascal
   options/kilopascal identity
   options/bar        kilopascal->bar
   options/atmosphere kilopascal->atmosphere
   options/torr       kilopascal->torr
   options/psi        kilopascal->psi})


(defn convert
  "Given a `pressure` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[pressure-measurements]].
   This function will throw an exception if unsupported measurement values are passed."
  {:changed "2.0"}
  [pressure source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? pressure))
    (if (= source-measurement target-measurement)
      pressure
      (let [source->kilopascal-fn (measurement->kilopascal source-measurement)
            kilopascal->target-fn (kilopascal->measurement target-measurement)]
        (-> pressure source->kilopascal-fn kilopascal->target-fn)))
    (throw (ex-info "Unsupported pressure conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :pressure           pressure}))))


(defn display
  "A function to render a human-readable `pressure` in `source-units`.
   For example,
   ```clj
    (display 1.5 :pascal) ;; => \"1.5 pa\"
   ```
   This function accepts an option map as an optional third argument. The following options are available:
     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added "2.0"}
  ([pressure source-units]
   (display pressure source-units {}))
  ([pressure source-units {:keys [precision suffix]
                           :or   {precision options/default-precision
                                  suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? pressure)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> pressure
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported pressure display options"
                     {:source-units     source-units
                      :allowed-values   measurements
                      :pressure         pressure
                      :precision        precision
                      :suffix           suffix
                      :allowed-suffixes options/supported-suffixes})))))
