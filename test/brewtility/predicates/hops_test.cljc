(ns brewtility.predicates.hops-test
  (:require #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])
            [brewtility.data.hops :as hops]
            [brewtility.predicates.hops :as sut]
            [common-beer-format.hops :as cbf-hops]))


;;
;; :use function tests
;;

(deftest boil?-test
  (testing "A hop with a `:use` matching `\"boil\"` returns true"
    (is (true? (sut/boil? (assoc hops/sample-hop :use "Boil"))))
    (is (true? (sut/boil? (assoc hops/sample-hop cbf-hops/use cbf-hops/boil)))))
  (testing "A hop with a `:use` not matching `\"boil\"` returns false"
    (is (false? (sut/boil? (assoc hops/sample-hop :use "DRY Hop"))))
    (is (false? (sut/boil? (assoc hops/sample-hop :use "Mash"))))
    (is (false? (sut/boil? (assoc hops/sample-hop :use "aroma"))))
    (is (false? (sut/boil? (assoc hops/sample-hop :use "first Wort"))))
    (is (false? (sut/boil? (assoc hops/sample-hop cbf-hops/use cbf-hops/dry-hop))))
    (is (false? (sut/boil? (assoc hops/sample-hop cbf-hops/use cbf-hops/mash))))
    (is (false? (sut/boil? (assoc hops/sample-hop cbf-hops/use cbf-hops/aroma))))
    (is (false? (sut/boil? (assoc hops/sample-hop cbf-hops/use cbf-hops/first-wort)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/boil? hops/sample-hop)))
    (is (boolean? (sut/boil? (hops/generate-hop)))))
  (testing "If the hop is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :use"
                  (sut/boil? (dissoc hops/sample-hop :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :use"
                   (sut/boil? (dissoc hops/sample-hop :use)))))))


(deftest dry-hop?-test
  (testing "A hop with a `:use` matching `\"dry hop\"` returns true"
    (is (true? (sut/dry-hop? (assoc hops/sample-hop :use "Dry Hop"))))
    (is (true? (sut/dry-hop? (assoc hops/sample-hop :use cbf-hops/dry-hop)))))
  (testing "A hop with a `:use` not matching `\"dry hop\"` returns false"
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use "boil"))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use "mASh"))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use "aroMA"))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use "first Wort"))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use cbf-hops/boil))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use cbf-hops/mash))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use cbf-hops/aroma))))
    (is (false? (sut/dry-hop? (assoc hops/sample-hop :use cbf-hops/first-wort)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/dry-hop? hops/sample-hop)))
    (is (boolean? (sut/dry-hop? (hops/generate-hop)))))
  (testing "If the hop is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :use"
                  (sut/dry-hop? (dissoc hops/sample-hop :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :use"
                   (sut/dry-hop? (dissoc hops/sample-hop :use)))))))


(deftest mash?-test
  (testing "A hop with a `:use` matching `\"mash\"` returns true"
    (is (true? (sut/mash? (assoc hops/sample-hop :use "mash"))))
    (is (true? (sut/mash? (assoc hops/sample-hop :use cbf-hops/mash)))))
  (testing "A hop with a `:use` not matching `\"mash\"` returns false"
    (is (false? (sut/mash? (assoc hops/sample-hop :use "DRY Hop"))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use "bOIL"))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use "aroma"))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use "first Wort"))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use cbf-hops/dry-hop))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use cbf-hops/boil))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use cbf-hops/aroma))))
    (is (false? (sut/mash? (assoc hops/sample-hop :use cbf-hops/first-wort)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/mash? hops/sample-hop)))
    (is (boolean? (sut/mash? (hops/generate-hop)))))
  (testing "If the hop is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :use"
                  (sut/mash? (dissoc hops/sample-hop :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :use"
                   (sut/mash? (dissoc hops/sample-hop :use)))))))


