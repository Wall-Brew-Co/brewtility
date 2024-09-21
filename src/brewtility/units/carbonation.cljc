(ns brewtility.units.carbonation
  "A namespace for converting between different units of carbonation.
   In the BeerXML spec, carbonation is a measure of the amount of carbon dioxide dissolved in beer.
   This namespace converts between that measure and other units.
   Currently, brewtility supports the following types of carbonation:
     - [volumes-of-co2](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages)
     - [grams-per-liter](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages)"
  {:added "2.2"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def measurements
  "The carbonation systems available across brewtility."
  #{options/volumes-of-co2 options/grams-per-liter})


(def measurements->display-name
  "A map from carbonation system names to their full and short unit names."
  {options/volumes-of-co2 {options/full  "volumes of CO2"
                           options/short "vols"}
   options/grams-per-liter {options/full  "grams per liter"
                            options/short "g/L"}})


(defn- volumes-of-co2->grams-per-liter
  "An implementation function to convert `volumes-of-co2` to grams per liter"
  {:no-doc true
   :added  "2.2"}
  [volumes-of-co2]
  (* 1.96 volumes-of-co2))


(defn- grams-per-liter->volumes-of-co2
  "An implementation function to convert `grams-per-liter` to volumes of CO2"
  {:no-doc true
   :added  "2.2"}
  [grams-per-liter]
  (/ grams-per-liter 1.96))


(def measurement->volumes-of-co2
  "A map from carbonation system names to the conversion function to volumes of CO2."
  {options/volumes-of-co2  identity
   options/grams-per-liter volumes-of-co2->grams-per-liter})


(def volumes-of-co2->measurement
  "A map from carbonation system names to the conversion function from volumes of CO2."
  {options/volumes-of-co2  identity
   options/grams-per-liter grams-per-liter->volumes-of-co2})


(defn convert
  "Given a `carbonation` in `source-measurement`, convert it to the `target-measurement`.
         Supported values for `source-measurement` and `target-measurement` are enumerated in [[carbonation-measurements]].
         This function will throw an exception if unsupported measurement values are passed."
  {:added "2.2"}
  [carbonation source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? carbonation))
    (if (= source-measurement target-measurement)
      carbonation
      (let [source->volumes-of-co2-fn (measurement->volumes-of-co2 source-measurement)
            volumes-of-co2->target-fn (volumes-of-co2->measurement target-measurement)]
        (-> carbonation source->volumes-of-co2-fn volumes-of-co2->target-fn)))
    (throw (ex-info "Unsupported carbonation conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :carbonation        carbonation}))))


(defn display
  "A function to render a human-readable `carbonation` in `source-units`.
     For example,
     ```clj
     (display 10 options/volumes-of-co2)
     ;; => \"10 vols\"
     ```"
  {:added "2.2"}
  ([carbonation source-units]
   (display carbonation source-units {}))
  ([carbonation source-units {:keys [precision suffix]
                              :or   {precision options/default-precision
                                     suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? carbonation)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> carbonation
           (convert source-units options/volumes-of-co2)
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported carbonation display units"
                     {:source-units       source-units
                      :allowed-values     measurements
                      :carbonation        carbonation
                      :precision          precision
                      :suffix             suffix
                      :supported-suffixes options/supported-suffixes})))))
