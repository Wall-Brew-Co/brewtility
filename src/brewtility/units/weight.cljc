(ns brewtility.units.weight
  "A namespace for converting between different units of weight.
    
   By the BeerXML spec, weight is measured in kilograms.
   This namespace provides a way to convert between kilograms and other units of weight.
    
    Currently, brewtility supports the following types of weight:
      - [gram](https://en.wikipedia.org/wiki/Gram)
      - [milligram](https://en.wikipedia.org/wiki/Milligram)
      - [kilogram](https://en.wikipedia.org/wiki/Kilogram)
      - [ounce](https://en.wikipedia.org/wiki/Ounce)
      - [pound](https://en.wikipedia.org/wiki/Pound_(mass))"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def ^:const measurements
  "The weight measurements available across brewtility"
  #{options/gram
    options/kilogram
    options/milligram
    options/ounce
    options/pound})


(def ^:const measurement->kilogram
  "A map from measurement names to doubles representing their fractional value to one kilogram"
  {options/gram      0.001
   options/kilogram  1.0
   options/milligram 0.000001
   options/ounce     0.02834952
   options/pound     0.45359237})


(def ^:const measurements->display-name
  "A map from measurement names to their full name and abbreviation"
  {options/gram      {options/full  "gram"
                      options/short "g"}
   options/kilogram  {options/full  "kilogram"
                      options/short "kg"}
   options/milligram {options/full  "milligram"
                      options/short "mg"}
   options/ounce     {options/full  "ounce"
                      options/short "oz"}
   options/pound     {options/full  "pound"
                      options/short "lb"}})


(defn- kilogram->measurement
  "An implementation function to convert kilograms to another unit of weight."
  {:no-doc true
   :added  "2.0"}
  [measurement-name]
  (/ 1.0 (get measurement->kilogram measurement-name)))


(defn convert
  "Given a `weight` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[weight-measurements]].

   This function will throw an exception if unsupported measurement values are passed."
  {:added   "2.0"}
  [weight source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? weight))
    (if (= source-measurement target-measurement)
      weight
      (let [source->kilogram-multiplier (measurement->kilogram source-measurement)
            kilogram->target-multiplier (kilogram->measurement target-measurement)]
        (* weight source->kilogram-multiplier kilogram->target-multiplier)))
    (throw (ex-info "Unsupported weight conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :weight             weight}))))


(defn display
  "A function to render a human-readable `weight` in `source-units`.

   For example,

   ```clj
    (display 1.5 :pound) ;; => \"1.5 lb\"
   ```

   This function accepts an option map as an optional third argument. The following options are available:

     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added "2.0"}
  ([weight source-units]
   (display weight source-units {}))
  ([weight source-units {:keys [precision suffix]
                         :or   {precision options/default-precision
                                suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? weight)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> weight
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported weight display options"
                     {:source-units     source-units
                      :allowed-values   measurements
                      :weight           weight
                      :precision        precision
                      :suffix           suffix
                      :allowed-suffixes options/supported-suffixes})))))
