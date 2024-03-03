(ns brewtility.enrich.impl-test
  (:require #? (:clj [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test :refer-macros [deftest is testing]])
            [brewtility.enrich.impl :as sut]
            [brewtility.units.color :as color]
            [brewtility.units.options :as options]
            [brewtility.units.pressure :as pressure]
            [brewtility.units.specific-gravity :as specific-gravity]
            [brewtility.units.temperature :as temperature]
            [brewtility.units.time :as time]
            [brewtility.units.volume :as volume]
            [brewtility.units.weight :as weight]
            [clojure.set :as set]
            [clojure.string :as str]))


(deftest code-type-tests
  (testing "Ensure maps used for options are structurally correct"
    (testing "Defaults by System - These must be maps from Systems of Measure to their corresponding measurements."
      (is (= (sort (keys sut/default-color-by-system))
             (sort (keys sut/default-pressure-by-system))
             (sort (keys sut/default-specific-gravity-by-system))
             (sort (keys sut/default-temperature-by-system))
             (sort (keys sut/default-time-by-system))
             (sort (keys sut/default-volume-by-system))
             (sort (keys sut/default-weight-by-system))
             (sort (vec options/systems-of-measure))))
      (is (set/subset? (set (vals sut/default-color-by-system)) color/measurements))
      (is (set/subset? (set (vals sut/default-pressure-by-system)) pressure/measurements))
      (is (set/subset? (set (vals sut/default-specific-gravity-by-system)) specific-gravity/measurements))
      (is (set/subset? (set (vals sut/default-temperature-by-system)) temperature/measurements))
      (is (set/subset? (set (vals sut/default-time-by-system)) time/measurements))
      (is (set/subset? (set (vals sut/default-volume-by-system)) volume/measurements))
      (is (set/subset? (set (vals sut/default-weight-by-system)) weight/measurements))))
  (testing "BeerXML Standard Units"
    (is (= (sort (keys sut/beer-xml-standard-units))
           (sort (vec options/measurement-types))))
    (is (contains? color/measurements (get sut/beer-xml-standard-units options/color)))
    (is (contains? pressure/measurements (get sut/beer-xml-standard-units options/pressure)))
    (is (contains? specific-gravity/measurements (get sut/beer-xml-standard-units options/specific-gravity)))
    (is (contains? temperature/measurements (get sut/beer-xml-standard-units options/temperature)))
    (is (contains? time/measurements (get sut/beer-xml-standard-units options/time)))
    (is (contains? volume/measurements (get sut/beer-xml-standard-units options/volume)))
    (is (contains? weight/measurements (get sut/beer-xml-standard-units options/weight))))
  (testing "Option keys"
    (is (keyword? sut/value-key))
    (is (keyword? sut/low-value-key))
    (is (keyword? sut/high-value-key))
    (is (keyword? sut/display-key))
    (is (keyword? sut/fine-grain-target-units))
    (is (keyword? sut/fine-grain-precision))
    (is (keyword? sut/fine-grain-suffix))))


