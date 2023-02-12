(ns brewtility.wrapping-test
  (:require [brewtility.data.equipment :as equipment.data]
            [brewtility.data.fermentables :as fermentables.data]
            [brewtility.data.hops :as hops.data]
            [brewtility.data.mash :as mash.data]
            [brewtility.data.miscs :as miscs.data]
            [brewtility.data.recipes :as recipes.data]
            [brewtility.data.styles :as styles.data]
            [brewtility.data.waters :as waters.data]
            [brewtility.data.yeasts :as yeasts.data]
            [brewtility.wrapping :as sut]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.equipment :as equipment.format]
            [common-beer-format.fermentables :as fermentables.format]
            [common-beer-format.hops :as hops.format]
            [common-beer-format.mash :as mash.format]
            [common-beer-format.miscs :as miscs.format]
            [common-beer-format.recipes :as recipes.format]
            [common-beer-format.styles :as styles.format]
            [common-beer-format.waters :as waters.format]
            [common-beer-format.yeasts :as yeasts.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest equipment-tests
  (testing "Wrapped equipment is a valid equipment-wrapper"
    (is (spoon.spec/test-valid? ::equipment.format/equipment-wrapper
                                (sut/wrap-equipment equipment.data/sample-equipment)))
    (is (spoon.spec/test-valid? ::equipment.format/equipment-wrapper
                                (sut/wrap-equipment (equipment.data/generate-equipment)))))
  (testing "Unwrapped equipment is a valid equipment"
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (sut/unwrap-equipment equipment.data/sample-equipment-wrapper)))
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (sut/unwrap-equipment (equipment.data/generate-equipment-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= equipment.data/sample-equipment
           (-> equipment.data/sample-equipment
               sut/wrap-equipment
               sut/unwrap-equipment)))
    (is (= equipment.data/sample-equipment-wrapper
           (-> equipment.data/sample-equipment-wrapper
               sut/unwrap-equipment
               sut/wrap-equipment)))))


(deftest fermentable-tests
  (testing "Wrapped fermentable is a valid fermentable-wrapper"
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable-wrapper
                                (sut/wrap-fermentable fermentables.data/sample-fermentable)))
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable-wrapper
                                (sut/wrap-fermentable (fermentables.data/generate-fermentable)))))
  (testing "Unwrapped fermentable is a valid fermentable"
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable
                                (sut/unwrap-fermentable fermentables.data/sample-fermentable-wrapper)))
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable
                                (sut/unwrap-fermentable (fermentables.data/generate-fermentable-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= fermentables.data/sample-fermentable
           (-> fermentables.data/sample-fermentable
               sut/wrap-fermentable
               sut/unwrap-fermentable)))
    (is (= fermentables.data/sample-fermentable-wrapper
           (-> fermentables.data/sample-fermentable-wrapper
               sut/unwrap-fermentable
               sut/wrap-fermentable)))))


(deftest fermentables-tests
  (testing "Wrapped fermentables is a valid fermentables-wrapper"
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables-wrapper
                                (sut/wrap-fermentables fermentables.data/sample-fermentables)))
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables-wrapper
                                (sut/wrap-fermentables (fermentables.data/generate-fermentables)))))
  (testing "Unwrapped fermentable is a valid fermentable"
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables
                                (sut/unwrap-fermentables fermentables.data/sample-fermentables-wrapper)))
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables
                                (sut/unwrap-fermentables (fermentables.data/generate-fermentables-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= fermentables.data/sample-fermentables
           (-> fermentables.data/sample-fermentables
               sut/wrap-fermentables
               sut/unwrap-fermentables)))
    (is (= fermentables.data/sample-fermentables-wrapper
           (-> fermentables.data/sample-fermentables-wrapper
               sut/unwrap-fermentables
               sut/wrap-fermentables)))))


