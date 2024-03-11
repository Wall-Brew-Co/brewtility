(ns brewtility.wrapping
  "Namespace for wrapping and unwrapping common-beer-format maps.

   Since the BeerXML format wraps attributes in tags indicating the type of ingreditent/process/etc.,
     the analogous common-beer-format maps are nested in a map with a single key.
   This namespace provides functions for wrapping and unwrapping these maps."
  {:added "1.4"})


;; Equipment

(defn wrap-equipment
  "Wrap an `equipment` map into an `equipment-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-equipment"
              "::common-beer-format.equipment/equipment"
              "::common-beer-format.equipment/equipment-wrapper"]}
  [equipment]
  {:equipment equipment})


(defn unwrap-equipment
  "Unwrap an `equipment-wrapper` map into an `equipment` map."
  {:added    "1.4"
   :see-also ["wrap-equipment"
              "::common-beer-format.equipment/equipment"
              "::common-beer-format.equipment/equipment-wrapper"]}
  [equipment-wrapper]
  (:equipment equipment-wrapper))


;; Fermentables

(defn wrap-fermentables
  "Wrap a `fermentables` collection into a `fermentables-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-fermentables"
              "::common-beer-format.fermentables/fermentables"
              "::common-beer-format.fermentables/fermentables-wrapper"]}
  [fermentables]
  {:fermentables fermentables})


(defn unwrap-fermentables
  "Unwrap a `fermentables-wrapper` map into a `fermentables` collection."
  {:added    "1.4"
   :see-also ["wrap-fermentables"
              "::common-beer-format.fermentables/fermentables"
              "::common-beer-format.fermentables/fermentables-wrapper"]}
  [fermentables-wrapper]
  (:fermentables fermentables-wrapper))


(defn wrap-fermentable
  "Wrap a `fermentable` map into a `fermentable-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-fermentable"
              "::common-beer-format.fermentables/fermentable"
              "::common-beer-format.fermentables/fermentable-wrapper"]}
  [fermentable]
  {:fermentable fermentable})


(defn unwrap-fermentable
  "Unwrap a `fermentable-wrapper` map into a `fermentable` map."
  {:added    "1.4"
   :see-also ["wrap-fermentable"
              "::common-beer-format.fermentables/fermentable"
              "::common-beer-format.fermentables/fermentable-wrapper"]}
  [fermentable-wrapper]
  (:fermentable fermentable-wrapper))


;; Hops

(defn wrap-hops
  "Wrap a `hops` collection into a `hops-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-hops"
              "::common-beer-format.hops/hops"
              "::common-beer-format.hops/hops-wrapper"]}
  [hops]
  {:hops hops})


(defn unwrap-hops
  "Unwrap a `hops-wrapper` map into a `hops` collection."
  {:added    "1.4"
   :see-also ["wrap-hops"
              "::common-beer-format.hops/hops"
              "::common-beer-format.hops/hops-wrapper"]}
  [hops-wrapper]
  (:hops hops-wrapper))


(defn wrap-hop
  "Wrap a `hop` map into a `hop-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-hop"
              "::common-beer-format.hops/hop"
              "::common-beer-format.hops/hop-wrapper"]}
  [hop]
  {:hop hop})


(defn unwrap-hop
  "Unwrap a `hop-wrapper` map into a `hop` map."
  {:added    "1.4"
   :see-also ["wrap-hop"
              "::common-beer-format.hops/hop"
              "::common-beer-format.hops/hop-wrapper"]}
  [hop-wrapper]
  (:hop hop-wrapper))


;; Mash

(defn wrap-mash-step
  "Wrap a `mash-step` map into a `mash-step-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-mash-step"
              "::common-beer-format.mash/mash-step"
              "::common-beer-format.mash/mash-step-wrapper"]}
  [mash-step]
  {:mash-step mash-step})


(defn unwrap-mash-step
  "Unwrap a `mash-step-wrapper` map into a `mash-step` map."
  {:added    "1.4"
   :see-also ["wrap-mash-step"
              "::common-beer-format.mash/mash-step"
              "::common-beer-format.mash/mash-step-wrapper"]}
  [mash-step-wrapper]
  (:mash-step mash-step-wrapper))


(defn wrap-mash
  "Wrap a `mash` map into a `mash-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-mash"
              "::common-beer-format.mash/mash"
              "::common-beer-format.mash/mash-wrapper"]}
  [mash]
  {:mash mash})


(defn unwrap-mash
  "Unwrap a `mash-wrapper` map into a `mash` map."
  {:added    "1.4"
   :see-also ["wrap-mash"
              "::common-beer-format.mash/mash"
              "::common-beer-format.mash/mash-wrapper"]}
  [mash-wrapper]
  (:mash mash-wrapper))


;; Misc

(defn wrap-misc
  "Wrap a `misc` map into a `misc-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-misc"
              "::common-beer-format.miscs/misc"
              "::common-beer-format.miscs/misc-wrapper"]}
  [misc]
  {:misc misc})


(defn unwrap-misc
  "Unwrap a `misc-wrapper` map into a `misc` map."
  {:added    "1.4"
   :see-also ["wrap-misc"
              "::common-beer-format.miscs/misc"
              "::common-beer-format.miscs/misc-wrapper"]}
  [misc-wrapper]
  (:misc misc-wrapper))


