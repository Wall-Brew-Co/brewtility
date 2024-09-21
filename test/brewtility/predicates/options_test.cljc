(ns brewtility.predicates.options-test
  (:require #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.predicates.options :as sut]))


(deftest type-test
  (testing "A sanity test for all option keywords"
    (is (keyword? sut/uppercase?))))
