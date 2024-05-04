(ns brewtility.data.yeasts
  "Namespace for static and generative test data for `common-beer-format.yeasts/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [com.wallbrew.spoon.string :as spoon.str]
            [common-beer-format.yeasts :as yeasts.format]))


(defn random-flocculation
  "Return a random valid flocculation."
  {:added "1.4"
   :no-doc true}
  []
  (-> yeasts.format/yeast-flocculation-types
      vec
      rand-nth))


(deftest flocculation-test
  (testing "Random flocculation should be valid"
    (is (spoon.spec/test-valid? ::yeasts.format/flocculation (random-flocculation)))))


(def sample-yeast
  "A hard-coded sample yeast for static unit tests"
  {yeasts.format/amount          0.250
   yeasts.format/attenuation     73.0
   yeasts.format/best-for        "Irish Dry Stouts"
   yeasts.format/flocculation    yeasts.format/medium
   yeasts.format/form            yeasts.format/liquid
   yeasts.format/laboratory      "Wyeast Labs"
   yeasts.format/max-temperature 22.2
   yeasts.format/min-temperature 16.7
   yeasts.format/name            "Irish Ale"
   yeasts.format/notes           "Dry, fruity flavor characteristic of stouts.  Full bodied, dry, clean flavor."
   yeasts.format/product-id      "1084"
   yeasts.format/type            "Ale"
   yeasts.format/version         1})


(def sample-yeast-wrapper
  "A hard-coded sample yeast-wrapper for static unit tests"
  {yeasts.format/yeast sample-yeast})


(def sample-yeasts
  "A hard-coded sample yeasts for static unit tests"
  [sample-yeast-wrapper])


(def sample-yeasts-wrapper
  "A hard-coded sample yeasts-wrapper for static unit tests"
  {yeasts.format/yeasts sample-yeasts})


(defn generate-yeast
  "Generate a random yeast object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::yeasts.format/yeast)))


(defn generate-yeast-wrapper
  "Generate a random yeast-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::yeasts.format/yeast-wrapper)))


(defn generate-yeasts
  "Generate a random yeasts object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::yeasts.format/yeasts)))


(defn generate-yeasts-wrapper
  "Generate a random yeasts-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::yeasts.format/yeasts-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast sample-yeast)
        "Static test data should conform to common-beer-format.yeast/yeast")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (assoc sample-yeast yeasts.format/flocculation (random-flocculation)))
        "Static test data should conform to common-beer-format.yeast/yeast, even with random flocculation")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast-wrapper sample-yeast-wrapper)
        "Static test data should conform to common-beer-format.yeast/yeast-wrapper")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts sample-yeasts)
        "Static test data should conform to common-beer-format.yeast/yeasts")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts-wrapper sample-yeasts-wrapper)
        "Static test data should conform to common-beer-format.yeast/yeasts-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (generate-yeast))
        "Generative test data should conform to common-beer-format.yeast/yeast")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (assoc (generate-yeast) yeasts.format/flocculation (random-flocculation)))
        "Generative test data should conform to common-beer-format.yeast/yeast, even with random flocculation")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast-wrapper (generate-yeast-wrapper))
        "Generative test data should conform to common-beer-format.yeast/yeast-wrapper")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts (generate-yeasts))
        "Generative test data should conform to common-beer-format.yeast/yeasts")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts-wrapper (generate-yeasts-wrapper))
        "Generative test data should conform to common-beer-format.yeast/yeasts-wrapper")))
