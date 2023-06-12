(ns brewtility.enrich.yeasts
  "Enricher-pattern functions for [yeasts](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) maps"
  {:added    "2.0"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.fermentables"
              "brewtility.enrich.hops"
              "brewtility.enrich.mash"
              "brewtility.enrich.yeasts"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.waters"]}
  (:require [brewtility.enrich.impl :as impl]))


(defn enrich-amount-is-weight
  "An enricher pattern function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) should be measured by weight/volume.
   When this feild is not present, it is assumed to be false."
  {:added    "2.0"
   :see-also ["enrich-yeast"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeast] (enrich-amount-is-weight yeast {}))
  ([yeast _] ; Used to maintain signature parity with enricher pattern functions
   (if (contains? yeast :amount-is-weight)
     yeast
     (assoc yeast :amount-is-weight false))))


(defn enrich-display-amount
  "An enricher pattern function to render a human-readable display amount of a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in a given system.
   If the `amount-is-weight` key evaluates truthy, the `amount` will be treated as a weight in kilograms.
   Otherwise, it will be treated as a volume in liters.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-yeast` and `enrich-yeast-wrapper`, this function also supports the following keys:
    - `:yeast-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:yeast-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:yeast-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-yeast"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeast] (enrich-display-amount yeast {}))
  ([yeast {:keys [yeast-amount-target-units
                  yeast-amount-precision
                  yeast-amount-suffix]
           :as   opts}]
   (let [options (merge opts {impl/value-key               :amount
                              impl/display-key             :display-amount
                              impl/fine-grain-target-units yeast-amount-target-units
                              impl/fine-grain-precision    yeast-amount-precision
                              impl/fine-grain-suffix       yeast-amount-suffix})]
     (if (:amount-is-weight yeast)
       (impl/enrich-displayable-weight yeast options)
       (impl/enrich-displayable-volume yeast options)))))


(defn enrich-display-min-temperature
  "An enricher pattern function to render a human-readable display minimum-viable fermentation temperature of a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in a given system.
   If the `min-temperature` key is not present, the map will not be changed.
   
   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the min-temperature into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the min-temperature Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-yeast` and `enrich-yeast-wrapper`, this function also supports the following keys:
    - `:yeast-min-temperature-target-units`: The unit to convert the min-temperature into. Supersedes `:system-of-measure`.
    - `:yeast-min-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:yeast-min-temperature-suffix`: The suffix type to append to the min-temperature. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-yeast"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeast] (enrich-display-min-temperature yeast {}))
  ([yeast {:keys [yeast-min-temperature-target-units
                  yeast-min-temperature-precision
                  yeast-min-temperature-suffix]
           :as   opts}]
   (if (:min-temperature yeast)
     (let [options (merge opts {impl/value-key               :min-temperature
                                impl/display-key             :disp-min-temp
                                impl/fine-grain-target-units yeast-min-temperature-target-units
                                impl/fine-grain-precision    yeast-min-temperature-precision
                                impl/fine-grain-suffix       yeast-min-temperature-suffix})]
       (impl/enrich-displayable-temperature yeast options))
     yeast)))


(defn enrich-display-max-temperature
  "An enricher pattern function to render a human-readable display maximum-viable fermentation temperature of a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in a given system.
   If the `max-temperature` key is not present, the map will not be changed.
   
   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the max-temperature into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the max-temperature Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-yeast` and `enrich-yeast-wrapper`, this function also supports the following keys:
    - `:yeast-max-temperature-target-units`: The unit to convert the max-temperature into. Supersedes `:system-of-measure`.
    - `:yeast-max-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:yeast-max-temperature-suffix`: The suffix type to append to the max-temperature. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-yeast"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeast] (enrich-display-max-temperature yeast {}))
  ([yeast {:keys [yeast-max-temperature-target-units
                  yeast-max-temperature-precision
                  yeast-max-temperature-suffix]
           :as   opts}]
   (if (:max-temperature yeast)
     (let [options (merge opts {impl/value-key               :max-temperature
                                impl/display-key             :disp-max-temp
                                impl/fine-grain-target-units yeast-max-temperature-target-units
                                impl/fine-grain-precision    yeast-max-temperature-precision
                                impl/fine-grain-suffix       yeast-max-temperature-suffix})]
       (impl/enrich-displayable-temperature yeast options))
     yeast)))


(defn enrich-yeast
  "An enricher pattern function to derive as many values from a [yeast record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) as possible.
   
  An option map may be passed as an optional second argument.
  The following keys are supported for controlling high-level behavior: 
   
   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
   
   - [[enrich-display-amount]]
       - `:yeast-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
       - `yeast-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `yeast-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-min-temperature]]
       - `:yeast-min-temperature-target-units`: The unit to convert the min-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-min-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-min-temperature-suffix`: The suffix type to append to the min-temperature. Supersedes `:suffix`.
    - [[enrich-display-max-temperature]]
       - `:yeast-max-temperature-target-units`: The unit to convert the max-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-max-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-max-temperature-suffix`: The suffix type to append to the max-temperature. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-amount-is-weight"
              "enrich-display-amount"
              "enrich-display-min-temperature"
              "enrich-display-max-temperature"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeast] (enrich-yeast yeast {}))
  ([yeast opts]
   (-> yeast
       (enrich-amount-is-weight opts)
       (enrich-display-amount opts)
       (enrich-display-min-temperature opts)
       (enrich-display-max-temperature opts))))


(defn enrich-yeast-wrapper
  "An enricher pattern function to derive as many values from a [yeast-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) as possible.
   
  An option map may be passed as an optional second argument.
  The following keys are supported for controlling high-level behavior: 
   
   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
   
   - [[enrich-display-amount]]
       - `:yeast-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
       - `yeast-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `yeast-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-min-temperature]]
       - `:yeast-min-temperature-target-units`: The unit to convert the min-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-min-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-min-temperature-suffix`: The suffix type to append to the min-temperature. Supersedes `:suffix`.
    - [[enrich-display-max-temperature]]
       - `:yeast-max-temperature-target-units`: The unit to convert the max-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-max-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-max-temperature-suffix`: The suffix type to append to the max-temperature. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-amount-is-weight"
              "enrich-display-amount"
              "enrich-display-min-temperature"
              "enrich-display-max-temperature"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeast-wrapper] (enrich-yeast-wrapper yeast-wrapper {}))
  ([yeast-wrapper opts]
   (update yeast-wrapper :yeast enrich-yeast opts)))


