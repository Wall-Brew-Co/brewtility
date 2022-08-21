(ns brewtility.data.miscs
  "Namespace for static and generative test data for `common-beer-format.miscs/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.miscs :as miscs.format]))


(def sample-misc
  "A hard-coded sample misc for static unit tests"
  {:amount  0.010
   :name    "Irish Moss"
   :notes   "Used as a clarifying agent during the last few minutes of the boil"
   :time    15.0
   :type    "Fining"
   :use     "Boil"
   :version 1})


(def sample-misc-wrapper
  "A hard-coded sample misc-wrapper for static unit tests"
  {:misc sample-misc})


(def sample-miscs
  "A hard-coded sample miscs for static unit tests"
  [sample-misc-wrapper])


(def sample-miscs-wrapper
  "A hard-coded sample miscs-wrapper for static unit tests"
  {:miscs sample-miscs})


(defn generate-misc
  "Generate a random misc object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::miscs.format/misc)))


(defn generate-misc-wrapper
  "Generate a random misc-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::miscs.format/misc-wrapper)))


(defn generate-miscs
  "Generate a random miscs object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::miscs.format/miscs)))


(defn generate-miscs-wrapper
  "Generate a random miscs-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::miscs.format/miscs-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::miscs.format/misc sample-misc)
        "Static test data should conform to common-beer-format.misc/misc")
    (is (spoon.spec/test-valid? ::miscs.format/misc-wrapper sample-misc-wrapper)
        "Static test data should conform to common-beer-format.misc/misc-wrapper")
    (is (spoon.spec/test-valid? ::miscs.format/miscs sample-miscs)
        "Static test data should conform to common-beer-format.misc/miscs")
    (is (spoon.spec/test-valid? ::miscs.format/miscs-wrapper sample-miscs-wrapper)
        "Static test data should conform to common-beer-format.misc/miscs-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::miscs.format/misc (generate-misc))
        "Generative test data should conform to common-beer-format.misc/misc")
    (is (spoon.spec/test-valid? ::miscs.format/misc-wrapper (generate-misc-wrapper))
        "Generative test data should conform to common-beer-format.misc/misc-wrapper")
    (is (spoon.spec/test-valid? ::miscs.format/miscs (generate-miscs))
        "Generative test data should conform to common-beer-format.misc/miscs")
    (is (spoon.spec/test-valid? ::miscs.format/miscs-wrapper (generate-miscs-wrapper))
        "Generative test data should conform to common-beer-format.misc/miscs-wrapper")))
