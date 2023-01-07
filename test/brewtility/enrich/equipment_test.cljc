(ns brewtility.enrich.equipment-test
  (:require [brewtility.data.equipment :as equipment.data]
            [brewtility.enrich.equipment :as equipment.enrich]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.equipment :as equipment.format]
            #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])))


(deftest enrich-calculated-boil-size-tests
  (testing "Ensure enrichment pattern works for calculating boil size"
    (is (= 20.288
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-calculated-boil-size
               :boil-size))
        "Boil size can be derived in liters when sufficient data is present")
    (is (= (:boil-size equipment.data/sample-equipment)
           (-> equipment.data/sample-equipment
               (dissoc :batch-size)
               (equipment.enrich/enrich-calculated-boil-size {:safe-calculating-boil-size true})
               :boil-size))
        "Boil size will not be modified when required data for calculations are missing and `safe-calculating-boil-size` is true")
    #?(:clj (is (thrown-with-msg? Exception #"Cannot calculate boil volume with non-numeric values"
                  (-> equipment.data/sample-equipment
                      (dissoc :batch-size)
                      equipment.enrich/enrich-calculated-boil-size
                      :boil-size))
                "Computation will throw an Exception when required data for calculations are missing and `safe-calculating-boil-size` is missing/false"))
    #?(:cljs (is (thrown-with-msg? js/Error #"Cannot calculate boil volume with non-numeric values"
                   (-> equipment.data/sample-equipment
                       (dissoc :batch-size)
                       equipment.enrich/enrich-calculated-boil-size
                       :boil-size))
                 "Computation will throw an Exception when required data for calculations are missing and `safe-calculating-boil-size` is missing/false"))
    (is (= "5.36 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-calculated-boil-size
               equipment.enrich/enrich-display-boil-size
               :display-boil-size)))))


