(ns brewtility.predicates.fermentables-test
  (:require [brewtility.data.fermentables :as fermentables]
            [brewtility.predicates.fermentables :as sut]
            [clojure.test :refer [deftest is testing]]
            [common-beer-format.fermentables :as cbf-fermentables]))


(deftest add-after-boil?-tests
  (testing "When the `:add-after-boil` field is present, use that value."
    (is (true? (sut/add-after-boil? (assoc fermentables/sample-fermentable :add-after-boil true))))
    (is (false? (sut/add-after-boil? (assoc fermentables/sample-fermentable :add-after-boil false)))))
  (testing "When the `:add-after-boil` field is missing, return false."
    (is (false? (sut/add-after-boil? (dissoc fermentables/sample-fermentable :add-after-boil)))))
  (testing "This is a function from a fermentable to a boolean."
    (is (boolean? (sut/add-after-boil? fermentables/sample-fermentable)))
    (is (boolean? (sut/add-after-boil? (fermentables/generate-fermentable))))))


(deftest grain?-test
  (testing "A fermentable with a `:type` matching `\"grain\" returns true"
    (is (true? (sut/grain? (assoc fermentables/sample-fermentable :type "Grain"))))
    (is (true? (sut/grain? (assoc fermentables/sample-fermentable :type cbf-fermentables/grain) {options/uppercase? true}))))
  (testing "A fermentable with a `:type` not matching `\"grain\" returns false"
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type "extract"))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type cbf-fermentables/extract))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type "sUgar"))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type cbf-fermentables/sugar))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type "Dry EXTract"))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type cbf-fermentables/dry-extract))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type "adJunct"))))
    (is (false? (sut/grain? (assoc fermentables/sample-fermentable :type cbf-fermentables/adjunct)))))
  (testing "This is a function from a fermentable to a boolean"
    (is (boolean? (sut/grain? fermentables/sample-fermentable)))
    (is (boolean? (sut/grain? (fermentables/generate-fermentable)))))
  (testing "If the fermentable is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Fermentable :type"
                  (sut/grain? (dissoc fermentables/sample-fermentable :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Fermentable :type"
                   (sut/grain? (dissoc fermentables/sample-fermentable :type)))))))


(deftest sugar?-test
  (testing "A fermentable with a `:type` matching `\"grain\" returns true"
    (is (true? (sut/sugar? (assoc fermentables/sample-fermentable :type "sugar"))))
    (is (true? (sut/sugar? (assoc fermentables/sample-fermentable :type "SUGAR"))))
    (is (true? (sut/sugar? (assoc fermentables/sample-fermentable :type cbf-fermentables/sugar)))))
  (testing "A fermentable with a `:type` not matching `\"grain\" returns false"
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type "extract"))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type "GRAIN"))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type "Dry EXTract"))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type "adJunct"))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type cbf-fermentables/extract))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type cbf-fermentables/grain))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type cbf-fermentables/dry-extract))))
    (is (false? (sut/sugar? (assoc fermentables/sample-fermentable :type cbf-fermentables/adjunct)))))
  (testing "This is a function from a fermentable to a boolean"
    (is (boolean? (sut/sugar? fermentables/sample-fermentable)))
    (is (boolean? (sut/sugar? (fermentables/generate-fermentable)))))
  (testing "If the fermentable is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Fermentable :type"
                  (sut/sugar? (dissoc fermentables/sample-fermentable :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Fermentable :type"
                   (sut/sugar? (dissoc fermentables/sample-fermentable :type)))))))


(deftest extract?-test
  (testing "A fermentable with a `:type` matching `\"grain\" returns true"
    (is (true? (sut/extract? (assoc fermentables/sample-fermentable :type "EXTRACT"))))
    (is (true? (sut/extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/extract)))))
  (testing "A fermentable with a `:type` not matching `\"grain\" returns false"
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type "sugar"))))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type "GRAIN"))))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type "Dry EXTract"))))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type "adJunct"))))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/sugar))))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/grain) {options/uppercase? true})))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/dry-extract))))
    (is (false? (sut/extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/adjunct)))))
  (testing "This is a function from a fermentable to a boolean"
    (is (boolean? (sut/extract? fermentables/sample-fermentable)))
    (is (boolean? (sut/extract? (fermentables/generate-fermentable)))))
  (testing "If the fermentable is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Fermentable :type"
                  (sut/extract? (dissoc fermentables/sample-fermentable :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Fermentable :type"
                   (sut/extract? (dissoc fermentables/sample-fermentable :type)))))))


(deftest dry-extract?-test
  (testing "A fermentable with a `:type` matching `\"grain\" returns true"
    (is (true? (sut/dry-extract? (assoc fermentables/sample-fermentable :type "DRY EXTRACT"))))
    (is (true? (sut/dry-extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/dry-extract)))))
  (testing "A fermentable with a `:type` not matching `\"grain\" returns false"
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type "sugar"))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type "GRAIN"))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type "EXTract"))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type "adJunct"))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/sugar))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/grain))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/extract))))
    (is (false? (sut/dry-extract? (assoc fermentables/sample-fermentable :type cbf-fermentables/adjunct)))))
  (testing "This is a function from a fermentable to a boolean"
    (is (boolean? (sut/dry-extract? fermentables/sample-fermentable)))
    (is (boolean? (sut/dry-extract? (fermentables/generate-fermentable)))))
  (testing "If the fermentable is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Fermentable :type"
                  (sut/dry-extract? (dissoc fermentables/sample-fermentable :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Fermentable :type"
                   (sut/dry-extract? (dissoc fermentables/sample-fermentable :type)))))))


(deftest adjunct?-test
  (testing "A fermentable with a `:type` matching `\"grain\" returns true"
    (is (true? (sut/adjunct? (assoc fermentables/sample-fermentable :type "adJunct"))))
    (is (true? (sut/adjunct? (assoc fermentables/sample-fermentable :type "adJunct") {options/uppercase? true})))
    (is (true? (sut/adjunct? (assoc fermentables/sample-fermentable :type cbf-fermentables/adjunct)))))
  (testing "A fermentable with a `:type` not matching `\"grain\" returns false"
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type "sugar"))))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type "GRAIN"))))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type "EXTract"))))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type "DRY EXTRact") {options/uppercase? true})))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type cbf-fermentables/sugar))))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type cbf-fermentables/grain))))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type cbf-fermentables/extract))))
    (is (false? (sut/adjunct? (assoc fermentables/sample-fermentable :type cbf-fermentables/dry-extract)))))
  (testing "This is a function from a fermentable to a boolean"
    (is (boolean? (sut/adjunct? fermentables/sample-fermentable)))
    (is (boolean? (sut/adjunct? (fermentables/generate-fermentable)))))
  (testing "If the fermentable is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Fermentable :type"
                  (sut/adjunct? (dissoc fermentables/sample-fermentable :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Fermentable :type"
                   (sut/adjunct? (dissoc fermentables/sample-fermentable :type)))))))

