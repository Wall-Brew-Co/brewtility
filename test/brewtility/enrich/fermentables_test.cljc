(ns brewtility.enrich.fermentables-test
  (:require [brewtility.data.fermentables :as fermentable.data]
            [brewtility.enrich.fermentables :as fermentable.enrich]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.fermentables :as fermentable.format]))


(deftest enrich-add-after-boil-test
  (testing "add-after-boil can be derived from a fermentable"
    (is (boolean? (:add-after-boil (fermentable.enrich/enrich-add-after-boil (fermentable.data/generate-fermentable))))
        "enrich-add-after-boil is a function from a fermentable to a map with a boolean add-after-boil key")
    (is (boolean? (:add-after-boil (fermentable.enrich/enrich-add-after-boil fermentable.data/sample-fermentable)))
        "enrich-add-after-boil is a function from a fermentable to a map with a boolean add-after-boil key")
    (is (true? (-> fermentable.data/sample-fermentable
                   (assoc :add-after-boil true)
                   fermentable.enrich/enrich-add-after-boil
                   :add-after-boil))
        "enrich-add-after-boil adds true when the add-after-boil key is truthy")
    (is (false? (-> fermentable.data/sample-fermentable
                    (assoc :add-after-boil false)
                    fermentable.enrich/enrich-add-after-boil
                    :add-after-boil))
        "enrich-add-after-boil adds false when the add-after-boil key is falsy")
    (is (false? (-> fermentable.data/sample-fermentable
                    (dissoc :add-after-boil)
                    fermentable.enrich/enrich-add-after-boil
                    :add-after-boil))
        "enrich-add-after-boil adds false when the add-after-boil key is nil")))


(deftest enrich-coarse-fine-diff-test
  (let [sample-grain       (assoc fermentable.data/sample-fermentable :type "grain")
        sample-adjunct     (assoc fermentable.data/sample-fermentable :type "adjunct")
        sample-extract     (assoc fermentable.data/sample-fermentable :type "extract")
        sample-dry-extract (assoc fermentable.data/sample-fermentable :type "dry extract")
        sample-sugar       (assoc fermentable.data/sample-fermentable :type "sugar")]
    (testing "coarse-fine-diff is only applicable for grains and adjuncts"
      (is (some? (:coarse-fine-diff (fermentable.enrich/enrich-coarse-fine-diff sample-grain))))
      (is (some? (:coarse-fine-diff (fermentable.enrich/enrich-coarse-fine-diff sample-adjunct)))))
    (testing "coarse-fine-diff is not applicable for any other types"
      (is (nil? (:coarse-fine-diff (fermentable.enrich/enrich-coarse-fine-diff sample-extract))))
      (is (nil? (:coarse-fine-diff (fermentable.enrich/enrich-coarse-fine-diff sample-dry-extract))))
      (is (nil? (:coarse-fine-diff (fermentable.enrich/enrich-coarse-fine-diff sample-sugar)))))))


(deftest enrich-moisture-test
  (let [sample-grain       (assoc fermentable.data/sample-fermentable :type "grain")
        sample-adjunct     (assoc fermentable.data/sample-fermentable :type "adjunct")
        sample-extract     (assoc fermentable.data/sample-fermentable :type "extract")
        sample-dry-extract (assoc fermentable.data/sample-fermentable :type "dry extract")
        sample-sugar       (assoc fermentable.data/sample-fermentable :type "sugar")]
    (testing "moisture is only applicable for grains and adjuncts"
      (is (some? (:moisture (fermentable.enrich/enrich-moisture sample-grain))))
      (is (some? (:moisture (fermentable.enrich/enrich-moisture sample-adjunct)))))
    (testing "moisture is not applicable for any other types"
      (is (nil? (:moisture (fermentable.enrich/enrich-moisture sample-extract))))
      (is (nil? (:moisture (fermentable.enrich/enrich-moisture sample-dry-extract))))
      (is (nil? (:moisture (fermentable.enrich/enrich-moisture sample-sugar)))))))


