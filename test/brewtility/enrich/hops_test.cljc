(ns brewtility.enrich.hops-test
  (:require [brewtility.data.hops :as hop.data]
            [brewtility.enrich.hops :as hop.enrich]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.hops :as hop.format]))


;;
;; Whole object enrichment tests
;;

(deftest enrich-hop-tests
  (testing "Ensure enrichment pattern functions generate conforming data"
    (is (spoon.spec/test-valid? ::hop.format/hop
                                (hop.enrich/enrich-hop hop.data/sample-hop))
        "Enrichment pattern should produce a valid hop object")
    (is (spoon.spec/test-valid? ::hop.format/hop-wrapper
                                (hop.enrich/enrich-hop-wrapper hop.data/sample-hop-wrapper))
        "Enrichment pattern should produce a valid hop object")
    (is (spoon.spec/test-valid? ::hop.format/hops
                                (hop.enrich/enrich-hops hop.data/sample-hops))
        "Enrichment pattern should produce a valid hop object")
    (is (spoon.spec/test-valid? ::hop.format/hops-wrapper
                                (hop.enrich/enrich-hops-wrapper hop.data/sample-hops-wrapper))
        "Enrichment pattern should produce a valid hop object")
    (testing "Static data comparison for enrichment pattern functions"
      (is (= {:amount         0.0638
              :display-time   "60.1 m"
              :use            "Boil"
              :name           "Goldings, East Kent"
              :time           60.1
              :display-amount "0.141 lb"
              :notes          "Great all purpose UK hop for ales, stouts, porters"
              :alpha          5.0
              :version        1}
             (hop.enrich/enrich-hop hop.data/sample-hop))))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary hop"
    (letfn [(gen-hop [] (hop.data/generate-hop))]
      (is (spoon.spec/test-valid? ::hop.format/hop
                                  (hop.enrich/enrich-display-amount (gen-hop)))
          "enrich-display-amount is a function to and from common-beer-format.hopspec/hop")
      (is (spoon.spec/test-valid? ::hop.format/hop
                                  (hop.enrich/enrich-display-time (gen-hop)))
          "enrich-display-time is a function to and from common-beer-format.hopspec/hop")))
  (testing "Ensure enrichment pattern works against arbitrary hop wrappers"
    (is (spoon.spec/test-valid? ::hop.format/hop-wrapper
                                (hop.enrich/enrich-hop-wrapper (hop.data/generate-hop-wrapper)))
        "enrich-hop-wrapper is a function to and from common-beer-format.hopspec/hop-wrapper")
    (is (spoon.spec/test-valid? ::hop.format/hops
                                (hop.enrich/enrich-hops (hop.data/generate-hops)))
        "enrich-hops is a function to and from common-beer-format.hopspec/hops")
    (is (spoon.spec/test-valid? ::hop.format/hops-wrapper
                                (hop.enrich/enrich-hops-wrapper (hop.data/generate-hops-wrapper)))
        "enrich-hops-wrapper is a function to and from common-beer-format.hopspec/hops-wrapper")))

