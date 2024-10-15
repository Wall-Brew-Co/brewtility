(ns brewtility.enrich.mash-test
  (:require [brewtility.data.mash :as mash.data]
            [brewtility.enrich.mash :as mash.enrich]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.mash :as mash.format]))


(deftest static-enrichment-tests
  (testing "Ensure enrichment pattern works against the static test mash steps"
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (mash.enrich/enrich-display-step-temperature mash.data/sample-mash-step))
        "enrich-display-step-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (mash.enrich/enrich-display-infuse-amount mash.data/sample-mash-step))
        "enrich-display-infuse-amount is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (mash.enrich/enrich-mash-step mash.data/sample-mash-step))
        "enrich-mash-step is a function to and from common-beer-format.mash/mash"))
  (testing "Ensure enrichment pattern works against static test mash step wrappers"
    (is (spoon.spec/test-valid? ::mash.format/mash-step-wrapper
                                (mash.enrich/enrich-mash-step-wrapper mash.data/sample-mash-step-wrapper))
        "enrich-mash-step-wrapper is a function to and from common-beer-format.mash/mash-step-wrapper"))
  (testing "Ensure enrichment pattern works against static test mash steps"
    (is (spoon.spec/test-valid? ::mash.format/mash-steps
                                (mash.enrich/enrich-mash-steps mash.data/sample-mash-steps))
        "enrich-mash-steps is a function to and from common-beer-format.mash/mash-steps"))
  (testing "Ensure enrichment pattern works against static test mash steps wrapper"
    (is (spoon.spec/test-valid? ::mash.format/mash-steps
                                (:mash-steps (mash.enrich/enrich-mash-steps-wrapper mash.data/sample-mash-steps-wrapper)))
        "enrich-mash-steps-wrapper is a function to and from common-beer-format.mash/mash-steps-wrapper"))
  (testing "Ensure enrichment pattern works against static test mash"
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-grain-temperature mash.data/sample-mash))
        "enrich-display-grain-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-tun-temperature mash.data/sample-mash))
        "enrich-display-tun-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-sparge-temperature mash.data/sample-mash))
        "enrich-display-sparge-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-tun-weight mash.data/sample-mash))
        "enrich-display-tun-weight is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-mash mash.data/sample-mash))
        "enrich-mash is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-wrapper
                                (mash.enrich/enrich-mash-wrapper mash.data/sample-mash-wrapper))
        "enrich-mash-wrapper is a function to and from common-beer-format.mash/mash-wrapper"))
  (testing "Static data comparison for enrichment pattern functions"
    (is (= {:name               "Single Step Infusion, 68 C"
            :version            1
            :grain-temp         22.0
            :mash-steps         [{:mash-step {:name               "Conversion Step, 68C"
                                              :version            1
                                              :type               "Infusion"
                                              :step-temp          68.0
                                              :step-time          60.0
                                              :infuse-amount      10.0
                                              :display-step-temp  "154.4 f"
                                              :display-infuse-amt "2.642 gal"}}]
            :display-grain-temp "71.6 f"}
           (mash.enrich/enrich-mash mash.data/sample-mash)))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary mash steps"
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (mash.enrich/enrich-display-step-temperature (mash.data/generate-mash-step)))
        "enrich-display-step-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (mash.enrich/enrich-display-infuse-amount (mash.data/generate-mash-step)))
        "enrich-display-infuse-amount is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-step
                                (mash.enrich/enrich-mash-step (mash.data/generate-mash-step)))
        "enrich-mash-step is a function to and from common-beer-format.mash/mash"))
  (testing "Ensure enrichment pattern works against arbitrary mash step wrappers"
    (is (spoon.spec/test-valid? ::mash.format/mash-step-wrapper
                                (mash.enrich/enrich-mash-step-wrapper (mash.data/generate-mash-step-wrapper)))
        "enrich-mash-step-wrapper is a function to and from common-beer-format.mash/mash-step-wrapper"))
  (testing "Ensure enrichment pattern works against arbitrary mash steps"
    (is (spoon.spec/test-valid? ::mash.format/mash-steps
                                (mash.enrich/enrich-mash-steps (mash.data/generate-mash-steps)))
        "enrich-mash-steps is a function to and from common-beer-format.mash/mash-steps"))
  (testing "Ensure enrichment pattern works against arbitrary mash steps wrapper"
    (is (spoon.spec/test-valid? ::mash.format/mash-steps
                                (:mash-steps (mash.enrich/enrich-mash-steps-wrapper (mash.data/generate-mash-steps-wrapper))))
        "enrich-mash-steps-wrapper is a function to and from common-beer-format.mash/mash-steps-wrapper"))
  (testing "Ensure enrichment pattern works against arbitrary mash"
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-grain-temperature (mash.data/generate-mash)))
        "enrich-display-grain-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-tun-temperature (mash.data/generate-mash)))
        "enrich-display-tun-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-sparge-temperature (mash.data/generate-mash)))
        "enrich-display-sparge-temperature is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-display-tun-weight (mash.data/generate-mash)))
        "enrich-display-tun-weight is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash
                                (mash.enrich/enrich-mash (mash.data/generate-mash)))
        "enrich-mash is a function to and from common-beer-format.mash/mash")
    (is (spoon.spec/test-valid? ::mash.format/mash-wrapper
                                (mash.enrich/enrich-mash-wrapper (mash.data/generate-mash-wrapper)))
        "enrich-mash-wrapper is a function to and from common-beer-format.mash/mash-wrapper")))
