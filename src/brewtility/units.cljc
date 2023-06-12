(ns brewtility.units
  "Namespace for converting between different units of measure.

   Currently, brewtility supports the following types of measurements:

     - [Alcohol Content](https://en.wikipedia.org/wiki/Alcohol_by_volume)
     - [Bitterness](https://en.wikipedia.org/wiki/Beer_measurement#Bitterness)
     - [Carbonation](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages)
     - [Color](https://en.wikipedia.org/wiki/Beer_measurement#Colour)
     - [Pressure](https://en.wikipedia.org/wiki/Pressure)
     - [Specific Gravity](https://en.wikipedia.org/wiki/Relative_density)
     - [Temperature](https://en.wikipedia.org/wiki/Temperature)
     - [Time](https://en.wikipedia.org/wiki/Time)
     - [Volume](https://en.wikipedia.org/wiki/Volume)
     - [Weight](https://en.wikipedia.org/wiki/Mass)

   These types of measurement cover four systems of measure:

     - [The British Imperial System](https://en.wikipedia.org/wiki/Imperial_units)
     - [The Metric System](https://en.wikipedia.org/wiki/Metric_system)
     - [The International System](https://en.wikipedia.org/wiki/International_System_of_Units)
     - [The US Customary Scale](https://en.wikipedia.org/wiki/United_States_customary_units)"
  {:added   "1.0"
   :changed "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.alcohol-content :as alcohol-content]
            [brewtility.units.bitterness :as bitterness]
            [brewtility.units.carbonation :as carbonation]
            [brewtility.units.color :as color]
            [brewtility.units.options :as options]
            [brewtility.units.pressure :as pressure]
            [brewtility.units.specific-gravity :as specific-gravity]
            [brewtility.units.temperature :as temperature]
            [brewtility.units.time :as time]
            [brewtility.units.volume :as volume]
            [brewtility.units.weight :as weight]))



(defn convert
  "Given a `measurement` in `source-units`, convert it to the `target-units` in a given `measurement-type`.

   For example:

   ```clj
     (convert :weight 1.5 :kg :lb) ;; => 3.31
   ```

   Supported values for `source-units` and `target-units` are enumerated in each unit system namespace.
   This function will throw an exception if unsupported unit values are passed.

   This function accepts an option map as an optional fourth argument. The following options are available:
     - `precision`: The number of decimal places to round to. Defaults to the precision of the converted value."
  {:added    "2.0"
   :see-also ["brewtility.units.alcohol-content/convert"
              "brewtility.units.bitterness/convert"
              "brewtility.units.carbonation/convert"
              "brewtility.units.color/convert"
              "brewtility.units.pressure/convert"
              "brewtility.units.specific-gravity/convert"
              "brewtility.units.temperature/convert"
              "brewtility.units.time/convert"
              "brewtility.units.volume/convert"
              "brewtility.units.weight/convert"]}
  ([measurement-type measurement source-units target-units]
   (convert measurement-type measurement source-units target-units {}))
  ([measurement-type measurement source-units target-units {:keys [precision]}]
   (let [converted-value (case measurement-type
                           :alcohol-content  (alcohol-content/convert measurement source-units target-units)
                           :bitterness       (bitterness/convert measurement source-units target-units)
                           :carbonation      (carbonation/convert measurement source-units target-units)
                           :color            (color/convert measurement source-units target-units)
                           :pressure         (pressure/convert measurement source-units target-units)
                           :specific-gravity (specific-gravity/convert measurement source-units target-units)
                           :temperature      (temperature/convert measurement source-units target-units)
                           :time             (time/convert measurement source-units target-units)
                           :volume           (volume/convert measurement source-units target-units)
                           :weight           (weight/convert measurement source-units target-units)
                           (throw (ex-info "Unsupported unit system"
                                           {:measurement-type measurement-type
                                            :allowed-values   options/measurement-types
                                            :measurement      measurement})))]
     (if precision
       (precision/->precision converted-value precision)
       converted-value))))


(defn display
  "A function to render a human-readable `measurement` in `source-units` for a `measurement-type`.

   For example,
   ```clj
    (display :weight 1.5 :pound) ;; => \"1.5 lb\"
   ```
   This function accepts an option map as an optional fourth argument. The following options are available:
     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added    "2.0"
   :see-also ["brewtility.units.alcohol-content/display"
              "brewtility.units.bitterness/display"
              "brewtility.units.carbonation/display"
              "brewtility.units.color/display"
              "brewtility.units.pressure/display"
              "brewtility.units.specific-gravity/display"
              "brewtility.units.temperature/display"
              "brewtility.units.time/display"
              "brewtility.units.volume/display"
              "brewtility.units.weight/display"]}
  ([measurement-type measurement source-units]
   (display measurement-type measurement source-units {}))
  ([measurement-type measurement source-units opts]

   (let [options (merge {options/precision options/default-precision
                         options/suffix    options/short} opts)]
     (case measurement-type
       :alcohol-content  (alcohol-content/display measurement source-units options)
       :bitterness       (bitterness/display measurement source-units options)
       :carbonation      (carbonation/display measurement source-units options)
       :color            (color/display measurement source-units options)
       :pressure         (pressure/display measurement source-units options)
       :specific-gravity (specific-gravity/display measurement source-units options)
       :temperature      (temperature/display measurement source-units options)
       :time             (time/display measurement source-units options)
       :volume           (volume/display measurement source-units options)
       :weight           (weight/display measurement source-units options)
       (throw (ex-info "Unsupported unit system"
                       {:measurement-type measurement-type
                        :allowed-values   options/measurement-types
                        :measurement      measurement}))))))
