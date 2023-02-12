# Precision

Since the BeerXML spec relies on double-precisions floating point numbers, the results of calculations lack absolute precisions.
Writing equality or boundary checks in these cases can be difficult, so functionality to approximate and round values was introduced into `brewtility.precision`

## Approximation

Returns true iff `n2` approximates `n1` within `variance` percent.

```clj
(:require [brewtility.precision :refer :all])

(approximates? 100 90 0.1)
;; => true

(approximates? 100 90 0.01)
;; => false
```

## Decimal Place Rounding

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