(deftest enrich-display-boil-size-tests
  (testing "Ensure enrichment pattern works for all displayable boil sizes"
    (is (= "6.921 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-boil-size
               :display-boil-size))
        "Boil size conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "6.92 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-boil-size {:precision 2})
               :display-boil-size))
        "Boil size precision can be set to 2 significant figures")
    (is (= "6.921 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-boil-size {:suffix :full})
               :display-boil-size))
        "Boil size suffix can be set to full descriptive text")
    (is (= "26.2 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-boil-size {:system-of-measure :metric})
               :display-boil-size)))
    (is (= "110.7 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-boil-size {:boil-size-target-units :cup
                                                           :boil-size-precision    1
                                                           :boil-size-suffix       :full})
               :display-boil-size))
        "Broad settings can be overriden with boil-size specific settings")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-boil-size equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-batch-size-tests
  (testing "Ensure enrichment pattern works for all displayable batch-sizes"
    (is (= "5.257 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-batch-size
               :display-batch-size))
        "batch-size conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "5.26 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-batch-size {:precision 2})
               :display-batch-size))
        "batch-size precision can be set to 2 significant figures")
    (is (= "5.257 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-batch-size {:suffix :full})
               :display-batch-size))
        "batch-size suffix can be set to full descriptive text")
    (is (= "19.9 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-batch-size {:system-of-measure :metric})
               :display-batch-size)))
    (is (= "84.1 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-batch-size {:batch-size-target-units :cup
                                                            :batch-size-precision    1
                                                            :batch-size-suffix       :full})
               :display-batch-size))
        "Broad settings can be overriden with batch-size specific settings")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-batch-size equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-tun-volume-tests
  (testing "Ensure enrichment pattern works for all displayable tun-volumes"
    (is (= "5.257 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-tun-volume
               :display-tun-volume))
        "tun-volume conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "5.26 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-volume {:precision 2})
               :display-tun-volume))
        "tun-volume precision can be set to 2 significant figures")
    (is (= "5.257 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-volume {:suffix :full})
               :display-tun-volume))
        "tun-volume suffix can be set to full descriptive text")
    (is (= "19.9 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-volume {:system-of-measure :metric})
               :display-tun-volume)))
    (is (= "84.1 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-volume {:tun-volume-target-units :cup
                                                            :tun-volume-precision    1
                                                            :tun-volume-suffix       :full})
               :display-tun-volume))
        "Broad settings can be overriden with tun-volume specific settings")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-tun-volume equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-tun-weight-tests
  (testing "Ensure enrichment pattern works for all displayable tun-weights"
    (is (= "5.512 lb"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-tun-weight
               :display-tun-weight))
        "tun-weight conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "5.51 lb"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-weight {:precision 2})
               :display-tun-weight))
        "tun-weight precision can be set to 2 significant figures")
    (is (= "5.512 pound"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-weight {:suffix :full})
               :display-tun-weight))
        "tun-weight suffix can be set to full descriptive text")
    (is (= "2.5 kg"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-weight {:system-of-measure :metric})
               :display-tun-weight)))
    (is (= "88.2 ounce"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-tun-weight {:tun-weight-target-units :ounce
                                                            :tun-weight-precision    1
                                                            :tun-weight-suffix       :full})
               :display-tun-weight))
        "Broad settings can be overriden with tun-weight specific settings")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-tun-weight equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-top-up-water-tests
  (testing "Ensure enrichment pattern works for all displayable top-up-waters"
    (is (= "0.132 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-top-up-water
               :display-top-up-water))
        "top-up-water conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "0.13 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-water {:precision 2})
               :display-top-up-water))
        "top-up-water precision can be set to 2 significant figures")
    (is (= "0.132 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-water {:suffix :full})
               :display-top-up-water))
        "top-up-water suffix can be set to full descriptive text")
    (is (= "0.5 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-water {:system-of-measure :metric})
               :display-top-up-water)))
    (is (= "2.1 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-water {:top-up-water-target-units :cup
                                                              :top-up-water-precision    1
                                                              :top-up-water-suffix       :full})
               :display-top-up-water))
        "Broad settings can be overriden with top-up-water specific settings")
    (is (nil? (-> equipment.data/sample-equipment
                  (dissoc :top-up-water)
                  equipment.enrich/enrich-display-top-up-water
                  :display-top-up-water))
        ":display-top-up-water is not added if trub-chiller-loss is not present")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-top-up-water equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-trub-chiller-loss-tests
  (testing "Ensure enrichment pattern works for all displayable trub-chiller-losss"
    (is (= "0.211 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-trub-chiller-loss
               :display-trub-chiller-loss))
        "trub-chiller-loss conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "0.21 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-trub-chiller-loss {:precision 2})
               :display-trub-chiller-loss))
        "trub-chiller-loss precision can be set to 2 significant figures")
    (is (= "0.211 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-trub-chiller-loss {:suffix :full})
               :display-trub-chiller-loss))
        "trub-chiller-loss suffix can be set to full descriptive text")
    (is (= "0.8 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-trub-chiller-loss {:system-of-measure :metric})
               :display-trub-chiller-loss)))
    (is (= "3.4 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-trub-chiller-loss {:trub-chiller-loss-target-units :cup
                                                                   :trub-chiller-loss-precision    1
                                                                   :trub-chiller-loss-suffix       :full})
               :display-trub-chiller-loss))
        "Broad settings can be overriden with trub-chiller-loss specific settings")
    (is (nil? (-> equipment.data/sample-equipment
                  (dissoc :trub-chiller-loss)
                  equipment.enrich/enrich-display-lauter-deadspace
                  :display-trub-chiller-loss))
        ":display-trub-chiller-loss is not added if trub-chiller-loss is not present")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-trub-chiller-loss equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-lauter-deadspace-tests
  (testing "Ensure enrichment pattern works for all displayable lauter-deadspaces"
    (is (= "0.211 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-lauter-deadspace
               :display-lauter-deadspace))
        "lauter-deadspace conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "0.21 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-lauter-deadspace {:precision 2})
               :display-lauter-deadspace))
        "lauter-deadspace precision can be set to 2 significant figures")
    (is (= "0.211 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-lauter-deadspace {:suffix :full})
               :display-lauter-deadspace))
        "lauter-deadspace suffix can be set to full descriptive text")
    (is (= "0.8 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-lauter-deadspace {:system-of-measure :metric})
               :display-lauter-deadspace)))
    (is (= "3.4 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-lauter-deadspace {:lauter-deadspace-target-units :cup
                                                                  :lauter-deadspace-precision    1
                                                                  :lauter-deadspace-suffix       :full})
               :display-lauter-deadspace))
        "Broad settings can be overriden with lauter-deadspace specific settings")
    (is (nil? (-> equipment.data/sample-equipment
                  (dissoc :lauter-deadspace)
                  (equipment.enrich/enrich-display-lauter-deadspace {:lauter-deadspace-target-units :cup
                                                                     :lauter-deadspace-precision    1
                                                                     :lauter-deadspace-suffix       :full})
                  :display-lauter-deadspace))
        "Display value will not be set if lauter-deadspace is not present")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-lauter-deadspace equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-display-top-up-kettle-tests
  (testing "Ensure enrichment pattern works for all displayable top-up-kettles"
    (is (= "0.132 gal"
           (-> equipment.data/sample-equipment
               equipment.enrich/enrich-display-top-up-kettle
               :display-top-up-kettle))
        "top-up-kettle conversion defaults to US gallons and 3 significant figures of precision")
    (is (= "0.13 gal"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-kettle {:precision 2})
               :display-top-up-kettle))
        "top-up-kettle precision can be set to 2 significant figures")
    (is (= "0.132 US gallon"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-kettle {:suffix :full})
               :display-top-up-kettle))
        "top-up-kettle suffix can be set to full descriptive text")
    (is (= "0.5 l"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-kettle {:system-of-measure :metric})
               :display-top-up-kettle)))
    (is (= "2.1 cup"
           (-> equipment.data/sample-equipment
               (equipment.enrich/enrich-display-top-up-kettle {:top-up-kettle-target-units :cup
                                                               :top-up-kettle-precision    1
                                                               :top-up-kettle-suffix       :full})
               :display-top-up-kettle))
        "Broad settings can be overriden with top-up-kettle specific settings")
    (is (nil? (-> equipment.data/sample-equipment
                  (dissoc :top-up-kettle)
                  equipment.enrich/enrich-display-top-up-kettle
                  :display-top-up-kettle))
        ":display-top-up-kettle is not added if top-up-kettle is not present")
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-display-top-up-kettle equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")))