(defn enrich-yeasts
  "An enricher pattern function to derive as many values from a [yeast-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) as possible.
   
  An option map may be passed as an optional second argument.
  The following keys are supported for controlling high-level behavior: 
   
   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
   
   - [[enrich-display-amount]]
       - `:yeast-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
       - `yeast-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `yeast-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-min-temperature]]
       - `:yeast-min-temperature-target-units`: The unit to convert the min-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-min-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-min-temperature-suffix`: The suffix type to append to the min-temperature. Supersedes `:suffix`.
    - [[enrich-display-max-temperature]]
       - `:yeast-max-temperature-target-units`: The unit to convert the max-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-max-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-max-temperature-suffix`: The suffix type to append to the max-temperature. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-amount-is-weight"
              "enrich-display-amount"
              "enrich-display-min-temperature"
              "enrich-display-max-temperature"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeasts] (enrich-yeasts yeasts {}))
  ([yeasts opts]
   (map #(enrich-yeast-wrapper % opts) yeasts)))


(defn enrich-yeasts-wrapper
  "An enricher pattern function to derive as many values from a [yeast-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) as possible.
   
  An option map may be passed as an optional second argument.
  The following keys are supported for controlling high-level behavior: 
   
   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
   
   - [[enrich-display-amount]]
       - `:yeast-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
       - `yeast-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `yeast-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-min-temperature]]
       - `:yeast-min-temperature-target-units`: The unit to convert the min-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-min-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-min-temperature-suffix`: The suffix type to append to the min-temperature. Supersedes `:suffix`.
    - [[enrich-display-max-temperature]]
       - `:yeast-max-temperature-target-units`: The unit to convert the max-temperature into. Supersedes `:system-of-measure`.
       - `:yeast-max-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
       - `:yeast-max-temperature-suffix`: The suffix type to append to the max-temperature. Supersedes `:suffix`."
  {:added    "2.0"
   :see-also ["enrich-amount-is-weight"
              "enrich-display-amount"
              "enrich-display-min-temperature"
              "enrich-display-max-temperature"
              "enrich-yeast-wrapper"
              "enrich-yeasts"
              "enrich-yeasts-wrapper"]}
  ([yeasts-wrapper] (enrich-yeasts-wrapper yeasts-wrapper {}))
  ([yeasts-wrapper opts]
   (update yeasts-wrapper :yeasts enrich-yeasts opts)))