(deftest ->displayable-color-test
  (testing "Ensure ->displayable-color supports its full suite of options."
    (is (= "2.955 ebc"
           (sut/->displayable-units options/color 1.5 options/srm options/ebc)
           (sut/->displayable-units options/color 1.5 options/srm options/ebc sut/default-display-options))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "3.0 ebc"
           (sut/->displayable-units options/color 1.5 options/srm options/ebc {options/precision 1})
           (sut/->displayable-units options/color 1.5 options/srm options/ebc {options/precision 1
                                                                               options/suffix    options/short}))
        "Conversion may override the default precision")
    (is (= "1.668 degrees lovibond"
           (sut/->displayable-units options/color 1.5 options/srm options/lovibond {options/suffix options/full})
           (sut/->displayable-units options/color 1.5 options/srm options/lovibond {options/precision 3
                                                                                    options/suffix    options/full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-pressure-test
  (testing "Ensure ->displayable-pressure supports its full suite of options"
    (is (= "0.103 bar"
           (sut/->displayable-units options/pressure 1.5 options/psi options/bar)
           (sut/->displayable-units options/pressure 1.5 options/psi options/bar sut/default-display-options))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "0.1 bar"
           (sut/->displayable-units options/pressure 1.5 options/psi options/bar {options/precision 1})
           (sut/->displayable-units options/pressure 1.5 options/psi options/bar {options/precision 1
                                                                                  options/suffix    options/short}))
        "Conversion may override the default precision")
    (is (= "10.342 kilopascals"
           (sut/->displayable-units options/pressure 1.5 options/psi options/kilopascal {options/suffix options/full})
           (sut/->displayable-units options/pressure 1.5 options/psi options/kilopascal {options/precision 3
                                                                                         options/suffix    options/full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-specific-gravity-test
  (testing "Ensure ->displayable-specific-gravity supports its full suite of options"
    (is (= "1.5 sg"
           (sut/->displayable-units options/specific-gravity 1.5 options/specific-gravity options/specific-gravity)
           (sut/->displayable-units options/specific-gravity 1.5 options/specific-gravity options/specific-gravity sut/default-display-options))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "1.5 sg"
           (sut/->displayable-units options/specific-gravity 1.5 options/specific-gravity options/specific-gravity {options/precision 1})
           (sut/->displayable-units options/specific-gravity 1.5 options/specific-gravity options/specific-gravity {options/precision 1
                                                                                                                    options/suffix    options/short}))
        "Conversion may override the default precision")
    (is (= "1.5 specific gravity"
           (sut/->displayable-units options/specific-gravity 1.5 options/specific-gravity options/specific-gravity {options/suffix options/full})
           (sut/->displayable-units options/specific-gravity 1.5 options/specific-gravity options/specific-gravity {options/precision 3
                                                                                                                    options/suffix    options/full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-temperature-test
  (testing "Ensure ->displayable-temperature supports its full suite of options"
    (is (= "318.75 k"
           (sut/->displayable-units options/temperature 45.6 options/c options/k)
           (sut/->displayable-units options/temperature 45.6 options/c options/k {options/precision 3
                                                                                  options/suffix    options/short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "318.8 k"
           (sut/->displayable-units options/temperature 45.6 options/c options/k {options/precision 1})
           (sut/->displayable-units options/temperature 45.6 options/c options/k {options/precision 1
                                                                                  options/suffix    options/short}))
        "Conversion may override the default precision")
    (is (= "318.75 kelvin"
           (sut/->displayable-units options/temperature 45.6 options/c options/k {options/precision 3
                                                                                  options/suffix    options/full})
           (sut/->displayable-units options/temperature 45.6 options/c options/k {options/suffix options/full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-time-test
  (testing "Ensure ->displayable-time supports its full suite of options"
    (is (= "0.76 m"
           (sut/->displayable-units options/time 45.6 options/second options/minute)
           (sut/->displayable-units options/time 45.6 options/second options/minute {options/precision 3
                                                                                     options/suffix    options/short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "0.8 m"
           (sut/->displayable-units options/time 45.6 options/second options/minute {options/precision 1})
           (sut/->displayable-units options/time 45.6 options/second options/minute {options/precision 1
                                                                                     options/suffix    options/short}))
        "Conversion may override the default precision")
    (is (= "0.76 minute"
           (sut/->displayable-units options/time 45.6 options/second options/minute {options/suffix options/full})
           (sut/->displayable-units options/time 45.6 options/second options/minute {options/precision 3
                                                                                     options/suffix    options/full}))
        "Conversion may override the default suffix")))


(deftest ->displayable-volume-test
  (testing "Ensure ->displayable-volume supports its full suite of options"
    (is (= "5.678 l"
           (sut/->displayable-units options/volume 1.5 :american-gallon :liter)
           (sut/->displayable-units options/volume 1.5 :american-gallon :liter {options/precision 3
                                                                                options/suffix    :short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "5.7 l"
           (sut/->displayable-units options/volume 1.5 :american-gallon :liter {options/precision 1
                                                                                options/suffix    :short}))
        "Conversion may override the default precision")
    (is (= "5.678 liter"
           (sut/->displayable-units options/volume 1.5 :american-gallon :liter {options/precision 3
                                                                                options/suffix    :full}))
        "Conversion may override the default suffix"))
  (testing "Ensure ->displayable-volume supports its full suite of options with static keys"
    (is (= "5.678 l"
           (sut/->displayable-units options/volume 1.5 options/american-gallon options/liter)
           (sut/->displayable-units options/volume 1.5 options/american-gallon options/liter {options/precision options/default-precision
                                                                                              options/suffix    options/short}))
        "Conversion defaults to 3 digits of precisions and shorthand unit names with static keys")
    (is (= "5.7 l"
           (sut/->displayable-units options/volume 1.5 options/american-gallon options/liter {options/precision 1
                                                                                              options/suffix    options/short}))
        "Conversion may override the default precision with static keys")
    (is (= "5.678 liter"
           (sut/->displayable-units options/volume 1.5 options/american-gallon options/liter {options/precision 3
                                                                                              options/suffix    options/full}))
        "Conversion may override the default suffix with static keys")))


(deftest ->displayable-weight-test
  (testing "Ensure ->displayable-weight supports its full suite of options"
    (is (= "0.053 oz"
           (sut/->displayable-units options/weight 1.5 options/gram options/ounce)
           (sut/->displayable-units options/weight 1.5 options/gram options/ounce sut/default-display-options))
        "Conversion defaults to 3 digits of precisions and shorthand unit names")
    (is (= "0.1 oz"
           (sut/->displayable-units options/weight 1.5 options/gram options/ounce {options/precision 1})
           (sut/->displayable-units options/weight 1.5 options/gram options/ounce {options/precision 1
                                                                                   options/suffix    options/short}))
        "Conversion may override the default precision")
    (is (= "0.053 ounce"
           (sut/->displayable-units options/weight 1.5 options/gram options/ounce {options/suffix options/full})
           (sut/->displayable-units options/weight 1.5 options/gram options/ounce {options/precision 3
                                                                                   options/suffix    options/full}))
        "Conversion may override the default suffix")))


(deftest target-unit-error-test
  (testing "Ensure target-unit-error sets an appropriate error for color"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/color :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "color")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "lovibond")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for pressure"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/pressure :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "pressure")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "psi")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for specific-gravity"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/specific-gravity :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "specific-gravity")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "specific-gravity")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for temperature"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/temperature :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "temperature")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "celsius")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for time"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/time :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "time")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "minute")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for volume"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "teaspoon")
          "The valid units are included in the error message")))
  (testing "Ensure target-unit-error sets an appropriate error for weight"
    (let [error-map     {:some "error"}
          new-error-map (sut/target-unit-error error-map options/weight :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "weight")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "pound")
          "The valid units are included in the error message"))))


(deftest source-unit-error-test
  (testing "Ensure source-unit-error sets an appropriate error for color"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/color :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "color")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "lovibond")
          "The valid units are included in the error message")))
  (testing "Ensure source-unit-error sets an appropriate error for pressure"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/pressure :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "pressure")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "psi")
          "The valid units are included in the error message")))
  (testing "Ensure source-unit-error sets an appropriate error for specific-gravity"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/specific-gravity :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "specific-gravity")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "specific-gravity")
          "The valid units are included in the error message")))
  (testing "Ensure source-unit-error sets an appropriate error for temperature"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/temperature :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "temperature")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "celsius")
          "The valid units are included in the error message")))
  (testing "Ensure source-unit-error sets an appropriate error for time"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/time :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "time")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "minute")
          "The valid units are included in the error message")))
  (testing "Ensure source-unit-error sets an appropriate error for volume"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "teaspoon")
          "The valid units are included in the error message")))
  (testing "Ensure source-unit-error sets an appropriate error for weight"
    (let [error-map     {:some "error"}
          new-error-map (sut/source-unit-error error-map options/weight :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map :units)
          "The new error is recorded")
      (is (str/includes? (:units new-error-map) "weight")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (:units new-error-map) "fake")
          "The invalid unit is included in the error message")
      (is (str/includes? (:units new-error-map) "pound")
          "The valid units are included in the error message"))))


