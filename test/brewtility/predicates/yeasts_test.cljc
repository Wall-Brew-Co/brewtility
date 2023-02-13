(ns brewtility.predicates.yeasts-test
  (:require #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])
            [brewtility.data.yeasts :as yeasts]
            [brewtility.predicates.yeasts :as sut]
            [common-beer-format.yeasts :as cbf-yeasts]))


;;
;; :type function tests
;;

(deftest lager?-test
  (testing "A yeast with a `:type` matching `\"lager\"` returns true"
    (is (true? (sut/lager? (assoc yeasts/sample-yeast :type "lager"))))
    (is (true? (sut/lager? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/lager)))))
  (testing "A yeast with a `:type` not matching `\"lager\"` returns false"
    (is (false? (sut/lager? (assoc yeasts/sample-yeast :type "ale"))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast :type "wheat"))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast :type "wine"))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast :type "champagne"))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/ale))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wheat))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wine))))
    (is (false? (sut/lager? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/champagne)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/lager? yeasts/sample-yeast)))
    (is (boolean? (sut/lager? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :type"
                  (sut/lager? (dissoc yeasts/sample-yeast :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :type"
                   (sut/lager? (dissoc yeasts/sample-yeast :type)))))))


(deftest ale?-test
  (testing "A yeast with a `:type` matching `\"ale\"` returns true"
    (is (true? (sut/ale? (assoc yeasts/sample-yeast :type "ale"))))
    (is (true? (sut/ale? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/ale)))))
  (testing "A yeast with a `:type` not matching `\"ale\"` returns false"
    (is (false? (sut/ale? (assoc yeasts/sample-yeast :type "lager"))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast :type "wheat"))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast :type "wine"))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast :type "champagne"))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/lager))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wheat))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wine))))
    (is (false? (sut/ale? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/champagne)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/ale? yeasts/sample-yeast)))
    (is (boolean? (sut/ale? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :type"
                  (sut/ale? (dissoc yeasts/sample-yeast :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :type"
                   (sut/ale? (dissoc yeasts/sample-yeast :type)))))))


(deftest wheat?-test
  (testing "A yeast with a `:type` matching `\"wheat\"` returns true"
    (is (true? (sut/wheat? (assoc yeasts/sample-yeast :type "wheat"))))
    (is (true? (sut/wheat? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wheat)))))
  (testing "A yeast with a `:type` not matching `\"wheat\"` returns false"
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast :type "lager"))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast :type "ale"))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast :type "wine"))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast :type "champagne"))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/lager))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/ale))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wine))))
    (is (false? (sut/wheat? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/champagne)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/wheat? yeasts/sample-yeast)))
    (is (boolean? (sut/wheat? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :type"
                  (sut/wheat? (dissoc yeasts/sample-yeast :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :type"
                   (sut/wheat? (dissoc yeasts/sample-yeast :type)))))))


(deftest wine?-test
  (testing "A yeast with a `:type` matching `\"wine\"` returns true"
    (is (true? (sut/wine? (assoc yeasts/sample-yeast :type "wine"))))
    (is (true? (sut/wine? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wine)))))
  (testing "A yeast with a `:type` not matching `\"wine\"` returns false"
    (is (false? (sut/wine? (assoc yeasts/sample-yeast :type "lager"))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast :type "ale"))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast :type "wheat"))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast :type "champagne"))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/lager))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/ale))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wheat))))
    (is (false? (sut/wine? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/champagne)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/wine? yeasts/sample-yeast)))
    (is (boolean? (sut/wine? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :type"
                  (sut/wine? (dissoc yeasts/sample-yeast :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :type"
                   (sut/wine? (dissoc yeasts/sample-yeast :type)))))))


(deftest champagne?-test
  (testing "A yeast with a `:type` matching `\"champagne\"` returns true"
    (is (true? (sut/champagne? (assoc yeasts/sample-yeast :type "champagne"))))
    (is (true? (sut/champagne? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/champagne)))))
  (testing "A yeast with a `:type` not matching `\"champagne\"` returns false"
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast :type "lager"))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast :type "ale"))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast :type "wheat"))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast :type "wine"))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/lager))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/ale))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wheat))))
    (is (false? (sut/champagne? (assoc yeasts/sample-yeast cbf-yeasts/type cbf-yeasts/wine)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/champagne? yeasts/sample-yeast)))
    (is (boolean? (sut/champagne? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:type`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :type"
                  (sut/champagne? (dissoc yeasts/sample-yeast :type)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :type"
                   (sut/champagne? (dissoc yeasts/sample-yeast :type)))))))


;;
;; :form function tests
;;

(deftest liquid?-test
  (testing "A yeast with a `:form` matching `\"liquid\"` returns true"
    (is (true? (sut/liquid? (assoc yeasts/sample-yeast :form "liquid"))))
    (is (true? (sut/liquid? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/liquid)))))
  (testing "A yeast with a `:form` not matching `\"liquid\"` returns false"
    (is (false? (sut/liquid? (assoc yeasts/sample-yeast :form "dry"))))
    (is (false? (sut/liquid? (assoc yeasts/sample-yeast :form "slant"))))
    (is (false? (sut/liquid? (assoc yeasts/sample-yeast :form "culture"))))
    (is (false? (sut/liquid? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/dry))))
    (is (false? (sut/liquid? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/slant))))
    (is (false? (sut/liquid? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/culture)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/liquid? yeasts/sample-yeast)))
    (is (boolean? (sut/liquid? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :form"
                  (sut/liquid? (dissoc yeasts/sample-yeast :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :form"
                   (sut/liquid? (dissoc yeasts/sample-yeast :form)))))))


