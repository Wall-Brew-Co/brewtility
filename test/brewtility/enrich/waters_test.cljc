(ns brewtility.enrich.waters-test
  (:require [brewtility.data.waters :as waters.data]
            [brewtility.enrich.waters :as waters.enrich]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.waters :as waters.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))

(deftest enrich-display-amount-test
  (testing "Ensure :display-amount renders correctly"
    (is (= "5.283 gal" (-> waters.data/sample-water
                           waters.enrich/enrich-display-amount
                           :display-amount))
        "waters.data/sample-water sets the amount to 15.0, which is the default unit")
    (is (= "4.109 gal" (-> waters.data/sample-water
                           (assoc :amount 15.555)
                           waters.enrich/enrich-display-amount
                           :display-amount))
        "enrich-display-amount defaults precision to 3")
    (is (= "4.11 gal" (-> waters.data/sample-water
                          (assoc :amount 15.555)
                          (waters.enrich/enrich-display-amount {:water-amount-precision 2})
                          :display-amount))
        "enrich-display-amount can be configured to use a different precision")
    (is (= "3043.263 tsp" (-> waters.data/sample-water
                              (assoc :amount 15.0)
                              (waters.enrich/enrich-display-amount {:water-amount-target-units :teaspoon})
                              :display-amount))
        "enrich-display-amount unites can be configured to use seconds")
    (is (= "3.963 US gallon" (-> waters.data/sample-water
                                 (assoc :amount 15.0)
                                 (waters.enrich/enrich-display-amount {:water-amount-suffix :full})
                                 :display-amount))
        "enrich-display-amount can be configured to use full suffixes")
    (is (= "4.11 gal" (-> waters.data/sample-water
                          (assoc :amount 15.555)
                          (waters.enrich/enrich-display-amount {:precision 2})
                          :display-amount))
        "enrich-display-amount can be configured to use a different precision with default settings keys")
    (is (= "15.0 l" (-> waters.data/sample-water
                        (assoc :amount 15.0)
                        (waters.enrich/enrich-display-amount {:system-of-measure :metric})
                        :display-amount))
        "enrich-display-amount unites can be configured with default settings keys")
    (is (= "3.963 US gallon" (-> waters.data/sample-water
                                 (assoc :amount 15.0)
                                 (waters.enrich/enrich-display-amount {:suffix :full})
                                 :display-amount))
        "enrich-display-amount can be configured to use full suffixes with default settings keys")))

;;
;; Whole object enrichment tests
;;

(deftest enrich-water-tests
  (testing "Ensure enrichment pattern functions generate conforming data"
    (is (spoon.spec/test-valid? ::waters.format/water
                                (waters.enrich/enrich-water waters.data/sample-water))
        "Enrichment pattern should produce a valid water object")
    (is (spoon.spec/test-valid? ::waters.format/water-wrapper
                                (waters.enrich/enrich-water-wrapper waters.data/sample-water-wrapper))
        "Enrichment pattern should produce a valid water object")
    (is (spoon.spec/test-valid? ::waters.format/waters
                                (waters.enrich/enrich-waters waters.data/sample-waters))
        "Enrichment pattern should produce a valid water object")
    (is (spoon.spec/test-valid? ::waters.format/waters-wrapper
                                (waters.enrich/enrich-waters-wrapper waters.data/sample-waters-wrapper))
        "Enrichment pattern should produce a valid water object")
    (testing "Static data comparison for enrichment pattern functions"
      (is (= {:amount         20.0
              :name           "Chicago"
              :sulfate        725.0
              :magnesium      45.0
              :calcium        295.0
              :bicarbonate    300.0
              :display-amount "5.283 gal"
              :notes          "The best there is"
              :sodium         55.0
              :ph             8.0
              :chloride       25.0
              :version        1}
             (waters.enrich/enrich-water waters.data/sample-water))))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary water"
    (letfn [(gen-water [] (waters.data/generate-water))]
      (is (spoon.spec/test-valid? ::waters.format/water
                                  (waters.enrich/enrich-display-amount (gen-water)))
          "enrich-display-amount is a function to and from common-beer-format.water/water")
      (is (spoon.spec/test-valid? ::waters.format/water
                                  (waters.enrich/enrich-water (gen-water)))
          "enrich-water is a function to and from common-beer-format.water/water")))
  (testing "Ensure enrichment pattern works against arbitrary water wrappers"
    (is (spoon.spec/test-valid? ::waters.format/water-wrapper
                                (waters.enrich/enrich-water-wrapper (waters.data/generate-water-wrapper)))
        "enrich-water-wrapper is a function to and from common-beer-format.water/water-wrapper")
    (is (spoon.spec/test-valid? ::waters.format/waters
                                (waters.enrich/enrich-waters (waters.data/generate-waters)))
        "enrich-waters is a function to and from common-beer-format.water/waters")
    (is (spoon.spec/test-valid? ::waters.format/waters-wrapper
                                (waters.enrich/enrich-waters-wrapper (waters.data/generate-waters-wrapper)))
        "enrich-waters-wrapper is a function to and from common-beer-format.water/waters-wrapper")))
