(ns brewtility.data.mash
  "Namespace for static and generative test data for `common-beer-format.mash/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.mash :as mash.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(def ^:const sample-mash-step
  "A hard-coded sample mash step for static unit tests"
  {:infuse-amount 10.0
   :name          "Conversion Step, 68C"
   :step-temp     68.0
   :step-time     60.0
   :type          "Infusion"
   :version       1})


(def ^:const sample-mash-step-wrapper
  "A hard-coded sample mash-step-wrapper for static unit tests"
  {:mash-step sample-mash-step})


(def ^:const sample-mash-steps
  "A hard-coded sample mash-steps for static unit tests"
  [sample-mash-step-wrapper])


(def ^:const sample-mash
  "A hard-coded sample mash for static unit tests"
  {:name       "Single Step Infusion, 68 C"
   :version    1
   :grain-temp 22.0
   :mash-steps sample-mash-steps})


(def ^:const sample-mash-wrapper
  "A hard-coded sample mash-wrapper for static unit tests"
  {:mash sample-mash})


(defn generate-mash-step
  "Generate a random mash-step object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::mash.format/mash-step)))


(defn generate-mash-step-wrapper
  "Generate a random mash-step-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::mash.format/mash-step-wrapper)))


(defn generate-mash-steps
  "Generate a random mash-steps object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::mash.format/mash-steps)))


(defn generate-mash
  "Generate a random mash object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::mash.format/mash)))


(defn generate-mash-wrapper
  "Generate a random mash-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::mash.format/mash-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::mash.format/mash-step sample-mash-step)
        "Static test data should conform to common-beer-format.hop/mash-step")
    (is (spoon.spec/test-valid? ::mash.format/mash-step-wrapper sample-mash-step-wrapper)
        "Static test data should conform to common-beer-format.hop/mash-step-wrapper")
    (is (spoon.spec/test-valid? ::mash.format/mash-steps sample-mash-steps)
        "Static test data should conform to common-beer-format.hop/mash-steps")
    (is (spoon.spec/test-valid? ::mash.format/mash sample-mash)
        "Static test data should conform to common-beer-format.hop/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-wrapper sample-mash-wrapper)
        "Static test data should conform to common-beer-format.hop/mash-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::mash.format/mash-step (generate-mash-step))
        "Generative test data should conform to common-beer-format.hop/mash-step")
    (is (spoon.spec/test-valid? ::mash.format/mash-step-wrapper (generate-mash-step-wrapper))
        "Generative test data should conform to common-beer-format.hop/mash-step-wrapper")
    (is (spoon.spec/test-valid? ::mash.format/mash-steps (generate-mash-steps))
        "Generative test data should conform to common-beer-format.hop/mash-steps")
    (is (spoon.spec/test-valid? ::mash.format/mash (generate-mash))
        "Generative test data should conform to common-beer-format.hop/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-wrapper (generate-mash-wrapper))
        "Generative test data should conform to common-beer-format.hop/mash-wrapper")))