(deftest enrich-equipment-tests
  (testing "Ensure enrichment pattern works for all equipment"
    (is (spoon.spec/test-valid? ::equipment.format/equipment
                                (equipment.enrich/enrich-equipment equipment.data/sample-equipment))
        "Enrichment pattern should produce a valid equipment object")
    (is (= {:display-top-up-kettle     "0.132 gal"
            :display-batch-size        "5.257 gal"
            :lauter-deadspace          0.8
            :calc-boil-volume          true
            :top-up-water              0.5
            :hop-utilization           100.5
            :name                      "8 Gal pot with 5 gal Igloo Cooler"
            :display-top-up-water      "0.132 gal"
            :boil-size                 20.288
            :display-trub-chiller-loss "0.211 gal"
            :display-lauter-deadspace  "0.211 gal"
            :trub-chiller-loss         0.8
            :boil-time                 60.5
            :display-tun-weight        "5.512 lb"
            :notes                     "Popular all grain setup. 5 Gallon Gott or Igloo cooler as mash tun with false bottom, and 7-9 gallon brewpot capable of boiling at least 6 gallons of wort. Primarily used for single infusion mashes."
            :tun-volume                19.9
            :top-up-kettle             0.5
            :display-tun-volume        "5.257 gal"
            :display-boil-size         "5.36 gal"
            :tun-weight                2.5
            :version                   1
            :tun-specific-heat         0.3
            :batch-size                19.9
            :evap-rate                 9.0}
           (equipment.enrich/enrich-equipment equipment.data/sample-equipment)))
    (is (= {:display-top-up-kettle     "0.5 litre"
            :display-batch-size        "19.9 litre"
            :lauter-deadspace          0.8
            :calc-boil-volume          true
            :top-up-water              0.5
            :hop-utilization           100.5
            :name                      "8 Gal pot with 5 gal Igloo Cooler"
            :display-top-up-water      "0.5 litre"
            :boil-size                 20.3
            :display-trub-chiller-loss "0.8 litre"
            :display-lauter-deadspace  "0.8 litre"
            :trub-chiller-loss         0.8
            :boil-time                 60.5
            :display-tun-weight        "2.5 kilogram"
            :notes                     "Popular all grain setup. 5 Gallon Gott or Igloo cooler as mash tun with false bottom, and 7-9 gallon brewpot capable of boiling at least 6 gallons of wort. Primarily used for single infusion mashes."
            :tun-volume                19.9
            :top-up-kettle             0.5
            :display-tun-volume        "19.9 litre"
            :display-boil-size         "20.3 litre"
            :tun-weight                2.5
            :version                   1
            :tun-specific-heat         0.3
            :batch-size                19.9
            :evap-rate                 9.0}
           (equipment.enrich/enrich-equipment equipment.data/sample-equipment {:system-of-measure :si
                                                                               :precision         1
                                                                               :suffix            :full}))
        "Composed enrichment pattern global settings set default behavior for all enrichers")))


