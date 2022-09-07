(ns brewtility.predicates.styles-test
  (:require [brewtility.data.styles :as styles]
            [brewtility.predicates.styles :as sut]
            [clojure.test :refer [deftest is testing]]
            [common-beer-format.styles :as cbf-styles]))



;;
;; :type function tests
;;

(deftest lager?-test
  (testing "A style with a `:type` matching `\"lager\"` returns true"
    (is (true? (sut/lager? (assoc styles/sample-style :type "lager"))))
    (is (true? (sut/lager? (assoc styles/sample-style cbf-styles/type cbf-styles/lager)))))
  (testing "A style with a `:type` not matching `\"lager\"` returns false"
    (is (false? (sut/lager? (assoc styles/sample-style :type "ale"))))
    (is (false? (sut/lager? (assoc styles/sample-style :type "mead"))))
    (is (false? (sut/lager? (assoc styles/sample-style :type "wheat"))))
    (is (false? (sut/lager? (assoc styles/sample-style :type "mixed"))))
    (is (false? (sut/lager? (assoc styles/sample-style :type "cider"))))
    (is (false? (sut/lager? (assoc styles/sample-style cbf-styles/type cbf-styles/ale))))
    (is (false? (sut/lager? (assoc styles/sample-style cbf-styles/type cbf-styles/mead))))
    (is (false? (sut/lager? (assoc styles/sample-style cbf-styles/type cbf-styles/wheat))))
    (is (false? (sut/lager? (assoc styles/sample-style cbf-styles/type cbf-styles/mixed))))
    (is (false? (sut/lager? (assoc styles/sample-style cbf-styles/type cbf-styles/cider)))))
  (testing "This is a function from a style to a boolean"
    (is (boolean? (sut/lager? styles/sample-style)))
    (is (boolean? (sut/lager? (styles/generate-style)))))
  (testing "If the style is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Style :type"
                  (sut/lager? (dissoc styles/sample-style :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Style :type"
                   (sut/lager? (dissoc styles/sample-style :type)))))))


(deftest ale?-test
  (testing "A style with a `:type` matching `\"ale\"` returns true"
    (is (true? (sut/ale? (assoc styles/sample-style :type "ale"))))
    (is (true? (sut/ale? (assoc styles/sample-style cbf-styles/type cbf-styles/ale)))))
  (testing "A style with a `:type` not matching `\"ale\"` returns false"
    (is (false? (sut/ale? (assoc styles/sample-style :type "lager"))))
    (is (false? (sut/ale? (assoc styles/sample-style :type "mead"))))
    (is (false? (sut/ale? (assoc styles/sample-style :type "wheat"))))
    (is (false? (sut/ale? (assoc styles/sample-style :type "mixed"))))
    (is (false? (sut/ale? (assoc styles/sample-style :type "cider"))))
    (is (false? (sut/ale? (assoc styles/sample-style cbf-styles/type cbf-styles/lager))))
    (is (false? (sut/ale? (assoc styles/sample-style cbf-styles/type cbf-styles/mead))))
    (is (false? (sut/ale? (assoc styles/sample-style cbf-styles/type cbf-styles/wheat))))
    (is (false? (sut/ale? (assoc styles/sample-style cbf-styles/type cbf-styles/mixed))))
    (is (false? (sut/ale? (assoc styles/sample-style cbf-styles/type cbf-styles/cider)))))
  (testing "This is a function from a style to a boolean"
    (is (boolean? (sut/ale? styles/sample-style)))
    (is (boolean? (sut/ale? (styles/generate-style)))))
  (testing "If the style is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Style :type"
                  (sut/ale? (dissoc styles/sample-style :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Style :type"
                   (sut/ale? (dissoc styles/sample-style :type)))))))


(deftest mead?-test
  (testing "A style with a `:type` matching `\"mead\"` returns true"
    (is (true? (sut/mead? (assoc styles/sample-style :type "mead"))))
    (is (true? (sut/mead? (assoc styles/sample-style cbf-styles/type cbf-styles/mead)))))
  (testing "A style with a `:type` not matching `\"mead\"` returns false"
    (is (false? (sut/mead? (assoc styles/sample-style :type "lager"))))
    (is (false? (sut/mead? (assoc styles/sample-style :type "ale"))))
    (is (false? (sut/mead? (assoc styles/sample-style :type "wheat"))))
    (is (false? (sut/mead? (assoc styles/sample-style :type "mixed"))))
    (is (false? (sut/mead? (assoc styles/sample-style :type "cider"))))
    (is (false? (sut/mead? (assoc styles/sample-style cbf-styles/type cbf-styles/lager))))
    (is (false? (sut/mead? (assoc styles/sample-style cbf-styles/type cbf-styles/ale))))
    (is (false? (sut/mead? (assoc styles/sample-style cbf-styles/type cbf-styles/wheat))))
    (is (false? (sut/mead? (assoc styles/sample-style cbf-styles/type cbf-styles/mixed))))
    (is (false? (sut/mead? (assoc styles/sample-style cbf-styles/type cbf-styles/cider)))))
  (testing "This is a function from a style to a boolean"
    (is (boolean? (sut/mead? styles/sample-style)))
    (is (boolean? (sut/mead? (styles/generate-style)))))
  (testing "If the style is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Style :type"
                  (sut/mead? (dissoc styles/sample-style :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Style :type"
                   (sut/mead? (dissoc styles/sample-style :type)))))))


