(ns brewtility.units-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.precision :as precision]
            [brewtility.units :as sut]
            [brewtility.units.color :as color]
            [brewtility.units.options :as options]))


;; These test cases existed prior to version 2.0.0 of brewtility.units
;;   The tests directly against `convert` are more robust
;;   These tests are left in place to ensure the new functions behave as expected
(deftest convert-volume-test
  (testing "Volume conversions should behave as expected."
    (is (= 1.0 (sut/convert :volume 1.0 options/liter options/litre)))
    (is (= 1.0 (sut/convert options/volume 1.0 options/litre options/litre)))
    (is (= 0.099 (precision/->3dp (sut/convert :volume 20 options/teaspoon options/liter))))
    (is (= 35.1 (precision/->3dp (sut/convert :volume 35.1 options/tablespoon options/tablespoon))))
    (is (= 12.0 (precision/->3dp (sut/convert :volume 9.99209 options/imperial-pint options/american-pint))))))


(deftest convert-weight-test
  (testing "Weight conversions should behave as expected."
    (is (= 1.0 (sut/convert :weight 1.0 options/kilogram options/kilogram)))
    (is (= 1.0 (sut/convert options/weight 1.0 options/pound options/pound)))
    (is (= 240.0 (precision/->3dp (sut/convert :weight 15.0 options/pound options/ounce))))
    (is (= 0.003 (precision/->3dp (sut/convert :weight 1205.5 options/milligram options/pound))))))


(deftest convert-temperature-test
  (testing "Temperature conversions should behave as expected."
    (is (= 32.0 (sut/convert :temperature 32.0 options/fahrenheit options/fahrenheit)))
    (is (= 32.0 (sut/convert options/temperature 0.0 options/celsius options/fahrenheit)))
    (is (= 340.706 (precision/->3dp (sut/convert :temperature 153.6008 options/fahrenheit options/kelvin))))
    (is (= 1743.15 (precision/->3dp (sut/convert :temperature 2016.3 options/kelvin options/c))))
    (is (= -40.0 (precision/->3dp (sut/convert :temperature -40.0 options/f options/c))))))


(deftest convert-color-test
  (testing "Color conversions behaves as expected."
    (is (= color/srm-1 (sut/convert :color 1 options/srm options/rgba)))
    (is (= color/srm-1 (sut/convert :color 1 :srm :rgba)))
    (is (= color/srm-13 (sut/convert options/color 10.16 options/lovibond options/rgba)))
    (is (= color/srm-13 (sut/convert :color 10.16 :lovibond :rgba)))
    (is (= color/srm-13 (sut/convert :color 25.61 options/ebc options/rgba)))
    (is (= 13.00988 (sut/convert :color 25.61 options/ebc options/srm)))
    (is (= 25.61 (sut/convert :color 25.61 options/srm options/srm)))
    (is (= 50.451699999999995 (sut/convert :color 25.61 options/srm options/ebc)))
    (is (= 10.16527388158866 (sut/convert :color 25.61 options/ebc options/lovibond)))
    (is (= 66.84467282 (sut/convert :color 25.61 options/lovibond options/ebc)))
    (is (= color/srm-13 (sut/convert :color 25.61 :ebc :rgba)))))


