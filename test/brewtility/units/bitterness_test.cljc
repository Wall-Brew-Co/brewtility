(ns brewtility.units.bitterness-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.bitterness :as sut]
            [brewtility.units.options :as options]
            [clojure.test :refer [deftest is testing]]))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/ibu options/ibu))
           (precision/->2dp (sut/convert 100.0 :ibu :ibu)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 ibu"
           (sut/display 1.5 :ibu)
           (sut/display 1.5 options/ibu))))
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
    (testing "measurement->ibu"
      (is (map? sut/measurement->ibu))
      (is (not-empty sut/measurement->ibu))
      (is (every? keyword? (keys sut/measurement->ibu)))
      (is (every? ifn? (vals sut/measurement->ibu))))
    (testing "ibu->measurement"
      (is (map? sut/ibu->measurement))
      (is (not-empty sut/ibu->measurement))
      (is (every? keyword? (keys sut/ibu->measurement)))
      (is (= (set (keys sut/measurement->ibu))
             (set (keys sut/ibu->measurement))
             (set (keys sut/measurements->display-name))
             sut/measurements))
      (is (every? ifn? (vals sut/ibu->measurement))))))
