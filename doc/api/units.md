# Units of Measure

Beer has become an international interest/
Additionally, brewing spans across the commercial and home environments.
As a result, the systems of measure and scales of measure can very greatly between brewers.
The BeerXML spec clearly delineates what units of measure are to be used to storing and transmitting data; however, they also allow users to pass `display` formats for values.
These are used to translate uniform, machine-friendly systems into more appropriate systems and scales based on geographic region and user case.

## Systems of Measure

Brewtility supports four systems of measure:

- [The British Imperial System](https://en.wikipedia.org/wiki/Imperial_units)
- [The Metric System](https://en.wikipedia.org/wiki/Metric_system)
- [The International System](https://en.wikipedia.org/wiki/International_System_of_Units)
- [The US Customary Scale](https://en.wikipedia.org/wiki/United_States_customary_units)

## Volume Conversion

Given a `volume` in `source-measurement`, convert it to the `target-measurement`.
Supported measurements are:

- Teaspoon
- Tablespoon
- Imperial Fluid Ounce
- American Fluid Ounce
- Cup
- Imperial Pint
- American Pint
- Imperial Quart
- American Quart
- Imperial Gallon
- American Gallon
- Litre (Or Liter, depending on regional convention)
- Millilitre (Or Milliliter, depending on regional convention)

```clj
(:require [brewtility.units :refer :all])

(convert-volume 1.0 :liter :litre)
;; => 1.0

(convert-volume 20 :teaspoon :liter)
;; => 0.099

(convert-volume 9.99209 :imperial-pint :american-pint)
;; => 12.0
```

## Weight Conversion

Given a `weight` in `source-measurement`, convert it to the `target-measurement`.
Supported measurements are:

- Ounce
- Pound
- Milligram
- Gram
- Kilogram

```clj
(:require [brewtility.units :refer :all])

(convert-weight 1.0 :kilogram :kilogram)
;; => 1.0

(convert-weight 15.0 :pound :ounce)
;; => 240.0

(convert-weight 1205.5 :milligram :pound)
;; => 0.003
```

## Temperature Conversion

Given a `temperature` in `source-measurement`, convert it to the `target-measurement`.
Supported measurements are:

- Celsius (Or Centigrade, depending on regional convention. Or C, for convenience)
- Kelvin (Or K, for convenience)
- Fahrenheit (Or F, for convenience)

```clj
(:require [brewtility.units :refer :all])

(convert-temperature 32.0 :fahrenheit :fahrenheit)
;; => 32.0

(convert-temperature 0.0 :c :f)
;; => 32.0

(convert-temperature 2016.3 :kelvin :c)
;; => 1743.15
```
