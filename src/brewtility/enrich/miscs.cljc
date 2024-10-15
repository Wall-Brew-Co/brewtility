(ns brewtility.enrich.miscs
  "Enricher-pattern functions for [miscs](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) maps"
  {:added    "2.3"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.fermentables"
              "brewtility.enrich.hops"
              "brewtility.enrich.mash"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.waters"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.enrich.impl :as impl]
            [brewtility.units.options :as options]))


(defn enrich-amount-is-weight
  "An enricher pattern function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) should be measured by weight/volume.
   When this feild is not present, it is assumed to be false."
  {:added    "2.3"
   :see-also ["enrich-misc"
              "enrich-misc-wrapper"
              "enrich-miscs"
              "enrich-miscs-wrapper"]}
  ([misc] (enrich-amount-is-weight misc {}))
  ([misc _] ; Used to maintain signature parity with enricher pattern functions
   (if (contains? misc :amount-is-weight)
     misc
     (assoc misc :amount-is-weight false))))


(defn enrich-display-time
  "An enricher pattern function to render a human-readable display time of a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is in a given system.

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

   To support fine-grained selections within the context of `enrich-misc` and `enrich-misc-wrapper`, this function also supports the following keys:
    - `:misc-time-target-units`: The unit to convert the time into. Supersedes `:system-of-measure`.
    - `:misc-time-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:misc-time-suffix`: The suffix type to append to the time. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-misc"
              "enrich-misc-wrapper"
              "enrich-miscs"
              "enrich-miscs-wrapper"]}
  ([misc] (enrich-display-time misc {}))
  ([misc {:keys [misc-time-target-units
                 misc-time-precision
                 misc-time-suffix]
          :as   options}]
   (let [options (merge options {impl/value-key               :time
                                 impl/display-key             :display-time
                                 impl/fine-grain-target-units misc-time-target-units
                                 impl/fine-grain-precision    misc-time-precision
                                 impl/fine-grain-suffix       misc-time-suffix})]
     (impl/enrich-displayable-units options/time misc options))))


(defn enrich-display-amount
  "An enricher pattern function to render a human-readable display amount of a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is in a given system.
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

   To support fine-grained selections within the context of `enrich-misc` and `enrich-misc-wrapper`, this function also supports the following keys:
    - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-misc"
              "enrich-misc-wrapper"
              "enrich-miscs"
              "enrich-miscs-wrapper"]}
  ([misc] (enrich-display-amount misc {}))
  ([misc {:keys [misc-amount-target-units
                 misc-amount-precision
                 misc-amount-suffix]
          :as   options}]
   (let [options (merge options {impl/value-key               :amount
                                 impl/display-key             :display-amount
                                 impl/fine-grain-target-units misc-amount-target-units
                                 impl/fine-grain-precision    misc-amount-precision
                                 impl/fine-grain-suffix       misc-amount-suffix})]
     (if (:amount-is-weight misc)
       (impl/enrich-displayable-units options/weight misc options)
       (impl/enrich-displayable-units options/volume misc options)))))


(defn enrich-misc
  "An enricher pattern function to derive as many values from a [misc record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

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
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-amount-is-weight"
              "enrich-misc-wrapper"
              "enrich-miscs"
              "enrich-miscs-wrapper"]}
  ([misc] (enrich-misc misc {}))
  ([misc options]
   (-> misc
       (enrich-amount-is-weight options)
       (enrich-display-amount options)
       (enrich-display-time options))))


(defn enrich-misc-wrapper
  "An enricher pattern function to derive as many values from an [misc-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

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
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-amount-is-weight"
              "enrich-misc"
              "enrich-miscs"
              "enrich-miscs-wrapper"]}
  ([misc] (enrich-misc-wrapper misc {}))
  ([misc options]
   (update misc :misc enrich-misc options)))


(defn enrich-miscs
  "An enricher pattern function to derive as many values from an [miscs record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

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
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-amount-is-weight"
              "enrich-misc-wrapper"
              "enrich-misc"
              "enrich-miscs-wrapper"]}
  ([miscs] (enrich-miscs miscs {}))
  ([miscs options]
   (map #(enrich-misc-wrapper % options) miscs)))


(defn enrich-miscs-wrapper
  "An enricher pattern function to derive as many values from an [miscs-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

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
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-display-amount"
              "enrich-display-time"
              "enrich-amount-is-weight"
              "enrich-misc-wrapper"
              "enrich-miscs"
              "enrich-misc"]}
  ([miscs] (enrich-miscs-wrapper miscs {}))
  ([miscs options]
   (update miscs :miscs enrich-miscs options)))
