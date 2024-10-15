(ns brewtility.predicates.fermentables
  "Predicate functions for [fermentables](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) maps."
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.hops"
              "brewtility.predicates.mash"
              "brewtility.predicates.miscs"
              "brewtility.predicates.recipes"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]
            [com.wallbrew.spoon.string :as spoon.string]))


(defn add-after-boil?
  "A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) was added after the start of the boil.
   In the BeerXML spec, this behavior is implicitly falsey.

   Therefore, if the :add-after-boil field is not present, this function will explicitly return false."
  {:added    "1.5"
   :arglists '([fermentable] [fermentable _options])}
  ([fermentable] (add-after-boil? fermentable {}))
  ;; Added to match the arity of the other predicate functions

  ([fermentable _options]
   (if (contains? fermentable :add-after-boil)
     (:add-after-boil fermentable)
     false)))


(defn grain?
  "A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is whole grain.
   This function will throw an exception if the `type` field is not present.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([fermentable] [fermentable {:keys [uppercase?]}])
   :see-also ["sugar?"
              "extract?"
              "dry-extract?"
              "adjunct?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([fermentable] (grain? fermentable {}))
  ([fermentable options]
   (let [fermentable-type (impl/fetch-or-throw! fermentable :type "Fermentable :type is required to determine if it is a grain.")]
     (spoon.string/same-text? "grain" fermentable-type options))))


(defn sugar?
  "A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is a raw sugar.
   This function will throw an exception if the `type` field is not present.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([fermentable] [fermentable {:keys [uppercase?]}])
   :see-also ["grain?"
              "extract?"
              "dry-extract?"
              "adjunct?"
              "spoon.string/same-text?"]}
  ([fermentable] (sugar? fermentable {}))
  ([fermentable options]
   (let [fermentable-type (impl/fetch-or-throw! fermentable :type "Fermentable :type is required to determine if it is a sugar")]
     (spoon.string/same-text? "sugar" fermentable-type options))))


(defn extract?
  "A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is a liquid malt extract.
   This function will throw an exception if the `type` field is not present.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([fermentable] [fermentable {:keys [uppercase?]}])
   :see-also ["grain?"
              "sugar?"
              "dry-extract?"
              "adjunct?"
              "spoon.string/same-text?"]}
  ([fermentable] (extract? fermentable {}))
  ([fermentable options]
   (let [fermentable-type (impl/fetch-or-throw! fermentable :type "Fermentable :type is required to determine if it is a extract.")]
     (spoon.string/same-text? "extract" fermentable-type options))))


(defn dry-extract?
  "A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is dried malt extract.
   This function will throw an exception if the `type` field is not present.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([fermentable] [fermentable {:keys [uppercase?]}])
   :see-also ["grain?"
              "sugar?"
              "extract?"
              "adjunct?"
              "spoon.string/same-text?"]}
  ([fermentable] (dry-extract? fermentable {}))
  ([fermentable options]
   (let [fermentable-type (impl/fetch-or-throw! fermentable :type "Fermentable :type is required to determine if it is a dry extract.")]
     (spoon.string/same-text? "dry extract" fermentable-type options))))


(defn adjunct?
  "A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is an adjunct, non-standard fermentable.
   This function will throw an exception if the `type` field is not present.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([fermentable] [fermentable {:keys [uppercase?]}])
   :see-also ["grain?"
              "sugar?"
              "extract?"
              "dry-extract?"
              "spoon.string/same-text?"]}
  ([fermentable] (adjunct? fermentable {}))
  ([fermentable options]
   (let [fermentable-type (impl/fetch-or-throw! fermentable :type "Fermentable :type is required to determine if it is an adjunct.")]
     (spoon.string/same-text? "adjunct" fermentable-type options))))
