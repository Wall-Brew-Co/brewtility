(ns brewtility.enrich.miscs-test
  (:require [brewtility.data.miscs :as miscs.data]
            [brewtility.enrich.miscs :as miscs.enrich]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.miscs :as miscs.format]))


(deftest enrich-amount-is-weight-test
  (testing "Ensure enricher doesn't alter the value of existing `:amount is weight` keys"
    (is (nil? (:amount-is-weight miscs.data/sample-misc))
        "miscs.data/sample-misc does not set `amount-is-weight`")
    (is (false? (-> miscs.data/sample-misc
                    miscs.enrich/enrich-amount-is-weight
                    :amount-is-weight))
        "miscs.data/sample-misc does not set `amount-is-weight`, so the enricher sets the value to false")
    (is (false? (-> miscs.data/sample-misc
                    (assoc :amount-is-weight false)
                    miscs.enrich/enrich-amount-is-weight
                    :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained")
    (is (true? (-> miscs.data/sample-misc
                   (assoc :amount-is-weight true)
                   miscs.enrich/enrich-amount-is-weight
                   :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained"))
  (testing "The two-arity version of `enrich-amount0is0-weight` has no behavioral differences"
    (is (false? (-> miscs.data/sample-misc
                    (miscs.enrich/enrich-amount-is-weight :some-value)
                    :amount-is-weight))
        "miscs.data/sample-misc does not set `amount-is-weight`, so the enricher sets the value to false")
    (is (false? (-> miscs.data/sample-misc
                    (assoc :amount-is-weight false)
                    (miscs.enrich/enrich-amount-is-weight :some-value)
                    :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained")
    (is (true? (-> miscs.data/sample-misc
                   (assoc :amount-is-weight true)
                   (miscs.enrich/enrich-amount-is-weight :some-value)
                   :amount-is-weight))
        "Explicitly setting `:amount-is-weight` to false causes the value to be retained")))


(deftest enrich-display-time-test
  (testing "Ensure enricher correctly defaults to setting display time"
    (is (= "15.555 m" (-> miscs.data/sample-misc
                          (assoc :time 15.555)
                          miscs.enrich/enrich-display-time
                          :display-time))
        "enrich-display-time defaults precision to 3")
    (is (= "15.56 m" (-> miscs.data/sample-misc
                         (assoc :time 15.555)
                         (miscs.enrich/enrich-display-time {:misc-time-precision 2})
                         :display-time))
        "enrich-display-time can be configured to use a different precision")
    (is (= "13.2 s" (-> miscs.data/sample-misc
                        (assoc :time 0.22)
                        (miscs.enrich/enrich-display-time {:misc-time-target-units :second})
                        :display-time))
        "enrich-display-time unites can be configured to use seconds")
    (is (= "15.1 minute" (-> miscs.data/sample-misc
                             (assoc :time 15.1)
                             (miscs.enrich/enrich-display-time {:misc-time-suffix :full})
                             :display-time))
        "enrich-display-time can be configured to use full suffixes")
    (is (= "15.56 m" (-> miscs.data/sample-misc
                         (assoc :time 15.555)
                         (miscs.enrich/enrich-display-time {:precision 2})
                         :display-time))
        "enrich-display-time can be configured to use a different precision with default settings keys")
    (is (= "15.1 m" (-> miscs.data/sample-misc
                        (assoc :time 15.1)
                        (miscs.enrich/enrich-display-time {:system-of-measure :metric})
                        :display-time))
        "enrich-display-time unites can be configured with default settings keys")
    (is (= "15.1 minute" (-> miscs.data/sample-misc
                             (assoc :time 15.1)
                             (miscs.enrich/enrich-display-time {:suffix :full})
                             :display-time))
        "enrich-display-time can be configured to use full suffixes with default settings keys"))
  (testing "Ensure resulting `misc` still conforms to the spec"
    (is (spoon.spec/test-valid? ::miscs.format/misc (-> miscs.data/sample-misc miscs.enrich/enrich-display-time))
        "miscs.data/sample-misc conforms to the spec")))


(deftest enrich-display-amount-test
  (testing "Ensure enricher correctly defaults to setting display amount as a weight"
    (let [weighted-misc (assoc miscs.data/sample-misc :amount-is-weight true)]
      (is (= "0.022 lb" (-> weighted-misc
                            miscs.enrich/enrich-display-amount
                            :display-amount))
          "miscs.data/sample-misc sets the amount to 15.0, which is the default unit")
      (is (= "34.293 lb" (-> weighted-misc
                             (assoc :amount 15.555)
                             miscs.enrich/enrich-display-amount
                             :display-amount))
          "enrich-display-amount defaults precision to 3")
      (is (= "34.29 lb" (-> weighted-misc
                            (assoc :amount 15.555)
                            (miscs.enrich/enrich-display-amount {:misc-amount-precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision")
      (is (= "15444.4 g" (-> weighted-misc
                             (assoc :amount 15.4444)
                             (miscs.enrich/enrich-display-amount {:misc-amount-target-units :gram})
                             :display-amount))
          "enrich-display-amount unites can be configured to use seconds")
      (is (= "33.069 pound" (-> weighted-misc
                                (assoc :amount 15.0)
                                (miscs.enrich/enrich-display-amount {:misc-amount-suffix :full})
                                :display-amount))
          "enrich-display-amount can be configured to use full suffixes")
      (is (= "34.29 lb" (-> weighted-misc
                            (assoc :amount 15.555)
                            (miscs.enrich/enrich-display-amount {:precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision with default settings keys")
      (is (= "15.4 kg" (-> weighted-misc
                           (assoc :amount 15.4)
                           (miscs.enrich/enrich-display-amount {:system-of-measure :metric})
                           :display-amount))
          "enrich-display-amount unites can be configured with default settings keys")
      (is (= "33.069 pound" (-> weighted-misc
                                (assoc :amount 15.0)
                                (miscs.enrich/enrich-display-amount {:suffix :full})
                                :display-amount))
          "enrich-display-amount can be configured to use full suffixes with default settings keys")))
  (testing "Ensure enricher correctly defaults to setting display amount as a volume"
    (let [volume-misc (assoc miscs.data/sample-misc :amount-is-weight false)]
      (is (= "0.003 gal" (-> volume-misc
                             miscs.enrich/enrich-display-amount
                             :display-amount))
          "miscs.data/sample-misc sets the amount to 15.0, which is the default unit")
      (is (= "4.109 gal" (-> volume-misc
                             (assoc :amount 15.555)
                             miscs.enrich/enrich-display-amount
                             :display-amount))
          "enrich-display-amount defaults precision to 3")
      (is (= "4.11 gal" (-> volume-misc
                            (assoc :amount 15.555)
                            (miscs.enrich/enrich-display-amount {:misc-amount-precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision")
      (is (= "3043.263 tsp" (-> volume-misc
                                (assoc :amount 15.0)
                                (miscs.enrich/enrich-display-amount {:misc-amount-target-units :teaspoon})
                                :display-amount))
          "enrich-display-amount unites can be configured to use seconds")
      (is (= "3.963 US gallon" (-> volume-misc
                                   (assoc :amount 15.0)
                                   (miscs.enrich/enrich-display-amount {:misc-amount-suffix :full})
                                   :display-amount))
          "enrich-display-amount can be configured to use full suffixes")
      (is (= "4.11 gal" (-> volume-misc
                            (assoc :amount 15.555)
                            (miscs.enrich/enrich-display-amount {:precision 2})
                            :display-amount))
          "enrich-display-amount can be configured to use a different precision with default settings keys")
      (is (= "15.1 l" (-> volume-misc
                          (assoc :amount 15.1)
                          (miscs.enrich/enrich-display-amount {:system-of-measure :metric})
                          :display-amount))
          "enrich-display-amount unites can be configured with default settings keys")
      (is (= "3.963 US gallon" (-> volume-misc
                                   (assoc :amount 15.0)
                                   (miscs.enrich/enrich-display-amount {:suffix :full})
                                   :display-amount))
          "enrich-display-amount can be configured to use full suffixes with default settings keys")))
  (testing "Ensure resulting `misc` still conforms to the spec"
    (is (spoon.spec/test-valid? ::miscs.format/misc (-> miscs.data/sample-misc
                                                        (assoc :amount-is-weight true)
                                                        miscs.enrich/enrich-display-amount))
        "miscs.data/sample-misc conforms to the spec if the amount is a weight")
    (is (spoon.spec/test-valid? ::miscs.format/misc (-> miscs.data/sample-misc
                                                        (assoc :amount-is-weight false)
                                                        miscs.enrich/enrich-display-amount))
        "miscs.data/sample-misc conforms to the spec if the amount is a volume")
    (is (spoon.spec/test-valid? ::miscs.format/misc (-> (miscs.data/generate-misc)
                                                        (assoc :amount-is-weight true)
                                                        miscs.enrich/enrich-display-amount))
        "miscs.data/sample-misc conforms to the spec if the amount is a weight")
    (is (spoon.spec/test-valid? ::miscs.format/misc (-> (miscs.data/generate-misc)
                                                        (assoc :amount-is-weight false)
                                                        miscs.enrich/enrich-display-amount))
        "miscs.data/sample-misc conforms to the spec if the amount is a volume")))


;;
;; Whole object enrichment tests
;;

(deftest enrich-misc-tests
  (testing "Ensure enrichment pattern functions generate conforming data"
    (is (spoon.spec/test-valid? ::miscs.format/misc
                                (miscs.enrich/enrich-misc miscs.data/sample-misc))
        "Enrichment pattern should produce a valid misc object")
    (is (spoon.spec/test-valid? ::miscs.format/misc-wrapper
                                (miscs.enrich/enrich-misc-wrapper miscs.data/sample-misc-wrapper))
        "Enrichment pattern should produce a valid misc object")
    (is (spoon.spec/test-valid? ::miscs.format/miscs
                                (miscs.enrich/enrich-miscs miscs.data/sample-miscs))
        "Enrichment pattern should produce a valid misc object")
    (is (spoon.spec/test-valid? ::miscs.format/miscs-wrapper
                                (miscs.enrich/enrich-miscs-wrapper miscs.data/sample-miscs-wrapper))
        "Enrichment pattern should produce a valid misc object")
    (testing "Static data comparison for enrichment pattern functions"
      (is (= {:amount           0.01
              :display-time     "15.1 m"
              :amount-is-weight false
              :use              "Boil"
              :name             "Irish Moss"
              :time             15.1
              :type             "Fining"
              :notes            "Used as a clarifying agent during the last few minutes of the boil"
              :display-amount   "0.003 gal"
              :version          1}
             (miscs.enrich/enrich-misc miscs.data/sample-misc))))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary misc"
    (letfn [(gen-misc [] (miscs.data/generate-misc))]
      (is (spoon.spec/test-valid? ::miscs.format/misc
                                  (miscs.enrich/enrich-amount-is-weight (gen-misc)))
          "enrich-amount-is-weight is a function to and from common-beer-format.misc/misc")
      (is (spoon.spec/test-valid? ::miscs.format/misc
                                  (miscs.enrich/enrich-display-time (gen-misc)))
          "enrich-display-time is a function to and from common-beer-format.misc/misc")
      (is (spoon.spec/test-valid? ::miscs.format/misc
                                  (miscs.enrich/enrich-display-amount (gen-misc)))
          "enrich-display-amount is a function to and from common-beer-format.misc/misc")
      (is (spoon.spec/test-valid? ::miscs.format/misc
                                  (miscs.enrich/enrich-misc (gen-misc)))
          "enrich-misc is a function to and from common-beer-format.misc/misc")))
  (testing "Ensure enrichment pattern works against arbitrary misc wrappers"
    (is (spoon.spec/test-valid? ::miscs.format/misc-wrapper
                                (miscs.enrich/enrich-misc-wrapper (miscs.data/generate-misc-wrapper)))
        "enrich-misc-wrapper is a function to and from common-beer-format.misc/misc-wrapper")
    (is (spoon.spec/test-valid? ::miscs.format/miscs
                                (miscs.enrich/enrich-miscs (miscs.data/generate-miscs)))
        "enrich-miscs is a function to and from common-beer-format.misc/miscs")
    (is (spoon.spec/test-valid? ::miscs.format/miscs-wrapper
                                (miscs.enrich/enrich-miscs-wrapper (miscs.data/generate-miscs-wrapper)))
        "enrich-miscs-wrapper is a function to and from common-beer-format.misc/miscs-wrapper")))
