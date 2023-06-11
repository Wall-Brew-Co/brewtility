(ns brewtility.units.specific-gravity-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.specific-gravity :as sut]))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/specific-gravity options/specific-gravity))
           (precision/->2dp (sut/convert 100.0 :specific-gravity :specific-gravity)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 sg"
           (sut/display 1.5 :specific-gravity)
           (sut/display 1.5 options/specific-gravity))))
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
