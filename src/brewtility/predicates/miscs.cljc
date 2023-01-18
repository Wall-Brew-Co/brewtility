(ns brewtility.predicates.miscs
  "Predicate functions for [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) maps"
  {:added    "1.5"
   :see-also ["brewtility.predicates.equipment"
              "brewtility.predicates.fermentables"
              "brewtility.predicates.hops"
              "brewtility.predicates.mash"
              "brewtility.predicates.recipes"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]}
  (:require [brewtility.predicates.impl :as impl]
            [com.wallbrew.spoon.string :as spoon.string]))


(defn spice?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a spice.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["fining?"
              "water-agent?"
              "herb?"
              "flavor?"
              "other?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (spice? misc {}))
  ([misc opts]
   (let [misc-type (impl/fetch-or-throw! misc :type "Misc :type is required to determine if it's a spice")]
     (spoon.string/same-text? "spice" misc-type opts))))


(defn fining?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is used to remove yeast and protein haze.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["spice?"
              "water-agent?"
              "herb?"
              "flavor?"
              "other?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (fining? misc {}))
  ([misc opts]
   (let [misc-type (impl/fetch-or-throw! misc :type "Misc :type is required to determine if it's used for fining")]
     (spoon.string/same-text? "fining" misc-type opts))))


(defn water-agent?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a water agent.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["spice?"
              "other?"
              "fining?"
              "herb?"
              "flavor?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (water-agent? misc {}))
  ([misc opts]
   (let [misc-type (impl/fetch-or-throw! misc :type "Misc :type is required to determine if it a water agent")]
     (spoon.string/same-text? "water agent" misc-type opts))))


(defn herb?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a herb.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["spice?"
              "water-agent?"
              "fining?"
              "flavor?"
              "other?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (herb? misc {}))
  ([misc opts]
   (let [misc-type (impl/fetch-or-throw! misc :type "Misc :type is required to determine if it an herb")]
     (spoon.string/same-text? "herb" misc-type opts))))


(defn flavor?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is added for flavor.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["spice?"
              "water-agent?"
              "fining?"
              "herb?"
              "other?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (flavor? misc {}))
  ([misc opts]
   (let [misc-type (impl/fetch-or-throw! misc :type "Misc :type is required to determine if it was added for flavor")]
     (spoon.string/same-text? "flavor" misc-type opts))))


(defn other?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is an unspecified type.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["spice?"
              "water-agent?"
              "fining?"
              "herb?"
              "flavor?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (other? misc {}))
  ([misc opts]
   (let [misc-type (impl/fetch-or-throw! misc :type "Misc :type is required to determine if it doesn't fit any other category")]
     (spoon.string/same-text? "other" misc-type opts))))


(defn boil?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the boil.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["mash?"
              "primary?"
              "secondary?"
              "bottling?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (boil? misc {}))
  ([misc opts]
   (let [misc-use (impl/fetch-or-throw! misc :use "Misc :use is required to determine if it was added during the boil")]
     (spoon.string/same-text? "boil" misc-use opts))))


(defn mash?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the mash.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "primary?"
              "secondary?"
              "bottling?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (mash? misc {}))
  ([misc opts]
   (let [misc-use (impl/fetch-or-throw! misc :use "Misc :use is required to determine if it was added during the mash")]
     (spoon.string/same-text? "mash" misc-use opts))))


(defn primary?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the primary fermentation.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "mash?"
              "secondary?"
              "bottling?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (primary? misc {}))
  ([misc opts]
   (let [misc-use (impl/fetch-or-throw! misc :use "Misc :use is required to determine if it was added during primary fermentation")]
     (spoon.string/same-text? "primary" misc-use opts))))


(defn secondary?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the secondary fermentation.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "mash?"
              "primary?"
              "bottling?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (secondary? misc {}))
  ([misc opts]
   (let [misc-use (impl/fetch-or-throw! misc :use "Misc :use is required to determine if it was added during secondary fermentation")]
     (spoon.string/same-text? "secondary" misc-use opts))))


(defn bottling?
  "A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during bottle conditioning.
   An option map can be passed to this function as an optional second parameter.

   Supported keys are:
     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false."
  {:added    "1.5"
   :see-also ["boil?"
              "mash?"
              "primary?"
              "secondary?"
              "com.wallbrew.spoon.string/same-text?"]}
  ([misc] (bottling? misc {}))
  ([misc opts]
   (let [misc-use (impl/fetch-or-throw! misc :use "Misc :use is required to determine if it was added during bottle conditioning")]
     (spoon.string/same-text? "bottling" misc-use opts))))
