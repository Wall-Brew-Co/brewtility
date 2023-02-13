(ns brewtility.predicates.miscs-test
  (:require #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])
            [brewtility.data.miscs :as miscs]
            [brewtility.predicates.miscs :as sut]
            [common-beer-format.miscs :as cbf-miscs]))


;;
;; :type function tests
;;

(deftest spice?-test
  (testing "A misc with a `:type` matching `\"spice\"` returns true"
    (is (true? (sut/spice? (assoc miscs/sample-misc :type "SPiCE"))))
    (is (true? (sut/spice? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/spice)))))
  (testing "A misc with a `:type` not matching `\"spice\"` returns false"
    (is (false? (sut/spice? (assoc miscs/sample-misc :type "fining"))))
    (is (false? (sut/spice? (assoc miscs/sample-misc :type "water agent"))))
    (is (false? (sut/spice? (assoc miscs/sample-misc :type "herb"))))
    (is (false? (sut/spice? (assoc miscs/sample-misc :type "flavor"))))
    (is (false? (sut/spice? (assoc miscs/sample-misc :type "other"))))
    (is (false? (sut/spice? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/fining))))
    (is (false? (sut/spice? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/water-agent))))
    (is (false? (sut/spice? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/herb))))
    (is (false? (sut/spice? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/flavor))))
    (is (false? (sut/spice? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/other)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/spice? miscs/sample-misc)))
    (is (boolean? (sut/spice? (miscs/generate-misc)))))
  (testing "If the misc is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :type"
                  (sut/spice? (dissoc miscs/sample-misc :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :type"
                   (sut/spice? (dissoc miscs/sample-misc :type)))))))


(deftest fining?-test
  (testing "A misc with a `:type` matching `\"fining\"` returns true"
    (is (true? (sut/fining? (assoc miscs/sample-misc :type "fining"))))
    (is (true? (sut/fining? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/fining)))))
  (testing "A misc with a `:type` not matching `\"fining\"` returns false"
    (is (false? (sut/fining? (assoc miscs/sample-misc :type "spice"))))
    (is (false? (sut/fining? (assoc miscs/sample-misc :type "water agent"))))
    (is (false? (sut/fining? (assoc miscs/sample-misc :type "herb"))))
    (is (false? (sut/fining? (assoc miscs/sample-misc :type "flavor"))))
    (is (false? (sut/fining? (assoc miscs/sample-misc :type "other"))))
    (is (false? (sut/fining? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/spice))))
    (is (false? (sut/fining? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/water-agent))))
    (is (false? (sut/fining? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/herb))))
    (is (false? (sut/fining? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/flavor))))
    (is (false? (sut/fining? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/other)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/fining? miscs/sample-misc)))
    (is (boolean? (sut/fining? (miscs/generate-misc)))))
  (testing "If the misc is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :type"
                  (sut/fining? (dissoc miscs/sample-misc :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :type"
                   (sut/fining? (dissoc miscs/sample-misc :type)))))))


(deftest water-agent?-test
  (testing "A misc with a `:type` matching `\"water-agent\"` returns true"
    (is (true? (sut/water-agent? (assoc miscs/sample-misc :type "water agent"))))
    (is (true? (sut/water-agent? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/water-agent)))))
  (testing "A misc with a `:type` not matching `\"water-agent\"` returns false"
    (is (false? (sut/water-agent? (assoc miscs/sample-misc :type "spice"))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc :type "other"))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc :type "fining"))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc :type "herb"))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc :type "flavor"))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/spice))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/other))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/fining))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/herb))))
    (is (false? (sut/water-agent? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/flavor)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/water-agent? miscs/sample-misc)))
    (is (boolean? (sut/water-agent? (miscs/generate-misc)))))
  (testing "If the misc is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :type"
                  (sut/water-agent? (dissoc miscs/sample-misc :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :type"
                   (sut/water-agent? (dissoc miscs/sample-misc :type)))))))


