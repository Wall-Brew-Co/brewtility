(ns brewtility.predicates.equipment
  "Predicate functions for [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) maps."
  {:added    "1.5"
   :see-also ["brewtility.predicates.fermentables"
              "brewtility.predicates.hops"
              "brewtility.predicates.mash"
              "brewtility.predicates.miscs"
              "brewtility.predicates.recipes"
              "brewtility.predicates.styles"
              "brewtility.predicates.waters"
              "brewtility.predicates.yeasts"]})


(defn calculated-boil-volume?
  "A predicate function to determine if an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) had its boil volume calculated.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :calc-boil-volume field is not present, this function will explicitly return false."
  {:added "1.5"}
  ([equipment] (calculated-boil-volume? equipment {}))
; Added to match the arity of the other predicate functions

  ([equipment _opts]
   (if (contains? equipment :calc-boil-volume)
     (:calc-boil-volume equipment)
     false)))
