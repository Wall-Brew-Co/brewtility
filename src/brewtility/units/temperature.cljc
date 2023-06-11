(ns brewtility.units.temperature
  "A namespace for converting between different units of temperature
   
   In the BeerXML spec, temperature is measured in degrees Celsius.
   This namespace converts between that and other units.
   
   Currently, brewtility supports the following types of temperature measurements:
     - [clesius](https://en.wikipedia.org/wiki/Celsius)
     - [fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit)
     - [kelvin](https://en.wikipedia.org/wiki/Kelvin_(unit))"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def ^:const measurements
  "The temperature measurements supported by brewtility."
  #{options/c
    options/celsius
    options/centigrade
    options/f
    options/fahrenheit
    options/k
    options/kelvin})


(def ^:const measurements->display-name
  "The temperature measurements supported by brewtility."
  {options/c          {options/full  "celsius"
                       options/short "c"}
   options/celsius    {options/full  "celsius"
                       options/short "c"}
   options/centigrade {options/full  "centigrade"
                       options/short "mg"}
   options/f          {options/full  "fahrenheit"
                       options/short "f"}
   options/fahrenheit {options/full  "fahrenheit"
                       options/short "f"}
   options/k          {options/full  "kelvin"
                       options/short "k"}
   options/kelvin     {options/full  "kelvin"
                       options/short "k"}})


(defn- celsius->fahrenheit
  "An implementation function to convert `temp` from celsius to fahrenheit"
  {:no-doc true
   :added  "2.0"}
  [temp]
  (+ 32 (* 1.8 temp)))


(defn- celsius->kelvin
  "An implementation function to convert `temp` from celsius to kelvin"
  {:no-doc true
   :added  "2.0"}
  [temp]
  (+ temp 273.15))


(defn- fahrenheit->celsius
  "An implementation function to convert `temp` from fahrenheit to celsius"
  {:no-doc true
   :added  "2.0"}
  [temp]
  (/ (* (- temp 32) 5) 9.0))


(defn- kelvin->celsius
  "An implementation function to convert `temp` from kelvin to celsius"
  {:no-doc true
   :added  "2.0"}
  [temp]
  (- temp 273.15))


(def ^:const measurement->celsius
  "A map from measurement names to the implementation function which converts them to degrees celsius"
  {options/c          identity
   options/celsius    identity
   options/centigrade identity
   options/f          fahrenheit->celsius
   options/fahrenheit fahrenheit->celsius
   options/k          kelvin->celsius
   options/kelvin     kelvin->celsius})


(def ^:const celsius->measurement
  "A map from measurement names to the implementation function which converts them from degrees celsius"
  {options/celsius    identity
   options/c          identity
   options/centigrade identity
   options/fahrenheit celsius->fahrenheit
   options/f          celsius->fahrenheit
   options/kelvin     celsius->kelvin
   options/k          celsius->kelvin})


(defn convert
  "Given a `temperature` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[temperature-measurements]].

   This function will throw an exception if unsupported measurement values are passed."
  {:changed "2.0"}
  [temperature source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? temperature))
    (if (= source-measurement target-measurement)
      temperature
      (let [source->celsius-fn (measurement->celsius source-measurement)
            celsius->target-fn (celsius->measurement target-measurement)]
        (-> temperature source->celsius-fn celsius->target-fn)))
    (throw (ex-info "Unsupported temperature conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :temperature        temperature}))))


(defn display
  "A function to render a human-readable `temperature` in `source-units`.

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
  ([temperature source-units]
   (display temperature source-units {}))
  ([temperature source-units {:keys [precision suffix]
                              :or   {precision options/default-precision
                                     suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? temperature)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> temperature
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported temperature display options"
                     {:source-units     source-units
                      :allowed-values   measurements
                      :temperature      temperature
                      :precision        precision
                      :suffix           suffix
                      :allowed-suffixes options/supported-suffixes})))))
