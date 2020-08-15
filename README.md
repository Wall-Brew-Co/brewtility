# brewtility

[![Clojars Project](https://img.shields.io/clojars/v/com.wallbrew/brewtility.svg)](https://clojars.org/com.wallbrew/brewtility)
![Clojure and ClojureScript CI](https://github.com/Wall-Brew-Co/brewtility/workflows/Clojure%20and%20ClojureScript%20CI/badge.svg)

A Clojure(Script) utility library for all of your brewing needs.

## Installation

A deployed copy of the most recent version of [brewtility can be found on clojars.](https://clojars.org/com.wallbrew/brewtility)
To use it, add the following as a dependency in your project.clj file:

[![Clojars Project](https://clojars.org/com.wallbrew/brewtility/latest-version.svg)](com.wallbrew/brewtility)

The next time you build your application, [Leiningen](https://leiningen.org/) or [deps.edn](https://clojure.org/guides/deps_and_cli) should pull it automatically.
Alternatively, you may clone or fork the repository to work with it directly.

## Expectations

This library is designed to work in conjunction with the [common-beer-format.](https://github.com/Wall-Brew-Co/common-beer-format)
The functions within the library make one main assumptions based on that fact:

1. All measurements are expected to be in the [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units), also known as the modern metric system. Since many brewing applications, especially those based in America, operate on Imperial Measurements, conversion utilities have been provided in the `brewtility.units` namespace.

## Provided Functionality

Below are examples of provided functionality for each namespace.
The library does provide access to other functions, but those primarily exist in support of those outlined here.

### Calculations

#### Beer Color Calculation

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

#### Gravity and ABV Calculation

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

#### IBU Calculation

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

### Color

#### Color Unit Conversion

Given a color measurement in one of the three most common systems, convert between each system of measure.
Supported color systems are as follows:

* Standard Reference Method (SRM)
* European Brewery Convention (EBC)
* Lovibond

```clj
(:require [brewtility.color :refer :all])

(lovibond->srm 7.94)
;; => 10.0

(srm->ebc 23.1)
;; => 45.51

(ebc->lovibond 60.38)
;; => 23.2
```

#### Display Color Lookup

Given a color measurement in one of the three most common systems, look up a corresponding RGBA color.
These functions are opinionated, and support and normalize down to the most common SRM scale: 1-40.
Supported color systems are as follows:

* Standard Reference Method (SRM)
* European Brewery Convention (EBC)
* Lovibond

```clj
(:require [brewtility.color :refer :all])

(lovibond->rgba 0.56)
;; => "rgba(255,230,153,1)"

(srm->rgba 6.2)
;; => "rgba(248,166,0,1)"

(ebc->rgba 25.61)
;; => "rgba(203,98,0,1)"
```

### Precision

#### Approximation

Returns true iff `n2` approximates `n1` within `variance` percent.

```clj
(:require [brewtility.precision :refer :all])

(approximates? 100 90 0.1)
;; => true

(approximates? 100 90 0.01)
;; => false
```

#### Decimal Place Rounding

Given a number, round it to a set number of places after the decimal point.

```clj
(:require [brewtility.precision :refer :all])

(->1dp 5.05)
;; => 5.1

(->2dp -100.005)
;; => -100.01

(->3dp 404.0004654)
;; => 404.0
```

### Units

#### Volume Conversion

Given a `volume` in `source-measurement`, convert it to the `target-measurement`.
Supported measurements are:

* Teaspoon
* Tablespoon
* Imperial Fluid Ounce
* American Fluid Ounce
* Cup
* Imperial Pint
* American Pint
* Imperial Quart
* American Quart
* Imperial Gallon
* American Gallon
* Litre (Or Liter, depending on regional convention)
* Millilitre (Or Milliliter, depending on regional convention)

```clj
(:require [brewtility.units :refer :all])

(convert-volume 1.0 :liter :litre)
;; => 1.0

(convert-volume 20 :teaspoon :liter)
;; => 0.099

(convert-volume 9.99209 :imperial-pint :american-pint)
;; => 12.0
```

#### Weight Conversion

Given a `weight` in `source-measurement`, convert it to the `target-measurement`.
Supported measurements are:

* Ounce
* Pound
* Milligram
* Gram
* Kilogram

```clj
(:require [brewtility.units :refer :all])

(convert-weight 1.0 :kilogram :kilogram)
;; => 1.0

(convert-weight 15.0 :pound :ounce)
;; => 240.0

(convert-weight 1205.5 :milligram :pound)
;; => 0.003
```

#### Temperature Conversion

Given a `temperature` in `source-measurement`, convert it to the `target-measurement`.
Supported measurements are:

* Celsius (Or Centigrade, depending on regional convention. Or C, for convenience)
* Kelvin (Or K, for convenience)
* Fahrenheit (Or F, for convenience)

```clj
(:require [brewtility.units :refer :all])

(convert-temperature 32.0 :fahrenheit :fahrenheit)
;; => 32.0

(convert-temperature 0.0 :c :f)
;; => 32.0

(convert-temperature 2016.3 :kelvin :c)
;; => 1743.15
```

## Testing

[doo](https://github.com/bensu/doo), a Leiningen plugin used to run ClojureScript tests in many JS environments, is already in `project.clj`.
[Karma](https://karma-runner.github.io/latest/index.html) is used as the test runner, and is included in `package.json`.

To install Karma, simply install the Node package:

```bash
npm install
```

Then build the application and run the tests:

```bash
lein test-build
```

The tests will also execute on the JVM, to ensure the library is compatible for apps in both deployment environments.

## License

Copyright © 2020 - [Wall Brew Co](https://wallbrew.com/)

This software is provided for free, public use as outlined in the [MIT License](https://github.com/Wall-Brew-Co/brewtility/blob/master/LICENSE)
