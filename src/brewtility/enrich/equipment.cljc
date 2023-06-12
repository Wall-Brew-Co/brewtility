(ns brewtility.enrich.equipment
  "Enricher-pattern functions for [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) maps"
  {:added    "2.1"
   :see-also ["brewtility.enrich.fermentables"
              "brewtility.enrich.hops"
              "brewtility.enrich.mash"
              "brewtility.enrich.miscs"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.waters"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.calculations :as calc]
            [brewtility.enrich.impl :as impl]
            [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(defn enrich-calculated-boil-size
  "An enricher pattern function to calculate the boil size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record via [[brewtility.calculations/calculate-equipment-boil-volume]].
   In the BeerXML spec, this behavior is controlled by the `:calc-boil-volume` field.
   When `:calc-boil-volume` is set to `true`, the `:boil-size` field is calculated as:
   `(batch-size - top-up-water - trub-chiller-loss) * (1 + (boil-time * evap-rate))`

   When `:calc-boil-volume` is set to `false`, the `:boil-size` field is left unmodified.
   As a note, both the BeerXML spec and common-beer-format only require the `boil-size` field to be a number.
   Neither the specification nor the implementation will check to ensure the value is correct given the other fields.

   An option map may be passed as an optional second argument.
   The following keys are supported:

     - `:safe-calculating-boil-size` - If this value is true, the program will not throw an exception if data is missing for the calculation.
                                       In that case, the exception will be caught internally and the `:boil-size` will not be modified from its original value.
                                       Defaults to `false`.
     - `:precision` - The number of decimal places to round the calculated value to.
                     Defaults to `3`."
  {:added    "2.1"
   :see-also ["brewtility.calculations/calculate-equipment-boil-volume"
              "enrich-equipment"
              "enrich-equipment-wrapper"]}
  ([equipment] (enrich-calculated-boil-size equipment {}))
  ([{:keys [calc-boil-volume]
     :as   equipment}
    {:keys [safe-calculating-boil-size precision]
     :or   {precision options/default-precision}}]
   (try
     (if calc-boil-volume
       (let [derived-boil-size (precision/->precision (calc/calculate-equipment-boil-volume equipment) precision)]
         (assoc equipment :boil-size derived-boil-size))
       equipment)
     #?(:clj (catch Exception e
               (if safe-calculating-boil-size
                 equipment
                 (throw e))))
     #?(:cljs (catch js/Error e
                (if safe-calculating-boil-size
                  equipment
                  (throw e)))))))