(deftest wheat?-test
  (testing "A style with a `:type` matching `\"wheat\"` returns true"
    (is (true? (sut/wheat? (assoc styles/sample-style :type "wheat"))))
    (is (true? (sut/wheat? (assoc styles/sample-style cbf-styles/type cbf-styles/wheat)))))
  (testing "A style with a `:type` not matching `\"wheat\"` returns false"
    (is (false? (sut/wheat? (assoc styles/sample-style :type "lager"))))
    (is (false? (sut/wheat? (assoc styles/sample-style :type "ale"))))
    (is (false? (sut/wheat? (assoc styles/sample-style :type "mead"))))
    (is (false? (sut/wheat? (assoc styles/sample-style :type "mixed"))))
    (is (false? (sut/wheat? (assoc styles/sample-style :type "cider"))))
    (is (false? (sut/wheat? (assoc styles/sample-style cbf-styles/type cbf-styles/lager))))
    (is (false? (sut/wheat? (assoc styles/sample-style cbf-styles/type cbf-styles/ale))))
    (is (false? (sut/wheat? (assoc styles/sample-style cbf-styles/type cbf-styles/mead))))
    (is (false? (sut/wheat? (assoc styles/sample-style cbf-styles/type cbf-styles/mixed))))
    (is (false? (sut/wheat? (assoc styles/sample-style cbf-styles/type cbf-styles/cider)))))
  (testing "This is a function from a style to a boolean"
    (is (boolean? (sut/wheat? styles/sample-style)))
    (is (boolean? (sut/wheat? (styles/generate-style)))))
  (testing "If the style is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Style :type"
                  (sut/wheat? (dissoc styles/sample-style :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Style :type"
                   (sut/wheat? (dissoc styles/sample-style :type)))))))


(deftest mixed?-test
  (testing "A style with a `:type` matching `\"mixed\"` returns true"
    (is (true? (sut/mixed? (assoc styles/sample-style :type "mixed"))))
    (is (true? (sut/mixed? (assoc styles/sample-style cbf-styles/type cbf-styles/mixed)))))
  (testing "A style with a `:type` not matching `\"mixed\"` returns false"
    (is (false? (sut/mixed? (assoc styles/sample-style :type "lager"))))
    (is (false? (sut/mixed? (assoc styles/sample-style :type "ale"))))
    (is (false? (sut/mixed? (assoc styles/sample-style :type "mead"))))
    (is (false? (sut/mixed? (assoc styles/sample-style :type "wheat"))))
    (is (false? (sut/mixed? (assoc styles/sample-style :type "cider"))))
    (is (false? (sut/mixed? (assoc styles/sample-style cbf-styles/type cbf-styles/lager))))
    (is (false? (sut/mixed? (assoc styles/sample-style cbf-styles/type cbf-styles/ale))))
    (is (false? (sut/mixed? (assoc styles/sample-style cbf-styles/type cbf-styles/mead))))
    (is (false? (sut/mixed? (assoc styles/sample-style cbf-styles/type cbf-styles/wheat))))
    (is (false? (sut/mixed? (assoc styles/sample-style cbf-styles/type cbf-styles/cider)))))
  (testing "This is a function from a style to a boolean"
    (is (boolean? (sut/mixed? styles/sample-style)))
    (is (boolean? (sut/mixed? (styles/generate-style)))))
  (testing "If the style is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Style :type"
                  (sut/mixed? (dissoc styles/sample-style :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Style :type"
                   (sut/mixed? (dissoc styles/sample-style :type)))))))


(deftest cider?-test
  (testing "A style with a `:type` matching `\"cider\"` returns true"
    (is (true? (sut/cider? (assoc styles/sample-style :type "cider"))))
    (is (true? (sut/cider? (assoc styles/sample-style cbf-styles/type cbf-styles/cider)))))
  (testing "A style with a `:type` not matching `\"cider\"` returns false"
    (is (false? (sut/cider? (assoc styles/sample-style :type "lager"))))
    (is (false? (sut/cider? (assoc styles/sample-style :type "ale"))))
    (is (false? (sut/cider? (assoc styles/sample-style :type "mead"))))
    (is (false? (sut/cider? (assoc styles/sample-style :type "wheat"))))
    (is (false? (sut/cider? (assoc styles/sample-style :type "mixed"))))
    (is (false? (sut/cider? (assoc styles/sample-style cbf-styles/type cbf-styles/lager))))
    (is (false? (sut/cider? (assoc styles/sample-style cbf-styles/type cbf-styles/ale))))
    (is (false? (sut/cider? (assoc styles/sample-style cbf-styles/type cbf-styles/mead))))
    (is (false? (sut/cider? (assoc styles/sample-style cbf-styles/type cbf-styles/wheat))))
    (is (false? (sut/cider? (assoc styles/sample-style cbf-styles/type cbf-styles/mixed)))))
  (testing "This is a function from a style to a boolean"
    (is (boolean? (sut/cider? styles/sample-style)))
    (is (boolean? (sut/cider? (styles/generate-style)))))
  (testing "If the style is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Style :type"
                  (sut/cider? (dissoc styles/sample-style :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Style :type"
                   (sut/cider? (dissoc styles/sample-style :type)))))))
