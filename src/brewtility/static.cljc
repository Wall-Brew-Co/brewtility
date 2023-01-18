(ns brewtility.static
  "A namespace of static symbolic keywords and values used in the option maps throughout brewtility.
   
   Implemented as part of the Symbolic Keyword pattern."
  {:added "1.3"}
  (:refer-clojure :exclude [short second]))


;; Defaults

(def ^:const default-precision
  "The default precision to use in `->displayable` functions"
  3)


;; Systems of Measure

(def ^:const system-of-measure
  "The system of measure used in the recipe or for a given unit.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Brewility supports the following systems of measure:
     - [British imperial](https://en.wikipedia.org/wiki/Imperial_units)
     - [Metric system](https://en.wikipedia.org/wiki/Metric_system)
     - [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units)
     - [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units)"
  :system-of-measure)


(def ^:const imperial
  "The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial)


(def ^:const metric
  "The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :metric)


(def ^:const us-customary
  "The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.."
  :us)


(def ^:const international-system
  "The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :si)


;; Enricher Setting Keys


(def ^:const precision
  "The number of decimal places to which a value is rounded.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :precision)


(def ^:const suffix
  "The type of suffix to add on to displayed units of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Brewility supports the following suffix types:
     - `:short` - A short suffix. (e.g. \"tsp\" instead of \"teaspoon\")
     - `:full` - The full name as a suffix (e.g. \"teaspoon\")"
  :suffix)


(def ^:const short
  "A short suffix. 
   (e.g. \"tsp\" instead of \"teaspoon\")
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :short)


(def ^:const full
  "The full name as a suffix.
   (e.g. \"teaspoon\")
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :full)


;; Color Systems

(def ^:const srm
  "The [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :srm)


(def ^:const ebc
  "The [European Brewery Convention](https://en.wikipedia.org/wiki/European_Brewery_Convention) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :ebc)


(def ^:const lovibond
  "The [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :lovibond)


(def ^:const rgba
  "The [RGBA](https://en.wikipedia.org/wiki/RGBA_color_space) color system.
   
   Commonly used with `brewtility.color` and in argument maps for enricher functions."
  :rgba)


;; Volume Units

(def ^:const american-fluid-ounce
  "The [American fluid ounce](https://en.wikipedia.org/wiki/Fluid_ounce#American_fluid_ounce) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-fluid-ounce)


(def ^:const american-gallon
  "The [American gallon](https://en.wikipedia.org/wiki/Gallon#Us_liquid_gallon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-gallon)


(def ^:const american-pint
  "The [American pint](https://en.wikipedia.org/wiki/Pint#US_liquid_pint) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-pint)


(def ^:const american-quart
  "The [American quart](https://en.wikipedia.org/wiki/Quart#US_liquid_quart) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :american-quart)


(def ^:const cup
  "The [cup](https://en.wikipedia.org/wiki/Cup_(unit)) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :cup)


(def ^:const imperial-fluid-ounce
  "The [Imperial fluid ounce](https://en.wikipedia.org/wiki/Fluid_ounce#Imperial_fluid_ounce) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-fluid-ounce)


(def ^:const imperial-gallon
  "The [Imperial gallon](https://en.wikipedia.org/wiki/Gallon#Imperial_gallon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-gallon)


(def ^:const imperial-pint
  "The [Imperial pint](https://en.wikipedia.org/wiki/Pint#Imperial_pint) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-pint)


(def ^:const imperial-quart
  "The [Imperial quart](https://en.wikipedia.org/wiki/Quart#Imperial_quart) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :imperial-quart)


(def ^:const liter
  "The [liter](https://en.wikipedia.org/wiki/Liter) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `litre` and `liter` are valid keys throughout brewtility to support regional spelling."
  :liter)


(def ^:const litre
  "The [liter](https://en.wikipedia.org/wiki/Litre) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `litre` and `liter` are valid keys throughout brewtility to support regional spelling."
  :litre)


(def ^:const milliliter
  "The [milliliter](https://en.wikipedia.org/wiki/Milliliter) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `millilitre` and `milliliter` are valid keys throughout brewtility to support regional spelling."
  :milliliter)


(def ^:const millilitre
  "The [milliliter](https://en.wikipedia.org/wiki/Millilitre) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: Both `millilitre` and `milliliter` are valid keys throughout brewtility to support regional spelling."
  :millilitre)


(def ^:const teaspoon
  "The [teaspoon](https://en.wikipedia.org/wiki/Teaspoon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :teaspoon)


(def ^:const tablespoon
  "The [tablespoon](https://en.wikipedia.org/wiki/Tablespoon) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :tablespoon)


;; Weight/Mass Units

(def ^:const gram
  "The [gram](https://en.wikipedia.org/wiki/Gram) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :gram)


(def ^:const kilogram
  "The [kilogram](https://en.wikipedia.org/wiki/Kilogram) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :kilogram)


(def ^:const milligram
  "The [milligram](https://en.wikipedia.org/wiki/Milligram) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :milligram)


(def ^:const ounce
  "The [ounce](https://en.wikipedia.org/wiki/Ounce#International_avoirdupois_ounce) unit of measure; specifically, the International avoirdupois ounce.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :ounce)


(def ^:const pound
  "The [pound](https://en.wikipedia.org/wiki/Pound_(mass)) unit of measure
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :pound)


;; Temperature Units

(def ^:const celsius
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :celsius)


(def ^:const c
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :c)


(def ^:const centigrade
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :centigrade)


(def ^:const fahrenheit
  "The [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `fahrenheit` and `f` are all valid keys throughout brewtility to support multiple conventions."
  :fahrenheit)


(def ^:const f
  "The [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `fahrenheit` and `f` are all valid keys throughout brewtility to support multiple conventions."
  :f)


(def ^:const kelvin
  "The [Kelvin](https://en.wikipedia.org/wiki/Kelvin) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `kelvin` and `k` are all valid keys throughout brewtility to support multiple conventions."
  :kelvin)


(def ^:const k
  "The [Kelvin](https://en.wikipedia.org/wiki/Kelvin) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions.
   Note: `kelvin` and `k` are all valid keys throughout brewtility to support multiple conventions."
  :k)


;; Time Units

(def ^:const day
  "The [day](https://en.wikipedia.org/wiki/Day) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :day)


(def ^:const hour
  "The [hour](https://en.wikipedia.org/wiki/Hour) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :hour)


(def ^:const microsecond
  "The [microsecond](https://en.wikipedia.org/wiki/Microsecond) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :microsecond)


(def ^:const millisecond
  "The [millisecond](https://en.wikipedia.org/wiki/Millisecond) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :millisecond)


(def ^:const minute
  "The [minute](https://en.wikipedia.org/wiki/Minute) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :minute)


(def ^:const nanosecond
  "The [nanosecond](https://en.wikipedia.org/wiki/Nanosecond) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :nanosecond)


(def ^:const second
  "The [second](https://en.wikipedia.org/wiki/Second) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :second)


(def ^:const week
  "The [week](https://en.wikipedia.org/wiki/Week) unit of measure.
   
   Commonly used with `brewtility.units` and in argument maps for enricher functions."
  :week)
