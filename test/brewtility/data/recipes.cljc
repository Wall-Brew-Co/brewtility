(ns brewtility.data.recipes
  "Namespace for static and generative test data for `common-beer-format.recipes/*` specs."
  (:require [brewtility.data.equipment :as equipment]
            [brewtility.data.fermentables :as fermentables]
            [brewtility.data.hops :as hops]
            [brewtility.data.mash :as mash]
            [brewtility.data.miscs :as miscs]
            [brewtility.data.styles :as styles]
            [brewtility.data.waters :as waters]
            [brewtility.data.yeasts :as yeasts]
            [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [com.wallbrew.spoon.string :as spoon.str]
            [common-beer-format.recipes :as recipes.format]))


(defn random-ibu-method
  "Return a random valid hop type."
  {:added "2.0"
   :no-doc true}
  []
  (-> recipes.format/ibu-method-types
      vec
      rand-nth
      spoon.str/->sporadic-case))


(deftest ibu-method-test
  (testing "Random IBU method should be valid"
    (is (spoon.spec/test-valid? ::recipes.format/ibu-method (random-ibu-method)))))


(def sample-recipe
  "A hard-coded sample recipe for static unit tests"
  (merge {:age                 24.0
          :age-temp            17.0
          :batch-size          18.93
          :boil-size           20.82
          :boil-time           60.0
          :brewer              "Brad Smith"
          :carbonation         2.1
          :carbonation-used    "Kegged"
          :date                "3 Jan 04"
          :efficiency          72.0
          :fermentation-stages 2
          :fg                  1.012
          :name                "Dry Stout"
          :og                  1.036
          :rating              "41"
          :taste-notes         "Nice dry Irish stout with a warm body but low starting gravity much like the famous drafts."
          :type                "All Grain"
          :version             1}
         equipment/sample-equipment-wrapper
         fermentables/sample-fermentables-wrapper
         hops/sample-hops-wrapper
         mash/sample-mash-wrapper
         miscs/sample-miscs-wrapper
         styles/sample-style-wrapper
         waters/sample-waters-wrapper
         yeasts/sample-yeasts-wrapper))


(def sample-recipe-wrapper
  "A hard-coded sample recipe-wrapper for static unit tests"
  {:recipe sample-recipe})


(def sample-recipes
  "A hard-coded sample recipes for static unit tests"
  [sample-recipe-wrapper])


(def sample-recipes-wrapper
  "A hard-coded sample recipes-wrapper for static unit tests"
  {:recipes sample-recipes})


(defn generate-recipe
  "Generate a random recipe object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::recipes.format/recipe)))


(defn generate-recipe-wrapper
  "Generate a random recipe-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::recipes.format/recipe-wrapper)))


(defn generate-recipes
  "Generate a random recipes object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::recipes.format/recipes)))


(defn generate-recipes-wrapper
  "Generate a random recipes-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::recipes.format/recipes-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::recipes.format/recipe sample-recipe)
        "Static test data should conform to common-beer-format.recipe/recipe")
    (is (spoon.spec/test-valid? ::recipes.format/recipe (assoc sample-recipe :ibu-method (random-ibu-method)))
        "Static test data should conform to common-beer-format.recipe/recipe, even with a random IBU method")
    (is (spoon.spec/test-valid? ::recipes.format/recipe-wrapper sample-recipe-wrapper)
        "Static test data should conform to common-beer-format.recipe/recipe-wrapper")
    (is (spoon.spec/test-valid? ::recipes.format/recipes sample-recipes)
        "Static test data should conform to common-beer-format.recipe/recipes")
    (is (spoon.spec/test-valid? ::recipes.format/recipes-wrapper sample-recipes-wrapper)
        "Static test data should conform to common-beer-format.recipe/recipes-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::recipes.format/recipe (generate-recipe))
        "Generative test data should conform to common-beer-format.recipe/recipe")
    (is (spoon.spec/test-valid? ::recipes.format/recipe (assoc (generate-recipe) :ibu-method (random-ibu-method)))
        "Generative test data should conform to common-beer-format.recipe/recipe, even with a random IBU method")
    (is (spoon.spec/test-valid? ::recipes.format/recipe-wrapper (generate-recipe-wrapper))
        "Generative test data should conform to common-beer-format.recipe/recipe-wrapper")
    (is (spoon.spec/test-valid? ::recipes.format/recipes (generate-recipes))
        "Generative test data should conform to common-beer-format.recipe/recipes")
    (is (spoon.spec/test-valid? ::recipes.format/recipes-wrapper (generate-recipes-wrapper))
        "Generative test data should conform to common-beer-format.recipe/recipes-wrapper")))
