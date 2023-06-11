(ns brewtility.units.color-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.color :as sut]
            [brewtility.units.options :as options]
            #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])))


(deftest srm->rgba-test
  (testing "SRM color lookup behaves as expected"
    (is (= sut/srm-1 (sut/convert 1 options/srm options/rgba)))
    (is (= sut/srm-1 (sut/convert 1 :srm :rgba)))
    (is (= sut/srm-2 (sut/convert 2 options/srm options/rgba)))
    (is (= sut/srm-3 (sut/convert 3 options/srm options/rgba)))
    (is (= sut/srm-4 (sut/convert 4 options/srm options/rgba)))
    (is (= sut/srm-5 (sut/convert 5 options/srm options/rgba)))
    (is (= sut/srm-6 (sut/convert 6 options/srm options/rgba)))
    (is (= sut/srm-7 (sut/convert 7 options/srm options/rgba)))
    (is (= sut/srm-8 (sut/convert 8 options/srm options/rgba)))
    (is (= sut/srm-9 (sut/convert 9 options/srm options/rgba)))
    (is (= sut/srm-10 (sut/convert 10 options/srm options/rgba)))
    (is (= sut/srm-11 (sut/convert 11 options/srm options/rgba)))
    (is (= sut/srm-12 (sut/convert 12 options/srm options/rgba)))
    (is (= sut/srm-13 (sut/convert 13 options/srm options/rgba)))
    (is (= sut/srm-14 (sut/convert 14 options/srm options/rgba)))
    (is (= sut/srm-15 (sut/convert 15 options/srm options/rgba)))
    (is (= sut/srm-16 (sut/convert 16 options/srm options/rgba)))
    (is (= sut/srm-17 (sut/convert 17 options/srm options/rgba)))
    (is (= sut/srm-18 (sut/convert 18 options/srm options/rgba)))
    (is (= sut/srm-19 (sut/convert 19 options/srm options/rgba)))
    (is (= sut/srm-20 (sut/convert 20 options/srm options/rgba)))
    (is (= sut/srm-21 (sut/convert 21 options/srm options/rgba)))
    (is (= sut/srm-22 (sut/convert 22 options/srm options/rgba)))
    (is (= sut/srm-23 (sut/convert 23 options/srm options/rgba)))
    (is (= sut/srm-24 (sut/convert 24 options/srm options/rgba)))
    (is (= sut/srm-25 (sut/convert 25 options/srm options/rgba)))
    (is (= sut/srm-26 (sut/convert 26 options/srm options/rgba)))
    (is (= sut/srm-27 (sut/convert 27 options/srm options/rgba)))
    (is (= sut/srm-28 (sut/convert 28 options/srm options/rgba)))
    (is (= sut/srm-29 (sut/convert 29 options/srm options/rgba)))
    (is (= sut/srm-30 (sut/convert 30 options/srm options/rgba)))
    (is (= sut/srm-31 (sut/convert 31 options/srm options/rgba)))
    (is (= sut/srm-32 (sut/convert 32 options/srm options/rgba)))
    (is (= sut/srm-33 (sut/convert 33 options/srm options/rgba)))
    (is (= sut/srm-34 (sut/convert 34 options/srm options/rgba)))
    (is (= sut/srm-35 (sut/convert 35 options/srm options/rgba)))
    (is (= sut/srm-36 (sut/convert 36 options/srm options/rgba)))
    (is (= sut/srm-37 (sut/convert 37 options/srm options/rgba)))
    (is (= sut/srm-38 (sut/convert 38 options/srm options/rgba)))
    (is (= sut/srm-39 (sut/convert 39 options/srm options/rgba)))
    (is (= sut/srm-40 (sut/convert 40 options/srm options/rgba)))
    (is (= sut/srm-1  (sut/convert 0 options/srm options/rgba)))
    (is (= sut/srm-1  (sut/convert -1 options/srm options/rgba)))
    (is (= sut/srm-6  (sut/convert 6.2 options/srm options/rgba)))
    (is (= sut/srm-6  (sut/convert 6.8 options/srm options/rgba)))
    (is (= sut/srm-40 (sut/convert 41 options/srm options/rgba)))))


