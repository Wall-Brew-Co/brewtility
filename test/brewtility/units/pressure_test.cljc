(ns brewtility.units.pressure-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.pressure :as sut]))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/pascal options/pascal))
           (precision/->2dp (sut/convert 100.0 :pascal :pascal))))
    (is (= 100000.0 (precision/->2dp (sut/convert 100.0 options/kilopascal options/pascal))))
    (is (= 1.0 (precision/->2dp (sut/convert 100.0 options/kilopascal options/bar))))
    (is (= 0.99 (precision/->2dp (sut/convert 100.0 options/kilopascal options/atmosphere))))
    (is (= 750.06 (precision/->2dp (sut/convert 100.0 options/kilopascal options/torr))))
    (is (= 14.5 (precision/->2dp (sut/convert 100.0 options/kilopascal options/psi))))
    (is (= 0.1 (precision/->2dp (sut/convert 100.0 options/pascal options/kilopascal))))
    (is (= 10000.0 (precision/->2dp (sut/convert 100.0 options/bar options/kilopascal))))
    (is (= 10132.5 (precision/->2dp (sut/convert 100.0 options/atmosphere options/kilopascal))))
    (is (= 13.33 (precision/->2dp (sut/convert 100.0 options/torr options/kilopascal))))
    (is (= 689.48 (precision/->2dp (sut/convert 100.0 options/psi options/kilopascal)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 pa"
           (sut/display 1.5 :pascal)
           (sut/display 1.5 options/pascal)))
    (is (= "1.5 pascal"
           (sut/display 1.5 :pascal {options/suffix options/full}))))
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
    (testing "measurement->kilopascal"
      (is (map? sut/measurement->kilopascal))
      (is (not-empty sut/measurement->kilopascal))
      (is (every? keyword? (keys sut/measurement->kilopascal)))
      (is (every? ifn? (vals sut/measurement->kilopascal))))
    (testing "kilopascal->measurement"
      (is (map? sut/kilopascal->measurement))
      (is (not-empty sut/kilopascal->measurement))
      (is (every? keyword? (keys sut/kilopascal->measurement)))
      (is (= (set (keys sut/measurement->kilopascal))
             (set (keys sut/kilopascal->measurement))
             (set (keys sut/measurements->display-name))
             sut/measurements))
      (is (every? ifn? (vals sut/kilopascal->measurement))))))
