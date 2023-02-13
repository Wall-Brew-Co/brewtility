(ns brewtility.predicates.waters
  "Predicate functions for [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) maps."
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.fermentables"
              "brewtility.predicates.hops"
              "brewtility.predicates.mash"
              "brewtility.predicates.miscs"
              "brewtility.predicates.styles"
              "brewtility.predicates.recipes"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]))


(defn acidic?
  "A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is acidic."
  {:added    "1.5"
   :see-also ["alkaline?"
              "neutral?"]}
  [water]
  (let [ph (impl/fetch-or-throw! water :ph "Water `:ph` is required to determine acidity.")]
    (> 7 ph)))


(defn alkaline?
  "A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is alkaline."
  {:added    "1.5"
   :see-also ["acidic?"
              "neutral?"]}
  [water]
  (let [ph (impl/fetch-or-throw! water :ph "Water `:ph` is required to determine alkalinity.")]
    (< 7 ph)))


(defn neutral?
  "A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is alkaline."
  {:added    "1.5"
   :see-also ["acidic?"
              "alkaline?"]}
  [water]
  (let [ph (impl/fetch-or-throw! water :ph "Water `:ph` is required to determine ph neutrality.")]
    (== 7 ph)))
