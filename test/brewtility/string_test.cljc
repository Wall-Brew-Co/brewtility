(ns brewtility.string-test
  (:require [brewtility.string :as sut]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(defn rand-str
  "Generate a random string of length `len`."
  [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))


(deftest prepare-for-compare-test
  (testing "Strings are appropriately re-cased and trimmed of whitespace"
    (is (= "" (sut/prepare-for-compare "   ")))
    (is (= "clojure" (sut/prepare-for-compare "ClOjUrE  ")))
    (is (= "clojure" (sut/prepare-for-compare "ClOjUrE  " {:uppercase? false})))
    (is (= "clojure" (sut/prepare-for-compare "ClOjUrE  " {sut/cast-to-uppercase? false})))
    (is (= "clojure" (sut/prepare-for-compare "clojure")))
    (is (= "100 lines of code" (sut/prepare-for-compare "  100 lines of CODE")))
    (is (= "a   b" (sut/prepare-for-compare "   a   b  ")))
    (is (= "CLOJURE" (sut/prepare-for-compare "ClOjUrE  " {:uppercase? true})))
    (is (= "CLOJURE" (sut/prepare-for-compare "ClOjUrE  " {sut/cast-to-uppercase? true})))
    (is (= "CLOJURE" (sut/prepare-for-compare "clojure" {:uppercase? true})))
    (is (= "100 LINES OF CODE" (sut/prepare-for-compare "  100 lines of CODE" {:uppercase? true})))
    (is (= "A   B" (sut/prepare-for-compare "   a   b  " {:uppercase? true})))
    (is (= ":CLOJURE" (sut/prepare-for-compare :ClOjUrE {:uppercase? true :coerce? true})))
    (is (= "CLOJURE" (sut/prepare-for-compare "clojure" {:uppercase? true :coerce? true})))
    (is (= "100" (sut/prepare-for-compare 100 {:uppercase? true :coerce? true})))
    (is (= "true" (sut/prepare-for-compare true {:coerce? true})))
    (is (= "" (sut/prepare-for-compare nil {:coerce? true})))))


(deftest same?-test
  (testing "Strings containing matching characters after perparation match"
    (is (true? (sut/same? "   clojure" "CLOJURE   ")))
    (is (true? (sut/same? "clojure   " "   CLOJURE   " {:uppercase? true})))
    (is (true? (sut/same? "   100 LINES OF CODE" "100 LINES OF CODE   ")))
    (is (false? (sut/same? "clo jure" "CLOJURE")))
    (is (false? (sut/same? "100" "!))" {:uppercase? true})))
    (is (true? (sut/same? true "true" {:coerce? true})))
    (is (true? (sut/same? nil "" {:coerce? true})))
    (is (true? (sut/same? :carrot ":CARROT" {:coerce? true})))))


(deftest includes?-test
  (testing "Strings containing matching characters after perparation match"
    (is (true? (sut/includes? "   clojure" "CLOJURE   ")))
    (is (true? (sut/includes? "CLOJURE   " "c")))
    (is (true? (sut/includes? "clojure   " "   CLOJURE   " {:uppercase? true})))
    (is (true? (sut/includes? "100 LINES OF CODE   " "   100 ")))
    (is (false? (sut/includes? "clo" "CLOJURE")))
    (is (false? (sut/includes? "100" "!))" {:uppercase? true})))
    (is (true? (sut/includes? "falsefaasetrue" true {:coerce? true})))
    (is (true? (sut/includes? nil "" {:coerce? true})))
    (is (true? (sut/includes? :carrot ":CARROT" {:coerce? true})))))


(deftest ->sporadic-case-tests
  (testing "Re-cased strings are the `same` as their original value"
    (is (sut/same? "clojure" (sut/->sporadic-case "clojure")))
    (is (sut/same? "100 lines of clojure" (sut/->sporadic-case "100 lines of clojure")))
    (is (sut/same? "clojure" (sut/->sporadic-case "CLOJURE"))))
  (testing "Re-cased strings are stings"
    (is (string? (sut/->sporadic-case (rand-str 1000))))))


(deftest ->spongebob-case-tests
  (testing "Re-cased strings are the `same` as their original value"
    (is (sut/same? "clojure" (sut/->spongebob-case "clojure")))
    (is (sut/same? "100 lines of clojure" (sut/->spongebob-case "100 lines of clojure")))
    (is (sut/same? "clojure" (sut/->spongebob-case "CLOJURE"))))
  (testing "Re-cased strings are stings"
    (is (string? (sut/->spongebob-case (rand-str 1000))))))
