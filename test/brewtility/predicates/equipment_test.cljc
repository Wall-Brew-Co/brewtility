(ns brewtility.predicates.equipment-test
  (:require [brewtility.data.equipment :as equipment]
            [brewtility.predicates.equipment :as sut]
            [clojure.test :refer [deftest is testing]]
            [common-beer-format.equipment :as cbf-equipment]))


(deftest calculated-boil-volume?-tests
  (testing "When the `:calc-boil-volume` field is present, use that value."
    (is (true? (sut/calculated-boil-volume? (assoc equipment/sample-equipment :calc-boil-volume true))))
    (is (false? (sut/calculated-boil-volume? (assoc equipment/sample-equipment :calc-boil-volume false))))
    (is (true? (sut/calculated-boil-volume? (assoc equipment/sample-equipment cbf-equipment/calc-boil-volume true))))
    (is (false? (sut/calculated-boil-volume? (assoc equipment/sample-equipment cbf-equipment/calc-boil-volume false)))))
  (testing "When the `:calc-boil-volume` field is missing, return false."
    (is (false? (sut/calculated-boil-volume? (dissoc equipment/sample-equipment cbf-equipment/calc-boil-volume)))))
  (testing "This is a function from a equipment to a boolean."
    (is (boolean? (sut/calculated-boil-volume? equipment/sample-equipment)))
    (is (boolean? (sut/calculated-boil-volume? (equipment/generate-equipment))))))
