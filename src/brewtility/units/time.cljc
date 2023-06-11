(ns brewtility.units.time
  "A namespace for converting between different units of time.
   
   In the BeerXML spec, temperature is measured in minutes.
   This namespace converts between that and other units.
   
   Currently, brewtility supports the following types of time measurements:
     - [microsecond](https://en.wikipedia.org/wiki/Microsecond)
     - [nanosecond](https://en.wikipedia.org/wiki/Nanosecond)
     - [millisecond](https://en.wikipedia.org/wiki/Millisecond)
     - [second](https://en.wikipedia.org/wiki/Second)
     - [minute](https://en.wikipedia.org/wiki/Minute)
     - [hour](https://en.wikipedia.org/wiki/Hour)
     - [day](https://en.wikipedia.org/wiki/Day)
     - [week](https://en.wikipedia.org/wiki/Week)"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options])
  (:refer-clojure :exclude [time]))


(def ^:const measurements
  "The time measurements available across brewtility"
  #{options/day
    options/hour
    options/microsecond
    options/millisecond
    options/minute
    options/nanosecond
    options/second
    options/week})


(def ^:const measurement->minute
  "A map from measurement names to doubles representing their fractional value to one minute"
  {options/day         1440.0
   options/hour        60.0
   options/microsecond (/ 1.0 60000000)
   options/millisecond (/ 1.0 60000)
   options/minute      1.0
   options/nanosecond  (/ 1.0 60000000000)
   options/second      (/ 1.0 60)
   options/week        10080.0})


(def ^:const measurements->display-name
  "A map from measurement names to their full name and abbreviation"
  {options/day         {options/full  "days"
                        options/short "d"}
   options/hour        {options/full  "hour"
                        options/short "hr"}
   options/microsecond {options/full  "microsecond"
                        options/short "µs"}
   options/millisecond {options/full  "millisecond"
                        options/short "ms"}
   options/minute      {options/full  "minute"
                        options/short "m"}
   options/nanosecond  {options/full  "nanosecond"
                        options/short "ns"}
   options/second      {options/full  "second"
                        options/short "s"}
   options/week        {options/full  "week"
                        options/short "w"}})


(defn- minute->measurement
  "An implementation function to convert minutes to another unit of time"
  {:no-doc true
   :added  "2.0"}
  [measurement-name]
  (/ 1.0 (get measurement->minute measurement-name)))


(defn convert
  "Given a `time-val` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[time-measurements]].

   This function will throw an exception if unsupported measurement values are passed."
  {:added "2.0"}
  [time-val source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? time-val))
    (if (= source-measurement target-measurement)
      time-val
      (let [source->litre-multiplier (measurement->minute source-measurement)
            litre->target-multiplier (minute->measurement target-measurement)]
        (* time-val source->litre-multiplier litre->target-multiplier)))
    (throw (ex-info "Unsupported time conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :time               time-val}))))


(defn display
  "A function to render a human-readable `time` in `source-units`.

   For example,

   ```clj
    (display 1.5 :second) ;; => \"1.5 s\"
   ```

   This function accepts an option map as an optional third argument. The following options are available:

     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added "2.0"}
  ([time source-units]
   (display time source-units {}))
  ([time source-units {:keys [precision suffix]
                       :or   {precision options/default-precision
                              suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? time)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> time
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported time display units"
                     {:source-units     source-units
                      :allowed-values   measurements
                      :time             time
                      :precision        precision
                      :suffix           suffix
                      :allowed-suffixes options/supported-suffixes})))))
