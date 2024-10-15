# Calculations

Many attributes of a beer, like the alcohol content and bitterness, can be calculated from the source ingredients.
Brewtility has many of the calculations required by the BeerXML specification, and other convenience functions packaged under `brewtility.calculations`.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Beer Color Calculation](#beer-color-calculation)
- [Gravity and ABV Calculation](#gravity-and-abv-calculation)
- [IBU Calculation](#ibu-calculation)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

The following is a list of supported functions:

## Beer Color Calculation

Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the projected color of the beer in one of the following color scales:

* Malt Color Units (MCU)
* Standard Reference Method (SRM)
* European Brewery Convention (EBC)
* Lovibond
* Red/Green/Blue/Alpha (RGBA)

```clj
(:require [brewtility.calculations :refer :all]
          [common-beer-format.data.fermentables.grains :as grains]
          [common-beer-format.data.fermentables.adjuncts :as adjuncts])

(def recipe-fermentables
  [(assoc (:rye-malt grains/grains) :amount 7)
   (assoc (:pale-malt-2-row-us grains/grains) :amount 3)
   (assoc (:rice-hulls adjuncts/adjuncts) :amount 2)
   (assoc (:grits adjuncts/adjuncts) :amount 4)])

(def batch-size 15)

(calculate-malt-color-units recipe-fermentables batch-size)
;; => 20.784

(calculate-srm-color recipe-fermentables batch-size)
;; => 21.273

(calculate-ebc-color recipe-fermentables batch-size)
;; => 20.41.907

(calculate-lovibond-color recipe-fermentables batch-size)
;; => 41.907

(calculate-rgba-color recipe-fermentables batch-size)
;; => "rgba(149,45,0,1)"
```

## Gravity and ABV Calculation

Given a collection of `common-beer-format` conforming `fermentables` and a conformed `batch-size` in liters, project the original gravity and estimate the ABV.

```clj
(:require [brewtility.calculations :refer :all]
          [common-beer-format.data.fermentables.grains :as grains]
          [common-beer-format.data.fermentables.adjuncts :as adjuncts])

(def recipe-fermentables
  [(assoc (:rye-malt grains/grains) :amount 7)
   (assoc (:pale-malt-2-row-us grains/grains) :amount 3)
   (assoc (:rice-hulls adjuncts/adjuncts) :amount 2)
   (assoc (:grits adjuncts/adjuncts) :amount 4)])

(def batch-size 19)

(calculate-potential-gravity fermentables batch-size)
;; => 1.146

(calculate-potential-final-gravity fermentables batch-size)
;; => 1.036

;; Reminder: All percentages are treated as decimals
(calculate-potential-abv fermentables batch-size)
;; => 0.147

(def my-yeast-attenuation 1.00)

;; Reminder: All percentages are treated as decimals
(calculate-potential-abv fermentables batch-size my-yeast-attenuation)
;; => 0.197
```

## IBU Calculation

Given a collection of `common-beer-format` conforming `hops`, `batch-size`, and `potential-gravity`, calculate the number of IBUs generated.

```clj
(:require [brewtility.calculations :refer :all]
          [common-beer-format.data.hops.both :as hops])

(def hops
  [(assoc (:el-dorado hops/both) :amount 0.01 :time 60)]
   (assoc (:horizon hops/both) :amount 0.02 :time 10)])

(def batch-size 15)
(def potential-gravity 1.03)

(calculate-recipe-ibus hops batch-size potential-gravity)
;; => 42.795
```
