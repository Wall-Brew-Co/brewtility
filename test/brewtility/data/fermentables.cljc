(ns brewtility.data.fermentables
  "Namespace for static and generative test data for `common-beer-format.fermentables/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.fermentables :as fermentables.format]))


(def sample-fermentable
  "A hard-coded sample fermentable for static unit tests"
  {:amount           0.45
   :coarse-fine-diff 1.5
   :color            500.1
   :diastatic-power  0.0
   :max-in-batch     10.0
   :moisture         5.0
   :name             "Black Barley"
   :notes            "Unmalted roasted barley for stouts, porters"
   :origin           "United States"
   :protein          13.2
   :supplier         "Gnome Brew"
   :type             "Grain"
   :version          1
   :yield            78.0})


(def sample-fermentable-wrapper
  "A hard-coded sample fermentable-wrapper for static unit tests"
  {:fermentable sample-fermentable})


(def sample-fermentables
  "A hard-coded sample fermentables for static unit tests"
  [sample-fermentable-wrapper])


(def sample-fermentables-wrapper
  "A hard-coded sample fermentables-wrapper for static unit tests"
  {:fermentables sample-fermentables})


(defn generate-fermentable
  "Generate a random fermentable object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::fermentables.format/fermentable)))


(defn generate-fermentable-wrapper
  "Generate a random fermentable-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::fermentables.format/fermentable-wrapper)))


(defn generate-fermentables
  "Generate a random fermentables object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::fermentables.format/fermentables)))


(defn generate-fermentables-wrapper
  "Generate a random fermentables-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::fermentables.format/fermentables-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable sample-fermentable)
        "Static test data should conform to common-beer-format.fermentable/fermentable")
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable-wrapper sample-fermentable-wrapper)
        "Static test data should conform to common-beer-format.fermentable/fermentable-wrapper")
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables sample-fermentables)
        "Static test data should conform to common-beer-format.fermentable/fermentables")
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables-wrapper sample-fermentables-wrapper)
        "Static test data should conform to common-beer-format.fermentable/fermentables-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable (generate-fermentable))
        "Generative test data should conform to common-beer-format.fermentable/fermentable")
    (is (spoon.spec/test-valid? ::fermentables.format/fermentable-wrapper (generate-fermentable-wrapper))
        "Generative test data should conform to common-beer-format.fermentable/fermentable-wrapper")
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables (generate-fermentables))
        "Generative test data should conform to common-beer-format.fermentable/fermentables")
    (is (spoon.spec/test-valid? ::fermentables.format/fermentables-wrapper (generate-fermentables-wrapper))
        "Generative test data should conform to common-beer-format.fermentable/fermentables-wrapper")))
