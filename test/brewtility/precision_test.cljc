(ns brewtility.precision-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.precision :as sut]))


(deftest approximates?-test
  (testing "Ensure approximation works as expected"
    (is (true? (sut/approximates? 100 100 0.00001)))
    (is (true? (sut/approximates? 100 90 0.1)))
    (is (false? (sut/approximates? 100 90 0.01)))))


(deftest ->1dp-test
  (testing "Ensure that rounding to 1 decimal point works."
    (is (= 0.0 (sut/->1dp 0.0)))
    (is (= 0.0 (sut/->1dp 0.001)))
    (is (= 5.1 (sut/->1dp 5.05)))
    (is (= -12.3 (sut/->1dp -12.3)))))


(deftest ->2dp-test
  (testing "Ensure that rounding to 2 decimal point works."
    (is (= 0.00 (sut/->2dp 0.0)))
    (is (= 0.00 (sut/->2dp 0.001)))
    (is (= 5.05 (sut/->2dp 5.0513)))
    (is (= -12.30 (sut/->2dp -12.3)))
    (is (= 100.01 (sut/->2dp 100.005)))))


(deftest ->3dp-test
  (testing "Ensure that rounding to 3 decimal point works."
    (is (= 0.000 (sut/->3dp 0.0)))
    (is (= 0.001 (sut/->3dp 0.001)))
    (is (= 5.050 (sut/->3dp 5.05)))
    (is (= -12.300 (sut/->3dp -12.3)))
    (is (= 100.005 (sut/->3dp 100.005)))
    (is (= 404.001 (sut/->3dp 404.0009654)))))
