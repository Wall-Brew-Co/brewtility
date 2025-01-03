(ns brewtility.predicates.recipes-test
  (:require [brewtility.data.recipes :as recipes]
            [brewtility.predicates.recipes :as sut]
            [clojure.test :refer [deftest is testing]]
            [common-beer-format.recipes :as cbf-recipes]))


;;
;; Boolean function test
;;

(deftest forced-carbonation?-tests
  (testing "When the `:forced-carbonation` field is present, use that value"
    (is (true? (sut/forced-carbonation? (assoc recipes/sample-recipe :forced-carbonation true))))
    (is (false? (sut/forced-carbonation? (assoc recipes/sample-recipe :forced-carbonation false))))
    (is (true? (sut/forced-carbonation? (assoc recipes/sample-recipe cbf-recipes/forced-carbonation true))))
    (is (false? (sut/forced-carbonation? (assoc recipes/sample-recipe cbf-recipes/forced-carbonation false)))))
  (testing "When the `:forced-carbonation` field is missing, return false"
    (is (false? (sut/forced-carbonation? (dissoc recipes/sample-recipe :forced-carbonation)))))
  (testing "This is a function from a fermentable to a boolean"
    (is (boolean? (sut/forced-carbonation? recipes/sample-recipe)))
    (is (boolean? (sut/forced-carbonation? (recipes/generate-recipe))))))


;;
;; :type function tests
;;

(deftest extract?-test
  (testing "A recipe with a `:type` matching `\"extract\"` returns true"
    (is (true? (sut/extract? (assoc recipes/sample-recipe :type "extract"))))
    (is (true? (sut/extract? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/extract)))))
  (testing "A recipe with a `:type` not matching `\"extract\"` returns false"
    (is (false? (sut/extract? (assoc recipes/sample-recipe :type "all grain"))))
    (is (false? (sut/extract? (assoc recipes/sample-recipe :type "partial mash"))))
    (is (false? (sut/extract? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/all-grain))))
    (is (false? (sut/extract? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/partial-mash)))))
  (testing "This is a function from a recipe to a boolean"
    (is (boolean? (sut/extract? recipes/sample-recipe)))
    (is (boolean? (sut/extract? (recipes/generate-recipe)))))
  (testing "If the recipe is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Recipe :type"
                  (sut/extract? (dissoc recipes/sample-recipe :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Recipe :type"
                   (sut/extract? (dissoc recipes/sample-recipe :type)))))))


(deftest all-grain?-test
  (testing "A recipe with a `:type` matching `\"all-grain\"` returns true"
    (is (true? (sut/all-grain? (assoc recipes/sample-recipe :type "all grain"))))
    (is (true? (sut/all-grain? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/all-grain)))))
  (testing "A recipe with a `:type` not matching `\"all-grain\"` returns false"
    (is (false? (sut/all-grain? (assoc recipes/sample-recipe :type "extract"))))
    (is (false? (sut/all-grain? (assoc recipes/sample-recipe :type "partial mash"))))
    (is (false? (sut/all-grain? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/extract))))
    (is (false? (sut/all-grain? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/partial-mash)))))
  (testing "This is a function from a recipe to a boolean"
    (is (boolean? (sut/all-grain? recipes/sample-recipe)))
    (is (boolean? (sut/all-grain? (recipes/generate-recipe)))))
  (testing "If the recipe is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Recipe :type"
                  (sut/all-grain? (dissoc recipes/sample-recipe :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Recipe :type"
                   (sut/all-grain? (dissoc recipes/sample-recipe :type)))))))


(deftest partial-mash?-test
  (testing "A recipe with a `:type` matching `\"partial-mash\"` returns true"
    (is (true? (sut/partial-mash? (assoc recipes/sample-recipe :type "partial mash"))))
    (is (true? (sut/partial-mash? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/partial-mash)))))
  (testing "A recipe with a `:type` not matching `\"partial-mash\"` returns false"
    (is (false? (sut/partial-mash? (assoc recipes/sample-recipe :type "extract"))))
    (is (false? (sut/partial-mash? (assoc recipes/sample-recipe :type "all grain"))))
    (is (false? (sut/partial-mash? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/extract))))
    (is (false? (sut/partial-mash? (assoc recipes/sample-recipe cbf-recipes/type cbf-recipes/all-grain)))))
  (testing "This is a function from a recipe to a boolean"
    (is (boolean? (sut/partial-mash? recipes/sample-recipe)))
    (is (boolean? (sut/partial-mash? (recipes/generate-recipe)))))
  (testing "If the recipe is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Recipe :type"
                  (sut/partial-mash? (dissoc recipes/sample-recipe :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Recipe :type"
                   (sut/partial-mash? (dissoc recipes/sample-recipe :type)))))))


