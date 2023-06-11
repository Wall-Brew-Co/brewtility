(ns brewtility.units.specific-gravity
  "A namespace for converting between different units of specific gravity.
   
   In the BeerXML spec, specific gravity is measured in relative to the weight of the same size sample of water.
   This namespace converts between that ratio and other units.
   
   Currently, brewtility supports the following types of specific gravity:
     - [specific-gravity](https://en.wikipedia.org/wiki/Specific_gravity)"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def ^:const measurements
  "The specific gravity systems available across brewtility."
  #{options/specific-gravity})


(def ^:const measurements->display-name
  "A map from specific gravity system names to their full and short unit names."
  {options/specific-gravity {options/full  "specific gravity"
                             options/short "sg"}})


(def ^:const measurement->specific-gravity
  "A map from specific gravity system names to the conversion function to specific gravity."
  {options/specific-gravity identity})


(def ^:const specific-gravity->measurement
  "A map from specific gravity system names to the conversion function from specific gravity."
  {options/specific-gravity identity})


(defn convert
  "Given a `specific-gravity` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[specific-gravity-measurements]].
   This function will throw an exception if unsupported measurement values are passed."
  {:changed "2.0"}
  [gravity source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? gravity))
    (if (= source-measurement target-measurement)
      gravity
      (let [source->specific-gravity-fn (measurement->specific-gravity source-measurement)
            specific-gravity->target-fn (specific-gravity->measurement target-measurement)]
        (-> gravity source->specific-gravity-fn specific-gravity->target-fn)))
    (throw (ex-info "Unsupported specific-gravity conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :specific-gravity   gravity}))))


(defn display
  "A function to render a human-readable `specific-gravity` in `source-units`.

   For example,
   ```clj
    (display 1.5 :specific-gravity) ;; => \"1.5 sg\"
   ```

   This function accepts an option map as an optional third argument. The following options are available:
     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added "2.0"}
  ([gravity source-units]
   (display gravity source-units {}))
  ([gravity source-units {:keys [precision suffix]
                          :or   {precision options/default-precision
                                 suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? gravity)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> gravity
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported specific-gravity display options"
                     {:source-units     source-units
                      :allowed-values   measurements
                      :specific-gravity gravity
                      :precision        precision
                      :suffix           suffix
                      :allowed-suffixes options/supported-suffixes})))))
