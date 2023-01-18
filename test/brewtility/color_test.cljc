(ns brewtility.color-test
  (:require [brewtility.color :as sut]
            [brewtility.precision :as bp]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest srm->rgba-test
  (testing "SRM color lookup behaves as expected"
    (is (= sut/srm-1 (sut/srm->rgba 1)))
    (is (= sut/srm-2 (sut/srm->rgba 2)))
    (is (= sut/srm-3 (sut/srm->rgba 3)))
    (is (= sut/srm-4 (sut/srm->rgba 4)))
    (is (= sut/srm-5 (sut/srm->rgba 5)))
    (is (= sut/srm-6 (sut/srm->rgba 6)))
    (is (= sut/srm-7 (sut/srm->rgba 7)))
    (is (= sut/srm-8 (sut/srm->rgba 8)))
    (is (= sut/srm-9 (sut/srm->rgba 9)))
    (is (= sut/srm-10 (sut/srm->rgba 10)))
    (is (= sut/srm-11 (sut/srm->rgba 11)))
    (is (= sut/srm-12 (sut/srm->rgba 12)))
    (is (= sut/srm-13 (sut/srm->rgba 13)))
    (is (= sut/srm-14 (sut/srm->rgba 14)))
    (is (= sut/srm-15 (sut/srm->rgba 15)))
    (is (= sut/srm-16 (sut/srm->rgba 16)))
    (is (= sut/srm-17 (sut/srm->rgba 17)))
    (is (= sut/srm-18 (sut/srm->rgba 18)))
    (is (= sut/srm-19 (sut/srm->rgba 19)))
    (is (= sut/srm-20 (sut/srm->rgba 20)))
    (is (= sut/srm-21 (sut/srm->rgba 21)))
    (is (= sut/srm-22 (sut/srm->rgba 22)))
    (is (= sut/srm-23 (sut/srm->rgba 23)))
    (is (= sut/srm-24 (sut/srm->rgba 24)))
    (is (= sut/srm-25 (sut/srm->rgba 25)))
    (is (= sut/srm-26 (sut/srm->rgba 26)))
    (is (= sut/srm-27 (sut/srm->rgba 27)))
    (is (= sut/srm-28 (sut/srm->rgba 28)))
    (is (= sut/srm-29 (sut/srm->rgba 29)))
    (is (= sut/srm-30 (sut/srm->rgba 30)))
    (is (= sut/srm-31 (sut/srm->rgba 31)))
    (is (= sut/srm-32 (sut/srm->rgba 32)))
    (is (= sut/srm-33 (sut/srm->rgba 33)))
    (is (= sut/srm-34 (sut/srm->rgba 34)))
    (is (= sut/srm-35 (sut/srm->rgba 35)))
    (is (= sut/srm-36 (sut/srm->rgba 36)))
    (is (= sut/srm-37 (sut/srm->rgba 37)))
    (is (= sut/srm-38 (sut/srm->rgba 38)))
    (is (= sut/srm-39 (sut/srm->rgba 39)))
    (is (= sut/srm-40 (sut/srm->rgba 40)))
    (is (= sut/srm-1  (sut/srm->rgba 0)))
    (is (= sut/srm-1  (sut/srm->rgba -1)))
    (is (= sut/srm-6  (sut/srm->rgba 6.2)))
    (is (= sut/srm-6  (sut/srm->rgba 6.8)))
    (is (= sut/srm-40 (sut/srm->rgba 41)))))


(deftest lovibond->rgba-test
  (testing "lovibond -> SRM -> rgba lookup behaves as expected"
    (is (= sut/srm-13 (sut/lovibond->rgba 10.16)))
    (is (= sut/srm-1  (sut/lovibond->rgba 0.56)))
    (is (= sut/srm-1  (sut/lovibond->rgba -0.18)))
    (is (= sut/srm-6  (sut/lovibond->rgba 5.14)))
    (is (= sut/srm-6  (sut/lovibond->rgba 5.58)))
    (is (= sut/srm-40 (sut/lovibond->rgba 30.83)))))


(deftest ebc->rgba-test
  (testing "EBC -> SRM -> rgba lookup behaves as expected"
    (is (= sut/srm-13 (sut/ebc->rgba 25.61)))
    (is (= sut/srm-1  (sut/ebc->rgba 0)))
    (is (= sut/srm-1  (sut/ebc->rgba -1.97)))
    (is (= sut/srm-6  (sut/ebc->rgba 12.21)))
    (is (= sut/srm-6  (sut/ebc->rgba 13.40)))
    (is (= sut/srm-40 (sut/ebc->rgba 80.77)))))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 10.0   (bp/->2dp (sut/lovibond->srm 7.94))  (bp/->2dp (sut/ebc->srm 19.69))))
    (is (= 23.2   (bp/->2dp (sut/srm->lovibond 30.66)) (bp/->2dp (sut/ebc->lovibond 60.38))))
    (is (= -45.53 (bp/->2dp (sut/srm->ebc -23.11))     (bp/->2dp (sut/lovibond->ebc -16.5))))))
