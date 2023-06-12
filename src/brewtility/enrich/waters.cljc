(ns brewtility.enrich.waters
  "Enricher-pattern functions for [waters](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) maps"
  {:added    "2.1"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.fermentables"
              "brewtility.enrich.hops"
              "brewtility.enrich.mash"
              "brewtility.enrich.miscs"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.enrich.impl :as impl]))


(defn enrich-display-amount
  "An enricher pattern function to render a human-readable display amount of a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) is in a given system.

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

   To support fine-grained selections within the context of `enrich-water` and `enrich-water-wrapper`, this function also supports the following keys:
    - `:water-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:water-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:water-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-water"
              "enrich-water-wrapper"
              "enrich-waters"
              "enrich-waters-wrapper"]}
  ([water] (enrich-display-amount water {}))
  ([water {:keys [water-amount-target-units
                  water-amount-precision
                  water-amount-suffix]
           :as   opts}]
   (let [options (merge opts {impl/value-key               :amount
                              impl/display-key             :display-amount
                              impl/fine-grain-target-units water-amount-target-units
                              impl/fine-grain-precision    water-amount-precision
                              impl/fine-grain-suffix       water-amount-suffix})]
     (impl/enrich-displayable-volume water options))))


(defn enrich-water
  "An enricher pattern function to derive as many values from an [water record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc).

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
        - `:water-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:water-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:water-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-water-wrapper"
              "enrich-waters"
              "enrich-waters-wrapper"]}
  ([water] (enrich-water water {}))
  ([water opts]
   (-> water
       (enrich-display-amount opts))))


(defn enrich-water-wrapper
  "An enricher pattern function to derive as many values from an [water record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) as possible.
     
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
        - `:water-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:water-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:water-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-water"
              "enrich-waters"
              "enrich-waters-wrapper"]}
  ([water] (enrich-water-wrapper water {}))
  ([water opts]
   (update water :water enrich-water opts)))


(defn enrich-waters
  "An enricher pattern function to derive as many values from a collection of [water records](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) as possible.
     
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
        - `:water-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:water-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:water-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-water"
              "enrich-water-wrapper"
              "enrich-waters-wrapper"]}
  ([waters] (enrich-waters waters {}))
  ([waters opts]
   (map #(enrich-water % opts) waters)))


(defn enrich-waters-wrapper
  "An enricher pattern function to derive as many values from a collection of [water records](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) as possible.
     
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
        - `:water-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:water-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:water-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-water"
              "enrich-water-wrapper"
              "enrich-waters"
              "enrich-waters-wrapper"]}
  ([waters] (enrich-waters waters {}))
  ([waters opts]
   (update waters :waters enrich-waters opts)))