(deftest enrich-diastatic-power-test
  (let [sample-grain       (assoc fermentable.data/sample-fermentable :type "grain")
        sample-adjunct     (assoc fermentable.data/sample-fermentable :type "adjunct")
        sample-extract     (assoc fermentable.data/sample-fermentable :type "extract")
        sample-dry-extract (assoc fermentable.data/sample-fermentable :type "dry extract")
        sample-sugar       (assoc fermentable.data/sample-fermentable :type "sugar")]
    (testing "diastatic-power is only applicable for grains and adjuncts"
      (is (some? (:diastatic-power (fermentable.enrich/enrich-diastatic-power sample-grain))))
      (is (some? (:diastatic-power (fermentable.enrich/enrich-diastatic-power sample-adjunct)))))
    (testing "diastatic-power is not applicable for any other types"
      (is (nil? (:diastatic-power (fermentable.enrich/enrich-diastatic-power sample-extract))))
      (is (nil? (:diastatic-power (fermentable.enrich/enrich-diastatic-power sample-dry-extract))))
      (is (nil? (:diastatic-power (fermentable.enrich/enrich-diastatic-power sample-sugar)))))))


(deftest enrich-protein-test
  (let [sample-grain       (assoc fermentable.data/sample-fermentable :type "grain")
        sample-adjunct     (assoc fermentable.data/sample-fermentable :type "adjunct")
        sample-extract     (assoc fermentable.data/sample-fermentable :type "extract")
        sample-dry-extract (assoc fermentable.data/sample-fermentable :type "dry extract")
        sample-sugar       (assoc fermentable.data/sample-fermentable :type "sugar")]
    (testing "protein is only applicable for grains and adjuncts"
      (is (some? (:protein (fermentable.enrich/enrich-protein sample-grain))))
      (is (some? (:protein (fermentable.enrich/enrich-protein sample-adjunct)))))
    (testing "protein is not applicable for any other types"
      (is (nil? (:protein (fermentable.enrich/enrich-protein sample-extract))))
      (is (nil? (:protein (fermentable.enrich/enrich-protein sample-dry-extract))))
      (is (nil? (:protein (fermentable.enrich/enrich-protein sample-sugar)))))))


(deftest enrich-recommend-mash-test
  (let [sample-grain       (assoc fermentable.data/sample-fermentable :type "grain")
        sample-adjunct     (assoc fermentable.data/sample-fermentable :type "adjunct")
        sample-extract     (assoc fermentable.data/sample-fermentable :type "extract")
        sample-dry-extract (assoc fermentable.data/sample-fermentable :type "dry extract")
        sample-sugar       (assoc fermentable.data/sample-fermentable :type "sugar")]
    (testing "recommend-mash is only applicable for grains and adjuncts"
      (is (true? (:recommend-mash (fermentable.enrich/enrich-recommend-mash sample-grain))))
      (is (true? (:recommend-mash (fermentable.enrich/enrich-recommend-mash sample-adjunct)))))
    (testing "recommend-mash is not applicable for any other types"
      (is (false? (:recommend-mash (fermentable.enrich/enrich-recommend-mash sample-extract))))
      (is (false? (:recommend-mash (fermentable.enrich/enrich-recommend-mash sample-dry-extract))))
      (is (false? (:recommend-mash (fermentable.enrich/enrich-recommend-mash sample-sugar)))))))


(deftest enrich-ibu-gal-per-lb-test
  (let [sample-grain       (assoc fermentable.data/sample-fermentable :type "grain" :ibu-gal-per-lb 0.123)
        sample-adjunct     (assoc fermentable.data/sample-fermentable :type "adjunct" :ibu-gal-per-lb 0.123)
        sample-extract     (assoc fermentable.data/sample-fermentable :type "extract" :ibu-gal-per-lb 0.123)
        sample-dry-extract (assoc fermentable.data/sample-fermentable :type "dry extract" :ibu-gal-per-lb 0.123)
        sample-sugar       (assoc fermentable.data/sample-fermentable :type "sugar" :ibu-gal-per-lb 0.123)]
    (testing "ibu-gal-per-lb is only applicable for extracts"
      (is (some? (:ibu-gal-per-lb (fermentable.enrich/enrich-ibu-gallons-per-pound sample-extract)))))
    (testing "ibu-gal-per-lb is not applicable for any other types"
      (is (nil? (:ibu-gal-per-lb (fermentable.enrich/enrich-ibu-gallons-per-pound sample-grain))))
      (is (nil? (:ibu-gal-per-lb (fermentable.enrich/enrich-ibu-gallons-per-pound sample-adjunct))))
      (is (nil? (:ibu-gal-per-lb (fermentable.enrich/enrich-ibu-gallons-per-pound sample-dry-extract))))
      (is (nil? (:ibu-gal-per-lb (fermentable.enrich/enrich-ibu-gallons-per-pound sample-sugar)))))))


