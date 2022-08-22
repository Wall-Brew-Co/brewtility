(ns brewtility.enrich.hops-test
  (:require [brewtility.data.hops :as hop.data]
            [brewtility.enrich.hops :as hop.enrich]
            [clojure.spec.alpha :as s]
            [common-beer-format.hops :as hop.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


;;
;; Whole object enrichment tests
;;

(deftest enrich-hop-tests
  (testing "Ensure enrichment pattern functions generate conforming data"
    (is (s/valid? ::hop.format/hop
                  (hop.enrich/enrich-hop hop.data/sample-hop))
        "Enrichment pattern should produce a valid hop object")
    (is (s/valid? ::hop.format/hop-wrapper
                  (hop.enrich/enrich-hop-wrapper hop.data/sample-hop-wrapper))
        "Enrichment pattern should produce a valid hop object")
    (is (s/valid? ::hop.format/hops
                  (hop.enrich/enrich-hops hop.data/sample-hops))
        "Enrichment pattern should produce a valid hop object")
    (is (s/valid? ::hop.format/hops-wrapper
                  (hop.enrich/enrich-hops-wrapper hop.data/sample-hops-wrapper))
        "Enrichment pattern should produce a valid hop object")
    (testing "Static data comparison for enrichment pattern functions"
      (is (= {:amount         0.0638
              :display-time   "60.0 m"
              :use            "Boil"
              :name           "Goldings, East Kent"
              :time           60.0
              :display-amount "0.141 lb"
              :notes          "Great all purpose UK hop for ales, stouts, porters"
              :alpha          5.0
              :version        1}
             (hop.enrich/enrich-hop hop.data/sample-hop))))))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary hop"
    (letfn [(gen-hop [] (hop.data/generate-hop))]
      (is (s/valid? ::hop.format/hop
                    (hop.enrich/enrich-display-amount (gen-hop)))
          "enrich-display-amount is a function to and from common-beer-format.hops/hop")
      (is (s/valid? ::hop.format/hop
                    (hop.enrich/enrich-display-time (gen-hop)))
          "enrich-display-time is a function to and from common-beer-format.hops/hop")))
  (testing "Ensure enrichment pattern works against arbitrary hop wrappers"
    (is (s/valid? ::hop.format/hop-wrapper
                  (hop.enrich/enrich-hop-wrapper (hop.data/generate-hop-wrapper)))
        "enrich-hop-wrapper is a function to and from common-beer-format.hops/hop-wrapper")
    (is (s/valid? ::hop.format/hops
                  (hop.enrich/enrich-hops (hop.data/generate-hops)))
        "enrich-hops is a function to and from common-beer-format.hops/hops")
    (is (s/valid? ::hop.format/hops-wrapper
                  (hop.enrich/enrich-hops-wrapper (hop.data/generate-hops-wrapper)))
        "enrich-hops-wrapper is a function to and from common-beer-format.hops/hops-wrapper")))