(deftest hop-tests
  (testing "Wrapped hop is a valid hop-wrapper"
    (is (spoon.spec/test-valid? ::hops.format/hop-wrapper
                                (sut/wrap-hop hops.data/sample-hop)))
    (is (spoon.spec/test-valid? ::hops.format/hop-wrapper
                                (sut/wrap-hop (hops.data/generate-hop)))))
  (testing "Unwrapped hop is a valid hop"
    (is (spoon.spec/test-valid? ::hops.format/hop
                                (sut/unwrap-hop hops.data/sample-hop-wrapper)))
    (is (spoon.spec/test-valid? ::hops.format/hop
                                (sut/unwrap-hop (hops.data/generate-hop-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= hops.data/sample-hop
           (-> hops.data/sample-hop
               sut/wrap-hop
               sut/unwrap-hop)))
    (is (= hops.data/sample-hop-wrapper
           (-> hops.data/sample-hop-wrapper
               sut/unwrap-hop
               sut/wrap-hop)))))


(deftest hops-tests
  (testing "Wrapped hops is a valid hops-wrapper"
    (is (spoon.spec/test-valid? ::hops.format/hops-wrapper
                                (sut/wrap-hops hops.data/sample-hops)))
    (is (spoon.spec/test-valid? ::hops.format/hops-wrapper
                                (sut/wrap-hops (hops.data/generate-hops)))))
  (testing "Unwrapped hop is a valid hop"
    (is (spoon.spec/test-valid? ::hops.format/hops
                                (sut/unwrap-hops hops.data/sample-hops-wrapper)))
    (is (spoon.spec/test-valid? ::hops.format/hops
                                (sut/unwrap-hops (hops.data/generate-hops-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= hops.data/sample-hops
           (-> hops.data/sample-hops
               sut/wrap-hops
               sut/unwrap-hops)))
    (is (= hops.data/sample-hops-wrapper
           (-> hops.data/sample-hops-wrapper
               sut/unwrap-hops
               sut/wrap-hops)))))


(deftest mash-step-tests
  (testing "Wrapped mash-step is a valid mash-step-wrapper"
    (is (spoon.spec/test-valid? ::mash.format/mash-step-wrapper
                                (sut/wrap-mash-step mash.data/sample-mash-step)))
    (is (spoon.spec/test-valid? ::mash.format/mash-step-wrapper
                                (sut/wrap-mash-step (mash.data/generate-mash-step)))))
  (testing "Unwrapped mash-step is a valid mash-step"
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (sut/unwrap-mash-step mash.data/sample-mash-step-wrapper)))
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (sut/unwrap-mash-step (mash.data/generate-mash-step-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= mash.data/sample-mash-step
           (-> mash.data/sample-mash-step
               sut/wrap-mash-step
               sut/unwrap-mash-step)))
    (is (= mash.data/sample-mash-step-wrapper
           (-> mash.data/sample-mash-step-wrapper
               sut/unwrap-mash-step
               sut/wrap-mash-step)))))


(deftest mash-tests
  (testing "Wrapped mash is a valid mash-wrapper"
    (is (spoon.spec/test-valid? ::mash.format/mash-wrapper
                                (sut/wrap-mash mash.data/sample-mash)))
    (is (spoon.spec/test-valid? ::mash.format/mash-wrapper
                                (sut/wrap-mash (mash.data/generate-mash)))))
  (testing "Unwrapped mash is a valid mash"
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (sut/unwrap-mash mash.data/sample-mash-wrapper)))
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (sut/unwrap-mash (mash.data/generate-mash-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= mash.data/sample-mash
           (-> mash.data/sample-mash
               sut/wrap-mash
               sut/unwrap-mash)))
    (is (= mash.data/sample-mash-wrapper
           (-> mash.data/sample-mash-wrapper
               sut/unwrap-mash
               sut/wrap-mash)))))