(deftest herb?-test
  (testing "A misc with a `:type` matching `\"herb\"` returns true"
    (is (true? (sut/herb? (assoc miscs/sample-misc :type "herb"))))
    (is (true? (sut/herb? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/herb)))))
  (testing "A misc with a `:type` not matching `\"herb\"` returns false"
    (is (false? (sut/herb? (assoc miscs/sample-misc :type "spice"))))
    (is (false? (sut/herb? (assoc miscs/sample-misc :type "water agent"))))
    (is (false? (sut/herb? (assoc miscs/sample-misc :type "fining"))))
    (is (false? (sut/herb? (assoc miscs/sample-misc :type "flavor"))))
    (is (false? (sut/herb? (assoc miscs/sample-misc :type "other"))))
    (is (false? (sut/herb? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/spice))))
    (is (false? (sut/herb? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/water-agent))))
    (is (false? (sut/herb? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/fining))))
    (is (false? (sut/herb? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/flavor))))
    (is (false? (sut/herb? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/other)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/herb? miscs/sample-misc)))
    (is (boolean? (sut/herb? (miscs/generate-misc)))))
  (testing "If the misc is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :type"
                  (sut/herb? (dissoc miscs/sample-misc :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :type"
                   (sut/herb? (dissoc miscs/sample-misc :type)))))))


(deftest flavor?-test
  (testing "A misc with a `:type` matching `\"flavor\"` returns true"
    (is (true? (sut/flavor? (assoc miscs/sample-misc :type "flavor"))))
    (is (true? (sut/flavor? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/flavor)))))
  (testing "A misc with a `:type` not matching `\"flavor\"` returns false"
    (is (false? (sut/flavor? (assoc miscs/sample-misc :type "spice"))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc :type "water agent"))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc :type "fining"))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc :type "herb"))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc :type "other"))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/spice))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/water-agent))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/fining))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/herb))))
    (is (false? (sut/flavor? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/other)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/flavor? miscs/sample-misc)))
    (is (boolean? (sut/flavor? (miscs/generate-misc)))))
  (testing "If the misc is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :type"
                  (sut/flavor? (dissoc miscs/sample-misc :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :type"
                   (sut/flavor? (dissoc miscs/sample-misc :type)))))))


(deftest other?-test
  (testing "A misc with a `:type` matching `\"other\"` returns true"
    (is (true? (sut/other? (assoc miscs/sample-misc :type "other"))))
    (is (true? (sut/other? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/other)))))
  (testing "A misc with a `:type` not matching `\"other\"` returns false"
    (is (false? (sut/other? (assoc miscs/sample-misc :type "spice"))))
    (is (false? (sut/other? (assoc miscs/sample-misc :type "water agent"))))
    (is (false? (sut/other? (assoc miscs/sample-misc :type "fining"))))
    (is (false? (sut/other? (assoc miscs/sample-misc :type "herb"))))
    (is (false? (sut/other? (assoc miscs/sample-misc :type "flavor"))))
    (is (false? (sut/other? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/spice))))
    (is (false? (sut/other? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/water-agent))))
    (is (false? (sut/other? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/fining))))
    (is (false? (sut/other? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/herb))))
    (is (false? (sut/other? (assoc miscs/sample-misc cbf-miscs/type cbf-miscs/flavor)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/other? miscs/sample-misc)))
    (is (boolean? (sut/other? (miscs/generate-misc)))))
  (testing "If the misc is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :type"
                  (sut/other? (dissoc miscs/sample-misc :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :type"
                   (sut/other? (dissoc miscs/sample-misc :type)))))))


;;
;; :use function tests
;;

(deftest boil?-test
  (testing "A misc with a `:use` matching `\"boil\"` returns true"
    (is (true? (sut/boil? (assoc miscs/sample-misc :use "boil"))))
    (is (true? (sut/boil? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/boil)))))
  (testing "A misc with a `:use` not matching `\"boil\"` returns false"
    (is (false? (sut/boil? (assoc miscs/sample-misc :use "mash"))))
    (is (false? (sut/boil? (assoc miscs/sample-misc :use "primary"))))
    (is (false? (sut/boil? (assoc miscs/sample-misc :use "secondary"))))
    (is (false? (sut/boil? (assoc miscs/sample-misc :use "bottling"))))
    (is (false? (sut/boil? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/mash))))
    (is (false? (sut/boil? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/primary))))
    (is (false? (sut/boil? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/secondary))))
    (is (false? (sut/boil? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/bottling)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/boil? miscs/sample-misc)))
    (is (boolean? (sut/boil? (miscs/generate-misc)))))
  (testing "If the misc is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :use"
                  (sut/boil? (dissoc miscs/sample-misc :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :use"
                   (sut/boil? (dissoc miscs/sample-misc :use)))))))


