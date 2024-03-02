(ns brewtility.enrich.hops
  "Enricher-pattern functions for [hops](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) maps"
  {:added    "2.1"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.fermentables"
              "brewtility.enrich.mash"
              "brewtility.enrich.miscs"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.waters"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.enrich.impl :as impl]
            [brewtility.units.options :as options]))


(defn enrich-display-amount
  "An enricher pattern function to render a human-readable display weight of a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) is in a given system.

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
    - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["brewtility.string/same?"
              "enrich-hop"
              "enrich-hop-wrapper"
              "enrich-hops"
              "enrich-hops-wrapper"]}
  ([hop] (enrich-display-amount hop {}))
  ([hop {:keys [hop-amount-target-units
                hop-amount-precision
                hop-amount-suffix]
         :as   opts}]
   (let [options (merge opts {impl/value-key               :amount
                              impl/display-key             :display-amount
                              impl/fine-grain-target-units hop-amount-target-units
                              impl/fine-grain-precision    hop-amount-precision
                              impl/fine-grain-suffix       hop-amount-suffix})]
     (impl/enrich-displayable-units options/weight hop options))))


(defn enrich-display-time
  "An enricher pattern function to render a human-readable display time of a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the time into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the time Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-hop` and `enrich-hop-wrapper`, this function also supports the following keys:
    - `:hop-time-target-units`: The unit to convert the time into. Supersedes `:system-of-measure`.
    - `:hop-time-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:hop-time-suffix`: The suffix type to append to the time. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-hop"
              "enrich-hop-wrapper"
              "enrich-hops"
              "enrich-hops-wrapper"]}
  ([hop] (enrich-display-time hop {}))
  ([hop {:keys [hop-time-target-units
                hop-time-precision
                hop-time-suffix]
         :as   opts}]
   (let [options (merge opts {impl/value-key               :time
                              impl/display-key             :display-time
                              impl/fine-grain-target-units hop-time-target-units
                              impl/fine-grain-precision    hop-time-precision
                              impl/fine-grain-suffix       hop-time-suffix})]
     (impl/enrich-displayable-units options/time hop options))))


(defn enrich-hop
  "An enricher pattern function to derive as many values from an [hop record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

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
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-hop-wrapper"
              "enrich-hops"
              "enrich-hops-wrapper"]}
  ([hop] (enrich-hop hop {}))
  ([hop opts]
   (-> hop
       (enrich-display-amount opts)
       (enrich-display-time opts))))


(defn enrich-hop-wrapper
  "An enricher pattern function to derive as many values from an [hop-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

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
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-hop"
              "enrich-hops"
              "enrich-hops-wrapper"]}
  ([hop] (enrich-hop-wrapper hop {}))
  ([hop opts]
   (update hop :hop enrich-hop opts)))


(defn enrich-hops
  "An enricher pattern function to derive as many values from an [hops record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

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
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-hop-wrapper"
              "enrich-hop"
              "enrich-hops-wrapper"]}
  ([hops] (enrich-hops hops {}))
  ([hops opts]
   (map #(enrich-hop-wrapper % opts) hops)))


(defn enrich-hops-wrapper
  "An enricher pattern function to derive as many values from an [hops-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

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
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-hop-wrapper"
              "enrich-hops"
              "enrich-hop"]}
  ([hops] (enrich-hops-wrapper hops {}))
  ([hops opts]
   (update hops :hops enrich-hops opts)))

