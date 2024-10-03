(ns brewtility.units.specific-gravity-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.specific-gravity :as sut]
            [clojure.test :refer [deftest is testing]]))


(deftest conversion-test
  (testing "Ensure various specific gravity unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/specific-gravity options/specific-gravity))
           (precision/->2dp (sut/convert 100.0 :specific-gravity :specific-gravity))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/plato options/plato))
           (precision/->2dp (sut/convert 100.0 :plato :plato))))
    (is (= 0.5 (precision/->1dp (sut/convert 1.002 options/specific-gravity options/plato))))
    (is (= 3.1 (precision/->1dp (sut/convert 1.012 options/specific-gravity options/plato))))
    (is (= 7.6 (precision/->1dp (sut/convert 1.03 options/specific-gravity options/plato))))
    (is (= 19.6 (precision/->1dp (sut/convert 1.081 options/specific-gravity options/plato))))
    (is (= 1.002 (precision/->3dp (sut/convert 0.5 options/plato options/specific-gravity))))
    (is (= 1.012 (precision/->3dp (sut/convert 3.0 options/plato options/specific-gravity))))
    (is (= 1.03 (precision/->3dp (sut/convert 7.5 options/plato options/specific-gravity))))
    (is (= 1.081 (precision/->3dp (sut/convert 19.5 options/plato options/specific-gravity)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :plato :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :plato :broken))))
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert nil :plato :specific-gravity))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert nil :plato :specific-gravity))))))


(deftest display-test
  (testing "Ensure various specific gravity unit conversions behave as expected"
    (is (= "1.5 sg"
           (sut/display 1.5 :specific-gravity)
           (sut/display 1.5 options/specific-gravity)))
    (is (= "1.5 °P"
           (sut/display 1.5 :plato)
           (sut/display 1.5 options/plato))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display nil :plato))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display nil :plato))))))


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
    (testing "measurement->specific-gravity"
      (is (map? sut/measurement->specific-gravity))
      (is (not-empty sut/measurement->specific-gravity))
      (is (every? keyword? (keys sut/measurement->specific-gravity)))
      (is (every? ifn? (vals sut/measurement->specific-gravity))))
    (testing "specific-gravity->measurement"
      (is (map? sut/specific-gravity->measurement))
      (is (not-empty sut/specific-gravity->measurement))
      (is (every? keyword? (keys sut/specific-gravity->measurement)))
      (is (= (set (keys sut/measurement->specific-gravity))
             (set (keys sut/specific-gravity->measurement))
             (set (keys sut/measurements->display-name))
             sut/measurements))
      (is (every? ifn? (vals sut/specific-gravity->measurement))))))
