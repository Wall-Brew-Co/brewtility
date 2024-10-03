(ns brewtility.units.time-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.time :as sut]
            [clojure.test :refer [deftest is testing]]))


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
    (testing "measurement->minute"
      (is (map? sut/measurement->minute))
      (is (not-empty sut/measurement->minute))
      (is (every? keyword? (keys sut/measurement->minute)))
      (is (every? number? (vals sut/measurement->minute))))
    (is (= (set (keys sut/measurement->minute))
           (set (keys sut/measurements->display-name))
           sut/measurements))))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/minute options/minute))
           (precision/->2dp (sut/convert 100.0 :minute :minute))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/nanosecond options/nanosecond))
           (precision/->2dp (sut/convert 100.0 :nanosecond :nanosecond))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/second options/second))
           (precision/->2dp (sut/convert 100.0 :second :second))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/millisecond options/millisecond))
           (precision/->2dp (sut/convert 100.0 :millisecond :millisecond))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/microsecond options/microsecond))
           (precision/->2dp (sut/convert 100.0 :microsecond :microsecond))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/day options/day))
           (precision/->2dp (sut/convert 100.0 :day :day))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/hour options/hour))
           (precision/->2dp (sut/convert 100.0 :hour :hour))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/week options/week))
           (precision/->2dp (sut/convert 100.0 :week :week))))
    (is (= 6000.0 (precision/->2dp (sut/convert 100.0 options/minute options/second))))
    (is (= 6000000.0 (precision/->2dp (sut/convert 100.0 options/minute options/millisecond))))
    (is (= 60000.0 (precision/->2dp (sut/convert 0.001 options/minute options/microsecond))))
    (is (= 600000.0 (precision/->2dp (sut/convert 0.00001 options/minute options/nanosecond))))
    (is (= 0.07 (precision/->2dp (sut/convert 100.0 options/minute options/day))))
    (is (= 1.67 (precision/->2dp (sut/convert 100.0 options/minute options/hour))))
    (is (= 0.01 (precision/->2dp (sut/convert 100.0 options/minute options/week))))
    (is (= 1.67 (precision/->2dp (sut/convert 100.0 options/second options/minute))))
    (is (= 0.0 (precision/->2dp (sut/convert 100.0 options/millisecond options/minute))))
    (is (= 0.0 (precision/->2dp (sut/convert 100.0 options/microsecond options/minute))))
    (is (= 0.0 (precision/->2dp (sut/convert 100.0 options/nanosecond options/minute))))
    (is (= 144000.0 (precision/->2dp (sut/convert 100.0 options/day options/minute))))
    (is (= 6000.0 (precision/->2dp (sut/convert 100.0 options/hour options/minute))))
    (is (= 1008000.0 (precision/->2dp (sut/convert 100.0 options/week options/minute)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 m"
           (sut/display 1.5 :minute)
           (sut/display 1.5 options/minute)))
    (is (= "1.5 d"
           (sut/display 1.5 :day)
           (sut/display 1.5 options/day)))
    (is (= "1.5 hr"
           (sut/display 1.5 :hour)
           (sut/display 1.5 options/hour)))
    (is (= "1.5 w"
           (sut/display 1.5 :week)
           (sut/display 1.5 options/week)))
    (is (= "1.5 ns"
           (sut/display 1.5 :nanosecond)
           (sut/display 1.5 options/nanosecond)))
    (is (= "1.5 ms"
           (sut/display 1.5 :millisecond)
           (sut/display 1.5 options/millisecond)))
    (is (= "1.5 µs"
           (sut/display 1.5 :microsecond)
           (sut/display 1.5 options/microsecond)))
    (is (= "1.5 s"
           (sut/display 1.5 :second)
           (sut/display 1.5 options/second)))
    (is (= "1.5 minute"
           (sut/display 1.5 :minute {options/suffix options/full})
           (sut/display 1.5 options/minute {options/suffix options/full}))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))))
