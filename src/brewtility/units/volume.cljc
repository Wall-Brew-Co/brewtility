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
            [brewtility.units.options :as opts]))


(def ^:const measurements
  "The volume measurements available across brewtility"
  #{opts/american-gallon
    opts/american-pint
    opts/american-quart
    opts/cup
    opts/imperial-gallon
    opts/imperial-pint
    opts/imperial-quart
    opts/liter
    opts/litre
    opts/milliliter
    opts/millilitre
    opts/tablespoon
    opts/teaspoon
    opts/american-fluid-ounce
    opts/imperial-fluid-ounce})


(def ^:const measurement->litre
  "A map from measurement names to doubles representing their fractional value to one liter"
  {opts/american-fluid-ounce 0.0295735
   opts/american-gallon      3.78541
   opts/american-pint        0.473176
   opts/american-quart       0.946353
   opts/cup                  0.236588
   opts/imperial-fluid-ounce 0.0284131
   opts/imperial-gallon      4.54609
   opts/imperial-pint        0.568261
   opts/imperial-quart       1.13652
   opts/liter                1.0
   opts/litre                1.0
   opts/milliliter           0.001
   opts/millilitre           0.001
   opts/tablespoon           0.0147868
   opts/teaspoon             0.00492892})


(def ^:const measurements->display-name
  "A map from measurement names to their full name and abbreviation"
  {opts/american-fluid-ounce {opts/full  "fluid ounce"
                              opts/short "fl oz"}
   opts/american-gallon      {opts/full  "US gallon"
                              opts/short "gal"}
   opts/american-pint        {opts/full  "american pint"
                              opts/short "pt"}
   opts/american-quart       {opts/full  "american quart"
                              opts/short "qt"}
   opts/cup                  {opts/full  "cup"
                              opts/short "c"}
   opts/imperial-fluid-ounce {opts/full  "imperial fluid ounce"
                              opts/short "imp fl oz"}
   opts/imperial-gallon      {opts/full  "imperial gallon"
                              opts/short "gal"}
   opts/imperial-pint        {opts/full  "imperial pint"
                              opts/short "pt"}
   opts/imperial-quart       {opts/full  "imperial quart"
                              opts/short "qt"}
   opts/liter                {opts/full  "liter"
                              opts/short "l"}
   opts/litre                {opts/full  "litre"
                              opts/short "l"}
   opts/milliliter           {opts/full  "milliliter"
                              opts/short "ml"}
   opts/millilitre           {opts/full  "millilitre"
                              opts/short "ml"}
   opts/tablespoon           {opts/full  "tablespoon"
                              opts/short "tbsp"}
   opts/teaspoon             {opts/full  "teaspoon"
                              opts/short "tsp"}})


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
                         :or   {precision opts/default-precision
                                suffix    opts/short}}]
   (if (and (contains? measurements source-units)
            (number? volume)
            (integer? precision)
            (contains? opts/supported-suffixes suffix))
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
                      :allowed-suffixes opts/supported-suffixes})))))
