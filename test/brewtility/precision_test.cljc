(ns brewtility.precision-test
  (:require [brewtility.precision :as sut]
            [clojure.test :refer [deftest is testing]]))


(deftest approximates?-test
  (testing "Ensure approximation works as expected"
    (is (true? (sut/approximates? 100 100 0.00001))
        "Equal numbers will always return true")
    (is (let [r-int (rand-int 1000000)]
          (true? (sut/approximates? r-int r-int 0.00001)))
        "Equal integers will always return true")
    (is (true? (sut/approximates? 100 90 0.1))
        "90 is within 10% of 100")
    (is (false? (sut/approximates? 100 90 0.01))
        "90 is not within 1% of 100"))
  (testing "Ensure that non-numeric values throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Cannot approximate using non-numeric values" (sut/approximates? nil 100 0.00001))))
    #?(:clj (is (thrown-with-msg? Exception #"Cannot approximate using non-numeric values" (sut/approximates? 100 nil 0.00001))))
    #?(:clj (is (thrown-with-msg? Exception #"Cannot approximate using non-numeric values" (sut/approximates? 100 100 nil))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Cannot approximate using non-numeric values" (sut/approximates? nil 100 0.00001))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Cannot approximate using non-numeric values" (sut/approximates? 100 nil 0.00001))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Cannot approximate using non-numeric values" (sut/approximates? 100 100 nil))))))


(deftest ->1dp-test
  (testing "Ensure that rounding to 1 decimal point works."
    (is (= 0.0 (sut/->1dp 0.0)))
    (is (= 0.0 (sut/->1dp 0.001)))
    (is (= 5.1 (sut/->1dp 5.05)))
    (is (= 5.0 (sut/->1dp 5.04)))
    (is (= -12.3 (sut/->1dp -12.3)))))


(deftest ->2dp-test
  (testing "Ensure that rounding to 2 decimal point works."
    (is (= 0.00 (sut/->2dp 0.0)))
    (is (= 0.00 (sut/->2dp 0.001)))
    (is (= 5.05 (sut/->2dp 5.0513)))
    (is (= 5.06 (sut/->2dp 5.0553)))
    (is (= -12.30 (sut/->2dp -12.3)))
    (is (= 100.01 (sut/->2dp 100.005)))))


(deftest ->3dp-test
  (testing "Ensure that rounding to 3 decimal point works."
    (is (= 0.000 (sut/->3dp 0.0)))
    (is (= 0.001 (sut/->3dp 0.001)))
    (is (= 5.050 (sut/->3dp 5.05)))
    (is (= 5.055 (sut/->3dp 5.055)))
    (is (= 5.055 (sut/->3dp 5.0554)))
    (is (= 5.056 (sut/->3dp 5.0555)))
    (is (= -12.300 (sut/->3dp -12.3)))
    (is (= 100.005 (sut/->3dp 100.005)))
    (is (= 404.001 (sut/->3dp 404.0009654)))))
