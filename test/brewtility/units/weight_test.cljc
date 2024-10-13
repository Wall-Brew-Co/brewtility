(ns brewtility.units.weight-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.weight :as sut]
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
    (testing "measurement->kilogram"
      (is (map? sut/measurement->kilogram))
      (is (not-empty sut/measurement->kilogram))
      (is (every? keyword? (keys sut/measurement->kilogram)))
      (is (every? number? (vals sut/measurement->kilogram))))
    (is (= (set (keys sut/measurement->kilogram))
           (set (keys sut/measurements->display-name))
           sut/measurements))))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/gram options/gram))
           (precision/->2dp (sut/convert 100.0 :gram :gram))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/kilogram options/kilogram))
           (precision/->2dp (sut/convert 100.0 :kilogram :kilogram))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/milligram options/milligram))
           (precision/->2dp (sut/convert 100.0 :milligram :milligram))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/ounce options/ounce))
           (precision/->2dp (sut/convert 100.0 :ounce :ounce))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/pound options/pound))
           (precision/->2dp (sut/convert 100.0 :pound :pound))))
    (is (= 0.1 (precision/->2dp (sut/convert 100.0 options/gram options/kilogram))))
    (is (= 100000.0 (precision/->2dp (sut/convert 100.0 options/gram options/milligram))))
    (is (= 3.53 (precision/->2dp (sut/convert 100.0 options/gram options/ounce))))
    (is (= 0.22 (precision/->2dp (sut/convert 100.0 options/gram options/pound))))
    (is (= 100000.0 (precision/->2dp (sut/convert 100.0 options/kilogram options/gram))))
    (is (= 0.1 (precision/->2dp (sut/convert 100.0 options/milligram options/gram))))
    (is (= 2834.95 (precision/->2dp (sut/convert 100.0 options/ounce options/gram))))
    (is (= 45359.24 (precision/->2dp (sut/convert 100.0 options/pound options/gram)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 g"
           (sut/display 1.5 :gram)
           (sut/display 1.5 options/gram)))
    (is (= "1.5 kg"
           (sut/display 1.5 :kilogram)
           (sut/display 1.5 options/kilogram)))
    (is (= "1.5 mg"
           (sut/display 1.5 :milligram)
           (sut/display 1.5 options/milligram)))
    (is (= "1.5 oz"
           (sut/display 1.5 :ounce)
           (sut/display 1.5 options/ounce)))
    (is (= "1.5 lb"
           (sut/display 1.5 :pound)
           (sut/display 1.5 options/pound)))
    (is (= "1.5 gram"
           (sut/display 1.5 :gram {options/suffix options/full})
           (sut/display 1.5 options/gram {options/suffix options/full})))
    (is (= "1.5 kilogram"
           (sut/display 1.5 :kilogram {options/suffix options/full})
           (sut/display 1.5 options/kilogram {options/suffix options/full})))
    (is (= "1.5 milligram"
           (sut/display 1.5 :milligram {options/suffix options/full})
           (sut/display 1.5 options/milligram {options/suffix options/full})))
    (is (= "1.5 ounce"
           (sut/display 1.5 :ounce {options/suffix options/full})
           (sut/display 1.5 options/ounce {options/suffix options/full})))
    (is (= "1.5 pound"
           (sut/display 1.5 :pound {options/suffix options/full})
           (sut/display 1.5 options/pound {options/suffix options/full}))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))))