;;
;; :ibu-method function tests
;;

(deftest rager?-test
  (testing "A recipe with a `:ibu-method` matching `\"rager\"` returns true"
    (is (true? (sut/rager? (assoc recipes/sample-recipe :ibu-method "rager"))))
    (is (true? (sut/rager? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/rager)))))
  (testing "A recipe with a `:ibu-method` not matching `\"rager\"` returns false"
    (is (false? (sut/rager? (assoc recipes/sample-recipe :ibu-method "tinseth"))))
    (is (false? (sut/rager? (assoc recipes/sample-recipe :ibu-method "garetz"))))
    (is (false? (sut/rager? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/tinseth))))
    (is (false? (sut/rager? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/garetz)))))
  (testing "This is a function from a recipe to a boolean"
    (is (boolean? (sut/rager? (assoc recipes/sample-recipe :ibu-method (recipes/random-ibu-method)))))
    (is (boolean? (sut/rager? (assoc (recipes/generate-recipe) :ibu-method (recipes/random-ibu-method))))))
  (testing "If the recipe is missing `:ibu-method`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Recipe :ibu-method"
                  (sut/rager? (dissoc recipes/sample-recipe :ibu-method)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Recipe :ibu-method"
                   (sut/rager? (dissoc recipes/sample-recipe :ibu-method)))))))


(deftest tinseth?-test
  (testing "A recipe with a `:ibu-method` matching `\"tinseth\"` returns true"
    (is (true? (sut/tinseth? (assoc recipes/sample-recipe :ibu-method "tinseth"))))
    (is (true? (sut/tinseth? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/tinseth)))))
  (testing "A recipe with a `:ibu-method` not matching `\"tinseth\"` returns false"
    (is (false? (sut/tinseth? (assoc recipes/sample-recipe :ibu-method "rager"))))
    (is (false? (sut/tinseth? (assoc recipes/sample-recipe :ibu-method "garetz"))))
    (is (false? (sut/tinseth? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/rager))))
    (is (false? (sut/tinseth? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/garetz)))))
  (testing "This is a function from a recipe to a boolean"
    (is (boolean? (sut/tinseth? (assoc recipes/sample-recipe :ibu-method (recipes/random-ibu-method)))))
    (is (boolean? (sut/tinseth? (assoc (recipes/generate-recipe) :ibu-method (recipes/random-ibu-method))))))
  (testing "If the recipe is missing `:ibu-method`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Recipe :ibu-method"
                  (sut/tinseth? (dissoc recipes/sample-recipe :ibu-method)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Recipe :ibu-method"
                   (sut/tinseth? (dissoc recipes/sample-recipe :ibu-method)))))))


(deftest garetz?-test
  (testing "A recipe with a `:ibu-method` matching `\"garetz\"` returns true"
    (is (true? (sut/garetz? (assoc recipes/sample-recipe :ibu-method "garetz"))))
    (is (true? (sut/garetz? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/garetz)))))
  (testing "A recipe with a `:ibu-method` not matching `\"garetz\"` returns false"
    (is (false? (sut/garetz? (assoc recipes/sample-recipe :ibu-method "rager"))))
    (is (false? (sut/garetz? (assoc recipes/sample-recipe :ibu-method "tinseth"))))
    (is (false? (sut/garetz? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/rager))))
    (is (false? (sut/garetz? (assoc recipes/sample-recipe cbf-recipes/ibu-method cbf-recipes/tinseth)))))
  (testing "This is a function from a recipe to a boolean"
    (is (boolean? (sut/garetz? (assoc recipes/sample-recipe :ibu-method (recipes/random-ibu-method)))))
    (is (boolean? (sut/garetz? (assoc (recipes/generate-recipe) :ibu-method (recipes/random-ibu-method))))))
  (testing "If the recipe is missing `:ibu-method`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Recipe :ibu-method"
                  (sut/garetz? (dissoc recipes/sample-recipe :ibu-method)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Recipe :ibu-method"
                   (sut/garetz? (dissoc recipes/sample-recipe :ibu-method)))))))