;;
;; Whole object enrichment tests
;;

(deftest enrich-fermentable-tests
  (testing "Ensure enrichment pattern functions generate conforming data"
    (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                (fermentable.enrich/enrich-fermentable fermentable.data/sample-fermentable))
        "Enrichment pattern should produce a valid fermentable object")
    (is (spoon.spec/test-valid? ::fermentable.format/fermentable-wrapper
                                (fermentable.enrich/enrich-fermentable-wrapper fermentable.data/sample-fermentable-wrapper))
        "Enrichment pattern should produce a valid fermentable object")
    (is (spoon.spec/test-valid? ::fermentable.format/fermentables
                                (fermentable.enrich/enrich-fermentables fermentable.data/sample-fermentables))
        "Enrichment pattern should produce a valid fermentable object")
    (is (spoon.spec/test-valid? ::fermentable.format/fermentables-wrapper
                                (fermentable.enrich/enrich-fermentables-wrapper fermentable.data/sample-fermentables-wrapper))
        "Enrichment pattern should produce a valid fermentable object")
    (testing "Static data comparison for enrichment pattern functions"
      (is (= {:amount           0.45
              :yield            78.0
              :supplier         "Gnome Brew"
              :color            500.1
              :display-color    "500.1 °L"
              :name             "Black Barley"
              :moisture         5.0
              :type             "Grain"
              :add-after-boil   false
              :display-amount   "0.992 lb"
              :notes            "Unmalted roasted barley for stouts, porters"
              :protein          13.2
              :origin           "United States"
              :coarse-fine-diff 1.5
              :version          1
              :max-in-batch     10.0
              :recommend-mash   true
              :diastatic-power  0.0}
             (fermentable.enrich/enrich-fermentable fermentable.data/sample-fermentable))))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary fermentable"
    (letfn [(gen-fermentable [] (fermentable.data/generate-fermentable))]
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-add-after-boil (gen-fermentable)))
          "enrich-add-after-boil is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-coarse-fine-diff (gen-fermentable)))
          "enrich-coarse-fine-diff is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-moisture (gen-fermentable)))
          "enrich-moisture is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-diastatic-power (gen-fermentable)))
          "enrich-diastatic-power is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-protein (gen-fermentable)))
          "enrich-protein is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-recommend-mash (gen-fermentable)))
          "enrich-recommend-mash is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-ibu-gallons-per-pound (gen-fermentable)))
          "enrich-ibu-gallons-per-pound is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-display-color (gen-fermentable)))
          "enrich-display-color is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-display-amount (gen-fermentable)))
          "enrich-display-amount is a function to and from common-beer-format.fermentable/fermentable")
      (is (spoon.spec/test-valid? ::fermentable.format/fermentable
                                  (fermentable.enrich/enrich-fermentable (gen-fermentable)))
          "enrich-fermentable is a function to and from common-beer-format.fermentable/fermentable")))
  (testing "Ensure enrichment pattern works against arbitrary fermentable wrappers"
    (is (spoon.spec/test-valid? ::fermentable.format/fermentable-wrapper
                                (fermentable.enrich/enrich-fermentable-wrapper (fermentable.data/generate-fermentable-wrapper)))
        "enrich-fermentable-wrapper is a function to and from common-beer-format.fermentable/fermentable-wrapper")
    (is (spoon.spec/test-valid? ::fermentable.format/fermentables
                                (fermentable.enrich/enrich-fermentables (fermentable.data/generate-fermentables)))
        "enrich-fermentables is a function to and from common-beer-format.fermentable/fermentables")
    (is (spoon.spec/test-valid? ::fermentable.format/fermentables-wrapper
                                (fermentable.enrich/enrich-fermentables-wrapper (fermentable.data/generate-fermentables-wrapper)))
        "enrich-fermentables-wrapper is a function to and from common-beer-format.fermentable/fermentables-wrapper")))