(deftest systems-of-meaure-error-test
  (testing "Ensure systems-of-meaure-error sets an appropriate error for color."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/color :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "color")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message")))
  (testing "Ensure systems-of-meaure-error sets an appropriate error for pressure."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/pressure :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "pressure")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message")))
  (testing "Ensure systems-of-meaure-error sets an appropriate error for specific-gravity."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/specific-gravity :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "specific-gravity")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message")))
  (testing "Ensure systems-of-meaure-error sets an appropriate error for temperature."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/temperature :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "temperature")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message")))
  (testing "Ensure systems-of-meaure-error sets an appropriate error for time."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/time :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "time")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message")))
  (testing "Ensure systems-of-meaure-error sets an appropriate error for volume."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message")))
  (testing "Ensure systems-of-meaure-error sets an appropriate error for weight."
    (let [error-map     {:some "error"}
          new-error-map (sut/systems-of-meaure-error error-map options/weight :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/system-of-measure)
          "The new error is recorded")
      (is (str/includes? (options/system-of-measure new-error-map) "weight")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "fake")
          "The invalid system of measure is included in the error message")
      (is (str/includes? (options/system-of-measure new-error-map) "metric")
          "The valid values are included in the error message"))))


(deftest precision-error-test
  (testing "Ensure precision-error sets an appropriate error"
    (let [error-map     {:some "error"}
          new-error-map (sut/precision-error error-map options/volume 10)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/precision)
          "The new error is recorded")
      (is (str/includes? (options/precision new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/precision new-error-map) "10")
          "The invalid system of measure is included in the error message"))))


