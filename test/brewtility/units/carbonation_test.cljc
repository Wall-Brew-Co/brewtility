(ns brewtility.units.carbonation-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.carbonation :as sut]
            [brewtility.units.options :as options]
            [clojure.test :refer [deftest is testing]]))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/volumes-of-co2 options/volumes-of-co2))
           (precision/->2dp (sut/convert 100.0 :volumes-of-co2 :volumes-of-co2))))
    (is (= 51.02 (precision/->2dp (sut/convert 100.0 options/volumes-of-co2 options/grams-per-liter))))
    (is (= 196.0 (precision/->2dp (sut/convert 100.0 options/grams-per-liter options/volumes-of-co2)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 vols"
           (sut/display 1.5 :volumes-of-co2)
           (sut/display 1.5 options/volumes-of-co2)))
    (is (= "1.5 volumes of CO2"
           (sut/display 1.5 :volumes-of-co2 {options/suffix options/full}))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))))


(deftest code-type-tests
  (testing "Ensure maps used for options are structurally correct"
    (testing "Measurement types"
      (is (set? sut/measurements))
      (is (every? keyword? sut/measurements))
      (is (not-empty sut/measurements)))
    (testing "Measurement display names"
      (is (map? sut/measurements->display-name))
      (is (not-empty sut/measurements->display-name))
      (is (every? keyword? (keys sut/measurements->display-name)))
      (is (every? map? (vals sut/measurements->display-name)))
      (is (every? #(contains? % options/full) (vals sut/measurements->display-name)))
      (is (every? #(contains? % options/short) (vals sut/measurements->display-name))))
    (testing "measurement->volumes-of-co2"
      (is (map? sut/measurement->volumes-of-co2))
      (is (not-empty sut/measurement->volumes-of-co2))
      (is (every? keyword? (keys sut/measurement->volumes-of-co2)))
      (is (every? ifn? (vals sut/measurement->volumes-of-co2))))
    (testing "volumes-of-co2->measurement"
      (is (map? sut/volumes-of-co2->measurement))
      (is (not-empty sut/volumes-of-co2->measurement))
      (is (every? keyword? (keys sut/volumes-of-co2->measurement)))
      (is (= (set (keys sut/measurement->volumes-of-co2))
             (set (keys sut/volumes-of-co2->measurement))
             (set (keys sut/measurements->display-name))
             sut/measurements))
      (is (every? ifn? (vals sut/volumes-of-co2->measurement))))))
