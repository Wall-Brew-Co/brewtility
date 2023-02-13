(ns brewtility.predicates.impl-test
  (:require #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])
            [brewtility.predicates.impl :as sut]))


(deftest fetch-or-throw!-test
  (testing "Values are returned when present"
    (is (true? (sut/fetch-or-throw! {:a true} :a "message")))
    (is (false? (sut/fetch-or-throw! {:a false} :a "message")))
    (is (= "some val" (sut/fetch-or-throw! {:some-key "some val"} :some-key "message"))))
  (testing "Exceptions are thrown when values are missing"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"message"
                  (sut/fetch-or-throw! {:a true} :b "message"))
                "The user supplied message is included in the exception"))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"message"
                   (sut/fetch-or-throw! {:a true} :b "message"))
                 "The user supplied message is included in the exception"))
    #?(:clj (is (= :missing-key
                   (try (sut/fetch-or-throw! {:a true} :b "message")
                        (catch Exception e (-> e ex-data :error-type))))
                "The exception includes metadata indicating the type of error"))
    #?(:cljs (is (= :missing-key
                    (try (sut/fetch-or-throw! {:a true} :b "message")
                         (catch js/Error e (-> e ex-data :error-type))))
                 "The exception includes metadata indicating the type of error"))
    #?(:clj (is (= :b
                   (try (sut/fetch-or-throw! {:a true} :b "message")
                        (catch Exception e (-> e ex-data :missing-key))))
                "The exception's metadata indicates the missing key"))
    #?(:cljs (is (= :b
                    (try (sut/fetch-or-throw! {:a true} :b "message")
                         (catch js/Error e (-> e ex-data :missing-key))))
                 "The exception's metadata indicates the missing key"))
    #?(:clj (is (= {:a true}
                   (try (sut/fetch-or-throw! {:a true} :b "message")
                        (catch Exception e (-> e ex-data :original-value))))
                "The exception's metadata includes the original value"))
    #?(:cljs (is (= {:a true}
                    (try (sut/fetch-or-throw! {:a true} :b "message")
                         (catch js/Error e (-> e ex-data :original-value))))
                 "The exception's metadata includes the original value"))
    #?(:clj (is (thrown-with-msg? Exception
                                  #"message"
                  (sut/fetch-or-throw! {:a nil} :a "message"))
                "fetch-or-throw! treats nil values the same as missing keys"))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"message"
                   (sut/fetch-or-throw! {:a nil} :a "message"))
                 "fetch-or-throw! treats nil values the same as missing keys"))))