(deftest suffix-error-test
  (testing "Ensure suffix-error sets an appropriate error"
    (let [error-map     {:some "error"}
          new-error-map (sut/suffix-error error-map options/volume :fake)]
      (is (contains? new-error-map :some)
          "Previously recorded errors are preserved")
      (is (contains? new-error-map options/suffix)
          "The new error is recorded")
      (is (str/includes? (options/suffix new-error-map) "volume")
          "The type of attempted conversion is included in the error message")
      (is (str/includes? (options/suffix new-error-map) "fake")
          "The invalid suffix type is included in the error message")
      (is (str/includes? (options/suffix new-error-map) "full")
          "The valid values are included in the error message"))))


(deftest parse-enrich-displayable-color-opts-test
  (let [valid-opts  {:target-units             options/lovibond
                     :source-units             options/srm
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-color-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/color valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-temperature-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/lovibond
                               :source-units             options/srm
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/color valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/color (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/color (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/color (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/color (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/color (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/color (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/color (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/color (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/color (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/color (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/color (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/color (assoc valid-opts options/suffix :fake))))))))


(deftest parse-enrich-displayable-pressure-opts-test
  (let [valid-opts  {:target-units             options/psi
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-pressure-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/pressure valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-pressure-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/psi
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/pressure valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts options/system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts options/system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts options/system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts options/system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/pressure (assoc valid-opts options/suffix :fake))))))))


(deftest parse-enrich-displayable-specific-gravity-opts-test
  (let [valid-opts  {:target-units             options/specific-gravity
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-specific-gravity-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/specific-gravity valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-specific-gravity-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/specific-gravity
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/specific-gravity valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts options/system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts options/system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts options/system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts options/system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/specific-gravity (assoc valid-opts options/suffix :fake))))))))


(deftest parse-enrich-displayable-temperature-opts-test
  (let [valid-opts  {:target-units             options/c
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-temperature-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/temperature valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-temperature-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/c
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/temperature valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts options/system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts options/system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts options/system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts options/system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/temperature (assoc valid-opts options/suffix :fake))))))))


(deftest parse-enrich-displayable-time-opts-test
  (let [valid-opts  {:target-units             options/minute
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-time-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/time valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-temperature-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/minute
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/time valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts options/system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts options/system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts options/system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts options/system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/time (assoc valid-opts options/suffix :fake))))))))


(deftest parse-enrich-displayable-volume-opts-test
  (let [valid-opts  {:target-units             options/teaspoon
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-volume-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/volume valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-temperature-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/teaspoon
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/volume valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts options/system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts options/system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts options/system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts options/system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/volume (assoc valid-opts options/suffix :fake))))))))


(deftest parse-enrich-displayable-weight-opts-test
  (let [valid-opts  {:target-units             options/pound
                     options/system-of-measure options/metric
                     options/precision         2
                     options/suffix            options/full}
        error-regex #"Invalid enrichment options for ->displayable-units:"]
    (testing "Ensure parse-enrich-displayable-weight-opts returns valid opts"
      (is (= valid-opts (sut/parse-enrich-displayable-units-opts options/weight valid-opts))
          "Valid opts are returned unchanged"))
    (testing "Ensure parse-enrich-displayable-temperature-opts returns valid opts with static keys"
      (let [valid-opts-w-keys {:target-units             options/pound
                               options/system-of-measure options/metric
                               options/precision         2
                               options/suffix            options/full}]
        (is (= valid-opts-w-keys (sut/parse-enrich-displayable-units-opts options/weight valid-opts-w-keys))
            "Valid opts are returned unchanged with static keys")))
    (testing "Missing any option throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts :target-units)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts :target-units)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts options/system-of-measure)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts options/system-of-measure)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts options/precision)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts options/precision)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts options/suffix)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (dissoc valid-opts options/suffix))))))
    (testing "An invalid selection for any require value throws an error"
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts :target-units :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts :target-units :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts options/system-of-measure :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts options/system-of-measure :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts options/precision :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts options/precision :fake)))))
      #?(:clj (is (thrown-with-msg? Exception error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts options/suffix :fake)))))
      #?(:cljs (is (thrown-with-msg? js/Error error-regex (sut/parse-enrich-displayable-units-opts options/weight (assoc valid-opts options/suffix :fake))))))))


