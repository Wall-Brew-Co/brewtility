(ns brewtility.color-test
  (:require [brewtility.color :as sut]
            [brewtility.precision :as bp]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest srm->rgba-test
  (testing "SRM color lookup behaves as expected"
    (is (= sut/srm-13 (sut/srm->rgba 13)))
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
