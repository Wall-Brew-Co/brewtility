(ns brewtility.units-test
  (:require [brewtility.units :as sut]
            [brewtility.precision :as bp]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest convert-volume-test
  (testing "Volume conversions should behave as expected"
    (is (= 1.0   (sut/convert-volume 1.0 :liter :litre)))
    (is (= 1.0   (sut/convert-volume 1.0 :litre :litre)))
    (is (= 0.099 (bp/->3dp (sut/convert-volume 20 :teaspoon :liter))))
    (is (= 35.1  (bp/->3dp (sut/convert-volume 35.1 :tablespoon :tablespoon))))
    (is (= 12.0  (bp/->3dp (sut/convert-volume 9.99209 :imperial-pint :american-pint))))))


(deftest convert-weight-test
  (testing "Weight conversions should behave as expected"
    (is (= 1.0     (sut/convert-weight 1.0 :kilogram :kilogram)))
    (is (= 1.0     (sut/convert-weight 1.0 :pound :pound)))
    (is (= 240.0   (bp/->3dp (sut/convert-weight 15.0 :pound :ounce))))
    (is (= 0.003   (bp/->3dp (sut/convert-weight 1205.5 :milligram :pound))))))


(deftest convert-temperature-test
  (testing "Temperature conversions should behave as expected"
    (is (= 32.0    (sut/convert-temperature 32.0 :fahrenheit :fahrenheit)))
    (is (= 32.0    (sut/convert-temperature 0.0 :celsius :fahrenheit)))
    (is (= 340.706 (bp/->3dp (sut/convert-temperature 153.6008 :fahrenheit :kelvin))))
    (is (= 1743.15 (bp/->3dp (sut/convert-temperature 2016.3 :kelvin :c))))
    (is (= -40.0   (bp/->3dp (sut/convert-temperature -40.0 :f :c))))))