(deftest enrich-displayable-color-test
  (testing "Validate displayable color enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/color base-map {sut/display-key :hello
                                                                              sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "0.595 srm")]
        (is (= result-map (sut/enrich-displayable-units options/color base-map {sut/display-key  :display
                                                                                sut/value-key    :value
                                                                                :source-units options/lovibond
                                                                                :target-units options/srm})))))))


(deftest enrich-displayable-pressure-test
  (testing "Validate displayable pressure enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/pressure base-map {sut/display-key :hello
                                                                                 sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "1000.0 pa")]
        (is (= result-map (sut/enrich-displayable-units options/pressure base-map {sut/display-key             :display
                                                                                   sut/value-key               :value
                                                                                   sut/fine-grain-target-units options/pascal})))))))


(deftest enrich-displayable-specific-gravity-test
  (testing "Validate displayable specific-gravity enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/specific-gravity base-map {sut/display-key :hello
                                                                                         sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "1.0 sg")]
        (is (= result-map (sut/enrich-displayable-units options/specific-gravity base-map {sut/display-key             :display
                                                                                           sut/value-key               :value
                                                                                           sut/fine-grain-target-units options/specific-gravity})))))))


(deftest enrich-displayable-temperature-test
  (testing "Validate displayable temperature enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/temperature base-map {sut/display-key :hello
                                                                                    sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "274.15 k")]
        (is (= result-map (sut/enrich-displayable-units options/temperature base-map {sut/display-key             :display
                                                                                      sut/value-key               :value
                                                                                      sut/fine-grain-target-units options/k})))))))


(deftest enrich-displayable-time-test
  (testing "Validate displayable time enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/time base-map {sut/display-key :hello
                                                                             sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "1.0 m")]
        (is (= result-map (sut/enrich-displayable-units options/time base-map {sut/display-key             :display
                                                                               sut/value-key               :value
                                                                               sut/fine-grain-target-units options/minute})))))))


(deftest enrich-displayable-volume-test
  (testing "Validate displayable volume enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/volume base-map {sut/display-key :hello
                                                                               sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "202.884 tsp")]
        (is (= result-map (sut/enrich-displayable-units options/volume base-map {sut/display-key             :display
                                                                                 sut/value-key               :value
                                                                                 sut/fine-grain-target-units options/teaspoon})))))))


(deftest enrich-displayable-weight-test
  (testing "Validate displayable weight enrichment."
    (testing "If no value is provided, return the original map."
      (let [base-map {:hello "there"}]
        (is (= base-map (sut/enrich-displayable-units options/weight base-map {sut/display-key :hello
                                                                               sut/value-key   :missing})))))
    (testing "When a value is provided, add the data at `:display-key`"
      (let [base-map   {:hello "there"
                        :value 1}
            result-map (assoc base-map :display "2.205 lb")]
        (is (= result-map (sut/enrich-displayable-units options/weight base-map {sut/display-key             :display
                                                                                 sut/value-key               :value
                                                                                 sut/fine-grain-target-units options/pound})))))))

(deftest enrich-displayable-range-test
  (testing "Validate displayable range enrichment."
    (testing "If neither value is provided, return the original map."
      (let [base-map {:a 1
                      :b 3}]
        (is (= base-map (sut/enrich-displayable-range options/weight base-map {sut/display-key    :hello
                                                                               sut/low-value-key  :missing
                                                                               sut/high-value-key :also-gone})))))
    (testing "When all values are provided, add the data at `:display-key`"
      (let [base-map   {:a 3
                        :b 6}
            result-map (assoc base-map :display "6.614 - 13.228 lb")]
        (is (= result-map (sut/enrich-displayable-range options/weight base-map {sut/display-key             :display
                                                                                 sut/low-value-key           :a
                                                                                 sut/high-value-key          :b
                                                                                 sut/fine-grain-target-units options/pound})))))))