(deftest first-wort?-test
  (testing "A hop with a `:use` matching `\"first wort\"` returns true"
    (is (true? (sut/first-wort? (assoc hops/sample-hop :use "first wort"))))
    (is (true? (sut/first-wort? (assoc hops/sample-hop :use cbf-hops/first-wort)))))
  (testing "A hop with a `:use` not matching `\"first wort\"` returns false"
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use "DRY Hop"))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use "bOIL"))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use "aroma"))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use "mash"))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use cbf-hops/dry-hop))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use cbf-hops/boil))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use cbf-hops/aroma))))
    (is (false? (sut/first-wort? (assoc hops/sample-hop :use cbf-hops/mash)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/first-wort? hops/sample-hop)))
    (is (boolean? (sut/first-wort? (hops/generate-hop)))))
  (testing "If the hop is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :use"
                  (sut/first-wort? (dissoc hops/sample-hop :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :use"
                   (sut/first-wort? (dissoc hops/sample-hop :use)))))))


(deftest aroma-use?-test
  (testing "A hop with a `:use` matching `\"aroma\"` returns true"
    (is (true? (sut/aroma-use? (assoc hops/sample-hop :use "aroma"))))
    (is (true? (sut/aroma-use? (assoc hops/sample-hop :use cbf-hops/aroma)))))
  (testing "A hop with a `:use` not matching `\"aroma\"` returns false"
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use "DRY Hop"))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use "bOIL"))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use "mash"))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use "first Wort"))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use cbf-hops/dry-hop))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use cbf-hops/boil))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use cbf-hops/mash))))
    (is (false? (sut/aroma-use? (assoc hops/sample-hop :use cbf-hops/first-wort)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/aroma-use? hops/sample-hop)))
    (is (boolean? (sut/aroma-use? (hops/generate-hop)))))
  (testing "If the hop is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :use"
                  (sut/aroma-use? (dissoc hops/sample-hop :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :use"
                   (sut/aroma-use? (dissoc hops/sample-hop :use)))))))


;;
;; :type function tests
;;

(deftest aroma-type?-test
  (testing "A hop with a `:type` matching `\"aroma\"` returns true"
    (is (true? (sut/aroma-type? (assoc hops/sample-hop :type "aroma"))))
    (is (true? (sut/aroma-type? (assoc hops/sample-hop cbf-hops/type cbf-hops/aroma)))))
  (testing "A hop with a `:type` not matching `\"aroma\"` returns false"
    (is (false? (sut/aroma-type? (assoc hops/sample-hop :type "bittering"))))
    (is (false? (sut/aroma-type? (assoc hops/sample-hop :type "both"))))
    (is (false? (sut/aroma-type? (assoc hops/sample-hop cbf-hops/type cbf-hops/bittering))))
    (is (false? (sut/aroma-type? (assoc hops/sample-hop cbf-hops/type cbf-hops/both)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/aroma-type? (assoc hops/sample-hop :type (hops/random-hop-type)))))
    (is (boolean? (sut/aroma-type? (assoc (hops/generate-hop) :type (hops/random-hop-type))))))
  (testing "If the hop is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :type"
                  (sut/aroma-type? (dissoc hops/sample-hop :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :type"
                   (sut/aroma-type? (dissoc hops/sample-hop :type)))))))


(deftest bittering?-test
  (testing "A hop with a `:type` matching `\"bittering\"` returns true"
    (is (true? (sut/bittering? (assoc hops/sample-hop :type "bittering"))))
    (is (true? (sut/bittering? (assoc hops/sample-hop :type cbf-hops/bittering)))))
  (testing "A hop with a `:type` not matching `\"bittering\"` returns false"
    (is (false? (sut/bittering? (assoc hops/sample-hop :type "aroma"))))
    (is (false? (sut/bittering? (assoc hops/sample-hop :type "both"))))
    (is (false? (sut/bittering? (assoc hops/sample-hop :type cbf-hops/aroma))))
    (is (false? (sut/bittering? (assoc hops/sample-hop :type cbf-hops/both)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/bittering? (assoc hops/sample-hop :type (hops/random-hop-type)))))
    (is (boolean? (sut/bittering? (assoc (hops/generate-hop) :type (hops/random-hop-type))))))
  (testing "If the hop is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :type"
                  (sut/bittering? (dissoc hops/sample-hop :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :type"
                   (sut/bittering? (dissoc hops/sample-hop :type)))))))


