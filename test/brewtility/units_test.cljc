(ns brewtility.units-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.precision :as bp]
            [brewtility.units :as sut]
            [brewtility.units.color :as color]
            [brewtility.units.options :as options]))


(deftest convert-volume-test
  (testing "Volume conversions should behave as expected"
    (is (= 1.0 (sut/convert :volume 1.0 options/liter options/litre)))
    (is (= 1.0 (sut/convert :volume 1.0 options/litre options/litre)))
    (is (= 0.099 (bp/->3dp (sut/convert :volume 20 options/teaspoon options/liter))))
    (is (= 35.1 (bp/->3dp (sut/convert :volume 35.1 options/tablespoon options/tablespoon))))
    (is (= 12.0 (bp/->3dp (sut/convert :volume 9.99209 options/imperial-pint options/american-pint))))))


(deftest convert-weight-test
  (testing "Weight conversions should behave as expected"
    (is (= 1.0 (sut/convert :weight 1.0 options/kilogram options/kilogram)))
    (is (= 1.0 (sut/convert :weight 1.0 options/pound options/pound)))
    (is (= 240.0 (bp/->3dp (sut/convert :weight 15.0 options/pound options/ounce))))
    (is (= 0.003 (bp/->3dp (sut/convert :weight 1205.5 options/milligram options/pound))))))


(deftest convert-temperature-test
  (testing "Temperature conversions should behave as expected"
    (is (= 32.0 (sut/convert :temperature 32.0 options/fahrenheit options/fahrenheit)))
    (is (= 32.0 (sut/convert :temperature 0.0 options/celsius options/fahrenheit)))
    (is (= 340.706 (bp/->3dp (sut/convert :temperature 153.6008 options/fahrenheit options/kelvin))))
    (is (= 1743.15 (bp/->3dp (sut/convert :temperature 2016.3 options/kelvin options/c))))
    (is (= -40.0 (bp/->3dp (sut/convert :temperature -40.0 options/f options/c))))))


(deftest convert-color-test
  (testing "Color conversions behaves as expected"
    (is (= color/srm-1 (sut/convert :color 1 options/srm options/rgba)))
    (is (= color/srm-1 (sut/convert :color 1 :srm :rgba)))
    (is (= color/srm-13 (sut/convert :color 10.16 options/lovibond options/rgba)))
    (is (= color/srm-13 (sut/convert :color 10.16 :lovibond :rgba)))
    (is (= color/srm-13 (sut/convert :color 25.61 options/ebc options/rgba)))
    (is (= 13.00988 (sut/convert :color 25.61 options/ebc options/srm)))
    (is (= 25.61 (sut/convert :color 25.61 options/srm options/srm)))
    (is (= 50.451699999999995 (sut/convert :color 25.61 options/srm options/ebc)))
    (is (= 10.16527388158866 (sut/convert :color 25.61 options/ebc options/lovibond)))
    (is (= 66.84467282 (sut/convert :color 25.61 options/lovibond options/ebc)))
    (is (= color/srm-13 (sut/convert :color 25.61 :ebc :rgba)))))