(deftest enrich-equipment-wrapper-tests
  (testing "Ensure enrichment pattern works for all equipment-wrapper"
    (is (spoon.spec/test-valid? ::equipment.format/equipment-wrapper
                                (equipment.enrich/enrich-equipment-wrapper equipment.data/sample-equipment-wrapper))
        "Enrichment pattern should produce a valid equipment-wrapper object")
    (is (= {:equipment {:display-top-up-kettle     "0.132 gal"
                        :display-batch-size        "5.257 gal"
                        :lauter-deadspace          0.8
                        :calc-boil-volume          true
                        :top-up-water              0.5
                        :hop-utilization           100.5
                        :name                      "8 Gal pot with 5 gal Igloo Cooler"
                        :display-top-up-water      "0.132 gal"
                        :boil-size                 20.288
                        :display-trub-chiller-loss "0.211 gal"
                        :display-lauter-deadspace  "0.211 gal"
                        :trub-chiller-loss         0.8
                        :boil-time                 60.5
                        :display-tun-weight        "5.512 lb"
                        :notes                     "Popular all grain setup. 5 Gallon Gott or Igloo cooler as mash tun with false bottom, and 7-9 gallon brewpot capable of boiling at least 6 gallons of wort. Primarily used for single infusion mashes."
                        :tun-volume                19.9
                        :top-up-kettle             0.5
                        :display-tun-volume        "5.257 gal"
                        :display-boil-size         "5.36 gal"
                        :tun-weight                2.5
                        :version                   1
                        :tun-specific-heat         0.3
                        :batch-size                19.9
                        :evap-rate                 9.0}}
           (equipment.enrich/enrich-equipment-wrapper equipment.data/sample-equipment-wrapper)))
    (is (= {:equipment {:display-top-up-kettle     "0.5 litre"
                        :display-batch-size        "19.9 litre"
                        :lauter-deadspace          0.8
                        :calc-boil-volume          true
                        :top-up-water              0.5
                        :hop-utilization           100.5
                        :name                      "8 Gal pot with 5 gal Igloo Cooler"
                        :display-top-up-water      "0.5 litre"
                        :boil-size                 20.3
                        :display-trub-chiller-loss "0.8 litre"
                        :display-lauter-deadspace  "0.8 litre"
                        :trub-chiller-loss         0.8
                        :boil-time                 60.5
                        :display-tun-weight        "2.5 kilogram"
                        :notes                     "Popular all grain setup. 5 Gallon Gott or Igloo cooler as mash tun with false bottom, and 7-9 gallon brewpot capable of boiling at least 6 gallons of wort. Primarily used for single infusion mashes."
                        :tun-volume                19.9
                        :top-up-kettle             0.5
                        :display-tun-volume        "19.9 litre"
                        :display-boil-size         "20.3 litre"
                        :tun-weight                2.5
                        :version                   1
                        :tun-specific-heat         0.3
                        :batch-size                19.9
                        :evap-rate                 9.0}}
           (equipment.enrich/enrich-equipment-wrapper equipment.data/sample-equipment-wrapper {:system-of-measure :si
                                                                                               :precision         1
                                                                                               :suffix            :full}))
        "Composed enrichment pattern global settings set default behavior for all enrichers")))


(deftest generative-enrichment-tests
  (testing "Ensure enrichment pattern works against arbitrary equipment"
    ;; We disable boil voulme calculation in generative tests because :boil-size must be positive
    ;; Which puts tight constraints on the test data, meaning actual generation of values would be
    ;; extremely unlikely or require highly complex generators.
    (letfn [(gen-equipment [] (assoc (equipment.data/generate-equipment) :calc-boil-volume false))]
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-boil-size (gen-equipment)))
          "enrich-display-boil-size is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-batch-size (gen-equipment)))
          "enrich-display-batch-size is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-tun-volume (gen-equipment)))
          "enrich-display-tun-volume is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-tun-weight (gen-equipment)))
          "enrich-display-tun-weight is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-top-up-water (gen-equipment)))
          "enrich-display-top-up-water is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-trub-chiller-loss (gen-equipment)))
          "enrich-display-trub-chiller-loss is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-lauter-deadspace (gen-equipment)))
          "enrich-display-lauter-deadspace is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-display-top-up-kettle (gen-equipment)))
          "enrich-display-top-up-kettle is a function to and from common-beer-format.equipment/equipment")
      (is (spoon.spec/test-valid? ::equipment.format/equipment
                                  (equipment.enrich/enrich-equipment (gen-equipment)))
          "enrich-equipment is a function to and from common-beer-format.equipment/equipment")))
  (testing "Ensure enrichment pattern works against arbitrary equipment wrappers"
    (is (spoon.spec/test-valid? ::equipment.format/equipment-wrapper
                                (equipment.enrich/enrich-equipment-wrapper
                                 (assoc-in (equipment.data/generate-equipment-wrapper) [:equipment :calc-boil-volume] false)))
        "enrich-equipment-wrapper is a function to and from common-beer-format.equipment/equipment-wrapper")))

