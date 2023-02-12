(ns brewtility.data.hops
  "Namespace for static and generative test data for `common-beer-format.hops/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [com.wallbrew.spoon.string :as spoon.str]
            [common-beer-format.hops :as hops.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(defn random-hop-type
  "Return a random valid hop type."
  {:added  "1.4"
   :no-doc true}
  []
  (-> hops.format/hop-types
      vec
      rand-nth
      spoon.str/->sporadic-case))


(deftest hop-type-test
  (testing "Random hop type should be valid"
    (is (spoon.spec/test-valid? ::hops.format/type (random-hop-type)))))


(defn random-hop-form
  "Return a random valid hop form."
  {:added "1.4"
   :no-doc true}
  []
  (-> hops.format/hop-forms
      vec
      rand-nth
      spoon.str/->sporadic-case))


(deftest hop-form-test
  (testing "Random hop form should be valid"
    (is (spoon.spec/test-valid? ::hops.format/form (random-hop-form)))))


(def ^:const sample-hop
  "A hard-coded sample hop for static unit tests"
  {:alpha   5.0
   :amount  0.0638
   :name    "Goldings, East Kent"
   :notes   "Great all purpose UK hop for ales, stouts, porters"
   :time    60.1
   :use     "Boil"
   :version 1})


(def ^:const sample-hop-wrapper
  "A hard-coded sample hop-wrapper for static unit tests"
  {:hop sample-hop})


(def ^:const sample-hops
  "A hard-coded sample hops for static unit tests"
  [sample-hop-wrapper])


(def ^:const sample-hops-wrapper
  "A hard-coded sample hops-wrapper for static unit tests"
  {:hops sample-hops})


(defn generate-hop
  "Generate a random hop object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::hops.format/hop)))


(defn generate-hop-wrapper
  "Generate a random hop-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::hops.format/hop-wrapper)))


(defn generate-hops
  "Generate a random hops object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::hops.format/hops)))


(defn generate-hops-wrapper
  "Generate a random hops-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::hops.format/hops-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::hops.format/hop sample-hop)
        "Static test data should conform to common-beer-format.hop/hop")
    (is (spoon.spec/test-valid? ::hops.format/hop (assoc sample-hop :type (random-hop-type)))
        "Static test data should conform to common-beer-format.hop/hop, even with an added optional type")
    (is (spoon.spec/test-valid? ::hops.format/hop (assoc sample-hop :form (random-hop-form)))
        "Static test data should conform to common-beer-format.hop/hop, even with an added optional form")
    (is (spoon.spec/test-valid? ::hops.format/hop sample-hop)
        "Static test data should conform to common-beer-format.hop/hop")
    (is (spoon.spec/test-valid? ::hops.format/hop-wrapper sample-hop-wrapper)
        "Static test data should conform to common-beer-format.hop/hop-wrapper")
    (is (spoon.spec/test-valid? ::hops.format/hops sample-hops)
        "Static test data should conform to common-beer-format.hop/hops")
    (is (spoon.spec/test-valid? ::hops.format/hops-wrapper sample-hops-wrapper)
        "Static test data should conform to common-beer-format.hop/hops-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::hops.format/hop (assoc (generate-hop) :type (random-hop-type)))
        "Generative test data should conform to common-beer-format.hop/hop, even with an added optional type")
    (is (spoon.spec/test-valid? ::hops.format/hop (assoc (generate-hop) :form (random-hop-form)))
        "Generative test data should conform to common-beer-format.hop/hop, even with an added optional form")
    (is (spoon.spec/test-valid? ::hops.format/hop (generate-hop))
        "Generative test data should conform to common-beer-format.hop/hop")
    (is (spoon.spec/test-valid? ::hops.format/hop-wrapper (generate-hop-wrapper))
        "Generative test data should conform to common-beer-format.hop/hop-wrapper")
    (is (spoon.spec/test-valid? ::hops.format/hops (generate-hops))
        "Generative test data should conform to common-beer-format.hop/hops")
    (is (spoon.spec/test-valid? ::hops.format/hops-wrapper (generate-hops-wrapper))
        "Generative test data should conform to common-beer-format.hop/hops-wrapper")))