(deftest misc-tests
  (testing "Wrapped misc is a valid misc-wrapper"
    (is (spoon.spec/test-valid? ::miscs.format/misc-wrapper
                                (sut/wrap-misc miscs.data/sample-misc)))
    (is (spoon.spec/test-valid? ::miscs.format/misc-wrapper
                                (sut/wrap-misc (miscs.data/generate-misc)))))
  (testing "Unwrapped misc is a valid misc"
    (is (spoon.spec/test-valid? ::miscs.format/misc
                                (sut/unwrap-misc miscs.data/sample-misc-wrapper)))
    (is (spoon.spec/test-valid? ::miscs.format/misc
                                (sut/unwrap-misc (miscs.data/generate-misc-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= miscs.data/sample-misc
           (-> miscs.data/sample-misc
               sut/wrap-misc
               sut/unwrap-misc)))
    (is (= miscs.data/sample-misc-wrapper
           (-> miscs.data/sample-misc-wrapper
               sut/unwrap-misc
               sut/wrap-misc)))))


(deftest miscs-tests
  (testing "Wrapped miscs is a valid miscs-wrapper"
    (is (spoon.spec/test-valid? ::miscs.format/miscs-wrapper
                                (sut/wrap-miscs miscs.data/sample-miscs)))
    (is (spoon.spec/test-valid? ::miscs.format/miscs-wrapper
                                (sut/wrap-miscs (miscs.data/generate-miscs)))))
  (testing "Unwrapped misc is a valid misc"
    (is (spoon.spec/test-valid? ::miscs.format/miscs
                                (sut/unwrap-miscs miscs.data/sample-miscs-wrapper)))
    (is (spoon.spec/test-valid? ::miscs.format/miscs
                                (sut/unwrap-miscs (miscs.data/generate-miscs-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= miscs.data/sample-miscs
           (-> miscs.data/sample-miscs
               sut/wrap-miscs
               sut/unwrap-miscs)))
    (is (= miscs.data/sample-miscs-wrapper
           (-> miscs.data/sample-miscs-wrapper
               sut/unwrap-miscs
               sut/wrap-miscs)))))


(deftest recipe-tests
  (testing "Wrapped recipe is a valid recipe-wrapper"
    (is (spoon.spec/test-valid? ::recipes.format/recipe-wrapper
                                (sut/wrap-recipe recipes.data/sample-recipe)))
    (is (spoon.spec/test-valid? ::recipes.format/recipe-wrapper
                                (sut/wrap-recipe (recipes.data/generate-recipe)))))
  (testing "Unwrapped recipe is a valid recipe"
    (is (spoon.spec/test-valid? ::recipes.format/recipe
                                (sut/unwrap-recipe recipes.data/sample-recipe-wrapper)))
    (is (spoon.spec/test-valid? ::recipes.format/recipe
                                (sut/unwrap-recipe (recipes.data/generate-recipe-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= recipes.data/sample-recipe
           (-> recipes.data/sample-recipe
               sut/wrap-recipe
               sut/unwrap-recipe)))
    (is (= recipes.data/sample-recipe-wrapper
           (-> recipes.data/sample-recipe-wrapper
               sut/unwrap-recipe
               sut/wrap-recipe)))))


(deftest recipes-tests
  (testing "Wrapped recipes is a valid recipes-wrapper"
    (is (spoon.spec/test-valid? ::recipes.format/recipes-wrapper
                                (sut/wrap-recipes recipes.data/sample-recipes)))
    (is (spoon.spec/test-valid? ::recipes.format/recipes-wrapper
                                (sut/wrap-recipes (recipes.data/generate-recipes)))))
  (testing "Unwrapped recipe is a valid recipe"
    (is (spoon.spec/test-valid? ::recipes.format/recipes
                                (sut/unwrap-recipes recipes.data/sample-recipes-wrapper)))
    (is (spoon.spec/test-valid? ::recipes.format/recipes
                                (sut/unwrap-recipes (recipes.data/generate-recipes-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= recipes.data/sample-recipes
           (-> recipes.data/sample-recipes
               sut/wrap-recipes
               sut/unwrap-recipes)))
    (is (= recipes.data/sample-recipes-wrapper
           (-> recipes.data/sample-recipes-wrapper
               sut/unwrap-recipes
               sut/wrap-recipes)))))


(deftest style-tests
  (testing "Wrapped style is a valid style-wrapper"
    (is (spoon.spec/test-valid? ::styles.format/style-wrapper
                                (sut/wrap-style styles.data/sample-style)))
    (is (spoon.spec/test-valid? ::styles.format/style-wrapper
                                (sut/wrap-style (styles.data/generate-style)))))
  (testing "Unwrapped style is a valid style"
    (is (spoon.spec/test-valid? ::styles.format/style
                                (sut/unwrap-style styles.data/sample-style-wrapper)))
    (is (spoon.spec/test-valid? ::styles.format/style
                                (sut/unwrap-style (styles.data/generate-style-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= styles.data/sample-style
           (-> styles.data/sample-style
               sut/wrap-style
               sut/unwrap-style)))
    (is (= styles.data/sample-style-wrapper
           (-> styles.data/sample-style-wrapper
               sut/unwrap-style
               sut/wrap-style)))))


(deftest styles-tests
  (testing "Wrapped styles is a valid styles-wrapper"
    (is (spoon.spec/test-valid? ::styles.format/styles-wrapper
                                (sut/wrap-styles styles.data/sample-styles)))
    (is (spoon.spec/test-valid? ::styles.format/styles-wrapper
                                (sut/wrap-styles (styles.data/generate-styles)))))
  (testing "Unwrapped style is a valid style"
    (is (spoon.spec/test-valid? ::styles.format/styles
                                (sut/unwrap-styles styles.data/sample-styles-wrapper)))
    (is (spoon.spec/test-valid? ::styles.format/styles
                                (sut/unwrap-styles (styles.data/generate-styles-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= styles.data/sample-styles
           (-> styles.data/sample-styles
               sut/wrap-styles
               sut/unwrap-styles)))
    (is (= styles.data/sample-styles-wrapper
           (-> styles.data/sample-styles-wrapper
               sut/unwrap-styles
               sut/wrap-styles)))))


(deftest water-tests
  (testing "Wrapped water is a valid water-wrapper"
    (is (spoon.spec/test-valid? ::waters.format/water-wrapper
                                (sut/wrap-water waters.data/sample-water)))
    (is (spoon.spec/test-valid? ::waters.format/water-wrapper
                                (sut/wrap-water (waters.data/generate-water)))))
  (testing "Unwrapped water is a valid water"
    (is (spoon.spec/test-valid? ::waters.format/water
                                (sut/unwrap-water waters.data/sample-water-wrapper)))
    (is (spoon.spec/test-valid? ::waters.format/water
                                (sut/unwrap-water (waters.data/generate-water-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= waters.data/sample-water
           (-> waters.data/sample-water
               sut/wrap-water
               sut/unwrap-water)))
    (is (= waters.data/sample-water-wrapper
           (-> waters.data/sample-water-wrapper
               sut/unwrap-water
               sut/wrap-water)))))


(deftest waters-tests
  (testing "Wrapped waters is a valid waters-wrapper"
    (is (spoon.spec/test-valid? ::waters.format/waters-wrapper
                                (sut/wrap-waters waters.data/sample-waters)))
    (is (spoon.spec/test-valid? ::waters.format/waters-wrapper
                                (sut/wrap-waters (waters.data/generate-waters)))))
  (testing "Unwrapped water is a valid water"
    (is (spoon.spec/test-valid? ::waters.format/waters
                                (sut/unwrap-waters waters.data/sample-waters-wrapper)))
    (is (spoon.spec/test-valid? ::waters.format/waters
                                (sut/unwrap-waters (waters.data/generate-waters-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= waters.data/sample-waters
           (-> waters.data/sample-waters
               sut/wrap-waters
               sut/unwrap-waters)))
    (is (= waters.data/sample-waters-wrapper
           (-> waters.data/sample-waters-wrapper
               sut/unwrap-waters
               sut/wrap-waters)))))


(deftest yeast-tests
  (testing "Wrapped yeast is a valid yeast-wrapper"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast-wrapper
                                (sut/wrap-yeast yeasts.data/sample-yeast)))
    (is (spoon.spec/test-valid? ::yeasts.format/yeast-wrapper
                                (sut/wrap-yeast (yeasts.data/generate-yeast)))))
  (testing "Unwrapped yeast is a valid yeast"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast
                                (sut/unwrap-yeast yeasts.data/sample-yeast-wrapper)))
    (is (spoon.spec/test-valid? ::yeasts.format/yeast
                                (sut/unwrap-yeast (yeasts.data/generate-yeast-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= yeasts.data/sample-yeast
           (-> yeasts.data/sample-yeast
               sut/wrap-yeast
               sut/unwrap-yeast)))
    (is (= yeasts.data/sample-yeast-wrapper
           (-> yeasts.data/sample-yeast-wrapper
               sut/unwrap-yeast
               sut/wrap-yeast)))))


(deftest yeasts-tests
  (testing "Wrapped yeasts is a valid yeasts-wrapper"
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts-wrapper
                                (sut/wrap-yeasts yeasts.data/sample-yeasts)))
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts-wrapper
                                (sut/wrap-yeasts (yeasts.data/generate-yeasts)))))
  (testing "Unwrapped yeast is a valid yeast"
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts
                                (sut/unwrap-yeasts yeasts.data/sample-yeasts-wrapper)))
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts
                                (sut/unwrap-yeasts (yeasts.data/generate-yeasts-wrapper)))))
  (testing "(Un)wrapping is reversible"
    (is (= yeasts.data/sample-yeasts
           (-> yeasts.data/sample-yeasts
               sut/wrap-yeasts
               sut/unwrap-yeasts)))
    (is (= yeasts.data/sample-yeasts-wrapper
           (-> yeasts.data/sample-yeasts-wrapper
               sut/unwrap-yeasts
               sut/wrap-yeasts)))))