(defn enrich-display-boil-size
  "An enricher pattern function to add the displayable boil size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the boil size into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:boil-size-target-units`: The unit to convert the boil size into. Supersedes `:system-of-measure`.
    - `:boil-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:boil-size-suffix`: The suffix type to append to the boil size. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-boil-size equipment {}))
  ([equipment
    {:keys [boil-size-target-units boil-size-precision boil-size-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :boil-size
                         impl/display-key             :display-boil-size
                         impl/fine-grain-target-units boil-size-target-units
                         impl/fine-grain-precision    boil-size-precision
                         impl/fine-grain-suffix       boil-size-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-display-batch-size
  "An enricher pattern function to add the displayable batch size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the batch size into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the batch size. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:batch-size-target-units`: The unit to convert the batch size into. Supersedes `:system-of-measure`.
    - `:batch-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:batch-size-suffix`: The suffix type to append to the batch size. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-batch-size equipment {}))
  ([equipment
    {:keys [batch-size-target-units batch-size-precision batch-size-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :batch-size
                         impl/display-key             :display-batch-size
                         impl/fine-grain-target-units batch-size-target-units
                         impl/fine-grain-precision    batch-size-precision
                         impl/fine-grain-suffix       batch-size-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-display-tun-volume
  "An enricher pattern function to add the displayable tun volume to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
     The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the tun volume into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the tun volume. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:tun-volume-target-units`: The unit to convert the tun volume into. Supersedes `:system-of-measure`.
    - `:tun-volume-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:tun-volume-suffix`: The suffix type to append to the tun volume. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-tun-volume equipment {}))
  ([equipment
    {:keys [tun-volume-target-units tun-volume-precision tun-volume-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :tun-volume
                         impl/display-key             :display-tun-volume
                         impl/fine-grain-target-units tun-volume-target-units
                         impl/fine-grain-precision    tun-volume-precision
                         impl/fine-grain-suffix       tun-volume-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-display-tun-weight
  "An enricher pattern function to add the displayable tun weight to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
     The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the tun weight into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the tun weight. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:tun-weight-target-units`: The unit to convert the tun weight into. Supersedes `:system-of-measure`.
    - `:tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:tun-weight-suffix`: The suffix type to append to the tun weight. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-tun-weight equipment {}))
  ([equipment
    {:keys [tun-weight-target-units tun-weight-precision tun-weight-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :tun-weight
                         impl/display-key             :display-tun-weight
                         impl/fine-grain-target-units tun-weight-target-units
                         impl/fine-grain-precision    tun-weight-precision
                         impl/fine-grain-suffix       tun-weight-suffix})]
     (impl/enrich-displayable-weight equipment options))))


(defn enrich-display-top-up-water
  "An enricher pattern function to add the displayable top up water to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the top up water into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the top up water. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:top-up-water-target-units`: The unit to convert the top up water into. Supersedes `:system-of-measure`.
    - `:top-up-water-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:top-up-water-suffix`: The suffix type to append to the top up water. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-top-up-water equipment {}))
  ([equipment
    {:keys [top-up-water-target-units top-up-water-precision top-up-water-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :top-up-water
                         impl/display-key             :display-top-up-water
                         impl/fine-grain-target-units top-up-water-target-units
                         impl/fine-grain-precision    top-up-water-precision
                         impl/fine-grain-suffix       top-up-water-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-display-trub-chiller-loss
  "An enricher pattern function to add the displayable trub chiller loss to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the trub chiller loss into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the trub chiller loss. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:trub-chiller-loss-target-units`: The unit to convert the trub chiller loss into. Supersedes `:system-of-measure`.
    - `:trub-chiller-loss-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:trub-chiller-loss-suffix`: The suffix type to append to the trub chiller loss. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-trub-chiller-loss equipment {}))
  ([equipment
    {:keys [trub-chiller-loss-target-units trub-chiller-loss-precision trub-chiller-loss-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :trub-chiller-loss
                         impl/display-key             :display-trub-chiller-loss
                         impl/fine-grain-target-units trub-chiller-loss-target-units
                         impl/fine-grain-precision    trub-chiller-loss-precision
                         impl/fine-grain-suffix       trub-chiller-loss-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-display-lauter-deadspace
  "An enricher pattern function to add the displayable lauter deadspace to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the lauter deadspace into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the lauter deadspace. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:lauter-deadspace-target-units`: The unit to convert the lauter deadspace into. Supersedes `:system-of-measure`.
    - `:lauter-deadspace-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:lauter-deadspace-suffix`: The suffix type to append to the lauter deadspace. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-lauter-deadspace equipment {}))
  ([equipment
    {:keys [lauter-deadspace-target-units lauter-deadspace-precision lauter-deadspace-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :lauter-deadspace
                         impl/display-key             :display-lauter-deadspace
                         impl/fine-grain-target-units lauter-deadspace-target-units
                         impl/fine-grain-precision    lauter-deadspace-precision
                         impl/fine-grain-suffix       lauter-deadspace-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-display-top-up-kettle
  "An enricher pattern function to add the displayable top up kettle to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the top up kettle into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the top up kettle. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained selections within the context of `enrich-equipment` and `enrich-equipment-wrapper`, this function also supports the following keys:
    - `:top-up-kettle-target-units`: The unit to convert the top up kettle into. Supersedes `:system-of-measure`.
    - `:top-up-kettle-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:top-up-kettle-suffix`: The suffix type to append to the top up kettle. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-equipment" "enrich-equipment-wrapper"]}
  ([equipment] (enrich-display-top-up-kettle equipment {}))
  ([equipment
    {:keys [top-up-kettle-target-units top-up-kettle-precision top-up-kettle-suffix]
     :as   opts}]
   (let [options (merge opts
                        {impl/value-key               :top-up-kettle
                         impl/display-key             :display-top-up-kettle
                         impl/fine-grain-target-units top-up-kettle-target-units
                         impl/fine-grain-precision    top-up-kettle-precision
                         impl/fine-grain-suffix       top-up-kettle-suffix})]
     (impl/enrich-displayable-volume equipment options))))


(defn enrich-equipment
  "An enricher pattern function to derive as many values from an [equipment record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

    - `:system-of-measure`: The unit system of measure to convert the displayable fields into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-calculated-boil-size]]
        - `:safe-calculating-boil-size` - If this value is true, the program will not throw an exception if data is missing for the calculation.
                                          In that case, the exception will be caught internally and the `:boil-size` will not be modified from its original value.
                                          Defaults to `false`.
    - [[enrich-display-boil-size]]
        - `:boil-size-target-units`: The unit to convert the boil size into. Supersedes `:system-of-measure`.
        - `:boil-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:boil-size-suffix`: The suffix type to append to the boil size. Supersedes `:suffix`.
    - [[enrich-display-batch-size]]
        - `:batch-size-target-units`: The unit to convert the batch size into. Supersedes `:system-of-measure`.
        - `:batch-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:batch-size-suffix`: The suffix type to append to the batch size. Supersedes `:suffix`.
    - [[enrich-diplay-tun-volume]]
        - `:tun-volume-target-units`: The unit to convert the tun volume into. Supersedes `:system-of-measure`.
        - `:tun-volume-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-volume-suffix`: The suffix type to append to the tun volume. Supersedes `:suffix`.
    - [[enrich-display-tun-weight]]
        - `:tun-weight-target-units`: The unit to convert the tun weight into. Supersedes `:system-of-measure`.
        - `:tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-weight-suffix`: The suffix type to append to the tun weight. Supersedes `:suffix`.
    - [[enrich-display-top-up-water]]
        - `:top-up-water-target-units`: The unit to convert the top up water into. Supersedes `:system-of-measure`.
        - `:top-up-water-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-water-suffix`: The suffix type to append to the top up water. Supersedes `:suffix`.
    - [[enrich-display-trub-chiller-loss]]
        - `:trub-chiller-loss-target-units`: The unit to convert the trub chiller loss into. Supersedes `:system-of-measure`.
        - `:trub-chiller-loss-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:trub-chiller-loss-suffix`: The suffix type to append to the trub chiller loss. Supersedes `:suffix`.
    - [[enrich-display-lauter-deadspace]]
        - `:lauter-deadspace-target-units`: The unit to convert the lauter deadspace into. Supersedes `:system-of-measure`.
        - `:lauter-deadspace-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:lauter-deadspace-suffix`: The suffix type to append to the lauter deadspace. Supersedes `:suffix`.
    - [[enrich-display-top-up-kettle]]
        - `:top-up-kettle-target-units`: The unit to convert the top up kettle into. Supersedes `:system-of-measure`.
        - `:top-up-kettle-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-kettle-suffix`: The suffix type to append to the top up kettle. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-calculated-boil-size"
              "enrich-calculated-boil-size"
              "enrich-diplay-tun-volume"
              "enrich-display-tun-weight"
              "enrich-display-top-up-water"
              "enrich-display-trub-chiller-loss"
              "enrich-display-lauter-deadspace"
              "enrich-display-top-up-kettle"
              "enrich-equipment-wrapper"]}
  ([equipment]
   (enrich-equipment equipment {}))

  ([equipment opts]
   (-> equipment
       (enrich-calculated-boil-size opts)
       (enrich-display-boil-size opts)
       (enrich-display-batch-size opts)
       (enrich-display-tun-volume opts)
       (enrich-display-tun-weight opts)
       (enrich-display-top-up-water opts)
       (enrich-display-trub-chiller-loss opts)
       (enrich-display-lauter-deadspace opts)
       (enrich-display-top-up-kettle opts))))


(defn enrich-equipment-wrapper
  "An enricher pattern function to derive as many values from an [equipment-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

    - `:system-of-measure`: The unit system of measure to convert the displayable fields into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"gal\"` for `\" US gallons\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-calculated-boil-size]]
        - `:safe-calculating-boil-size` - If this value is true, the program will not throw an exception if data is missing for the calculation.
                                          In that case, the exception will be caught internally and the `:boil-size` will not be modified from its original value.
                                          Defaults to `false`.
    - [[enrich-display-boil-size]]
        - `:boil-size-target-units`: The unit to convert the boil size into. Supersedes `:system-of-measure`.
        - `:boil-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:boil-size-suffix`: The suffix type to append to the boil size. Supersedes `:suffix`.
    - [[enrich-display-batch-size]]
        - `:batch-size-target-units`: The unit to convert the batch size into. Supersedes `:system-of-measure`.
        - `:batch-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:batch-size-suffix`: The suffix type to append to the batch size. Supersedes `:suffix`.
    - [[enrich-diplay-tun-volume]]
        - `:tun-volume-target-units`: The unit to convert the tun volume into. Supersedes `:system-of-measure`.
        - `:tun-volume-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-volume-suffix`: The suffix type to append to the tun volume. Supersedes `:suffix`.
    - [[enrich-display-tun-weight]]
        - `:tun-weight-target-units`: The unit to convert the tun weight into. Supersedes `:system-of-measure`.
        - `:tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-weight-suffix`: The suffix type to append to the tun weight. Supersedes `:suffix`.
    - [[enrich-display-top-up-water]]
        - `:top-up-water-target-units`: The unit to convert the top up water into. Supersedes `:system-of-measure`.
        - `:top-up-water-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-water-suffix`: The suffix type to append to the top up water. Supersedes `:suffix`.
    - [[enrich-display-trub-chiller-loss]]
        - `:trub-chiller-loss-target-units`: The unit to convert the trub chiller loss into. Supersedes `:system-of-measure`.
        - `:trub-chiller-loss-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:trub-chiller-loss-suffix`: The suffix type to append to the trub chiller loss. Supersedes `:suffix`.
    - [[enrich-display-lauter-deadspace]]
        - `:lauter-deadspace-target-units`: The unit to convert the lauter deadspace into. Supersedes `:system-of-measure`.
        - `:lauter-deadspace-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:lauter-deadspace-suffix`: The suffix type to append to the lauter deadspace. Supersedes `:suffix`.
    - [[enrich-display-top-up-kettle]]
        - `:top-up-kettle-target-units`: The unit to convert the top up kettle into. Supersedes `:system-of-measure`.
        - `:top-up-kettle-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-kettle-suffix`: The suffix type to append to the top up kettle. Supersedes `:suffix`."
  {:added    "2.1"
   :see-also ["enrich-calculated-boil-size"
              "enrich-calculated-boil-size"
              "enrich-diplay-tun-volume"
              "enrich-display-tun-weight"
              "enrich-display-top-up-water"
              "enrich-display-trub-chiller-loss"
              "enrich-display-lauter-deadspace"
              "enrich-display-top-up-kettle"
              "enrich-equipment"]}
  ([equipment-wrapper]
   (enrich-equipment-wrapper equipment-wrapper {}))

  ([equipment-wrapper opts]
   (update equipment-wrapper :equipment enrich-equipment opts)))
