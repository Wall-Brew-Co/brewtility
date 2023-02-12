(ns brewtility.data.equipment
  "Namespace for static and generative test data for `common-beer-format.equipment/equipment` and `common-beer-format.equipment/equipment-wrapper`"
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.equipment :as equipment.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(def ^:const sample-equipment
  "A hard-coded sample equipment for static unit tests"
  {:batch-size        19.9
   :boil-size         26.2
   :boil-time         60.5
   :calc-boil-volume  true
   :evap-rate         9.0
   :hop-utilization   100.5
   :lauter-deadspace  0.8
   :name              "8 Gal pot with 5 gal Igloo Cooler"
   :notes             "Popular all grain setup. 5 Gallon Gott or Igloo cooler as mash tun with false bottom, and 7-9 gallon brewpot capable of boiling at least 6 gallons of wort. Primarily used for single infusion mashes."
   :top-up-kettle     0.5
   :top-up-water      0.5
   :trub-chiller-loss 0.8
   :tun-specific-heat 0.3
   :tun-volume        19.9
   :tun-weight        2.5
   :version           1})


(def ^:const sample-equipment-wrapper
  "A hard-coded sample equipment-wrapper for static unit tests"
  {:equipment sample-equipment})


(defn generate-equipment
  "Generate a random equipment object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::equipment.format/equipment)))


(defn generate-equipment-wrapper
  "Generate a random equipment-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::equipment.format/equipment-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::equipment.format/equipment sample-equipment)
        "Static test data should conform to common-beer-format.equipment/equipment")
    (is (spoon.spec/test-valid? ::equipment.format/equipment-wrapper sample-equipment-wrapper)
        "Static test data should conform to common-beer-format.equipment/equipment-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::equipment.format/equipment (generate-equipment))
        "Generative test data should conform to common-beer-format.equipment/equipment")
    (is (spoon.spec/test-valid? ::equipment.format/equipment-wrapper (generate-equipment-wrapper))
        "Generative test data should conform to common-beer-format.equipment/equipment-wrapper")))