(deftest mash?-test
  (testing "A misc with a `:use` matching `\"mash\"` returns true"
    (is (true? (sut/mash? (assoc miscs/sample-misc :use "mash"))))
    (is (true? (sut/mash? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/mash)))))
  (testing "A misc with a `:use` not matching `\"mash\"` returns false"
    (is (false? (sut/mash? (assoc miscs/sample-misc :use "boil"))))
    (is (false? (sut/mash? (assoc miscs/sample-misc :use "primary"))))
    (is (false? (sut/mash? (assoc miscs/sample-misc :use "secondary"))))
    (is (false? (sut/mash? (assoc miscs/sample-misc :use "bottling"))))
    (is (false? (sut/mash? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/boil))))
    (is (false? (sut/mash? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/primary))))
    (is (false? (sut/mash? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/secondary))))
    (is (false? (sut/mash? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/bottling)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/mash? miscs/sample-misc)))
    (is (boolean? (sut/mash? (miscs/generate-misc)))))
  (testing "If the misc is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :use"
                  (sut/mash? (dissoc miscs/sample-misc :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :use"
                   (sut/mash? (dissoc miscs/sample-misc :use)))))))


(deftest primary?-test
  (testing "A misc with a `:use` matching `\"primary\"` returns true"
    (is (true? (sut/primary? (assoc miscs/sample-misc :use "primary"))))
    (is (true? (sut/primary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/primary)))))
  (testing "A misc with a `:use` not matching `\"primary\"` returns false"
    (is (false? (sut/primary? (assoc miscs/sample-misc :use "boil"))))
    (is (false? (sut/primary? (assoc miscs/sample-misc :use "mash"))))
    (is (false? (sut/primary? (assoc miscs/sample-misc :use "secondary"))))
    (is (false? (sut/primary? (assoc miscs/sample-misc :use "bottling"))))
    (is (false? (sut/primary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/boil))))
    (is (false? (sut/primary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/mash))))
    (is (false? (sut/primary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/secondary))))
    (is (false? (sut/primary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/bottling)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/primary? miscs/sample-misc)))
    (is (boolean? (sut/primary? (miscs/generate-misc)))))
  (testing "If the misc is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :use"
                  (sut/primary? (dissoc miscs/sample-misc :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :use"
                   (sut/primary? (dissoc miscs/sample-misc :use)))))))


(deftest secondary?-test
  (testing "A misc with a `:use` matching `\"secondary\"` returns true"
    (is (true? (sut/secondary? (assoc miscs/sample-misc :use "secondary"))))
    (is (true? (sut/secondary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/secondary)))))
  (testing "A misc with a `:use` not matching `\"secondary\"` returns false"
    (is (false? (sut/secondary? (assoc miscs/sample-misc :use "boil"))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc :use "mash"))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc :use "primary"))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc :use "bottling"))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/boil))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/mash))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/primary))))
    (is (false? (sut/secondary? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/bottling)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/secondary? miscs/sample-misc)))
    (is (boolean? (sut/secondary? (miscs/generate-misc)))))
  (testing "If the misc is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :use"
                  (sut/secondary? (dissoc miscs/sample-misc :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :use"
                   (sut/secondary? (dissoc miscs/sample-misc :use)))))))


(deftest bottling?-test
  (testing "A misc with a `:use` matching `\"bottling\"` returns true"
    (is (true? (sut/bottling? (assoc miscs/sample-misc :use "bottling"))))
    (is (true? (sut/bottling? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/bottling)))))
  (testing "A misc with a `:use` not matching `\"bottling\"` returns false"
    (is (false? (sut/bottling? (assoc miscs/sample-misc :use "boil"))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc :use "mash"))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc :use "primary"))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc :use "secondary"))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/boil))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/mash))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/primary))))
    (is (false? (sut/bottling? (assoc miscs/sample-misc cbf-miscs/use cbf-miscs/secondary)))))
  (testing "This is a function from a misc to a boolean"
    (is (boolean? (sut/bottling? miscs/sample-misc)))
    (is (boolean? (sut/bottling? (miscs/generate-misc)))))
  (testing "If the misc is missing `:use`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Misc :use"
                  (sut/bottling? (dissoc miscs/sample-misc :use)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Misc :use"
                   (sut/bottling? (dissoc miscs/sample-misc :use)))))))
