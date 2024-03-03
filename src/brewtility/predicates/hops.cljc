(ns brewtility.predicates.hops
  "Predicate functions for [hops](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) maps."
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.fermentables"
              "brewtility.predicates.mash"
              "brewtility.predicates.miscs"
              "brewtility.predicates.recipes"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]
            [com.wallbrew.spoon.string :as spoon.string]))


;;
;; :use functions
;;


(defn boil?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was used during the boil.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["dry-hop?"
              "mash?"
              "first-wort?"
              "aroma?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (boil? hop {}))
  ([hop opts]
   (let [hop-use (impl/fetch-or-throw! hop :use "Hop :use is required to determine if it is added during the boil.")]
     (spoon.string/same-text? "boil" hop-use opts))))


(defn dry-hop?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was used as a dry-hop addition in secondary fermentation.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "mash?"
              "first-wort?"
              "aroma?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (dry-hop? hop {}))
  ([hop opts]
   (let [hop-use (impl/fetch-or-throw! hop :use "Hop :use is required to determine if it is added as a dry hop.")]
     (spoon.string/same-text? "dry hop" hop-use opts))))


(defn mash?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added during the mash and before the boil.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "dry-hop?"
              "first-wort?"
              "aroma?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (mash? hop {}))
  ([hop opts]
   (let [hop-use (impl/fetch-or-throw! hop :use "Hop :use is required to determine if it is added in the mash.")]
     (spoon.string/same-text? "mash" hop-use opts))))


(defn first-wort?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added to the wort at the first possible moment.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "dry-hop?"
              "mash?"
              "aroma?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (first-wort? hop {}))
  ([hop opts]
   (let [hop-use (impl/fetch-or-throw! hop :use "Hop :use is required to determine if it is added to the wort")]
     (spoon.string/same-text? "first wort" hop-use opts))))


(defn aroma-use?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added at the end of the boil for aroma.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "dry-hop?"
              "mash?"
              "first-wort?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (aroma-use? hop {}))
  ([hop opts]
   (let [hop-use (impl/fetch-or-throw! hop :use "Hop :use is required to determine if it is added at the end of the boil.")]
     (spoon.string/same-text? "aroma" hop-use opts))))


;;
;; :type functions
;;

(defn aroma-type?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for aroma.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["bittering?"
              "both?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (aroma-type? hop {}))
  ([hop opts]
   (let [hop-type (impl/fetch-or-throw! hop :type "Hop :type is required to determine if it is added for aroma.")]
     (spoon.string/same-text? "aroma" hop-type opts))))


(defn bittering?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for bittering.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["aroma-type?"
              "both?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (bittering? hop {}))
  ([hop opts]
   (let [hop-type (impl/fetch-or-throw! hop :type "Hop :type is required to determine if it is added for bitterness.")]
     (spoon.string/same-text? "bittering" hop-type opts))))


(defn both?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for both bittering and aroma.
   An option map can be passed to this function as an optional second parameter.
   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["aroma-type?"
              "bittering?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (both? hop {}))
  ([hop opts]
   (let [hop-type (impl/fetch-or-throw! hop :type "Hop :type is required to determine if it is added for both bitterness and aroma.")]
     (spoon.string/same-text? "both" hop-type opts))))


;;
;; :form functions
;;


(defn pellet?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a compressed pellet.
   An option map can be passed to this function as an optional second parameter.
   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:form` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["plug?"
              "leaf?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (pellet? hop {}))
  ([hop opts]
   (let [hop-form (impl/fetch-or-throw! hop :form "Hop :form is required to determine if it is a compressed pellet.")]
     (spoon.string/same-text? "pellet" hop-form opts))))


(defn plug?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a compressed whole-hop plug.
   An option map can be passed to this function as an optional second parameter.
   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:form` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["pellet?"
              "leaf?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (plug? hop {}))
  ([hop opts]
   (let [hop-form (impl/fetch-or-throw! hop :form "Hop :form is required to determine if it is a compressed whole hop.")]
     (spoon.string/same-text? "plug" hop-form opts))))


(defn leaf?
  "A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a whole hop cone.
   An option map can be passed to this function as an optional second parameter.
   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:form` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["pellet?"
              "plug?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([hop] (leaf? hop {}))
  ([hop opts]
   (let [hop-form (impl/fetch-or-throw! hop :form "Hop :form is required to determine if it is a whole hop cone.")]
     (spoon.string/same-text? "leaf" hop-form opts))))