(deftest dry?-test
  (testing "A yeast with a `:form` matching `\"dry\"` returns true"
    (is (true? (sut/dry? (assoc yeasts/sample-yeast :form "dry"))))
    (is (true? (sut/dry? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/dry)))))
  (testing "A yeast with a `:form` not matching `\"dry\"` returns false"
    (is (false? (sut/dry? (assoc yeasts/sample-yeast :form "liquid"))))
    (is (false? (sut/dry? (assoc yeasts/sample-yeast :form "slant"))))
    (is (false? (sut/dry? (assoc yeasts/sample-yeast :form "culture"))))
    (is (false? (sut/dry? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/liquid))))
    (is (false? (sut/dry? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/slant))))
    (is (false? (sut/dry? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/culture)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/dry? yeasts/sample-yeast)))
    (is (boolean? (sut/dry? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :form"
                  (sut/dry? (dissoc yeasts/sample-yeast :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :form"
                   (sut/dry? (dissoc yeasts/sample-yeast :form)))))))


(deftest slant?-test
  (testing "A yeast with a `:form` matching `\"slant\"` returns true"
    (is (true? (sut/slant? (assoc yeasts/sample-yeast :form "slant"))))
    (is (true? (sut/slant? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/slant)))))
  (testing "A yeast with a `:form` not matching `\"slant\"` returns false"
    (is (false? (sut/slant? (assoc yeasts/sample-yeast :form "liquid"))))
    (is (false? (sut/slant? (assoc yeasts/sample-yeast :form "dry"))))
    (is (false? (sut/slant? (assoc yeasts/sample-yeast :form "culture"))))
    (is (false? (sut/slant? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/liquid))))
    (is (false? (sut/slant? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/dry))))
    (is (false? (sut/slant? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/culture)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/slant? yeasts/sample-yeast)))
    (is (boolean? (sut/slant? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :form"
                  (sut/slant? (dissoc yeasts/sample-yeast :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :form"
                   (sut/slant? (dissoc yeasts/sample-yeast :form)))))))


(deftest culture?-test
  (testing "A yeast with a `:form` matching `\"culture\"` returns true"
    (is (true? (sut/culture? (assoc yeasts/sample-yeast :form "culture"))))
    (is (true? (sut/culture? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/culture)))))
  (testing "A yeast with a `:form` not matching `\"culture\"` returns false"
    (is (false? (sut/culture? (assoc yeasts/sample-yeast :form "liquid"))))
    (is (false? (sut/culture? (assoc yeasts/sample-yeast :form "dry"))))
    (is (false? (sut/culture? (assoc yeasts/sample-yeast :form "slant"))))
    (is (false? (sut/culture? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/liquid))))
    (is (false? (sut/culture? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/dry))))
    (is (false? (sut/culture? (assoc yeasts/sample-yeast cbf-yeasts/form cbf-yeasts/slant)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/culture? yeasts/sample-yeast)))
    (is (boolean? (sut/culture? (yeasts/generate-yeast)))))
  (testing "If the yeast is missing `:form`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :form"
                  (sut/culture? (dissoc yeasts/sample-yeast :form)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :form"
                   (sut/culture? (dissoc yeasts/sample-yeast :form)))))))


;;
;; :flocculation function tests
;;

(deftest low-flocculation?-test
  (testing "A yeast with a `:flocculation` matching `\"culture\"` returns true"
    (is (true? (sut/low-flocculation? (assoc yeasts/sample-yeast :flocculation "low"))))
    (is (true? (sut/low-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/low)))))
  (testing "A yeast with a `:flocculation` not matching `\"culture\"` returns false"
    (is (false? (sut/low-flocculation? (assoc yeasts/sample-yeast :flocculation "medium"))))
    (is (false? (sut/low-flocculation? (assoc yeasts/sample-yeast :flocculation "high"))))
    (is (false? (sut/low-flocculation? (assoc yeasts/sample-yeast :flocculation "very high"))))
    (is (false? (sut/low-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/medium))))
    (is (false? (sut/low-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/high))))
    (is (false? (sut/low-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/very-high)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/low-flocculation? (assoc yeasts/sample-yeast :flocculation (yeasts/random-flocculation)))))
    (is (boolean? (sut/low-flocculation? (assoc (yeasts/generate-yeast) :flocculation (yeasts/random-flocculation))))))
  (testing "If the yeast is missing `:flocculation`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :flocculation"
                  (sut/low-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :flocculation"
                   (sut/low-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))))