(deftest convert-test
  (testing "Supported systems can convert all units."
    (testing "Color:"
      (is (= 10.0
             (precision/->2dp (sut/convert options/color 7.94 options/lovibond options/srm))
             (precision/->2dp (sut/convert options/color 19.69 options/ebc options/srm))))
      (is (= 10.0
             (precision/->2dp (sut/convert options/color 7.94 :lovibond :srm))
             (precision/->2dp (sut/convert options/color 19.69 :ebc :srm))))
      (is (= 23.2
             (precision/->2dp (sut/convert options/color 30.66 options/srm options/lovibond))
             (precision/->2dp (sut/convert options/color 60.38 options/ebc options/lovibond))))
      (is (= -45.53
             (precision/->2dp (sut/convert options/color -23.11 options/srm options/ebc))
             (precision/->2dp (sut/convert options/color -16.5 options/lovibond options/ebc)))))
    (testing "Pressure:"
      (is (= 100.0
             (precision/->2dp (sut/convert options/pressure 100.0 options/pascal options/pascal))
             (precision/->2dp (sut/convert options/pressure 100.0 :pascal :pascal))))
      (is (= 100000.0 (precision/->2dp (sut/convert options/pressure 100.0 options/kilopascal options/pascal))))
      (is (= 1.0 (precision/->2dp (sut/convert options/pressure 100.0 options/kilopascal options/bar))))
      (is (= 0.99 (precision/->2dp (sut/convert options/pressure 100.0 options/kilopascal options/atmosphere))))
      (is (= 750.06 (precision/->2dp (sut/convert options/pressure 100.0 options/kilopascal options/torr))))
      (is (= 14.5 (precision/->2dp (sut/convert options/pressure 100.0 options/kilopascal options/psi))))
      (is (= 0.1 (precision/->2dp (sut/convert options/pressure 100.0 options/pascal options/kilopascal))))
      (is (= 10000.0 (precision/->2dp (sut/convert options/pressure 100.0 options/bar options/kilopascal))))
      (is (= 10132.5 (precision/->2dp (sut/convert options/pressure 100.0 options/atmosphere options/kilopascal))))
      (is (= 13.33 (precision/->2dp (sut/convert options/pressure 100.0 options/torr options/kilopascal))))
      (is (= 689.48 (precision/->2dp (sut/convert options/pressure 100.0 options/psi options/kilopascal)))))
    (testing "Specific Gravity:"
      (is (= 100.0
             (precision/->2dp (sut/convert options/specific-gravity 100.0 options/specific-gravity options/specific-gravity))
             (precision/->2dp (sut/convert options/specific-gravity 100.0 :specific-gravity :specific-gravity)))))
    (testing "Temperature:"
      (is (= 100.0
             (precision/->2dp (sut/convert options/temperature 100.0 options/c options/c))
             (precision/->2dp (sut/convert options/temperature 100.0 :celsius :c))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/temperature 100.0 options/f options/fahrenheit))
             (precision/->2dp (sut/convert options/temperature 100.0 :f :f))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/temperature 100.0 options/k options/kelvin))
             (precision/->2dp (sut/convert options/temperature 100.0 :k :k))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/temperature 100.0 options/centigrade options/centigrade))
             (precision/->2dp (sut/convert options/temperature 100.0 :centigrade :centigrade))))
      (is (= 212.0 (precision/->2dp (sut/convert options/temperature 100 options/celsius options/fahrenheit))))
      (is (= 363.15 (precision/->2dp (sut/convert options/temperature 90 options/celsius options/kelvin))))
      (is (= 100.0 (precision/->2dp (sut/convert options/temperature 100.0 options/celsius options/centigrade))))
      (is (= 32.22 (precision/->2dp (sut/convert options/temperature 90 options/fahrenheit options/celsius))))
      (is (= 305.37 (precision/->2dp (sut/convert options/temperature 90 options/fahrenheit options/kelvin))))
      (is (= 32.22 (precision/->2dp (sut/convert options/temperature 90 options/fahrenheit options/centigrade))))
      (is (= -173.15 (precision/->2dp (sut/convert options/temperature 100 options/kelvin options/celsius))))
      (is (= -297.67 (precision/->2dp (sut/convert options/temperature 90 options/kelvin options/fahrenheit))))
      (is (= -183.15 (precision/->2dp (sut/convert options/temperature 90 options/kelvin options/centigrade))))
      (is (= 100.0 (precision/->2dp (sut/convert options/temperature 100 options/centigrade options/celsius))))
      (is (= 212.0 (precision/->2dp (sut/convert options/temperature 100 options/centigrade options/fahrenheit))))
      (is (= 373.15 (precision/->2dp (sut/convert options/temperature 100 options/centigrade options/kelvin)))))
    (testing "Time:"
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/minute options/minute))
             (precision/->2dp (sut/convert options/time 100.0 :minute :minute))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/nanosecond options/nanosecond))
             (precision/->2dp (sut/convert options/time 100.0 :nanosecond :nanosecond))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/second options/second))
             (precision/->2dp (sut/convert options/time 100.0 :second :second))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/millisecond options/millisecond))
             (precision/->2dp (sut/convert options/time 100.0 :millisecond :millisecond))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/microsecond options/microsecond))
             (precision/->2dp (sut/convert options/time 100.0 :microsecond :microsecond))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/day options/day))
             (precision/->2dp (sut/convert options/time 100.0 :day :day))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/hour options/hour))
             (precision/->2dp (sut/convert options/time 100.0 :hour :hour))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/time 100.0 options/week options/week))
             (precision/->2dp (sut/convert options/time 100.0 :week :week))))
      (is (= 6000.0 (precision/->2dp (sut/convert options/time 100.0 options/minute options/second))))
      (is (= 6000000.0 (precision/->2dp (sut/convert options/time 100.0 options/minute options/millisecond))))
      (is (= 60000.0 (precision/->2dp (sut/convert options/time 0.001 options/minute options/microsecond))))
      (is (= 600000.0 (precision/->2dp (sut/convert options/time 0.00001 options/minute options/nanosecond))))
      (is (= 0.07 (precision/->2dp (sut/convert options/time 100.0 options/minute options/day))))
      (is (= 1.67 (precision/->2dp (sut/convert options/time 100.0 options/minute options/hour))))
      (is (= 0.01 (precision/->2dp (sut/convert options/time 100.0 options/minute options/week))))
      (is (= 1.67 (precision/->2dp (sut/convert options/time 100.0 options/second options/minute))))
      (is (= 0.0 (precision/->2dp (sut/convert options/time 100.0 options/millisecond options/minute))))
      (is (= 0.0 (precision/->2dp (sut/convert options/time 100.0 options/microsecond options/minute))))
      (is (= 0.0 (precision/->2dp (sut/convert options/time 100.0 options/nanosecond options/minute))))
      (is (= 144000.0 (precision/->2dp (sut/convert options/time 100.0 options/day options/minute))))
      (is (= 6000.0 (precision/->2dp (sut/convert options/time 100.0 options/hour options/minute))))
      (is (= 1008000.0 (precision/->2dp (sut/convert options/time 100.0 options/week options/minute)))))
    (testing "Volume:"
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/liter options/litre))
             (precision/->2dp (sut/convert options/volume 100.0 :litre :liter))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/milliliter options/millilitre))
             (precision/->2dp (sut/convert options/volume 100.0 :milliliter :milliliter))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/american-fluid-ounce options/american-fluid-ounce))
             (precision/->2dp (sut/convert options/volume 100.0 :american-fluid-ounce :american-fluid-ounce))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/american-gallon options/american-gallon))
             (precision/->2dp (sut/convert options/volume 100.0 :american-gallon :american-gallon))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/american-pint options/american-pint))
             (precision/->2dp (sut/convert options/volume 100.0 :american-pint :american-pint))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/american-quart options/american-quart))
             (precision/->2dp (sut/convert options/volume 100.0 :american-quart :american-quart))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/cup options/cup))
             (precision/->2dp (sut/convert options/volume 100.0 :cup :cup))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/imperial-fluid-ounce options/imperial-fluid-ounce))
             (precision/->2dp (sut/convert options/volume 100.0 :imperial-fluid-ounce :imperial-fluid-ounce))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/imperial-gallon options/imperial-gallon))
             (precision/->2dp (sut/convert options/volume 100.0 :imperial-gallon :imperial-gallon))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/imperial-pint options/imperial-pint))
             (precision/->2dp (sut/convert options/volume 100.0 :imperial-pint :imperial-pint))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/imperial-quart options/imperial-quart))
             (precision/->2dp (sut/convert options/volume 100.0 :imperial-quart :imperial-quart))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/tablespoon options/tablespoon))
             (precision/->2dp (sut/convert options/volume 100.0 :tablespoon :tablespoon))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/volume 100.0 options/teaspoon options/teaspoon))
             (precision/->2dp (sut/convert options/volume 100.0 :teaspoon :teaspoon))))
      (is (= 3381.41 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/american-fluid-ounce))))
      (is (= 26.42 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/american-gallon))))
      (is (= 211.34 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/american-pint))))
      (is (= 105.67 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/american-quart))))
      (is (= 422.68 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/cup))))
      (is (= 3519.5 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/imperial-fluid-ounce))))
      (is (= 22.0 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/imperial-gallon))))
      (is (= 175.98 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/imperial-pint))))
      (is (= 87.99 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/imperial-quart))))
      (is (= 6762.79 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/tablespoon))))
      (is (= 20288.42 (precision/->2dp (sut/convert options/volume 100.0 options/liter options/teaspoon))))
      (is (= 2.96 (precision/->2dp (sut/convert options/volume 100.0 options/american-fluid-ounce options/liter))))
      (is (= 378.54 (precision/->2dp (sut/convert options/volume 100.0 options/american-gallon options/liter))))
      (is (= 47.32 (precision/->2dp (sut/convert options/volume 100.0 options/american-pint options/liter))))
      (is (= 94.64 (precision/->2dp (sut/convert options/volume 100.0 options/american-quart options/liter))))
      (is (= 23.66 (precision/->2dp (sut/convert options/volume 100.0 options/cup options/liter))))
      (is (= 2.84 (precision/->2dp (sut/convert options/volume 100.0 options/imperial-fluid-ounce options/liter))))
      (is (= 454.61 (precision/->2dp (sut/convert options/volume 100.0 options/imperial-gallon options/liter))))
      (is (= 56.83 (precision/->2dp (sut/convert options/volume 100.0 options/imperial-pint options/liter))))
      (is (= 113.65 (precision/->2dp (sut/convert options/volume 100.0 options/imperial-quart options/liter))))
      (is (= 1.48 (precision/->2dp (sut/convert options/volume 100.0 options/tablespoon options/liter))))
      (is (= 0.49 (precision/->2dp (sut/convert options/volume 100.0 options/teaspoon options/liter)))))
    (testing "Weight:"
      (is (= 100.0
             (precision/->2dp (sut/convert options/weight 100.0 options/gram options/gram))
             (precision/->2dp (sut/convert options/weight 100.0 :gram :gram))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/weight 100.0 options/kilogram options/kilogram))
             (precision/->2dp (sut/convert options/weight 100.0 :kilogram :kilogram))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/weight 100.0 options/milligram options/milligram))
             (precision/->2dp (sut/convert options/weight 100.0 :milligram :milligram))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/weight 100.0 options/ounce options/ounce))
             (precision/->2dp (sut/convert options/weight 100.0 :ounce :ounce))))
      (is (= 100.0
             (precision/->2dp (sut/convert options/weight 100.0 options/pound options/pound))
             (precision/->2dp (sut/convert options/weight 100.0 :pound :pound))))
      (is (= 0.1 (precision/->2dp (sut/convert options/weight 100.0 options/gram options/kilogram))))
      (is (= 100000.0 (precision/->2dp (sut/convert options/weight 100.0 options/gram options/milligram))))
      (is (= 3.53 (precision/->2dp (sut/convert options/weight 100.0 options/gram options/ounce))))
      (is (= 0.22 (precision/->2dp (sut/convert options/weight 100.0 options/gram options/pound))))
      (is (= 100000.0 (precision/->2dp (sut/convert options/weight 100.0 options/kilogram options/gram))))
      (is (= 0.1 (precision/->2dp (sut/convert options/weight 100.0 options/milligram options/gram))))
      (is (= 2834.95 (precision/->2dp (sut/convert options/weight 100.0 options/ounce options/gram))))
      (is (= 45359.24 (precision/->2dp (sut/convert options/weight 100.0 options/pound options/gram))))))
  (testing "Invalid options generate an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/convert :ope 10.0 :invalid :broken))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/convert :mistake 10.0 :invalid :broken))))))


(deftest display-test
  (testing "Supported systems can cast display values"
    (testing "Color:"
      (is (= "10.1 srm"
             (sut/display options/color 10.1 options/srm)
             (sut/display options/color 10.1 :srm)))
      (is (= "23.2 ebc"
             (sut/display options/color 23.2 options/ebc)
             (sut/display options/color 23.2 :ebc)))
      (is (= "10.1 degrees lovibond"
             (sut/display options/color 10.1 options/lovibond {options/suffix options/full})
             (sut/display options/color 10.1 :lovibond {options/suffix options/full})))
      (is (= color/srm-37 (sut/display options/color color/srm-37 options/rgba))))
    (testing "Pressure:"
      (is (= "1.5 pa"
             (sut/display options/pressure 1.5 :pascal)
             (sut/display options/pressure 1.5 options/pascal)))
      (is (= "1.5 pascal"
             (sut/display options/pressure 1.5 :pascal {options/suffix options/full}))))
    (testing "Specific Gravity:"
      (is (= "1.5 sg"
             (sut/display options/specific-gravity 1.5 :specific-gravity)
             (sut/display options/specific-gravity 1.5 options/specific-gravity))))
    (testing "Temperature:"
      (is (= "1.5 c"
             (sut/display options/temperature 1.5 :celsius)
             (sut/display options/temperature 1.5 options/celsius)
             (sut/display options/temperature 1.5 options/c)))
      (is (= "1.5 k"
             (sut/display options/temperature 1.5 :kelvin)
             (sut/display options/temperature 1.5 options/kelvin)
             (sut/display options/temperature 1.5 options/k)))
      (is (= "1.5 f"
             (sut/display options/temperature 1.5 :fahrenheit)
             (sut/display options/temperature 1.5 options/fahrenheit)
             (sut/display options/temperature 1.5 options/f)))
      (is (= "1.5 mg"
             (sut/display options/temperature 1.5 options/centigrade)))
      (is (= "1.5 celsius"
             (sut/display options/temperature 1.5 :celsius {options/suffix options/full})
             (sut/display options/temperature 1.5 options/celsius {options/suffix options/full})
             (sut/display options/temperature 1.5 options/c {options/suffix options/full})))
      (is (= "1.5 kelvin"
             (sut/display options/temperature 1.5 :kelvin {options/suffix options/full})
             (sut/display options/temperature 1.5 options/kelvin {options/suffix options/full})
             (sut/display options/temperature 1.5 options/k {options/suffix options/full})))
      (is (= "1.5 fahrenheit"
             (sut/display options/temperature 1.5 :fahrenheit {options/suffix options/full})
             (sut/display options/temperature 1.5 options/fahrenheit {options/suffix options/full})
             (sut/display options/temperature 1.5 options/f {options/suffix options/full})))
      (is (= "1.5 centigrade"
             (sut/display options/temperature 1.5 options/centigrade {options/suffix options/full}))))
    (testing "Time:"
      (is (= "1.5 m"
             (sut/display options/time 1.5 :minute)
             (sut/display options/time 1.5 options/minute)))
      (is (= "1.5 d"
             (sut/display options/time 1.5 :day)
             (sut/display options/time 1.5 options/day)))
      (is (= "1.5 hr"
             (sut/display options/time 1.5 :hour)
             (sut/display options/time 1.5 options/hour)))
      (is (= "1.5 w"
             (sut/display options/time 1.5 :week)
             (sut/display options/time 1.5 options/week)))
      (is (= "1.5 ns"
             (sut/display options/time 1.5 :nanosecond)
             (sut/display options/time 1.5 options/nanosecond)))
      (is (= "1.5 ms"
             (sut/display options/time 1.5 :millisecond)
             (sut/display options/time 1.5 options/millisecond)))
      (is (= "1.5 µs"
             (sut/display options/time 1.5 :microsecond)
             (sut/display options/time 1.5 options/microsecond)))
      (is (= "1.5 s"
             (sut/display options/time 1.5 :second)
             (sut/display options/time 1.5 options/second)))
      (is (= "1.5 minute"
             (sut/display options/time 1.5 :minute {options/suffix options/full})
             (sut/display options/time 1.5 options/minute {options/suffix options/full}))))
    (testing "Volume:"
      (is (= "1.5 l"
             (sut/display options/volume 1.5 :liter)
             (sut/display options/volume 1.5 options/litre)))
      (is (= "1.5 liter"
             (sut/display options/volume 1.5 :liter {options/suffix options/full})
             (sut/display options/volume 1.5 options/liter {options/suffix options/full})))
      (is (= "1.5 fl oz"
             (sut/display options/volume 1.5 :american-fluid-ounce)
             (sut/display options/volume 1.5 options/american-fluid-ounce)))
      (is (= "1.5 fluid ounce"
             (sut/display options/volume 1.5 :american-fluid-ounce {options/suffix options/full})
             (sut/display options/volume 1.5 options/american-fluid-ounce {options/suffix options/full})))
      (is (= "1.5 gal"
             (sut/display options/volume 1.5 :american-gallon)
             (sut/display options/volume 1.5 options/american-gallon)))
      (is (= "1.5 US gallon"
             (sut/display options/volume 1.5 :american-gallon {options/suffix options/full})
             (sut/display options/volume 1.5 options/american-gallon {options/suffix options/full})))
      (is (= "1.5 pt"
             (sut/display options/volume 1.5 :american-pint)
             (sut/display options/volume 1.5 options/american-pint)))
      (is (= "1.5 american pint"
             (sut/display options/volume 1.5 :american-pint {options/suffix options/full})
             (sut/display options/volume 1.5 options/american-pint {options/suffix options/full})))
      (is (= "1.5 qt"
             (sut/display options/volume 1.5 :american-quart)
             (sut/display options/volume 1.5 options/american-quart)))
      (is (= "1.5 american quart"
             (sut/display options/volume 1.5 :american-quart {options/suffix options/full})
             (sut/display options/volume 1.5 options/american-quart {options/suffix options/full})))
      (is (= "1.5 c"
             (sut/display options/volume 1.5 :cup)
             (sut/display options/volume 1.5 options/cup)))
      (is (= "1.5 cup"
             (sut/display options/volume 1.5 :cup {options/suffix options/full})
             (sut/display options/volume 1.5 options/cup {options/suffix options/full})))
      (is (= "1.5 imp fl oz"
             (sut/display options/volume 1.5 :imperial-fluid-ounce)
             (sut/display options/volume 1.5 options/imperial-fluid-ounce)))
      (is (= "1.5 imperial fluid ounce"
             (sut/display options/volume 1.5 :imperial-fluid-ounce {options/suffix options/full})
             (sut/display options/volume 1.5 options/imperial-fluid-ounce {options/suffix options/full})))
      (is (= "1.5 gal"
             (sut/display options/volume 1.5 :imperial-gallon)
             (sut/display options/volume 1.5 options/imperial-gallon)))
      (is (= "1.5 imperial gallon"
             (sut/display options/volume 1.5 :imperial-gallon {options/suffix options/full})
             (sut/display options/volume 1.5 options/imperial-gallon {options/suffix options/full})))
      (is (= "1.5 pt"
             (sut/display options/volume 1.5 :imperial-pint)
             (sut/display options/volume 1.5 options/imperial-pint)))
      (is (= "1.5 imperial pint"
             (sut/display options/volume 1.5 :imperial-pint {options/suffix options/full})
             (sut/display options/volume 1.5 options/imperial-pint {options/suffix options/full})))
      (is (= "1.5 qt"
             (sut/display options/volume 1.5 :imperial-quart)
             (sut/display options/volume 1.5 options/imperial-quart)))
      (is (= "1.5 imperial quart"
             (sut/display options/volume 1.5 :imperial-quart {options/suffix options/full})
             (sut/display options/volume 1.5 options/imperial-quart {options/suffix options/full})))
      (is (= "1.5 tbsp"
             (sut/display options/volume 1.5 :tablespoon)
             (sut/display options/volume 1.5 options/tablespoon)))
      (is (= "1.5 tablespoon"
             (sut/display options/volume 1.5 :tablespoon {options/suffix options/full})
             (sut/display options/volume 1.5 options/tablespoon {options/suffix options/full})))
      (is (= "1.5 tsp"
             (sut/display options/volume 1.5 :teaspoon)
             (sut/display options/volume 1.5 options/teaspoon)))
      (is (= "1.5 teaspoon"
             (sut/display options/volume 1.5 :teaspoon {options/suffix options/full})
             (sut/display options/volume 1.5 options/teaspoon {options/suffix options/full}))))
    (testing "Weight:"
      (is (= "1.5 g"
             (sut/display options/weight 1.5 :gram)
             (sut/display options/weight 1.5 options/gram)))
      (is (= "1.5 kg"
             (sut/display options/weight 1.5 :kilogram)
             (sut/display options/weight 1.5 options/kilogram)))
      (is (= "1.5 mg"
             (sut/display options/weight 1.5 :milligram)
             (sut/display options/weight 1.5 options/milligram)))
      (is (= "1.5 oz"
             (sut/display options/weight 1.5 :ounce)
             (sut/display options/weight 1.5 options/ounce)))
      (is (= "1.5 lb"
             (sut/display options/weight 1.5 :pound)
             (sut/display options/weight 1.5 options/pound)))
      (is (= "1.5 gram"
             (sut/display options/weight 1.5 :gram {options/suffix options/full})
             (sut/display options/weight 1.5 options/gram {options/suffix options/full})))
      (is (= "1.5 kilogram"
             (sut/display options/weight 1.5 :kilogram {options/suffix options/full})
             (sut/display options/weight 1.5 options/kilogram {options/suffix options/full})))
      (is (= "1.5 milligram"
             (sut/display options/weight 1.5 :milligram {options/suffix options/full})
             (sut/display options/weight 1.5 options/milligram {options/suffix options/full})))
      (is (= "1.5 ounce"
             (sut/display options/weight 1.5 :ounce {options/suffix options/full})
             (sut/display options/weight 1.5 options/ounce {options/suffix options/full})))
      (is (= "1.5 pound"
             (sut/display options/weight 1.5 :pound {options/suffix options/full})
             (sut/display options/weight 1.5 options/pound {options/suffix options/full})))))
  (testing "Invalid options generate an exception"
    #?(:clj (is (thrown-with-msg? Exception #"Unsupported" (sut/display :ope 10.0 :invalid))))
    #?(:cljs (is (thrown-with-msg? js/Error #"Unsupported" (sut/display :bad 10.0 :invalid))))))
