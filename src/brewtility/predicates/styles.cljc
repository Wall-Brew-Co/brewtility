(ns brewtility.predicates.styles
  "Predicate functions for [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) maps"
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.fermentables"
              "brewtility.predicates.hops"
              "brewtility.predicates.mash"
              "brewtility.predicates.miscs"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]
            [com.wallbrew.spoon.string :as spoon.string]))


(defn lager?
  "A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for lagers.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["ale?"
              "mead?"
              "wheat?"
              "mixed?"
              "cider?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([style] (lager? style {}))
  ([style opts]
   (let [style-type (impl/fetch-or-throw! style :type "Style :type is required to determine if it's for lagers")]
     (spoon.string/same-text? "lager" style-type opts))))



(defn ale?
  "A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for ales.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["lager?"
              "mead?"
              "wheat?"
              "mixed?"
              "cider?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([style] (ale? style {}))
  ([style opts]
   (let [style-type (impl/fetch-or-throw! style :type "Style :type is required to determine if it's for ales")]
     (spoon.string/same-text? "ale" style-type opts))))


(defn mead?
  "A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for meads.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["lager?"
              "ale?"
              "wheat?"
              "mixed?"
              "cider?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([style] (mead? style {}))
  ([style opts]
   (let [style-type (impl/fetch-or-throw! style :type "Style :type is required to determine if it's for meads")]
     (spoon.string/same-text? "mead" style-type opts))))


(defn wheat?
  "A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for wheat beers.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["lager?"
              "ale?"
              "mead?"
              "mixed?"
              "cider?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([style] (wheat? style {}))
  ([style opts]
   (let [style-type (impl/fetch-or-throw! style :type "Style :type is required to determine if it's for wheat beers")]
     (spoon.string/same-text? "wheat" style-type opts))))


(defn mixed?
  "A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for blended/mixed beers.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["lager?"
              "ale?"
              "mead?"
              "wheat?"
              "cider?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([style] (mixed? style {}))
  ([style opts]
   (let [style-type (impl/fetch-or-throw! style :type "Style :type is required to determine if it's for mixed styles or blends")]
     (spoon.string/same-text? "mixed" style-type opts))))


(defn cider?
  "A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for ciders.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["lager?"
              "ale?"
              "mead?"
              "wheat?"
              "mixed?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([style] (cider? style {}))
  ([style opts]
   (let [style-type (impl/fetch-or-throw! style :type "Style :type is required to determine if it's for ciders")]
     (spoon.string/same-text? "cider" style-type opts))))
