(ns brewtility.data.waters
  "Namespace for static and generative test data for `common-beer-format.waters/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.waters :as waters.format]))


(defn random-ph
  "Generate a random `:ph` value."
  {:added  "2.0"
   :no-doc true}
  []
  (rand 14))


(deftest ph-test
  (testing "Random ph should be valid"
    (is (spoon.spec/test-valid? ::waters.format/ph (random-ph)))))


(def sample-water
  "A hard-coded sample water for static unit tests"
  {waters.format/amount      20.0
   waters.format/bicarbonate 300.0
   waters.format/calcium     295.0
   waters.format/chloride    25.0
   waters.format/magnesium   45.0
   waters.format/name        "Chicago"
   waters.format/notes       "The best there is"
   waters.format/ph          8.0
   waters.format/sodium      55.0
   waters.format/sulfate     725.0
   waters.format/version     1})


(def sample-water-wrapper
  "A hard-coded sample water-wrapper for static unit tests"
  {waters.format/water sample-water})


(def sample-waters
  "A hard-coded sample waters for static unit tests"
  [sample-water-wrapper])


(def sample-waters-wrapper
  "A hard-coded sample waters-wrapper for static unit tests"
  {waters.format/waters sample-waters})


(defn generate-water
  "Generate a random water object"
  {:added  "2.0"
   :no-doc true}
  []
  (gen/generate (spec/gen ::waters.format/water)))


(defn generate-water-wrapper
  "Generate a random water-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::waters.format/water-wrapper)))


(defn generate-waters
  "Generate a random waters object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::waters.format/waters)))


(defn generate-waters-wrapper
  "Generate a random waters-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::waters.format/waters-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::waters.format/water sample-water)
        "Static test data should conform to common-beer-format.water/water")
    (is (spoon.spec/test-valid? ::waters.format/water (assoc sample-water waters.format/ph (random-ph)))
        "Static test data should conform to common-beer-format.water/water")
    (is (spoon.spec/test-valid? ::waters.format/water-wrapper sample-water-wrapper)
        "Static test data should conform to common-beer-format.water/water-wrapper")
    (is (spoon.spec/test-valid? ::waters.format/waters sample-waters)
        "Static test data should conform to common-beer-format.water/waters")
    (is (spoon.spec/test-valid? ::waters.format/waters-wrapper sample-waters-wrapper)
        "Static test data should conform to common-beer-format.water/waters-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::waters.format/water (generate-water))
        "Generative test data should conform to common-beer-format.water/water")
    (is (spoon.spec/test-valid? ::waters.format/water-wrapper (generate-water-wrapper))
        "Generative test data should conform to common-beer-format.water/water-wrapper")
    (is (spoon.spec/test-valid? ::waters.format/waters (generate-waters))
        "Generative test data should conform to common-beer-format.water/waters")
    (is (spoon.spec/test-valid? ::waters.format/waters-wrapper (generate-waters-wrapper))
        "Generative test data should conform to common-beer-format.water/waters-wrapper")))
