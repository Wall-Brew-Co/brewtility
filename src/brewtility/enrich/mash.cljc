(ns brewtility.enrich.mash
  "Enricher-pattern functions for [mashes and mash steps](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps"
  {:added    "1.3"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.mash-steps"
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

   To support fine-grained selections within the context of `enrich-mash-step` and `enrich-mash-step-wrapper`, this function also supports the following keys:
    - `:display-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.enrich.impl/->displayable-temperature"
              "enrich-mash-step"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash-steps-wrapper"
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

   To support fine-grained selections within the context of `enrich-mash-step` and `enrich-mash-step-wrapper`, this function also supports the following keys:
    - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-mash-step"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash-steps-wrapper"
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


(defn enrich-mash-step
  "An enricher pattern function to derive as many values from an [mash-step record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

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
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["enrich-display-step-temperature"
              "enrich-display-infuse-amount"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash-steps-wrapper"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash-step] (enrich-mash-step mash-step {}))
  ([mash-step opts]
   (-> mash-step
       (enrich-display-step-temperature opts)
       (enrich-display-infuse-amount opts))))


(defn enrich-mash-step-wrapper
  "An enricher pattern function to derive as many values from an [mash-step wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

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
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["enrich-display-step-temperature"
              "enrich-display-infuse-amount"
              "enrich-mash-step"
              "enrich-mash-steps"
              "enrich-mash-steps-wrapper"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash-step] (enrich-mash-step-wrapper mash-step {}))
  ([mash-step opts]
   (update mash-step :mash-step enrich-mash-step opts)))


(defn enrich-mash-steps
  "An enricher pattern function to derive as many values from a collection of [mash-step wrapper records](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

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
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["enrich-display-step-temperature"
              "enrich-display-infuse-amount"
              "enrich-mash-step"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps-wrapper"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash-steps] (enrich-mash-steps mash-steps {}))
  ([mash-steps opts]
   (map #(enrich-mash-step-wrapper % opts) mash-steps)))


(defn enrich-mash-steps-wrapper
  "An enricher pattern function to derive as many values from a collection of [mash-steps wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

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
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["enrich-display-step-temperature"
              "enrich-display-infuse-amount"
              "enrich-mash-step"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-mash-steps-wrapper mash {}))
  ([mash opts]
   (update mash :mash-steps enrich-mash-steps opts)))


(defn enrich-display-grain-temperature
  "An enricher pattern function to render a human-readable temperature of the grain temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

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

   To support fine-grained selections within the context of `enrich-mash-step` and `enrich-mash-step-wrapper`, this function also supports the following keys:
    - `:display-grain-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-grain-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-grain-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.enrich.impl/->displayable-temperature"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-display-grain-temperature mash {}))
  ([mash {:keys [display-grain-temperature-target-units
                 display-grain-temperature-precision
                 display-grain-temperature-suffix]
          :as   opts}]
   (let [options (merge opts {:value-key               :grain-temp
                              :display-key             :display-grain-temp
                              :fine-grain-target-units display-grain-temperature-target-units
                              :fine-grain-precision    display-grain-temperature-precision
                              :fine-grain-suffix       display-grain-temperature-suffix})]
     (impl/enrich-displayable-temperature mash options))))


(defn enrich-display-tun-temperature
  "An enricher pattern function to render a human-readable temperature of the tun temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

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

   To support fine-tuned selections within the context of `enrich-mash-step` and `enrich-mash-step-wrapper`, this function also supports the following keys:
    - `:display-tun-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-tun-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-tun-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.enrich.impl/->displayable-temperature"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-display-tun-temperature mash {}))
  ([mash {:keys [display-tun-temperature-target-units
                 display-tun-temperature-precision
                 display-tun-temperature-suffix]
          :as   opts}]
   (let [options (merge opts {:value-key               :tun-temp
                              :display-key             :display-tun-temp
                              :fine-grain-target-units display-tun-temperature-target-units
                              :fine-grain-precision    display-tun-temperature-precision
                              :fine-grain-suffix       display-tun-temperature-suffix})]
     (impl/enrich-displayable-temperature mash options))))


(defn enrich-display-sparge-temperature
  "An enricher pattern function to render a human-readable temperature of the sparge temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

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

   To support fine-spargeed selections within the context of `enrich-mash-step` and `enrich-mash-step-wrapper`, this function also supports the following keys:
    - `:display-sparge-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-sparge-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-sparge-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.enrich.impl/->displayable-temperature"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-display-sparge-temperature mash {}))
  ([mash {:keys [display-sparge-temperature-target-units
                 display-sparge-temperature-precision
                 display-sparge-temperature-suffix]
          :as   opts}]
   (let [options (merge opts {:value-key               :sparge-temp
                              :display-key             :display-sparge-temp
                              :fine-grain-target-units display-sparge-temperature-target-units
                              :fine-grain-precision    display-sparge-temperature-precision
                              :fine-grain-suffix       display-sparge-temperature-suffix})]
     (impl/enrich-displayable-temperature mash options))))


(defn enrich-display-tun-weight
  "An enricher pattern function to render a human-readable weight of the tun weight during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

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

   To support fine-tuned selections within the context of `enrich-mash-step` and `enrich-mash-step-wrapper`, this function also supports the following keys:
    - `:display-tun-weight-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-tun-weight-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["brewtility.enrich.impl/->displayable-weight"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-display-tun-weight mash {}))
  ([mash {:keys [display-tun-weight-target-units
                 display-tun-weight-precision
                 display-tun-weight-suffix]
          :as   opts}]
   (let [options (merge opts {:value-key               :tun-weight
                              :display-key             :display-tun-weight
                              :fine-grain-target-units display-tun-weight-target-units
                              :fine-grain-precision    display-tun-weight-precision
                              :fine-grain-suffix       display-tun-weight-suffix})]
     (impl/enrich-displayable-weight mash options))))


(defn enrich-mash
  "An enricher pattern function to derive as many values from an [mash record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

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
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-grain-temperature]]
        - `:display-grain-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-grain-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-grain-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-tun-temperature]]
        - `:display-tun-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-sparge-temperature]]
        - `:display-sparge-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-sparge-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-sparge-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`
    - [[enrich-display-tun-weight]]
        - `:display-tun-weight-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-weight-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["enrich-display-step-temperature"
              "enrich-display-infuse-amount"
              "enrich-display-grain-temperature"
              "enrich-display-tun-temperature"
              "enrich-display-sparge-temperature"
              "enrich-display-tun-weight"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-mash mash {}))
  ([mash opts]
   (-> mash
       (enrich-mash-steps-wrapper opts)
       (enrich-display-grain-temperature opts)
       (enrich-display-tun-temperature opts)
       (enrich-display-sparge-temperature opts)
       (enrich-display-tun-weight opts))))


(defn enrich-mash-wrapper
  "An enricher pattern function to derive as many values from an [mash wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

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
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-grain-temperature]]
        - `:display-grain-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-grain-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-grain-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-tun-temperature]]
        - `:display-tun-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-sparge-temperature]]
        - `:display-sparge-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-sparge-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-sparge-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`
    - [[enrich-display-tun-weight]]
        - `:display-tun-weight-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-weight-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "1.3"
   :see-also ["enrich-display-step-temperature"
              "enrich-display-infuse-amount"
              "enrich-display-grain-temperature"
              "enrich-display-tun-temperature"
              "enrich-display-sparge-temperature"
              "enrich-display-tun-weight"
              "enrich-mash-step-wrapper"
              "enrich-mash-steps"
              "enrich-mash"
              "enrich-mash-wrapper"]}
  ([mash] (enrich-mash-wrapper mash {}))
  ([mash opts]
   (update mash :mash enrich-mash opts)))