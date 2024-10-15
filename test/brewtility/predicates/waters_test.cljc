(ns brewtility.predicates.waters-test
  (:require [brewtility.data.waters :as waters]
            [brewtility.predicates.waters :as sut]
            [clojure.spec.alpha :as spec]
            [clojure.test :refer [deftest is testing]]
            [clojure.test.check.clojure-test :as check.test]
            [clojure.test.check.properties :as prop]
            [common-beer-format.waters :as cbf-waters]))


;;
;; :ph function tests
;;

(deftest acidic?-test
  (testing "A water with a `:ph` beneath `7` returns true"
    (is (true? (sut/acidic? (assoc waters/sample-water :ph 6))))
    (is (true? (sut/acidic? (assoc waters/sample-water cbf-waters/ph 6)))))
  (testing "A water with a `:ph` equal to or above `7` returns false"
    (is (false? (sut/acidic? (assoc waters/sample-water :ph 7))))
    (is (false? (sut/acidic? (assoc waters/sample-water :ph 7.0))))
    (is (false? (sut/acidic? (assoc waters/sample-water :ph 9))))
    (is (false? (sut/acidic? (assoc waters/sample-water cbf-waters/ph 7))))
    (is (false? (sut/acidic? (assoc waters/sample-water cbf-waters/ph 7.0))))
    (is (false? (sut/acidic? (assoc waters/sample-water cbf-waters/ph 9)))))
  (testing "This is a function from a water to a boolean"
    (is (boolean? (sut/acidic? (assoc waters/sample-water :ph (waters/random-ph)))))
    (is (boolean? (sut/acidic? (assoc (waters/generate-water) :ph (waters/random-ph))))))
  (testing "If the water is missing `:ph`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Water `:ph`"
                  (sut/acidic? (dissoc waters/sample-water :ph)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Water `:ph`"
                   (sut/acidic? (dissoc waters/sample-water :ph)))))))


(deftest alkaline?-test
  (testing "A water with a `:ph` above `7` returns true"
    (is (true? (sut/alkaline? (assoc waters/sample-water :ph 9))))
    (is (true? (sut/alkaline? (assoc waters/sample-water cbf-waters/ph 9)))))
  (testing "A water with a `:ph` equal to or beneath `7` returns false"
    (is (false? (sut/alkaline? (assoc waters/sample-water :ph 7))))
    (is (false? (sut/alkaline? (assoc waters/sample-water :ph 7.0))))
    (is (false? (sut/alkaline? (assoc waters/sample-water :ph 2))))
    (is (false? (sut/alkaline? (assoc waters/sample-water cbf-waters/ph 7))))
    (is (false? (sut/alkaline? (assoc waters/sample-water cbf-waters/ph 7.0))))
    (is (false? (sut/alkaline? (assoc waters/sample-water cbf-waters/ph 2)))))
  (testing "This is a function from a water to a boolean"
    (is (boolean? (sut/alkaline? (assoc waters/sample-water :ph (waters/random-ph)))))
    (is (boolean? (sut/alkaline? (assoc (waters/generate-water) :ph (waters/random-ph))))))
  (testing "If the water is missing `:ph`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Water `:ph`"
                  (sut/alkaline? (dissoc waters/sample-water :ph)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Water `:ph`"
                   (sut/alkaline? (dissoc waters/sample-water :ph)))))))


(deftest neutral?-test
  (testing "A water with a `:ph` equal to `7` returns true"
    (is (true? (sut/neutral? (assoc waters/sample-water :ph 7))))
    (is (true? (sut/neutral? (assoc waters/sample-water :ph 7.0))))
    (is (true? (sut/neutral? (assoc waters/sample-water cbf-waters/ph 7))))
    (is (true? (sut/neutral? (assoc waters/sample-water cbf-waters/ph 7.0)))))
  (testing "A water with a `:ph` not equal to `7` returns false"
    (is (false? (sut/neutral? (assoc waters/sample-water :ph 2))))
    (is (false? (sut/neutral? (assoc waters/sample-water :ph 9))))
    (is (false? (sut/neutral? (assoc waters/sample-water cbf-waters/ph 2))))
    (is (false? (sut/neutral? (assoc waters/sample-water cbf-waters/ph 9)))))
  (testing "This is a function from a water to a boolean"
    (is (boolean? (sut/neutral? (assoc waters/sample-water :ph (waters/random-ph)))))
    (is (boolean? (sut/neutral? (assoc (waters/generate-water) :ph (waters/random-ph))))))
  (testing "If the water is missing `:ph`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Water `:ph`"
                  (sut/neutral? (dissoc waters/sample-water :ph)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Water `:ph`"
                   (sut/neutral? (dissoc waters/sample-water :ph)))))))


(declare acidic?-boolean
         alkaline?-boolean
         neutral?-boolean)


(check.test/defspec
  acidic?-boolean 100
  (prop/for-all
    [water (spec/gen ::cbf-waters/water)]
    (boolean? (sut/acidic? (assoc water cbf-waters/ph (waters/random-ph))))))


(check.test/defspec
  alkaline?-boolean 100
  (prop/for-all
    [water (spec/gen ::cbf-waters/water)]
    (boolean? (sut/alkaline? (assoc water cbf-waters/ph (waters/random-ph))))))


(check.test/defspec
  neutral?-boolean 100
  (prop/for-all
    [water (spec/gen ::cbf-waters/water)]
    (boolean? (sut/neutral? (assoc water cbf-waters/ph (waters/random-ph))))))
