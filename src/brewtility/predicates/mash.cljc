(ns brewtility.predicates.mash
  "Predicate functions for [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps."
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.fermentables"
              "brewtility.predicates.hops"
              "brewtility.predicates.miscs"
              "brewtility.predicates.recipes"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]
            [com.wallbrew.spoon.string :as spoon.string]))


(defn adjust-for-equipment?
  "A predicate function to determine if a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) should account for any temperature effects of the equipment used.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the `:equip-adjust` field is not present, this function will return false."
  {:added    "1.5"
   :arglists '([mash] [mash _options])}
  ([mash] (adjust-for-equipment? mash {}))
  ;; Added to match the arity of the other predicate functions

  ([mash _options]
   (if (contains? mash :equip-adjust)
     (:equip-adjust mash)
     false)))


(defn infusion?
  "A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used as a basic infusion.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([mash-step] [mash-step {:keys [uppercase?]}])
   :see-also ["temperature?"
              "decoction?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([mash-step] (infusion? mash-step {}))
  ([mash-step options]
   (let [mash-step-type (impl/fetch-or-throw! mash-step :type "Mash step :type is required to determine if it's an infusion step.")]
     (spoon.string/same-text? "infusion" mash-step-type options))))


(defn temperature?
  "A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used to bring wort up to temperature.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([mash-step] [mash-step {:keys [uppercase?]}])
   :see-also ["infusion?"
              "decoction?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([mash-step] (temperature? mash-step {}))
  ([mash-step options]
   (let [mash-step-type (impl/fetch-or-throw! mash-step :type "Mash step :type is required to determine if it's used to increase wort temperature.")]
     (spoon.string/same-text? "temperature" mash-step-type options))))


(defn decoction?
  "A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used to extract and concentrate flavor.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([mash-step] [mash-step {:keys [uppercase?]}])
   :see-also ["infusion?"
              "temperature?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([mash-step] (decoction? mash-step {}))
  ([mash-step options]
   (let [mash-step-type (impl/fetch-or-throw! mash-step :type "Mash step :type is required to determine if it's used to concentrate flavors by evaporating water.")]
     (spoon.string/same-text? "decoction" mash-step-type options))))
