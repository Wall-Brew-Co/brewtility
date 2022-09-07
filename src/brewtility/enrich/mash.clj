(ns brewtility.enrich.mash
  "Enricher-pattern functions for [mashes and mash steps](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps"
  {:added    "1.3"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.hops"
              "brewtility.enrich.fermentables"
              "brewtility.enrich.miscs"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.waters"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.enrich.impl :as impl]))


(defn enrich-display-step-temperature
  "An enricher pattern function to render a human-readable display temperature of a [mash step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-hop` and `enrich-hop-wrapper`, this function also supports the following keys:
    - `:display-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-mash-step"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash-step] (enrich-display-step-temperature mash-step {}))
  ([mash-step {:keys [display-temperature-target-units
                      display-temperature-precision
                      display-temperature-suffix]
               :as   opts}]
   (let [options (merge opts {:value-key               :step-temp
                              :display-key             :display-step-temp
                              :fine-grain-target-units display-temperature-target-units
                              :fine-grain-precision    display-temperature-precision
                              :fine-grain-suffix       display-temperature-suffix})]
     (impl/enrich-displayable-temperature mash-step options))))


(defn enrich-display-infuse-amount
  "An enricher pattern function to render a human-readable infuse amount of a [mash step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-hop` and `enrich-hop-wrapper`, this function also supports the following keys:
    - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-mash-step"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash-step] (enrich-display-infuse-amount mash-step {}))
  ([mash-step {:keys [display-infuse-amount-target-units
                      display-infuse-amount-precision
                      display-infuse-amount-suffix]
               :as   opts}]
   (let [options (merge opts {:value-key               :infuse-amount
                              :display-key             :display-infuse-amt
                              :fine-grain-target-units display-infuse-amount-target-units
                              :fine-grain-precision    display-infuse-amount-precision
                              :fine-grain-suffix       display-infuse-amount-suffix})]
     (impl/enrich-displayable-volume mash-step options))))
