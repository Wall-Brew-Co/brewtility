(ns brewtility.predicates.mash-test
  (:require [brewtility.data.mash :as mash]
            [brewtility.predicates.mash :as sut]
            [clojure.spec.alpha :as spec]
            [clojure.test :refer [deftest is testing]]
            [clojure.test.check.clojure-test :as check.test]
            [clojure.test.check.properties :as prop]
            [common-beer-format.mash :as cbf-mash]))


(deftest adjust-for-equipment?-tests
  (testing "When the `:equip-adjust` field is present, use that value"
    (is (true? (sut/adjust-for-equipment? (assoc mash/sample-mash :equip-adjust true))))
    (is (false? (sut/adjust-for-equipment? (assoc mash/sample-mash :equip-adjust false)))))
  (testing "When the `:equip-adjust` field is missing, return false"
    (is (false? (sut/adjust-for-equipment? (dissoc mash/sample-mash cbf-mash/equip-adjust)))))
  (testing "This is a function from a mash to a boolean"
    (is (boolean? (sut/adjust-for-equipment? mash/sample-mash)))
    (is (boolean? (sut/adjust-for-equipment? (mash/generate-mash))))))


(deftest infusion?-test
  (testing "A mash-step with a `:type` matching `\"infusion\"` returns true"
    (is (true? (sut/infusion? (assoc mash/sample-mash-step :type "INFUsion"))))
    (is (true? (sut/infusion? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/infusion)))))
  (testing "A mash-step with a `:type` not matching `\"infusion\"` returns false"
    (is (false? (sut/infusion? (assoc mash/sample-mash-step :type "temperature"))))
    (is (false? (sut/infusion? (assoc mash/sample-mash-step :type "decoction"))))
    (is (false? (sut/infusion? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/temperature))))
    (is (false? (sut/infusion? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/decoction)))))
  (testing "This is a function from a mash-step to a boolean"
    (is (boolean? (sut/infusion? mash/sample-mash-step)))
    (is (boolean? (sut/infusion? (mash/generate-mash-step)))))
  (testing "If the mash-step is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Mash step :type"
                  (sut/infusion? (dissoc mash/sample-mash-step :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Mash step :type"
                   (sut/infusion? (dissoc mash/sample-mash-step :type)))))))


(deftest temperature?-test
  (testing "A mash-step with a `:type` matching `\"temperature\"` returns true"
    (is (true? (sut/temperature? (assoc mash/sample-mash-step :type "temperature"))))
    (is (true? (sut/temperature? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/temperature)))))
  (testing "A mash-step with a `:type` not matching `\"temperature\"` returns false"
    (is (false? (sut/temperature? (assoc mash/sample-mash-step :type "infusion"))))
    (is (false? (sut/temperature? (assoc mash/sample-mash-step :type "decoction"))))
    (is (false? (sut/temperature? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/infusion))))
    (is (false? (sut/temperature? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/decoction)))))
  (testing "This is a function from a mash-step to a boolean"
    (is (boolean? (sut/temperature? mash/sample-mash-step)))
    (is (boolean? (sut/temperature? (mash/generate-mash-step)))))
  (testing "If the mash-step is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Mash step :type"
                  (sut/temperature? (dissoc mash/sample-mash-step :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Mash step :type"
                   (sut/temperature? (dissoc mash/sample-mash-step :type)))))))


(deftest decoction?-test
  (testing "A mash-step with a `:type` matching `\"decoction\"` returns true"
    (is (true? (sut/decoction? (assoc mash/sample-mash-step :type "decoction"))))
    (is (true? (sut/decoction? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/decoction)))))
  (testing "A mash-step with a `:type` not matching `\"decoction\"` returns false"
    (is (false? (sut/decoction? (assoc mash/sample-mash-step :type "infusion"))))
    (is (false? (sut/decoction? (assoc mash/sample-mash-step :type "TEMPERature"))))
    (is (false? (sut/decoction? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/infusion))))
    (is (false? (sut/decoction? (assoc mash/sample-mash-step cbf-mash/type cbf-mash/temperature)))))
  (testing "This is a function from a mash-step to a boolean"
    (is (boolean? (sut/decoction? mash/sample-mash-step)))
    (is (boolean? (sut/decoction? (mash/generate-mash-step)))))
  (testing "If the mash-step is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Mash step :type"
                  (sut/decoction? (dissoc mash/sample-mash-step :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Mash step :type"
                   (sut/decoction? (dissoc mash/sample-mash-step :type)))))))


(declare adjust-for-equipment?-boolean
         infusion?-boolean
         temperature?-boolean
         decoction?-boolean)


(check.test/defspec
  adjust-for-equipment?-boolean 100
  (prop/for-all
    [mash (spec/gen ::cbf-mash/mash)]
    (boolean? (sut/adjust-for-equipment? mash))))


(check.test/defspec
  infusion?-boolean 100
  (prop/for-all
    [mash-step (spec/gen ::cbf-mash/mash-step)]
    (boolean? (sut/infusion? mash-step))))


(check.test/defspec
  temperature?-boolean 100
  (prop/for-all
    [mash-step (spec/gen ::cbf-mash/mash-step)]
    (boolean? (sut/temperature? mash-step))))


(check.test/defspec
  decoction?-boolean 100
  (prop/for-all
    [mash-step (spec/gen ::cbf-mash/mash-step)]
    (boolean? (sut/decoction? mash-step))))
