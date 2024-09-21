(ns brewtility.units.bitterness
  "A namespace for converting between different units of bitterness.
   In the BeerXML spec, IBU is a measure of the bitterness of beer, which is determined by the quantity, type, and timing of hops used in brewing.
   This namespace converts between that measure and other units.
   Currently, brewtility supports the following types of IBU:
     - [international-bitterness-units](https://en.wikipedia.org/wiki/International_bitterness_units)"
  {:added "2.2"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def measurements
  "The bitterness measurement systems available across brewtility."
  #{options/ibu})


(def measurements->display-name
  "A map from IBU system names to their full and short unit names."
  {options/ibu {options/full  "international bitterness units"
                options/short "ibu"}})


(def measurement->ibu
  "A map from IBU system names to the conversion function to IBU."
  {options/ibu identity})


(def ibu->measurement
  "A map from IBU system names to the conversion function from IBU."
  {options/ibu identity})


(defn convert
  "Given a `bitterness` in `source-measurement`, convert it to the `target-measurement`.
     Supported values for `source-measurement` and `target-measurement` are enumerated in [[bitterness-measurements]].
     This function will throw an exception if unsupported measurement values are passed."
  {:added "2.2"}
  [bitterness source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? bitterness))
    (if (= source-measurement target-measurement)
      bitterness
      (let [source->ibu-fn (measurement->ibu source-measurement)
            ibu->target-fn (ibu->measurement target-measurement)]
        (-> bitterness source->ibu-fn ibu->target-fn)))
    (throw (ex-info "Unsupported bitterness conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :bitterness         bitterness}))))


(defn display
  "A function to render a human-readable `bitterness` in `source-units`.
   For example,
   ```clj
   (display 10 options/ibu)
   ;; => \"10 ibu\"
   ```"
  {:added "2.2"}
  ([bitterness source-units]
   (display bitterness source-units {}))
  ([bitterness source-units {:keys [precision suffix]
                             :or   {precision options/default-precision
                                    suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? bitterness)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> bitterness
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported bitterness display units"
                     {:source-units       source-units
                      :allowed-values     measurements
                      :bitterness         bitterness
                      :precision          precision
                      :suffix             suffix
                      :supported-suffixes options/supported-suffixes})))))
