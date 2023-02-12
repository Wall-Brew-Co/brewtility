(ns brewtility.calculations-test
  (:require #? (:clj  [clojure.test :refer [deftest is testing]])
            #? (:cljs [cljs.test    :refer-macros [deftest is testing]])
            [brewtility.calculations :as sut]
            [brewtility.color :as color]
            [brewtility.precision :as bp]
            [common-beer-data.fermentables.adjuncts :as adjuncts]
            [common-beer-data.fermentables.grains :as grains]
            [common-beer-data.hops.both :as hops]))


(deftest normalize-fermentable-test
  (testing "common-beer-format fermentables can be normalized for Malt Color computation"
    (let [grain              (assoc (:rye-malt grains/grains) :amount 10)
          normalized-grain   (sut/normalize-fermentable grain)
          adjunct            (assoc (:grits adjuncts/adjuncts) :amount 5)
          normalized-adjunct (sut/normalize-fermentable adjunct)]
      (is (= (dissoc normalized-grain :amount) (dissoc (:rye-malt grains/grains) :amount)))
      (is (= 22.046 (bp/->3dp (:amount normalized-grain))))
      (is (= (dissoc normalized-adjunct :amount :color) (dissoc (:grits adjuncts/adjuncts) :amount :color)))
      (is (= 11.023 (bp/->3dp (:amount normalized-adjunct))))
      (is (= 1.299 (bp/->3dp (:color normalized-adjunct)))))))


(deftest calculate-malt-color-units-test
  (testing "Colors can be correctly computer for recipes"
    (let [grain-1      (assoc (:rye-malt grains/grains) :amount 7)
          grain-2      (assoc (:pale-malt-2-row-us grains/grains) :amount 3)
          adjunct-1    (assoc (:rice-hulls adjuncts/adjuncts) :amount 2)
          adjunct-2    (assoc (:grits adjuncts/adjuncts) :amount 4)
          fermentables [grain-1 grain-2 adjunct-1 adjunct-2]
          batch-size   19]
      (is (= 20.784 (bp/->3dp (sut/calculate-malt-color-units fermentables batch-size))))
      (is (= 21.273 (bp/->3dp (sut/calculate-srm-color fermentables batch-size))))
      (is (= 41.907 (bp/->3dp (sut/calculate-ebc-color fermentables batch-size))))
      (is (= 41.907 (bp/->3dp (sut/calculate-lovibond-color fermentables batch-size))))
      (is (= color/srm-21 (sut/calculate-rgba-color fermentables batch-size))))))


(deftest gravity-conversion-test
  (testing "Potential gravity and gravity points can be computed correctly"
    (is (= 264.555 (bp/->3dp (sut/potential-gravity->gravity-points 1.04 3))))
    (is (= 0.0 (bp/->3dp (sut/potential-gravity->gravity-points 1.0 5143))))
    (is (= 1.043 (bp/->3dp (sut/gravity-points->potential-gravity 40 3.5))))
    (is (= 1.0 (bp/->3dp (sut/gravity-points->potential-gravity 0 3.5))))))


(deftest calculate-potential-gravity-test
  (testing "Gravity/ABV can be correctly computer for recipes"
    (let [grain-1      (assoc (:rye-malt grains/grains) :amount 7)
          grain-2      (assoc (:pale-malt-2-row-us grains/grains) :amount 3)
          adjunct-1    (assoc (:rice-hulls adjuncts/adjuncts) :amount 2)
          adjunct-2    (assoc (:grits adjuncts/adjuncts) :amount 4)
          fermentables [grain-1 grain-2 adjunct-1 adjunct-2]
          batch-size   19]
      (is (= 1.146 (bp/->3dp (sut/calculate-potential-gravity fermentables batch-size))))
      (is (= 1.036 (bp/->3dp (sut/calculate-potential-final-gravity fermentables batch-size))))
      (is (= 1.0   (bp/->3dp (sut/calculate-potential-final-gravity fermentables batch-size 1))))
      (is (= 1.146 (bp/->3dp (sut/calculate-potential-final-gravity fermentables batch-size 0))))
      (is (= 0.147 (bp/->3dp (sut/calculate-potential-abv fermentables batch-size))))
      (is (= 0.197 (bp/->3dp (sut/calculate-potential-abv fermentables batch-size 1))))
      (is (= 0.0   (bp/->3dp (sut/calculate-potential-abv fermentables batch-size 0)))))))


