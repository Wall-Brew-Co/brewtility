(ns brewtility.predicates.yeasts
  "Predicate functions for [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) maps"
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.fermentables"
              "brewtility.predicates.hops"
              "brewtility.predicates.mash"
              "brewtility.predicates.miscs"
              "brewtility.predicates.recipes"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]
            [com.wallbrew.spoon.string :as spoon.string]))


;;
;; :type functions
;;

(defn ale?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for ale.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["lager?"
              "wheat?"
              "wine?"
              "champagne?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (ale? yeast {}))
  ([yeast opts]
   (let [yeast-type (impl/fetch-or-throw! yeast :type "Yeast :type is required to determine if it's for ales")]
     (spoon.string/same-text? "ale" yeast-type opts))))


(defn lager?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for lager.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["ale?"
              "wheat?"
              "wine?"
              "champagne?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (lager? yeast {}))
  ([yeast opts]
   (let [yeast-type (impl/fetch-or-throw! yeast :type "Yeast :type is required to determine if it's for lagers")]
     (spoon.string/same-text? "lager" yeast-type opts))))


(defn wheat?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for wheat beers.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["ale?"
              "lager?"
              "wine?"
              "champagne?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (wheat? yeast {}))
  ([yeast opts]
   (let [yeast-type (impl/fetch-or-throw! yeast :type "Yeast :type is required to determine if it's for wheat beers")]
     (spoon.string/same-text? "wheat" yeast-type opts))))


(defn wine?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a common wine strain.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["ale?"
              "lager?"
              "wheat?"
              "champagne?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (wine? yeast {}))
  ([yeast opts]
   (let [yeast-type (impl/fetch-or-throw! yeast :type "Yeast :type is required to determine if it's a strain typical to wines")]
     (spoon.string/same-text? "wine" yeast-type opts))))


(defn champagne?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a common champagne strain.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["ale?"
              "lager?"
              "wheat?"
              "wine?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (champagne? yeast {}))
  ([yeast opts]
   (let [yeast-type (impl/fetch-or-throw! yeast :type "Yeast :type is required to determine if it's a strain typical to champagnes")]
     (spoon.string/same-text? "champagne" yeast-type opts))))


;;
;; :form functions
;;

(defn liquid?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in liquid form.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["dry?"
              "slant?"
              "culture?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (liquid? yeast {}))
  ([yeast opts]
   (let [yeast-form (impl/fetch-or-throw! yeast :form "Yeast :form is required to determine if it is in liquid form")]
     (spoon.string/same-text? "liquid" yeast-form opts))))


(defn dry?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in dry form.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["liquid?"
              "slant?"
              "culture?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (dry? yeast {}))
  ([yeast opts]
   (let [yeast-form (impl/fetch-or-throw! yeast :form "Yeast :form is required to determine if it is in dry form")]
     (spoon.string/same-text? "dry" yeast-form opts))))


(defn slant?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in slant form.
   Typically, this is a solid growth of yeast cells in a test tube.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["liquid?"
              "dry?"
              "culture?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (slant? yeast {}))
  ([yeast opts]
   (let [yeast-form (impl/fetch-or-throw! yeast :form "Yeast :form is required to determine if it is in slant form")]
     (spoon.string/same-text? "slant" yeast-form opts))))


(defn culture?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a living culture.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["liquid?"
              "dry?"
              "slant?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (culture? yeast {}))
  ([yeast opts]
   (let [yeast-form (impl/fetch-or-throw! yeast :form "Yeast :form is required to determine if it is in culture form")]
     (spoon.string/same-text? "culture" yeast-form opts))))


;;
;; :flocculation functions
;;

(defn low-flocculation?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a low amount of floc.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["medium-flocculation?"
              "high-flocculation?"
              "very-high-flocculation?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (low-flocculation? yeast {}))
  ([yeast opts]
   (let [yeast-flocculation (impl/fetch-or-throw! yeast :flocculation "Yeast :flocculation is required to determine if generates a low amount of floc")]
     (spoon.string/same-text? "low" yeast-flocculation opts))))


(defn medium-flocculation?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a medium amount of floc.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["low-flocculation?"
              "high-flocculation?"
              "very-high-flocculation?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (medium-flocculation? yeast {}))
  ([yeast opts]
   (let [yeast-flocculation (impl/fetch-or-throw! yeast :flocculation "Yeast :flocculation is required to determine if generates a medium amount of floc")]
     (spoon.string/same-text? "medium" yeast-flocculation opts))))


(defn high-flocculation?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a high amount of floc.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["low-flocculation?"
              "high-flocculation?"
              "very-high-flocculation?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (high-flocculation? yeast {}))
  ([yeast opts]
   (let [yeast-flocculation (impl/fetch-or-throw! yeast :flocculation "Yeast :flocculation is required to determine if generates a high amount of floc")]
     (spoon.string/same-text? "high" yeast-flocculation opts))))


(defn very-high-flocculation?
  "A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a very high amount of floc.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["low-flocculation?"
              "high-flocculation?"
              "medium-flocculation?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([yeast] (very-high-flocculation? yeast {}))
  ([yeast opts]
   (let [yeast-flocculation (impl/fetch-or-throw! yeast :flocculation "Yeast :flocculation is required to determine if generates a very high amount of floc")]
     (spoon.string/same-text? "very high" yeast-flocculation opts))))