(defn wrap-miscs
  "Wrap a `miscs` collection into a `miscs-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-miscs"
              "::common-beer-format.miscs/miscs"
              "::common-beer-format.miscs/miscs-wrapper"]}
  [miscs]
  {:miscs miscs})


(defn unwrap-miscs
  "Unwrap a `miscs-wrapper` map into a `miscs` collection."
  {:added    "1.4"
   :see-also ["wrap-miscs"
              "::common-beer-format.miscs/miscs"
              "::common-beer-format.miscs/miscs-wrapper"]}
  [miscs-wrapper]
  (:miscs miscs-wrapper))


;; Recipes

(defn wrap-recipe
  "Wrap a `recipe` map into a `recipe-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-recipe"
              "::common-beer-format.recipes/recipe"
              "::common-beer-format.recipes/recipe-wrapper"]}
  [recipe]
  {:recipe recipe})


(defn unwrap-recipe
  "Unwrap a `recipe-wrapper` map into a `recipe` map."
  {:added    "1.4"
   :see-also ["wrap-recipe"
              "::common-beer-format.recipes/recipe"
              "::common-beer-format.recipes/recipe-wrapper"]}
  [recipe-wrapper]
  (:recipe recipe-wrapper))


(defn wrap-recipes
  "Wrap a `recipes` collection into a `recipes-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-recipes"
              "::common-beer-format.recipes/recipes"
              "::common-beer-format.recipes/recipes-wrapper"]}
  [recipes]
  {:recipes recipes})


(defn unwrap-recipes
  "Unwrap a `recipes-wrapper` map into a `recipes` collection."
  {:added    "1.4"
   :see-also ["wrap-recipes"
              "::common-beer-format.recipes/recipes"
              "::common-beer-format.recipes/recipes-wrapper"]}
  [recipes-wrapper]
  (:recipes recipes-wrapper))


;; Styles

(defn wrap-style
  "Wrap a `style` map into a `style-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-style"
              "::common-beer-format.styles/style"
              "::common-beer-format.styles/style-wrapper"]}
  [style]
  {:style style})


(defn unwrap-style
  "Unwrap a `style-wrapper` map into a `style` map."
  {:added    "1.4"
   :see-also ["wrap-style"
              "::common-beer-format.styles/style"
              "::common-beer-format.styles/style-wrapper"]}
  [style-wrapper]
  (:style style-wrapper))


(defn wrap-styles
  "Wrap a `styles` collection into a `styles-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-styles"
              "::common-beer-format.styles/styles"
              "::common-beer-format.styles/styles-wrapper"]}
  [styles]
  {:styles styles})


(defn unwrap-styles
  "Unwrap a `styles-wrapper` map into a `styles` collection."
  {:added    "1.4"
   :see-also ["wrap-styles"
              "::common-beer-format.styles/styles"
              "::common-beer-format.styles/styles-wrapper"]}
  [styles-wrapper]
  (:styles styles-wrapper))


;; Waters

(defn wrap-water
  "Wrap a `water` map into a `water-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-water"
              "::common-beer-format.waters/water"
              "::common-beer-format.waters/water-wrapper"]}
  [water]
  {:water water})


(defn unwrap-water
  "Unwrap a `water-wrapper` map into a `water` map."
  {:added    "1.4"
   :see-also ["wrap-water"
              "::common-beer-format.waters/water"
              "::common-beer-format.waters/water-wrapper"]}
  [water-wrapper]
  (:water water-wrapper))


(defn wrap-waters
  "Wrap a `waters` collection into a `waters-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-waters"
              "::common-beer-format.waters/waters"
              "::common-beer-format.waters/waters-wrapper"]}
  [waters]
  {:waters waters})


(defn unwrap-waters
  "Unwrap a `waters-wrapper` map into a `waters` collection."
  {:added    "1.4"
   :see-also ["wrap-waters"
              "::common-beer-format.waters/waters"
              "::common-beer-format.waters/waters-wrapper"]}
  [waters-wrapper]
  (:waters waters-wrapper))


;; Yeasts

(defn wrap-yeast
  "Wrap a `yeast` map into a `yeast-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-yeast"
              "::common-beer-format.yeasts/yeast"
              "::common-beer-format.yeasts/yeast-wrapper"]}
  [yeast]
  {:yeast yeast})


(defn unwrap-yeast
  "Unwrap a `yeast-wrapper` map into a `yeast` map."
  {:added    "1.4"
   :see-also ["wrap-yeast"
              "::common-beer-format.yeasts/yeast"
              "::common-beer-format.yeasts/yeast-wrapper"]}
  [yeast-wrapper]
  (:yeast yeast-wrapper))


(defn wrap-yeasts
  "Wrap a `yeasts` collection into a `yeasts-wrapper` map."
  {:added    "1.4"
   :see-also ["unwrap-yeasts"
              "::common-beer-format.yeasts/yeasts"
              "::common-beer-format.yeasts/yeasts-wrapper"]}
  [yeasts]
  {:yeasts yeasts})


(defn unwrap-yeasts
  "Unwrap a `yeasts-wrapper` map into a `yeasts` collection."
  {:added    "1.4"
   :see-also ["wrap-yeasts"
              "::common-beer-format.yeasts/yeasts"
              "::common-beer-format.yeasts/yeasts-wrapper"]}
  [yeasts-wrapper]
  (:yeasts yeasts-wrapper))
