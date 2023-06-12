(ns brewtility.enrich.yeasts-test
  (:require [brewtility.data.yeasts :as yeasts.data]
            [brewtility.enrich.yeasts :as yeasts.enrich]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.yeasts :as yeasts.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest enrich-amount-is-weight-test
  (testing "Ensure enricher doesn't alter the value of existing `:amount is weight` keys"
    (is (nil? (:amount-is-weight yeasts.data/sample-yeast))
        "yeasts.data/sample-yeast does not set `amount-is-weight`")
    (is (false? (-> yeasts.data/sample-yeast
                    yeasts.enrich/enrich-amount-is-weight
                    :amount-is-weight))
        "yeasts.data/sample-yeast does not set `amount-is-weight`, so the enricher sets the value to false")
    (is (false? (-> yeasts.data/sample-yeast
                    (assoc :amount-is-weight false)
                    yeasts.enrich/enrich-amount-is-weight
                    :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained")
    (is (true? (-> yeasts.data/sample-yeast
                   (assoc :amount-is-weight true)
                   yeasts.enrich/enrich-amount-is-weight
                   :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained"))
  (testing "The two-arity version of `enrich-amount0is0-weight` has no behavioral differences"
    (is (false? (-> yeasts.data/sample-yeast
                    (yeasts.enrich/enrich-amount-is-weight :some-value)
                    :amount-is-weight))
        "yeasts.data/sample-yeast does not set `amount-is-weight`, so the enricher sets the value to false")
    (is (false? (-> yeasts.data/sample-yeast
                    (assoc :amount-is-weight false)
                    (yeasts.enrich/enrich-amount-is-weight :some-value)
                    :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained")
    (is (true? (-> yeasts.data/sample-yeast
                   (assoc :amount-is-weight true)
                   (yeasts.enrich/enrich-amount-is-weight :some-value)
                   :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained")))


(deftest enrich-display-amount-test
  (testing "Ensure enricher correctly defaults to setting display amount as a weight"
    (let [weighted-yeast (assoc yeasts.data/sample-yeast :amount-is-weight true)]
      (is (= "0.551 lb" (-> weighted-yeast
                            yeasts.enrich/enrich-display-amount
                            :display-amount))
          "yeasts.data/sample-yeast sets the amount to 15.0, which is the default unit")
      (is (= "34.293 lb" (-> weighted-yeast
                             (assoc :amount 15.555)
                             yeasts.enrich/enrich-display-amount
                             :display-amount))
          "enrich-display-amount defaults precision to 3")
      (is (= "34.29 lb" (-> weighted-yeast
                            (assoc :amount 15.555)
                            (yeasts.enrich/enrich-display-amount {:yeast-amount-precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision")
      (is (= "15444.4 g" (-> weighted-yeast
                             (assoc :amount 15.4444)
                             (yeasts.enrich/enrich-display-amount {:yeast-amount-target-units :gram})
                             :display-amount))
          "enrich-display-amount unites can be configured to use seconds")
      (is (= "33.069 pound" (-> weighted-yeast
                                (assoc :amount 15.0)
                                (yeasts.enrich/enrich-display-amount {:yeast-amount-suffix :full})
                                :display-amount))
          "enrich-display-amount can be configured to use full suffixes")
      (is (= "34.29 lb" (-> weighted-yeast
                            (assoc :amount 15.555)
                            (yeasts.enrich/enrich-display-amount {:precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision with default settings keys")
      (is (= "15.4 kg" (-> weighted-yeast
                           (assoc :amount 15.4)
                           (yeasts.enrich/enrich-display-amount {:system-of-measure :metric})
                           :display-amount))
          "enrich-display-amount unites can be configured with default settings keys")
      (is (= "33.069 pound" (-> weighted-yeast
                                (assoc :amount 15.0)
                                (yeasts.enrich/enrich-display-amount {:suffix :full})
                                :display-amount))
          "enrich-display-amount can be configured to use full suffixes with default settings keys")))
  (testing "Ensure enricher correctly defaults to setting display amount as a volume"
    (let [volume-yeast (assoc yeasts.data/sample-yeast :amount-is-weight false)]
      (is (= "0.066 gal" (-> volume-yeast
                             yeasts.enrich/enrich-display-amount
                             :display-amount))
          "yeasts.data/sample-yeast sets the amount to 15.0, which is the default unit")
      (is (= "4.109 gal" (-> volume-yeast
                             (assoc :amount 15.555)
                             yeasts.enrich/enrich-display-amount
                             :display-amount))
          "enrich-display-amount defaults precision to 3")
      (is (= "4.11 gal" (-> volume-yeast
                            (assoc :amount 15.555)
                            (yeasts.enrich/enrich-display-amount {:yeast-amount-precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision")
      (is (= "3043.263 tsp" (-> volume-yeast
                                (assoc :amount 15.0)
                                (yeasts.enrich/enrich-display-amount {:yeast-amount-target-units :teaspoon})
                                :display-amount))
          "enrich-display-amount unites can be configured to use seconds")
      (is (= "3.963 US gallon" (-> volume-yeast
                                   (assoc :amount 15.0)
                                   (yeasts.enrich/enrich-display-amount {:yeast-amount-suffix :full})
                                   :display-amount))
          "enrich-display-amount can be configured to use full suffixes")
      (is (= "4.11 gal" (-> volume-yeast
                            (assoc :amount 15.555)
                            (yeasts.enrich/enrich-display-amount {:precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision with default settings keys")
      (is (= "15.1 l" (-> volume-yeast
                          (assoc :amount 15.1)
                          (yeasts.enrich/enrich-display-amount {:system-of-measure :metric})
                          :display-amount))
          "enrich-display-amount unites can be configured with default settings keys")
      (is (= "3.963 US gallon" (-> volume-yeast
                                   (assoc :amount 15.0)
                                   (yeasts.enrich/enrich-display-amount {:suffix :full})
                                   :display-amount))
          "enrich-display-amount can be configured to use full suffixes with default settings keys")))
  (testing "Ensure resulting `yeast` still conforms to the spec"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (-> yeasts.data/sample-yeast
                                                          (assoc :amount-is-weight true)
                                                          yeasts.enrich/enrich-display-amount))
        "yeasts.data/sample-yeast conforms to the spec if the amount is a weight")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (-> yeasts.data/sample-yeast
                                                          (assoc :amount-is-weight false)
                                                          yeasts.enrich/enrich-display-amount))
        "yeasts.data/sample-yeast conforms to the spec if the amount is a volume")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (-> (yeasts.data/generate-yeast)
                                                          (assoc :amount-is-weight true)
                                                          yeasts.enrich/enrich-display-amount))
        "yeasts.data/sample-yeast conforms to the spec if the amount is a weight")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast (-> (yeasts.data/generate-yeast)
                                                          (assoc :amount-is-weight false)
                                                          yeasts.enrich/enrich-display-amount))
        "yeasts.data/sample-yeast conforms to the spec if the amount is a volume")))


;;
;; Whole object enrichment tests
;;

(deftest enrich-yeast-tests
  (testing "Ensure enrichment pattern functions generate conforming data"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast
                                (yeasts.enrich/enrich-yeast yeasts.data/sample-yeast))
        "Enrichment pattern should produce a valid yeast object")
    (is (spoon.spec/test-valid? ::yeasts.format/yeast-wrapper
                                (yeasts.enrich/enrich-yeast-wrapper yeasts.data/sample-yeast-wrapper))
        "Enrichment pattern should produce a valid yeast object")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts
                                (yeasts.enrich/enrich-yeasts yeasts.data/sample-yeasts))
        "Enrichment pattern should produce a valid yeast object")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts-wrapper
                                (yeasts.enrich/enrich-yeasts-wrapper yeasts.data/sample-yeasts-wrapper))
        "Enrichment pattern should produce a valid yeast object")
    (testing "Static data comparison for enrichment pattern functions"
      (is (= {:amount           0.25
              :amount-is-weight false
              :attenuation      73.0
              :best-for         "Irish Dry Stouts"
              :disp-max-temp    "71.96 f"
              :disp-min-temp    "62.06 f"
              :display-amount   "0.066 gal"
              :flocculation     "Medium"
              :form             "Liquid"
              :laboratory       "Wyeast Labs"
              :max-temperature  22.2
              :min-temperature  16.7
              :name             "Irish Ale"
              :notes            "Dry, fruity flavor characteristic of stouts.  Full bodied, dry, clean flavor."
              :product-id       "1084"
              :type             "Ale"
              :version          1}
             (yeasts.enrich/enrich-yeast yeasts.data/sample-yeast)))
      (is (= {:amount           0.25
              :amount-is-weight false
              :attenuation      73.0
              :best-for         "Irish Dry Stouts"
              :display-amount   "0.066 gal"
              :flocculation     "Medium"
              :form             "Liquid"
              :laboratory       "Wyeast Labs"
              :name             "Irish Ale"
              :notes            "Dry, fruity flavor characteristic of stouts.  Full bodied, dry, clean flavor."
              :product-id       "1084"
              :type             "Ale"
              :version          1}
             (yeasts.enrich/enrich-yeast (dissoc yeasts.data/sample-yeast :max-temperature :min-temperature)))))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary yeast"
    (letfn [(gen-yeast [] (yeasts.data/generate-yeast))]
      (is (spoon.spec/test-valid? ::yeasts.format/yeast
                                  (yeasts.enrich/enrich-amount-is-weight (gen-yeast)))
          "enrich-amount-is-weight is a function to and from common-beer-format.yeast/yeast")
      (is (spoon.spec/test-valid? ::yeasts.format/yeast
                                  (yeasts.enrich/enrich-display-amount (gen-yeast)))
          "enrich-display-amount is a function to and from common-beer-format.yeast/yeast")
      (is (spoon.spec/test-valid? ::yeasts.format/yeast
                                  (yeasts.enrich/enrich-yeast (gen-yeast)))
          "enrich-yeast is a function to and from common-beer-format.yeast/yeast")))
  (testing "Ensure enrichment pattern works against arbitrary yeast wrappers"
    (is (spoon.spec/test-valid? ::yeasts.format/yeast-wrapper
                                (yeasts.enrich/enrich-yeast-wrapper (yeasts.data/generate-yeast-wrapper)))
        "enrich-yeast-wrapper is a function to and from common-beer-format.yeast/yeast-wrapper")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts
                                (yeasts.enrich/enrich-yeasts (yeasts.data/generate-yeasts)))
        "enrich-yeasts is a function to and from common-beer-format.yeast/yeasts")
    (is (spoon.spec/test-valid? ::yeasts.format/yeasts-wrapper
                                (yeasts.enrich/enrich-yeasts-wrapper (yeasts.data/generate-yeasts-wrapper)))
        "enrich-yeasts-wrapper is a function to and from common-beer-format.yeast/yeasts-wrapper")))
