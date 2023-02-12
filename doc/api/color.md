# Color

The color of a beer is derived from the color of the fermentables used in the mash.
Brewtility currently supports conversions between three color systems:

* [Standard Reference Method (SRM)](https://en.wikipedia.org/wiki/Standard_Reference_Method)
* [European Brewery Convention (EBC)](https://en.wikipedia.org/wiki/European_Brewery_Convention)
* [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour)

Additionally, brewtility maintains functionality to render these color systems as RGBa color codes.

## Color Unit Conversion

Given a color measurement in one of the three most common systems, convert between each system of measure.

```clj
(:require [brewtility.color :refer :all])

(lovibond->srm 7.94)
;; => 10.0

(srm->ebc 23.1)
;; => 45.51

(ebc->lovibond 60.38)
;; => 23.2
```

## Display Color Lookup

Given a color measurement in one of the three most common systems, look up a corresponding RGBa color.
These functions are opinionated, and support and normalize down to the most common SRM scale: 1-40.

```clj
(:require [brewtility.color :refer :all])

(lovibond->rgba 0.56)
;; => "rgba(255,230,153,1)"

(srm->rgba 6.2)
;; => "rgba(248,166,0,1)"

(ebc->rgba 25.61)
;; => "rgba(203,98,0,1)"
```
