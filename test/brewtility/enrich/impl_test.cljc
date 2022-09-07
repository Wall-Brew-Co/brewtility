(ns brewtility.enrich.impl-test
  (:require [brewtility.enrich.impl :as sut]
            [clojure.string :as str]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest ->displayable-volume-test
  (testing "Ensure ->displayable-volume supports its full suite of options"
    (is (= "5.678 l"
           (sut/->displayable-volume 1.5 :american-gallon :liter)
           (sut/->displayable-volume 1.5 :american-gallon :liter {:precision 3
                                                                  :suffix    :short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "5.7 l"
           (sut/->displayable-volume 1.5 :american-gallon :liter {:precision 1
                                                                  :suffix    :short}))
        "Conversion may override the default precision")
    (is (= "5.678 liter"
           (sut/->displayable-volume 1.5 :american-gallon :liter {:precision 3
                                                                  :suffix    :full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-weight-test
  (testing "Ensure ->displayable-weight supports its full suite of options"
    (is (= "0.053 oz"
           (sut/->displayable-weight 1.5 :gram :ounce)
           (sut/->displayable-weight 1.5 :gram :ounce {:precision 3
                                                       :suffix    :short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "0.1 oz"
           (sut/->displayable-weight 1.5 :gram :ounce {:precision 1
                                                       :suffix    :short}))
        "Conversion may override the default precision")
    (is (= "0.053 ounce"
           (sut/->displayable-weight 1.5 :gram :ounce {:precision 3
                                                       :suffix    :full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-time-test
  (testing "Ensure ->displayable-time supports its full suite of options"
    (is (= "0.76 m"
           (sut/->displayable-time 45.6 :second :minute)
           (sut/->displayable-time 45.6 :second :minute {:precision 3
                                                         :suffix    :short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "0.8 m"
           (sut/->displayable-time 45.6 :second :minute {:precision 1
                                                         :suffix    :short}))
        "Conversion may override the default precision")
    (is (= "0.76 minute"
           (sut/->displayable-time 45.6 :second :minute {:precision 3
                                                         :suffix    :full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-temperature-test
  (testing "Ensure ->displayable-temperature supports its full suite of options"
    (is (= "318.75 k"
           (sut/->displayable-temperature 45.6 :c :k)
           (sut/->displayable-temperature 45.6 :c :k {:precision 3
                                                      :suffix    :short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "318.8 k"
           (sut/->displayable-temperature 45.6 :c :k {:precision 1
                                                      :suffix    :short}))
        "Conversion may override the default precision")
    (is (= "318.75 kelvin"
           (sut/->displayable-temperature 45.6 :c :k {:precision 3
                                                      :suffix    :full}))
        "Conversion may override the default suffix")))


(deftest target-unit-error-test
  (testing "Ensure target-unit-error sets an appropriate error for volume"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map :volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "teaspoon")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for weight"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map :weight :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "weight")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "pound")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for time"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map :time :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "time")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "minute")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for temperature"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map :temperature :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "temperature")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "celsius")
          "The valid units are included in the error message"))))


(deftest systems-of-meaure-error-test
  (testing "Ensure systems-of-meaure-error sets an appropriate error"
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map :volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :system-of-measure)
          "The new error is recorded")
      (is (str/includes? (:system-of-measure new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (:system-of-measure new-error-map) "metric")
          "The valid values are included in the error message"))))


(deftest precision-error-test
  (testing "Ensure precision-error sets an appropriate error"
    (let [error-map     {:some "error"}
          new-error-map (sut/precision-error error-map :volume 10)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :precision)
          "The new error is recorded")
      (is (str/includes? (:precision new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:precision new-error-map) "10")
          "The invalid system of measure is included in the error message"))))


(deftest suffix-error-test
  (testing "Ensure suffix-error sets an appropriate error"
    (let [error-map     {:some "error"}
          new-error-map (sut/suffix-error error-map :volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :suffix)
          "The new error is recorded")
      (is (str/includes? (:suffix new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:suffix new-error-map) "fake")
          "The invalid suffix type is included in the error message")
      (is (str/includes? (:suffix new-error-map) "full")
          "The valid values are included in the error message"))))


(deftest verify-enrich-displayable-volume-opts-test
  (let [valid-opts  {:target-units      :teaspoon
                     :system-of-measure :metric
                     :precision         2
                     :suffix            :full}
        error-regex #"Invalid displayable volume enrichment options"]
    (testing "Ensure verify-enrich-displayable-volume-opts returns valid opts"
      (is (= valid-opts (sut/verify-enrich-displayable-volume-opts valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (dissoc valid-opts :suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-volume-opts (assoc valid-opts :suffix :fake))))))))


(deftest verify-enrich-displayable-weight-opts-test
  (let [valid-opts  {:target-units      :pound
                     :system-of-measure :metric
                     :precision         2
                     :suffix            :full}
        error-regex #"Invalid displayable weight enrichment options"]
    (testing "Ensure verify-enrich-displayable-weight-opts returns valid opts"
      (is (= valid-opts (sut/verify-enrich-displayable-weight-opts valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (dissoc valid-opts :suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-weight-opts (assoc valid-opts :suffix :fake))))))))


(deftest verify-enrich-displayable-time-opts-test
  (let [valid-opts  {:target-units      :minute
                     :system-of-measure :metric
                     :precision         2
                     :suffix            :full}
        error-regex #"Invalid displayable time enrichment options"]
    (testing "Ensure verify-enrich-displayable-time-opts returns valid opts"
      (is (= valid-opts (sut/verify-enrich-displayable-time-opts valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (dissoc valid-opts :suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-time-opts (assoc valid-opts :suffix :fake))))))))


(deftest verify-enrich-displayable-temperature-opts-test
  (let [valid-opts  {:target-units      :c
                     :system-of-measure :metric
                     :precision         2
                     :suffix            :full}
        error-regex #"Invalid displayable temperature enrichment options"]
    (testing "Ensure verify-enrich-displayable-temperature-opts returns valid opts"
      (is (= valid-opts (sut/verify-enrich-displayable-temperature-opts valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (dissoc valid-opts :suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/verify-enrich-displayable-temperature-opts (assoc valid-opts :suffix :fake))))))))
