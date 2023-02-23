(ns brewtility.units.options
  "A namespace of static symbolic keywords and values used in the option maps throughout brewtility.
   
   Implemented as part of the Symbolic Keyword pattern."
  {:added "2.0"}
  (:refer-clojure :exclude [short second]))

;; Defaults

(def default-precision
  "The default precision to use in `->displayable` functions"
  3)


;; Systems of Measure

(def system-of-measure
  "The system of measure used in the recipe or for a given unit.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Brewility supports the following systems of measure:
     - [British imperial](https://en.wikipedia.org/wiki/Imperial_units)
     - [Metric system](https://en.wikipedia.org/wiki/Metric_system)
     - [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units)
     - [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units)"
  :system-of-measure)


(def imperial
  "The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial)


(def metric
  "The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :metric)


(def us-customary
  "The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.."
  :us)


(def international-system
  "The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :si)


;; Enricher Setting Keys


(def precision
  "The number of decimal places to which a value is rounded.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :precision)


(def suffix
  "The type of suffix to add on to displayed units of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Brewility supports the following suffix types:
     - `:short` - A short suffix. (e.g. \"tsp\" instead of \"teaspoon\")
     - `:full` - The full name as a suffix (e.g. \"teaspoon\")"
  :suffix)


(def short
  "A short suffix. 
   (e.g. \"tsp\" instead of \"teaspoon\")
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :short)


(def full
  "The full name as a suffix.
   (e.g. \"teaspoon\")
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :full)


(def supported-suffixes
  "A set of supported suffix types."
  #{short full})


;; Color Systems

(def srm
  "The [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :srm)


(def ebc
  "The [European Brewery Convention](https://en.wikipedia.org/wiki/European_Brewery_Convention) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :ebc)


(def lovibond
  "The [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :lovibond)


(def rgba
  "The [RGBA](https://en.wikipedia.org/wiki/RGBA_color_space) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :rgba)


;; Volume Units

(def american-fluid-ounce
  "The [American fluid ounce](https://en.wikipedia.org/wiki/Fluid_ounce#American_fluid_ounce) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-fluid-ounce)


(def american-gallon
  "The [American gallon](https://en.wikipedia.org/wiki/Gallon#Us_liquid_gallon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-gallon)


(def american-pint
  "The [American pint](https://en.wikipedia.org/wiki/Pint#US_liquid_pint) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-pint)


(def american-quart
  "The [American quart](https://en.wikipedia.org/wiki/Quart#US_liquid_quart) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-quart)


(def cup
  "The [cup](https://en.wikipedia.org/wiki/Cup_(unit)) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :cup)


(def imperial-fluid-ounce
  "The [Imperial fluid ounce](https://en.wikipedia.org/wiki/Fluid_ounce#Imperial_fluid_ounce) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-fluid-ounce)


(def imperial-gallon
  "The [Imperial gallon](https://en.wikipedia.org/wiki/Gallon#Imperial_gallon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-gallon)


(def imperial-pint
  "The [Imperial pint](https://en.wikipedia.org/wiki/Pint#Imperial_pint) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-pint)


(def imperial-quart
  "The [Imperial quart](https://en.wikipedia.org/wiki/Quart#Imperial_quart) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-quart)


(def liter
  "The [liter](https://en.wikipedia.org/wiki/Liter) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `litre` and `liter` are valid keys throughout brewtility to support regional spelling."
  :liter)


(def litre
  "The [liter](https://en.wikipedia.org/wiki/Litre) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `litre` and `liter` are valid keys throughout brewtility to support regional spelling."
  :litre)


(def milliliter
  "The [milliliter](https://en.wikipedia.org/wiki/Milliliter) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `millilitre` and `milliliter` are valid keys throughout brewtility to support regional spelling."
  :milliliter)


(def millilitre
  "The [milliliter](https://en.wikipedia.org/wiki/Millilitre) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `millilitre` and `milliliter` are valid keys throughout brewtility to support regional spelling."
  :millilitre)


(def teaspoon
  "The [teaspoon](https://en.wikipedia.org/wiki/Teaspoon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :teaspoon)


(def tablespoon
  "The [tablespoon](https://en.wikipedia.org/wiki/Tablespoon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :tablespoon)


;; Weight/Mass Units

(def gram
  "The [gram](https://en.wikipedia.org/wiki/Gram) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :gram)


(def kilogram
  "The [kilogram](https://en.wikipedia.org/wiki/Kilogram) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :kilogram)


(def milligram
  "The [milligram](https://en.wikipedia.org/wiki/Milligram) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :milligram)


(def ounce
  "The [ounce](https://en.wikipedia.org/wiki/Ounce#International_avoirdupois_ounce) unit of measure; specifically, the International avoirdupois ounce.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :ounce)


(def pound
  "The [pound](https://en.wikipedia.org/wiki/Pound_(mass)) unit of measure
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :pound)


;; Temperature Units

(def celsius
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :celsius)


(def c
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :c)


(def centigrade
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :centigrade)


(def fahrenheit
  "The [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `fahrenheit` and `f` are all valid keys throughout brewtility to support multiple conventions."
  :fahrenheit)


(def f
  "The [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `fahrenheit` and `f` are all valid keys throughout brewtility to support multiple conventions."
  :f)


(def kelvin
  "The [Kelvin](https://en.wikipedia.org/wiki/Kelvin) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `kelvin` and `k` are all valid keys throughout brewtility to support multiple conventions."
  :kelvin)


(def k
  "The [Kelvin](https://en.wikipedia.org/wiki/Kelvin) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `kelvin` and `k` are all valid keys throughout brewtility to support multiple conventions."
  :k)


;; Time Units

(def day
  "The [day](https://en.wikipedia.org/wiki/Day) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :day)


(def hour
  "The [hour](https://en.wikipedia.org/wiki/Hour) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :hour)


(def microsecond
  "The [microsecond](https://en.wikipedia.org/wiki/Microsecond) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :microsecond)


(def millisecond
  "The [millisecond](https://en.wikipedia.org/wiki/Millisecond) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :millisecond)


(def minute
  "The [minute](https://en.wikipedia.org/wiki/Minute) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :minute)


(def nanosecond
  "The [nanosecond](https://en.wikipedia.org/wiki/Nanosecond) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :nanosecond)


(def second
  "The [second](https://en.wikipedia.org/wiki/Second) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :second)


(def week
  "The [week](https://en.wikipedia.org/wiki/Week) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :week)
