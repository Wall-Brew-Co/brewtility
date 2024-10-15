(ns brewtility.predicates.recipes
  "Predicate functions for [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) maps"
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


(defn forced-carbonation?
  "A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was forcefully carbonated.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :forced-carbonation field is not present, this function will explicitly return false."
  {:added    "1.5"
   :arglists '([recipe] [recipe _options])}
  ([recipe] (forced-carbonation? recipe {}))
  ;; Added to match the arity of the other predicate functions

  ([recipe _options]
   (if (contains? recipe :forced-carbonation)
     (:forced-carbonation recipe)
     false)))


(defn extract?
  "A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was made with extract only.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([recipe] [recipe {:keys [uppercase?]}])
   :see-also ["all-grain?"
              "partial-mash?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([recipe] (extract? recipe {}))
  ([recipe options]
   (let [recipe-type (impl/fetch-or-throw! recipe :type "Recipe :type is required to determine if it's based on malt extract")]
     (spoon.string/same-text? "extract" recipe-type options))))


(defn partial-mash?
  "A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was partially mashed.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([recipe] [recipe {:keys [uppercase?]}])
   :see-also ["extract?"
              "all-grain?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([recipe] (partial-mash? recipe {}))
  ([recipe options]
   (let [recipe-type (impl/fetch-or-throw! recipe :type "Recipe :type is required to determine if it was partially mashed")]
     (spoon.string/same-text? "partial mash" recipe-type options))))


(defn all-grain?
  "A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was made entirely of grain.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([recipe] [recipe {:keys [uppercase?]}])
   :see-also ["extract?"
              "partial-mash?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([recipe] (all-grain? recipe {}))
  ([recipe options]
   (let [recipe-type (impl/fetch-or-throw! recipe :type "Recipe :type is required to determine if it was brewed with grain only")]
     (spoon.string/same-text? "all grain" recipe-type options))))


(defn rager?
  "A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the Rager method.
   This method was originally developed by Jackie Rager, and is most accurate for partial mash recipes.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:ibu-method` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([recipe] [recipe {:keys [uppercase?]}])
   :see-also ["tinseth?"
              "garetz?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([recipe] (rager? recipe {}))
  ([recipe options]
   (let [recipe-ibu-method (impl/fetch-or-throw! recipe :ibu-method "Recipe :ibu-method is required to determine if IBUs were calculated with the Rager method")]
     (spoon.string/same-text? "rager" recipe-ibu-method options))))


(defn tinseth?
  "A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the tinseth method.
   This method was originally developed by Glenn Tinseth, and is most accurate for all grain recipes.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:ibu-method` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([recipe] [recipe {:keys [uppercase?]}])
   :see-also ["rager?"
              "garetz?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([recipe] (tinseth? recipe {}))
  ([recipe options]
   (let [recipe-ibu-method (impl/fetch-or-throw! recipe :ibu-method "Recipe :ibu-method is required to determine if IBUs were calculated with the Tinseth method")]
     (spoon.string/same-text? "tinseth" recipe-ibu-method options))))


(defn garetz?
  "A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the garetz method.
   This method was originally developed by Mark Garetz, and is most accurate for High IBU recipes.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:ibu-method` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :arglists '([recipe] [recipe {:keys [uppercase?]}])
   :see-also ["rager?"
              "tinseth?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([recipe] (garetz? recipe {}))
  ([recipe options]
   (let [recipe-ibu-method (impl/fetch-or-throw! recipe :ibu-method "Recipe :ibu-method is required to determine if IBUs were calculated with the Garetz method")]
     (spoon.string/same-text? "garetz" recipe-ibu-method options))))
