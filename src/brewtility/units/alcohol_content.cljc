(ns brewtility.units.alcohol-content
  "A namespace for converting between different units for measuring alcohol content.
     In the BeerXML spec, the default unit to measure the alcohol content is ABV (Alcohol by Volume).
     This namespace converts between that measure and other units.
     Currently, brewtility supports the following types of IBU:
       - [abv](https://en.wikipedia.org/wiki/Alcohol_by_volume)"
  {:added "2.2"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def measurements
  "The alcohol content measurement systems available across brewtility."
  #{options/abv})


(def measurements->display-name
  "A map from alcohol content system names to their full and short unit names."
  {options/abv {options/full  "% alcohol by volume"
                options/short "% abv"}})


(def measurement->abv
  "A map from alcohol content system names to the conversion function to ABV"
  {options/abv identity})


(def abv->measurement
  "A map from alcohol content system names to the conversion function from ABV."
  {options/abv identity})


(defn convert
  "Given an `alcohol-content` in `source-measurement`, convert it to the `target-measurement`.
     Supported values for `source-measurement` and `target-measurement` are enumerated in [[alcohol-content-measurements]].
     This function will throw an exception if unsupported measurement values are passed."
  {:added "2.2"}
  [alcohol-content source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? alcohol-content))
    (if (= source-measurement target-measurement)
      alcohol-content
      (let [source->ibu-fn (measurement->abv source-measurement)
            ibu->target-fn (abv->measurement target-measurement)]
        (-> alcohol-content source->ibu-fn ibu->target-fn)))
    (throw (ex-info "Unsupported alcohol content conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :alcohol-content    alcohol-content}))))


(defn display
  "A function to render a human-readable `alcohol-content` in `source-units`.
   For example,
   ```clj
   (display .1 options/abv)
   ;; => \"10% abv\"
   ```"
  {:added "2.2"}
  ([alcohol-content source-units]
   (display alcohol-content source-units {}))
  ([alcohol-content source-units {:keys [precision suffix]
                                  :or   {precision options/default-precision
                                         suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? alcohol-content)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> alcohol-content
           (* 100.0) ; ABV is a percentage, so multiply by 100
           (precision/->precision precision)
           (str display-name)))
     (throw (ex-info "Unsupported alcohol content display units"
                     {:source-units       source-units
                      :allowed-values     measurements
                      :alcohol-content    alcohol-content
                      :precision          precision
                      :suffix             suffix
                      :supported-suffixes options/supported-suffixes})))))
