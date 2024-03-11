(ns brewtility.units.alcohol-content-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.precision :as precision]
            [brewtility.units.alcohol-content :as sut]
            [brewtility.units.options :as options]))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/abv options/abv))
           (precision/->2dp (sut/convert 100.0 :abv :abv)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "15.3% abv"
           (sut/display 0.153 :abv)
           (sut/display 0.153 options/abv))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display nil :abv))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display nil :abv))))))


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
    (testing "measurement->abv"
      (is (map? sut/measurement->abv))
      (is (not-empty sut/measurement->abv))
      (is (every? keyword? (keys sut/measurement->abv)))
      (is (every? ifn? (vals sut/measurement->abv))))
    (testing "abv->measurement"
      (is (map? sut/abv->measurement))
      (is (not-empty sut/abv->measurement))
      (is (every? keyword? (keys sut/abv->measurement)))
      (is (= (set (keys sut/measurement->abv))
             (set (keys sut/abv->measurement))
             (set (keys sut/measurements->display-name))
             sut/measurements))
      (is (every? ifn? (vals sut/abv->measurement))))))
