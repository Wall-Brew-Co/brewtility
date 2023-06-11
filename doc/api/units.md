# Units of Measure

Beer has become an international interest.
Additionally, brewing spans across the commercial and home environments.
As a result, the systems of measure and scales of measure can very greatly between brewers.
The BeerXML spec clearly delineates what units of measure are to be used to storing and transmitting data; however, they also allow users to pass `display` formats for values.
These are used to translate uniform, machine-friendly systems into more appropriate systems and scales based on geographic region and user case.

The `brewtility.units` namespace contains functions to convert between different systems and units of measure across several different types of measurement.
Additionally, this namespace may be used to render common display formats.

The names of these systems and units are frequently used in code, and shorthand symbolic references to these names may be found in `brewtility.units.options`

## Basic Use

The majority of the functionality belongs to the functions `convert` and `display`.
This provides a consistent interface to every system of measure, unit type, and more.

```clj
(:require [brewtility.units :as units]
          [brewtility.units.options :as options]
          [brewtility.units.volume :as volume])

;; You can use the keys in `brewtility.units.options` as arguments.
;; This guarantees you don't have typos in code, and can link to helpful documentation
(units/convert units/volume 1.0 options/liter options/litre)
;; => 1.0

;; Or, if you prefer, bare keywords are accepted too
(units/convert units/volume 20 :teaspoon :liter)
;; => 0.099

;; If you only plan on dealing with volumes,
;;   then you can import the `brewtility.units.volume` namespace
(volume/convert 9.99209 :imperial-pint :american-pint)
;; => 12.0

;; You can also render display values
(units/display :volume 1.5 :liter)
;; => \"1.5 l\"

;; The keys in `brewtility.units.options` are also acceptable
(units/display options/volume 1.5 options/liter)
;; => \"1.5 l\"

;; You may supply additional options, such as the default precision for rounding
;;   and the type of suffix to use
(units/display options/volume 1.45 options/liter {options/precision 1})
;; => \"1.5 l\"

(units/display options/volume 1.45 options/liter {options/suffix options/full})
;; => \"1.45 liter\"

;; Like `convert`, you can also call the measurement-type's functionality directly
(volume/display 1.45 options/liter {options/precision 1})
;; => \"1.5 l\"

;; And, of course, the symbolic keywords are ultimately plain keywords.
(volume/display 1.45 :liter {:suffix :full :precision 1})
;; => \"1.5 liter\"
```

## Supported Systems

Brewtility supports four systems of measure:

- [The British Imperial System](https://en.wikipedia.org/wiki/Imperial_units)
- [The Metric System](https://en.wikipedia.org/wiki/Metric_system)
- [The International System](https://en.wikipedia.org/wiki/International_System_of_Units)
- [The US Customary Scale](https://en.wikipedia.org/wiki/United_States_customary_units)

These are the most commonly seen systems in brewing.
There are measurement functions for the most common types of measurements within these systems:

- [Color](##color)
- [Pressure](##pressure)
- [Specific Gravity](##specific-gravity)
- [Temperature](##temperature)
- [Time](##time)
- [Volume](##volume)
- [Weight](##weight)

### Color

Currently, brewtility supports the following types of color:

- [SRM](https://en.wikipedia.org/wiki/Standard_Reference_Method)
- [EBC](https://en.wikipedia.org/wiki/European_Brewery_Convention)
- [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour)
- [RGBa](https://en.wikipedia.org/wiki/RGBA_color_model)

The `RGBa` system is special, as it can only be used as an argument for the result of a unit conversion.
Unfortunately, there is not a great deterministic way to cast the values back to the other systems.
brewtility will thrown an exception in this case and explain the problem.

### Pressure

Currently, brewtility supports the following types of pressure:

- [pascal](https://en.wikipedia.org/wiki/Pascal_(unit)#Multiples_and_submultiples)
- [kilopascal](https://en.wikipedia.org/wiki/Pascal_(unit)#Multiples_and_submultiples)
- [bar](https://en.wikipedia.org/wiki/Bar_(unit))
- [atmosphere](https://en.wikipedia.org/wiki/Atmosphere_(unit))
- [torr](https://en.wikipedia.org/wiki/Torr)
- [psi](<https://en.wikipedia.org/wiki/Pound-force_per_square_inch>

### Specific Gravity

Currently, brewtility supports the following types of specific gravity:

- [specific-gravity](https://en.wikipedia.org/wiki/Specific_gravity)

While there is currently only one system, the same namespace and functionality exists as the other measurement types.
This allows for progressive evolution, and provides a consistent interface to every measurement type encoded in the BeerXML specification.

### Temperature

Currently, brewtility supports the following types of temperature measurements:

- [celsius](https://en.wikipedia.org/wiki/Celsius)
- [fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit)
- [kelvin](https://en.wikipedia.org/wiki/Kelvin_(unit))

Given the prevalence of shorthand names in temperature measurements, brewtility also accepts `c`, `k`, and `f`.

### Time

Currently, brewtility supports the following types of time measurements:

- [microsecond](https://en.wikipedia.org/wiki/Microsecond)
- [nanosecond](https://en.wikipedia.org/wiki/Nanosecond)
- [millisecond](https://en.wikipedia.org/wiki/Millisecond)
- [second](https://en.wikipedia.org/wiki/Second)
- [minute](https://en.wikipedia.org/wiki/Minute)
- [hour](https://en.wikipedia.org/wiki/Hour)
- [day](https://en.wikipedia.org/wiki/Day)
- [week](https://en.wikipedia.org/wiki/Week)

### Volume

Currently, brewtility supports the following types of volume:

- [american-fluid-ounce](https://en.wikipedia.org/wiki/Fluid_ounce)
- [american-gallon](https://en.wikipedia.org/wiki/Gallon)
- [american-pint](https://en.wikipedia.org/wiki/Pint)
- [american-quart](https://en.wikipedia.org/wiki/Quart)
- [cup](https://en.wikipedia.org/wiki/Cup_(unit))
- [imperial-fluid-ounce](https://en.wikipedia.org/wiki/Fluid_ounce)
- [imperial-gallon](https://en.wikipedia.org/wiki/Gallon)
- [imperial-pint](https://en.wikipedia.org/wiki/Pint)
- [imperial-quart](https://en.wikipedia.org/wiki/Quart)
- [liter](https://en.wikipedia.org/wiki/Litre)
- [litre](https://en.wikipedia.org/wiki/Litre)
- [milliliter](https://en.wikipedia.org/wiki/Millilitre)
- [millilitre](https://en.wikipedia.org/wiki/Millilitre)
- [tablespoon](https://en.wikipedia.org/wiki/Tablespoon)
- [teaspoon](https://en.wikipedia.org/wiki/Teaspoon))

Given the prevalence of the French spellings in English recipes, both `:litre` and `:liter` can be passed as options.

### Weight

Currently, brewtility supports the following types of weight:

- [gram](https://en.wikipedia.org/wiki/Gram)
- [milligram](https://en.wikipedia.org/wiki/Milligram)
- [kilogram](https://en.wikipedia.org/wiki/Kilogram)
- [ounce](https://en.wikipedia.org/wiki/Ounce)
- [pound](https://en.wikipedia.org/wiki/Pound_(mass))
