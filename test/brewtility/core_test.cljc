(ns brewtility.core-test
  (:require [brewtility.core :as sut]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))

(deftest sample-test
  (testing "I don't test anything useful!"
    (is (= 1 1))))
