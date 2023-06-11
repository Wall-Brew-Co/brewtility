(ns brewtility.units.volume
  "A namespace for converting between different units of volume.
    
   In the BeerXML spec, volume is measured in liters.
   This namespace provides a way to convert between liters and other units.
    
    Currently, brewtility supports the following types of volume:
      - [american-fluid-ounce](https://en.wikipedia.org/wiki/Fluid_ounce)
      - [american-gallon](https://en.wikipedia.org/wiki/Gallon)
      - [american-pint](https://en.wikipedia.org/wiki/Pint)
      - [american-quart](https://en.wikipedia.org/wiki/Quart)
      - [cup](https://en.wikipedia.org/wiki/Cup_(unit))
      - [imperial-fluid-ounce](https://en.wikipedia.org/wiki/Fluid_ounce)
      - [imperial-gallon](https://en.wikipedia.org/wiki/Gallon)
      - [imperial-pint](https://en.wikipedia.org/wiki/Pint)
      - [imperial-quart](https://en.wikipedia.org/wiki/Quart)
      - [liter](https://en.wikipedia.org/wiki/Litre)
      - [litre](https://en.wikipedia.org/wiki/Litre)
      - [milliliter](https://en.wikipedia.org/wiki/Millilitre)
      - [millilitre](https://en.wikipedia.org/wiki/Millilitre)
      - [tablespoon](https://en.wikipedia.org/wiki/Tablespoon)
      - [teaspoon](https://en.wikipedia.org/wiki/Teaspoon))"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def ^:const measurements
  "The volume measurements available across brewtility"
  #{options/american-gallon
    options/american-pint
    options/american-quart
    options/cup
    options/imperial-gallon
    options/imperial-pint
    options/imperial-quart
    options/liter
    options/litre
    options/milliliter
    options/millilitre
    options/tablespoon
    options/teaspoon
    options/american-fluid-ounce
    options/imperial-fluid-ounce})


(def ^:const measurement->litre
  "A map from measurement names to doubles representing their fractional value to one liter"
  {options/american-fluid-ounce 0.0295735
   options/american-gallon      3.78541
   options/american-pint        0.473176
   options/american-quart       0.946353
   options/cup                  0.236588
   options/imperial-fluid-ounce 0.0284131
   options/imperial-gallon      4.54609
   options/imperial-pint        0.568261
   options/imperial-quart       1.13652
   options/liter                1.0
   options/litre                1.0
   options/milliliter           0.001
   options/millilitre           0.001
   options/tablespoon           0.0147868
   options/teaspoon             0.00492892})


(def ^:const measurements->display-name
  "A map from measurement names to their full name and abbreviation"
  {options/american-fluid-ounce {options/full  "fluid ounce"
                                 options/short "fl oz"}
   options/american-gallon      {options/full  "US gallon"
                                 options/short "gal"}
   options/american-pint        {options/full  "american pint"
                                 options/short "pt"}
   options/american-quart       {options/full  "american quart"
                                 options/short "qt"}
   options/cup                  {options/full  "cup"
                                 options/short "c"}
   options/imperial-fluid-ounce {options/full  "imperial fluid ounce"
                                 options/short "imp fl oz"}
   options/imperial-gallon      {options/full  "imperial gallon"
                                 options/short "gal"}
   options/imperial-pint        {options/full  "imperial pint"
                                 options/short "pt"}
   options/imperial-quart       {options/full  "imperial quart"
                                 options/short "qt"}
   options/liter                {options/full  "liter"
                                 options/short "l"}
   options/litre                {options/full  "litre"
                                 options/short "l"}
   options/milliliter           {options/full  "milliliter"
                                 options/short "ml"}
   options/millilitre           {options/full  "millilitre"
                                 options/short "ml"}
   options/tablespoon           {options/full  "tablespoon"
                                 options/short "tbsp"}
   options/teaspoon             {options/full  "teaspoon"
                                 options/short "tsp"}})


(defn- liter->measurement
  "An implementation function to convert liters to another unit of volume"
  {:no-doc true
   :added  "2.0"}
  [measurement-name]
  (/ 1.0 (get measurement->litre measurement-name)))


(defn convert
  "Given a `volume` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[measurements]].

   This function will throw an exception if unsupported measurement values are passed."
  {:changed "2.0"}
  [volume source-measurement target-measurement]
  (if (and (contains? measurements source-measurement)
           (contains? measurements target-measurement)
           (number? volume))
    (if (= source-measurement target-measurement)
      volume
      (let [source->litre-multiplier (measurement->litre source-measurement)
            litre->target-multiplier (liter->measurement target-measurement)]
        (* volume source->litre-multiplier litre->target-multiplier)))
    (throw (ex-info "Unsupported volume conversion units"
                    {:source-measurement source-measurement
                     :target-measurement target-measurement
                     :allowed-values     measurements
                     :volume             volume}))))


(defn display
  "A function to render a human-readable `volume` in `source-units`.

   For example,

   ```clj
    (display 1.5 :litre) ;; => \"1.5 l\"
   ```

   This function accepts an option map as an optional third argument. The following options are available:

     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added "2.0"}
  ([volume source-units]
   (display volume source-units {}))
  ([volume source-units {:keys [precision suffix]
                         :or   {precision options/default-precision
                                suffix    options/short}}]
   (if (and (contains? measurements source-units)
            (number? volume)
            (integer? precision)
            (contains? options/supported-suffixes suffix))
     (let [display-name (get-in measurements->display-name [source-units suffix])]
       (-> volume
           (precision/->precision precision)
           (str " " display-name)))
     (throw (ex-info "Unsupported volume display units"
                     {:source-units     source-units
                      :allowed-values   measurements
                      :volume           volume
                      :precision        precision
                      :suffix           suffix
                      :allowed-suffixes options/supported-suffixes})))))