(deftest lovibond->rgba-test
  (testing "lovibond -> SRM -> rgba lookup behaves as expected"
    (is (= sut/srm-13 (sut/convert 10.16 options/lovibond options/rgba)))
    (is (= sut/srm-13 (sut/convert 10.16 :lovibond :rgba)))
    (is (= sut/srm-1  (sut/convert 0.56 options/lovibond options/rgba)))
    (is (= sut/srm-1  (sut/convert -0.18 options/lovibond options/rgba)))
    (is (= sut/srm-6  (sut/convert 5.14 options/lovibond options/rgba)))
    (is (= sut/srm-6  (sut/convert 5.58 options/lovibond options/rgba)))
    (is (= sut/srm-40 (sut/convert 30.83 options/lovibond options/rgba)))))


(deftest ebc->rgba-test
  (testing "EBC -> SRM -> rgba lookup behaves as expected"
    (is (= sut/srm-13 (sut/convert 25.61 options/ebc options/rgba)))
    (is (= sut/srm-13 (sut/convert 25.61 :ebc :rgba)))
    (is (= sut/srm-1  (sut/convert 0 options/ebc options/rgba)))
    (is (= sut/srm-1  (sut/convert -1.97 options/ebc options/rgba)))
    (is (= sut/srm-6  (sut/convert 12.21 options/ebc options/rgba)))
    (is (= sut/srm-6  (sut/convert 13.40 options/ebc options/rgba)))
    (is (= sut/srm-40 (sut/convert 80.77 options/ebc options/rgba)))))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 10.0
           (precision/->2dp (sut/convert 7.94 options/lovibond options/srm))
           (precision/->2dp (sut/convert 19.69 options/ebc options/srm))))
    (is (= 10.0
           (precision/->2dp (sut/convert 7.94 :lovibond :srm))
           (precision/->2dp (sut/convert 19.69 :ebc :srm))))
    (is (= 23.2
           (precision/->2dp (sut/convert 30.66 options/srm options/lovibond))
           (precision/->2dp (sut/convert 60.38 options/ebc options/lovibond))))
    (is (= -45.53
           (precision/->2dp (sut/convert -23.11 options/srm options/ebc))
           (precision/->2dp (sut/convert -16.5 options/lovibond options/ebc)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :srm))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :srm))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "10.0 srm"
           (sut/display 10.0 options/srm))
        (sut/display 10.0 :srm))
    (is (= "23.2 ebc"
           (sut/display 23.2 options/ebc))
        (sut/display 23.2 :ebc))
    (is (= "10.0 degrees lovibond"
           (sut/display 10.0 options/lovibond {options/suffix options/full}))
        (sut/display 10.0 :lovibond))
    (is (= sut/srm-37 (sut/display sut/srm-37 options/rgba))))
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
    (testing "SRM color map"
      (is (map? sut/srm-color-map))
      (is (not-empty sut/srm-color-map))
      (is (every? number? (keys sut/srm-color-map)))
      (is (every? string? (vals sut/srm-color-map))))
    (testing "measurement->srm"
      (is (map? sut/measurement->srm))
      (is (not-empty sut/measurement->srm))
      (is (every? keyword? (keys sut/measurement->srm)))
      (is (every? ifn? (vals sut/measurement->srm))))
    (testing "srm->measurement"
      (is (map? sut/srm->measurement))
      (is (not-empty sut/srm->measurement))
      (is (every? keyword? (keys sut/srm->measurement)))
      (is (= (sort (keys sut/srm->measurement))
             (sort (keys sut/measurements->display-name))
             (sort sut/measurements)))
      (is (every? ifn? (vals sut/srm->measurement))))))
