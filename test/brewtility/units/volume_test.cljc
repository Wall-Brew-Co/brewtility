(ns brewtility.units.volume-test
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]
            [brewtility.units.volume :as sut]
            [clojure.test :refer [deftest is testing]]))


(deftest code-type-tests
  (testing "Ensure maps used for options are structurally correct"
    (testing "Measurement types"
      (is (set? sut/measurements))
      (is (every? keyword? sut/measurements))
      (is (not-empty sut/measurements)))
    (testing "Measurement display names"
      (is (map? sut/measurements->display-name))
      (is (not-empty sut/measurements->display-name))
      (is (every? keyword? (keys sut/measurements->display-name)))
      (is (every? map? (vals sut/measurements->display-name)))
      (is (every? #(contains? % options/full) (vals sut/measurements->display-name)))
      (is (every? #(contains? % options/short) (vals sut/measurements->display-name))))
    (testing "measurement->litre"
      (is (map? sut/measurement->litre))
      (is (not-empty sut/measurement->litre))
      (is (every? keyword? (keys sut/measurement->litre)))
      (is (every? number? (vals sut/measurement->litre))))
    (is (= (set (keys sut/measurement->litre))
           (set (keys sut/measurements->display-name))
           sut/measurements))))


(deftest conversion-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/liter options/litre))
           (precision/->2dp (sut/convert 100.0 :litre :liter))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/milliliter options/millilitre))
           (precision/->2dp (sut/convert 100.0 :milliliter :milliliter))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/american-fluid-ounce options/american-fluid-ounce))
           (precision/->2dp (sut/convert 100.0 :american-fluid-ounce :american-fluid-ounce))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/american-gallon options/american-gallon))
           (precision/->2dp (sut/convert 100.0 :american-gallon :american-gallon))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/american-pint options/american-pint))
           (precision/->2dp (sut/convert 100.0 :american-pint :american-pint))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/american-quart options/american-quart))
           (precision/->2dp (sut/convert 100.0 :american-quart :american-quart))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/cup options/cup))
           (precision/->2dp (sut/convert 100.0 :cup :cup))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/imperial-fluid-ounce options/imperial-fluid-ounce))
           (precision/->2dp (sut/convert 100.0 :imperial-fluid-ounce :imperial-fluid-ounce))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/imperial-gallon options/imperial-gallon))
           (precision/->2dp (sut/convert 100.0 :imperial-gallon :imperial-gallon))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/imperial-pint options/imperial-pint))
           (precision/->2dp (sut/convert 100.0 :imperial-pint :imperial-pint))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/imperial-quart options/imperial-quart))
           (precision/->2dp (sut/convert 100.0 :imperial-quart :imperial-quart))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/tablespoon options/tablespoon))
           (precision/->2dp (sut/convert 100.0 :tablespoon :tablespoon))))
    (is (= 100.0
           (precision/->2dp (sut/convert 100.0 options/teaspoon options/teaspoon))
           (precision/->2dp (sut/convert 100.0 :teaspoon :teaspoon))))
    (is (= 3381.41 (precision/->2dp (sut/convert 100.0 options/liter options/american-fluid-ounce))))
    (is (= 26.42 (precision/->2dp (sut/convert 100.0 options/liter options/american-gallon))))
    (is (= 211.34 (precision/->2dp (sut/convert 100.0 options/liter options/american-pint))))
    (is (= 105.67 (precision/->2dp (sut/convert 100.0 options/liter options/american-quart))))
    (is (= 422.68 (precision/->2dp (sut/convert 100.0 options/liter options/cup))))
    (is (= 3519.5 (precision/->2dp (sut/convert 100.0 options/liter options/imperial-fluid-ounce))))
    (is (= 22.0 (precision/->2dp (sut/convert 100.0 options/liter options/imperial-gallon))))
    (is (= 175.98 (precision/->2dp (sut/convert 100.0 options/liter options/imperial-pint))))
    (is (= 87.99 (precision/->2dp (sut/convert 100.0 options/liter options/imperial-quart))))
    (is (= 6762.79 (precision/->2dp (sut/convert 100.0 options/liter options/tablespoon))))
    (is (= 20288.42 (precision/->2dp (sut/convert 100.0 options/liter options/teaspoon))))
    (is (= 2.96 (precision/->2dp (sut/convert 100.0 options/american-fluid-ounce options/liter))))
    (is (= 378.54 (precision/->2dp (sut/convert 100.0 options/american-gallon options/liter))))
    (is (= 47.32 (precision/->2dp (sut/convert 100.0 options/american-pint options/liter))))
    (is (= 94.64 (precision/->2dp (sut/convert 100.0 options/american-quart options/liter))))
    (is (= 23.66 (precision/->2dp (sut/convert 100.0 options/cup options/liter))))
    (is (= 2.84 (precision/->2dp (sut/convert 100.0 options/imperial-fluid-ounce options/liter))))
    (is (= 454.61 (precision/->2dp (sut/convert 100.0 options/imperial-gallon options/liter))))
    (is (= 56.83 (precision/->2dp (sut/convert 100.0 options/imperial-pint options/liter))))
    (is (= 113.65 (precision/->2dp (sut/convert 100.0 options/imperial-quart options/liter))))
    (is (= 1.48 (precision/->2dp (sut/convert 100.0 options/tablespoon options/liter))))
    (is (= 0.49 (precision/->2dp (sut/convert 100.0 options/teaspoon options/liter)))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Ensure various color unit conversions behave as expected"
    (is (= "1.5 l"
           (sut/display 1.5 :liter)
           (sut/display 1.5 options/litre)))
    (is (= "1.5 liter"
           (sut/display 1.5 :liter {options/suffix options/full})
           (sut/display 1.5 options/liter {options/suffix options/full})))
    (is (= "1.5 fl oz"
           (sut/display 1.5 :american-fluid-ounce)
           (sut/display 1.5 options/american-fluid-ounce)))
    (is (= "1.5 fluid ounce"
           (sut/display 1.5 :american-fluid-ounce {options/suffix options/full})
           (sut/display 1.5 options/american-fluid-ounce {options/suffix options/full})))
    (is (= "1.5 gal"
           (sut/display 1.5 :american-gallon)
           (sut/display 1.5 options/american-gallon)))
    (is (= "1.5 US gallon"
           (sut/display 1.5 :american-gallon {options/suffix options/full})
           (sut/display 1.5 options/american-gallon {options/suffix options/full})))
    (is (= "1.5 pt"
           (sut/display 1.5 :american-pint)
           (sut/display 1.5 options/american-pint)))
    (is (= "1.5 american pint"
           (sut/display 1.5 :american-pint {options/suffix options/full})
           (sut/display 1.5 options/american-pint {options/suffix options/full})))
    (is (= "1.5 qt"
           (sut/display 1.5 :american-quart)
           (sut/display 1.5 options/american-quart)))
    (is (= "1.5 american quart"
           (sut/display 1.5 :american-quart {options/suffix options/full})
           (sut/display 1.5 options/american-quart {options/suffix options/full})))
    (is (= "1.5 c"
           (sut/display 1.5 :cup)
           (sut/display 1.5 options/cup)))
    (is (= "1.5 cup"
           (sut/display 1.5 :cup {options/suffix options/full})
           (sut/display 1.5 options/cup {options/suffix options/full})))
    (is (= "1.5 imp fl oz"
           (sut/display 1.5 :imperial-fluid-ounce)
           (sut/display 1.5 options/imperial-fluid-ounce)))
    (is (= "1.5 imperial fluid ounce"
           (sut/display 1.5 :imperial-fluid-ounce {options/suffix options/full})
           (sut/display 1.5 options/imperial-fluid-ounce {options/suffix options/full})))
    (is (= "1.5 gal"
           (sut/display 1.5 :imperial-gallon)
           (sut/display 1.5 options/imperial-gallon)))
    (is (= "1.5 imperial gallon"
           (sut/display 1.5 :imperial-gallon {options/suffix options/full})
           (sut/display 1.5 options/imperial-gallon {options/suffix options/full})))
    (is (= "1.5 pt"
           (sut/display 1.5 :imperial-pint)
           (sut/display 1.5 options/imperial-pint)))
    (is (= "1.5 imperial pint"
           (sut/display 1.5 :imperial-pint {options/suffix options/full})
           (sut/display 1.5 options/imperial-pint {options/suffix options/full})))
    (is (= "1.5 qt"
           (sut/display 1.5 :imperial-quart)
           (sut/display 1.5 options/imperial-quart)))
    (is (= "1.5 imperial quart"
           (sut/display 1.5 :imperial-quart {options/suffix options/full})
           (sut/display 1.5 options/imperial-quart {options/suffix options/full})))
    (is (= "1.5 tbsp"
           (sut/display 1.5 :tablespoon)
           (sut/display 1.5 options/tablespoon)))
    (is (= "1.5 tablespoon"
           (sut/display 1.5 :tablespoon {options/suffix options/full})
           (sut/display 1.5 options/tablespoon {options/suffix options/full})))
    (is (= "1.5 tsp"
           (sut/display 1.5 :teaspoon)
           (sut/display 1.5 options/teaspoon)))
    (is (= "1.5 teaspoon"
           (sut/display 1.5 :teaspoon {options/suffix options/full})
           (sut/display 1.5 options/teaspoon {options/suffix options/full}))))
  (testing "Invalid options throw an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display 10.0 :invalid))))))