(deftest medium-flocculation?-test
  (testing "A yeast with a `:flocculation` matching `\"culture\"` returns true"
    (is (true? (sut/medium-flocculation? (assoc yeasts/sample-yeast :flocculation "medium"))))
    (is (true? (sut/medium-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/medium)))))
  (testing "A yeast with a `:flocculation` not matching `\"culture\"` returns false"
    (is (false? (sut/medium-flocculation? (assoc yeasts/sample-yeast :flocculation "low"))))
    (is (false? (sut/medium-flocculation? (assoc yeasts/sample-yeast :flocculation "high"))))
    (is (false? (sut/medium-flocculation? (assoc yeasts/sample-yeast :flocculation "very high"))))
    (is (false? (sut/medium-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/low))))
    (is (false? (sut/medium-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/high))))
    (is (false? (sut/medium-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/very-high)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/medium-flocculation? (assoc yeasts/sample-yeast :flocculation (yeasts/random-flocculation)))))
    (is (boolean? (sut/medium-flocculation? (assoc (yeasts/generate-yeast) :flocculation (yeasts/random-flocculation))))))
  (testing "If the yeast is missing `:flocculation`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :flocculation"
                  (sut/medium-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :flocculation"
                   (sut/medium-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))))


(deftest high-flocculation?-test
  (testing "A yeast with a `:flocculation` matching `\"culture\"` returns true"
    (is (true? (sut/high-flocculation? (assoc yeasts/sample-yeast :flocculation "high"))))
    (is (true? (sut/high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/high)))))
  (testing "A yeast with a `:flocculation` not matching `\"culture\"` returns false"
    (is (false? (sut/high-flocculation? (assoc yeasts/sample-yeast :flocculation "low"))))
    (is (false? (sut/high-flocculation? (assoc yeasts/sample-yeast :flocculation "medium"))))
    (is (false? (sut/high-flocculation? (assoc yeasts/sample-yeast :flocculation "very high"))))
    (is (false? (sut/high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/low))))
    (is (false? (sut/high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/medium))))
    (is (false? (sut/high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/very-high)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/high-flocculation? (assoc yeasts/sample-yeast :flocculation (yeasts/random-flocculation)))))
    (is (boolean? (sut/high-flocculation? (assoc (yeasts/generate-yeast) :flocculation (yeasts/random-flocculation))))))
  (testing "If the yeast is missing `:flocculation`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :flocculation"
                  (sut/high-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :flocculation"
                   (sut/high-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))))


(deftest very-high-flocculation?-test
  (testing "A yeast with a `:flocculation` matching `\"culture\"` returns true"
    (is (true? (sut/very-high-flocculation? (assoc yeasts/sample-yeast :flocculation "very high"))))
    (is (true? (sut/very-high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/very-high)))))
  (testing "A yeast with a `:flocculation` not matching `\"culture\"` returns false"
    (is (false? (sut/very-high-flocculation? (assoc yeasts/sample-yeast :flocculation "low"))))
    (is (false? (sut/very-high-flocculation? (assoc yeasts/sample-yeast :flocculation "medium"))))
    (is (false? (sut/very-high-flocculation? (assoc yeasts/sample-yeast :flocculation "high"))))
    (is (false? (sut/very-high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/low))))
    (is (false? (sut/very-high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/medium))))
    (is (false? (sut/very-high-flocculation? (assoc yeasts/sample-yeast cbf-yeasts/flocculation cbf-yeasts/high)))))
  (testing "This is a function from a yeast to a boolean"
    (is (boolean? (sut/very-high-flocculation? (assoc yeasts/sample-yeast :flocculation (yeasts/random-flocculation)))))
    (is (boolean? (sut/very-high-flocculation? (assoc (yeasts/generate-yeast) :flocculation (yeasts/random-flocculation))))))
  (testing "If the yeast is missing `:flocculation`, throw an exception"
    #?(:clj (is (thrown-with-msg? Exception
                                  #"Yeast :flocculation"
                  (sut/very-high-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))
    #?(:cljs (is (thrown-with-msg? js/Error
                                   #"Yeast :flocculation"
                   (sut/very-high-flocculation? (dissoc yeasts/sample-yeast :flocculation)))))))