(deftest both?-test
  (testing "A hop with a `:type` matching `\"both\"` returns true"
    (is (true? (sut/both? (assoc hops/sample-hop :type "both"))))
    (is (true? (sut/both? (assoc hops/sample-hop :type cbf-hops/both)))))
  (testing "A hop with a `:type` not matching `\"both\"` returns false"
    (is (false? (sut/both? (assoc hops/sample-hop :type "aroma"))))
    (is (false? (sut/both? (assoc hops/sample-hop :type "bittering"))))
    (is (false? (sut/both? (assoc hops/sample-hop :type cbf-hops/aroma))))
    (is (false? (sut/both? (assoc hops/sample-hop :type cbf-hops/bittering)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/both? (assoc hops/sample-hop :type (hops/random-hop-type)))))
    (is (boolean? (sut/both? (assoc (hops/generate-hop) :type (hops/random-hop-type))))))
  (testing "If the hop is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :type"
                  (sut/both? (dissoc hops/sample-hop :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :type"
                   (sut/both? (dissoc hops/sample-hop :type)))))))


;;
;; :form function tests
;;


(deftest pellet?-test
  (testing "A hop with a `:form` matching `\"pellet\"` returns true"
    (is (true? (sut/pellet? (assoc hops/sample-hop :form "pellet"))))
    (is (true? (sut/pellet? (assoc hops/sample-hop :form cbf-hops/pellet)))))
  (testing "A hop with a `:form` not matching `\"pellet\"` returns false"
    (is (false? (sut/pellet? (assoc hops/sample-hop :form "plug"))))
    (is (false? (sut/pellet? (assoc hops/sample-hop :form "lEAf"))))
    (is (false? (sut/pellet? (assoc hops/sample-hop :form cbf-hops/plug))))
    (is (false? (sut/pellet? (assoc hops/sample-hop :form cbf-hops/leaf)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/pellet? (assoc hops/sample-hop :form (hops/random-hop-form)))))
    (is (boolean? (sut/pellet? (assoc (hops/generate-hop) :form (hops/random-hop-form))))))
  (testing "If the hop is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :form"
                  (sut/pellet? (dissoc hops/sample-hop :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :form"
                   (sut/pellet? (dissoc hops/sample-hop :form)))))))


(deftest plug?-test
  (testing "A hop with a `:form` matching `\"plug\"` returns true"
    (is (true? (sut/plug? (assoc hops/sample-hop :form "plug"))))
    (is (true? (sut/plug? (assoc hops/sample-hop cbf-hops/form cbf-hops/plug)))))
  (testing "A hop with a `:form` not matching `\"plug\"` returns false"
    (is (false? (sut/plug? (assoc hops/sample-hop :form "pELLET"))))
    (is (false? (sut/plug? (assoc hops/sample-hop :form "lEAf"))))
    (is (false? (sut/plug? (assoc hops/sample-hop cbf-hops/form cbf-hops/pellet))))
    (is (false? (sut/plug? (assoc hops/sample-hop cbf-hops/form cbf-hops/leaf)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/plug? (assoc hops/sample-hop :form (hops/random-hop-form)))))
    (is (boolean? (sut/plug? (assoc (hops/generate-hop) :form (hops/random-hop-form))))))
  (testing "If the hop is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :form"
                  (sut/plug? (dissoc hops/sample-hop :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :form"
                   (sut/plug? (dissoc hops/sample-hop :form)))))))


(deftest leaf?-test
  (testing "A hop with a `:form` matching `\"leaf\"` returns true"
    (is (true? (sut/leaf? (assoc hops/sample-hop :form "leaf"))))
    (is (true? (sut/leaf? (assoc hops/sample-hop :form cbf-hops/leaf)))))
  (testing "A hop with a `:form` not matching `\"leaf\"` returns false"
    (is (false? (sut/leaf? (assoc hops/sample-hop :form "pELLET"))))
    (is (false? (sut/leaf? (assoc hops/sample-hop :form "plug"))))
    (is (false? (sut/leaf? (assoc hops/sample-hop :form cbf-hops/pellet))))
    (is (false? (sut/leaf? (assoc hops/sample-hop :form cbf-hops/plug)))))
  (testing "This is a function from a hop to a boolean"
    (is (boolean? (sut/leaf? (assoc hops/sample-hop :form (hops/random-hop-form)))))
    (is (boolean? (sut/leaf? (assoc (hops/generate-hop) :form (hops/random-hop-form))))))
  (testing "If the hop is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Hop :form"
                  (sut/leaf? (dissoc hops/sample-hop :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Hop :form"
                   (sut/leaf? (dissoc hops/sample-hop :form)))))))
