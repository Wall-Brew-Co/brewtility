(ns brewtility.units.temperature-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.temperature :as sut]
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
    (testing "measurement->celsius"
      (is (map? sut/measurement->celsius))
      (is (not-empty sut/measurement->celsius))
      (is (every? keyword? (keys sut/measurement->celsius)))
      (is (every? ifn? (vals sut/measurement->celsius))))
    (testing "celsius->measurement"
      (is (map? sut/celsius->measurement))
      (is (not-empty sut/celsius->measurement))
      (is (every? keyword? (keys sut/celsius->measurement)))
      (is (= (set (keys sut/measurement->celsius))
             (set (keys sut/celsius->measurement))
             (set (keys sut/measurements->display-name))
             sut/measurements))
      (is (every? ifn? (vals sut/celsius->measurement))))))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (testing "Source and target systems are identical"
      (is (= 100.0
             (precision/->2dp (sut/convert 100.0 options/c options/c))
             (precision/->2dp (sut/convert 100.0 :celsius :c))))
      (is (= 100.0
             (precision/->2dp (sut/convert 100.0 options/f options/fahrenheit))
             (precision/->2dp (sut/convert 100.0 :f :f))))
      (is (= 100.0
             (precision/->2dp (sut/convert 100.0 options/k options/kelvin))
             (precision/->2dp (sut/convert 100.0 :k :k))))
      (is (= 100.0
             (precision/->2dp (sut/convert 100.0 options/centigrade options/centigrade))
             (precision/->2dp (sut/convert 100.0 :centigrade :centigrade)))))
    (testing "Source and target systems differ"
      (is (= 212.0 (precision/->2dp (sut/convert 100 options/celsius options/fahrenheit))))
      (is (= 363.15 (precision/->2dp (sut/convert 90 options/celsius options/kelvin))))
      (is (= 100.0 (precision/->2dp (sut/convert 100.0 options/celsius options/centigrade))))
      (is (= 32.22 (precision/->2dp (sut/convert 90 options/fahrenheit options/celsius))))
      (is (= 305.37 (precision/->2dp (sut/convert 90 options/fahrenheit options/kelvin))))
      (is (= 32.22 (precision/->2dp (sut/convert 90 options/fahrenheit options/centigrade))))
      (is (= -173.15 (precision/->2dp (sut/convert 100 options/kelvin options/celsius))))
      (is (= -297.67 (precision/->2dp (sut/convert 90 options/kelvin options/fahrenheit))))
      (is (= -183.15 (precision/->2dp (sut/convert 90 options/kelvin options/centigrade))))
      (is (= 100.0 (precision/->2dp (sut/convert 100 options/centigrade options/celsius))))
      (is (= 212.0 (precision/->2dp (sut/convert 100 options/centigrade options/fahrenheit))))
      (is (= 373.15 (precision/->2dp (sut/convert 100 options/centigrade options/kelvin))))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 c"
           (sut/display 1.5 :celsius)
           (sut/display 1.5 options/celsius)
           (sut/display 1.5 options/c)))
    (is (= "1.5 k"
           (sut/display 1.5 :kelvin)
           (sut/display 1.5 options/kelvin)
           (sut/display 1.5 options/k)))
    (is (= "1.5 f"
           (sut/display 1.5 :fahrenheit)
           (sut/display 1.5 options/fahrenheit)
           (sut/display 1.5 options/f)))
    (is (= "1.5 mg"
           (sut/display 1.5 options/centigrade)))
    (is (= "1.5 celsius"
           (sut/display 1.5 :celsius {options/suffix options/full})
           (sut/display 1.5 options/celsius {options/suffix options/full})
           (sut/display 1.5 options/c {options/suffix options/full})))
    (is (= "1.5 kelvin"
           (sut/display 1.5 :kelvin {options/suffix options/full})
           (sut/display 1.5 options/kelvin {options/suffix options/full})
           (sut/display 1.5 options/k {options/suffix options/full})))
    (is (= "1.5 fahrenheit"
           (sut/display 1.5 :fahrenheit {options/suffix options/full})
           (sut/display 1.5 options/fahrenheit {options/suffix options/full})
           (sut/display 1.5 options/f {options/suffix options/full})))
    (is (= "1.5 centigrade"
           (sut/display 1.5 options/centigrade {options/suffix options/full}))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))))