(deftest calculate-hop-utilization-test
  (testing "The percent hop utilization can be calculated correctly"
    (is (= 0.0   (bp/->3dp (sut/calculate-hop-utilization 1.03 0))))
    (is (= 0.1   (bp/->3dp (sut/calculate-hop-utilization 1.03 10))))
    (is (= 0.276 (bp/->3dp (sut/calculate-hop-utilization 1.03 60))))
    (is (= 0.301 (bp/->3dp (sut/calculate-hop-utilization 1.03 120))))
    (is (= 0.0   (bp/->3dp (sut/calculate-hop-utilization 1.07 0))))
    (is (= 0.07  (bp/->3dp (sut/calculate-hop-utilization 1.07 10))))
    (is (= 0.193 (bp/->3dp (sut/calculate-hop-utilization 1.07 60))))
    (is (= 0.210 (bp/->3dp (sut/calculate-hop-utilization 1.07 120))))
    (is (= 0.0   (bp/->3dp (sut/calculate-hop-utilization 1.12 0))))
    (is (= 0.045 (bp/->3dp (sut/calculate-hop-utilization 1.12 10))))
    (is (= 0.123 (bp/->3dp (sut/calculate-hop-utilization 1.12 60))))
    (is (= 0.134 (bp/->3dp (sut/calculate-hop-utilization 1.12 120))))))


(deftest calculate-alpha-acid-units-test
  (testing "The amount of alpha acids release by a known quantity of hops can be computed"
    (is (= 0.0 (bp/->3dp (sut/calculate-alpha-acid-units 0.0 0.5))))
    (is (= 0.0 (bp/->3dp (sut/calculate-alpha-acid-units 0.05 0.0))))
    (is (= 79.014 (bp/->3dp (sut/calculate-alpha-acid-units 0.14 (:alpha (:el-dorado hops/both))))))
    (is (= 41.976 (bp/->3dp (sut/calculate-alpha-acid-units 0.14 (:alpha (:northdown hops/both))))))))


(deftest calculate-ibu-per-hop-test
  (testing "A single hop addition's IBU contribution can be calculated"
    (let [hop (:el-dorado hops/both)]
      (is (= 0.0    (bp/->3dp (sut/calculate-ibu-per-hop (assoc hop :amount 0.01 :time 0) 15 1.03))))
      (is (= 10.677 (bp/->3dp (sut/calculate-ibu-per-hop (assoc hop :amount 0.01 :time 10) 15 1.03))))
      (is (= 29.448 (bp/->3dp (sut/calculate-ibu-per-hop (assoc hop :amount 0.01 :time 60) 15 1.03))))
      (is (= 32.12  (bp/->3dp (sut/calculate-ibu-per-hop (assoc hop :amount 0.01 :time 120) 15 1.03))))
      (is (= 16.06  (bp/->3dp (sut/calculate-ibu-per-hop (assoc hop :amount 0.01 :time 120) 30 1.03))))
      (is (= 24.529 (bp/->3dp (sut/calculate-ibu-per-hop (assoc hop :amount 0.01 :time 120) 15 1.06)))))))


(deftest calculate-recipe-ibus-test
  (testing "All hop addition IBU contributions can be calculated"
    (let [hop-1 (:el-dorado hops/both)
          hop-2 (:horizon hops/both)]
      (is (= 0.0    (bp/->3dp (sut/calculate-recipe-ibus [(assoc hop-1 :amount 0.01 :time 0)   (assoc hop-2 :amount 0.02 :time 0)] 15 1.03))))
      (is (= 10.677 (bp/->3dp (sut/calculate-recipe-ibus [(assoc hop-1 :amount 0.01 :time 10)  (assoc hop-2 :amount 0.02 :time 0)] 15 1.03))))
      (is (= 42.795 (bp/->3dp (sut/calculate-recipe-ibus [(assoc hop-1 :amount 0.01 :time 60)  (assoc hop-2 :amount 0.02 :time 10)] 15 1.03))))
      (is (= 68.93  (bp/->3dp (sut/calculate-recipe-ibus [(assoc hop-1 :amount 0.01 :time 120) (assoc hop-2 :amount 0.02 :time 60)] 15 1.03))))
      (is (= 34.465  (bp/->3dp (sut/calculate-recipe-ibus [(assoc hop-1 :amount 0.01 :time 120) (assoc hop-2 :amount 0.02 :time 60)] 30 1.03))))
      (is (= 52.64 (bp/->3dp (sut/calculate-recipe-ibus [(assoc hop-1 :amount 0.01 :time 120) (assoc hop-2 :amount 0.02 :time 60)] 15 1.06)))))))
