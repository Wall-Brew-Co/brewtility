(ns brewtility.predicates.options-test
  (:require [brewtility.predicates.options :as sut]
            [clojure.test :refer [deftest is testing]]))


(deftest type-test
  (testing "A sanity test for all option keywords"
    (is (keyword? sut/uppercase?))))
