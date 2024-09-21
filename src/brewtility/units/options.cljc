(ns brewtility.units.options
  "A namespace of static symbolic keywords and values used in the option maps throughout brewtility.

   Implemented as part of the Symbolic Keyword pattern."
  {:added "2.0"}
  (:refer-clojure :exclude [second short time]))


;; Defaults

(def default-precision
  "The default precision to use in `->displayable` functions"
  3)


;; Systems of Measure

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}


(def system-of-measure
  "The system of measure used in the recipe or for a given unit.

   Commonly used with `brewtility.units` and in argument/option maps.
   Brewility supports the following systems of measure:
     - [British imperial](https://en.wikipedia.org/wiki/Imperial_units)
     - [Metric system](https://en.wikipedia.org/wiki/Metric_system)
     - [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units)
     - [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units)"
  :system-of-measure)


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}


(def imperial
  "The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :imperial)


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}


(def metric
  "The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :metric)


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}


(def us-customary
  "The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.

   Commonly used with `brewtility.units` and in argument/option maps.."
  :us)


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}


(def international-system
  "The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :si)


(def systems-of-measure
  "The set of supported measurement systems"
  #{imperial international-system metric us-customary})




;; Enricher Setting Keys


(def precision
  "The number of decimal places to which a value is rounded.

   Commonly used with `brewtility.units` and in argument/option maps."
  :precision)


(def suffix
  "The type of suffix to add on to displayed units of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Brewility supports the following suffix types:
     - `:short` - A short suffix. (e.g. \"tsp\" instead of \"teaspoon\")
     - `:full` - The full name as a suffix (e.g. \"teaspoon\")"
  :suffix)


(def short
  "A short suffix.
   (e.g. \"tsp\" instead of \"teaspoon\")

   Commonly used with `brewtility.units` and in argument/option maps."
  :short)


(def full
  "The full name as a suffix.
   (e.g. \"teaspoon\")

   Commonly used with `brewtility.units` and in argument/option maps."
  :full)


(def supported-suffixes
  "A set of supported suffix types."
  #{short full})


(def alcohol-content
  "The alcohol content systems used in the recipe.

   Commonly used with `brewtility.units.alcohol-content` and in argument/option maps.
   Currently, brewtility supports the following types of alcohol content measurements:
     - [abv](https://en.wikipedia.org/wiki/Alcohol_by_volume)"
  :alcohol-content)


(def bitterness
  "The bitterness systems used in the recipe.

   Commonly used with `brewtility.units.bitterness` and in argument/option maps.
   Currently, brewtility supports the following types of bitterness measurements:
     - [international-bitterness-units](https://en.wikipedia.org/wiki/International_bitterness_units)"
  :bitterness)


(def carbonation
  "The carbonation systems used in the recipe.

   Commonly used with `brewtility.units.carbonation` and in argument/option maps.
   Currently, brewtility supports the following types of carbonation measurements:
     - [volumes-of-co2](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages)
     - [grams-per-liter](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages)"
  :carbonation)




(def color
  "The color systems used in the recipe.

   Commonly used with `brewtility.units.color` and in argument/option maps.
   Brewility supports the following color systems:
     - [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method)
     - [European Brewery Convention](https://en.wikipedia.org/wiki/European_Brewery_Convention)
     - [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour)
     - [RGBA](https://en.wikipedia.org/wiki/RGBA_color_space)"
  :color)


(def pressure
  "The pressure systems used in the recipe.

   Commonly used with `brewtility.units.pressure` and in argument/option maps.
   Currently, brewtility supports the following types of pressure:
      - [pascal](https://en.wikipedia.org/wiki/Pascal_(unit)#Multiples_and_submultiples)
      - [kilopascal](https://en.wikipedia.org/wiki/Pascal_(unit)#Multiples_and_submultiples)
      - [bar](https://en.wikipedia.org/wiki/Bar_(unit))
      - [atmosphere](https://en.wikipedia.org/wiki/Atmosphere_(unit))
      - [torr](https://en.wikipedia.org/wiki/Torr)
      - [psi](https://en.wikipedia.org/wiki/Pound-force_per_square_inch"
  :pressure)


(def specific-gravity
  "The specific gravity systems used in the recipe.

   Commonly used with `brewtility.units.specific-gravity` and in argument/option maps.
   Currently, brewtility supports the following types of specific gravity:
   Currently, brewtility supports the following types of specific gravity:
     - [specific-gravity](https://en.wikipedia.org/wiki/Specific_gravity)"
  :specific-gravity)


(def temperature
  "The temperature systems used in the recipe.

   Commonly used with `brewtility.units.temperature` and in argument/option maps.
   Currently, brewtility supports the following types of temperature:
     - [celsius](https://en.wikipedia.org/wiki/Celsius)
     - [fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit)
     - [kelvin](https://en.wikipedia.org/wiki/Kelvin)"
  :temperature)


(def time
  "The time systems used in the recipe.

   Commonly used with `brewtility.units.time` and in argument/option maps.
   Currently, brewtility supports the following types of time measurements:
     - [microsecond](https://en.wikipedia.org/wiki/Microsecond)
     - [nanosecond](https://en.wikipedia.org/wiki/Nanosecond)
     - [millisecond](https://en.wikipedia.org/wiki/Millisecond)
     - [second](https://en.wikipedia.org/wiki/Second)
     - [minute](https://en.wikipedia.org/wiki/Minute)
     - [hour](https://en.wikipedia.org/wiki/Hour)
     - [day](https://en.wikipedia.org/wiki/Day)
     - [week](https://en.wikipedia.org/wiki/Week)"
  :time)


(def volume
  "The volume systems used in the recipe.

   Commonly used with `brewtility.units.volume` and in argument/option maps.
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
      - [teaspoon](https://en.wikipedia.org/wiki/Teaspoon))"
  :volume)


(def weight
  "The weight systems used in the recipe.

   Commonly used with `brewtility.units.weight` and in argument/option maps.
   Currently, brewtility supports the following types of weight:
      - [gram](https://en.wikipedia.org/wiki/Gram)
      - [milligram](https://en.wikipedia.org/wiki/Milligram)
      - [kilogram](https://en.wikipedia.org/wiki/Kilogram)
      - [ounce](https://en.wikipedia.org/wiki/Ounce)
      - [pound](https://en.wikipedia.org/wiki/Pound_(mass))"
  :weight)


(def measurement-types
  "The measurement types available across brewtility."
  #{alcohol-content
    bitterness
    carbonation
    color
    pressure
    specific-gravity
    temperature
    time
    volume
    weight})


;; Alcohol Content Systems
(def abv
  "The [Alcohol by Volume](https://en.wikipedia.org/wiki/Alcohol_by_volume) system of measure.

   Commonly used with `brewtility.units.alcohol-content` and in argument/option maps."
  :abv)


;; Bitterness Systems
(def ibu
  "The [International Bitterness Units](https://en.wikipedia.org/wiki/International_bitterness_units) system of measure.

   Commonly used with `brewtility.units.bitterness` and in argument/option maps."
  :ibu)


;; Carbonation Systems
(def volumes-of-co2
  "The [volumes of CO2](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages) system of measure.

   Commonly used with `brewtility.units.carbonation` and in argument/option maps."
  :volumes-of-co2)


(def grams-per-liter
  "The [grams per liter](https://en.wikipedia.org/wiki/Carbon_dioxide#Beverages) system of measure.

   Commonly used with `brewtility.units.carbonation` and in argument/option maps."
  :grams-per-liter)







;; Color Systems

(def srm
  "The [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) color system.

   Commonly used with `brewtility.units.color` and in argument/option maps."
  :srm)


(def ebc
  "The [European Brewery Convention](https://en.wikipedia.org/wiki/European_Brewery_Convention) color system.

   Commonly used with `brewtility.units.color` and in argument/option maps."
  :ebc)


(def lovibond
  "The [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) color system.

   Commonly used with `brewtility.units.color` and in argument/option maps."
  :lovibond)


(def rgba
  "The [RGBA](https://en.wikipedia.org/wiki/RGBA_color_space) color system.

   Commonly used with `brewtility.units.color` and in argument/option maps."
  :rgba)


;; Specific Gravity Units

(def plato
  "The [Degrees Plato](https://en.wikipedia.org/wiki/Plato_scale#Extract) system of measure.

   Commonly used with `brewtility.units.specific-gravity` and in argument/option maps."
  :plato)



;; Volume Units

(def american-fluid-ounce
  "The [American fluid ounce](https://en.wikipedia.org/wiki/Fluid_ounce#American_fluid_ounce) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :american-fluid-ounce)


(def american-gallon
  "The [American gallon](https://en.wikipedia.org/wiki/Gallon#Us_liquid_gallon) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :american-gallon)


(def american-pint
  "The [American pint](https://en.wikipedia.org/wiki/Pint#US_liquid_pint) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :american-pint)


(def american-quart
  "The [American quart](https://en.wikipedia.org/wiki/Quart#US_liquid_quart) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :american-quart)


(def cup
  "The [cup](https://en.wikipedia.org/wiki/Cup_(unit)) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :cup)


(def imperial-fluid-ounce
  "The [Imperial fluid ounce](https://en.wikipedia.org/wiki/Fluid_ounce#Imperial_fluid_ounce) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :imperial-fluid-ounce)


(def imperial-gallon
  "The [Imperial gallon](https://en.wikipedia.org/wiki/Gallon#Imperial_gallon) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :imperial-gallon)


(def imperial-pint
  "The [Imperial pint](https://en.wikipedia.org/wiki/Pint#Imperial_pint) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :imperial-pint)


(def imperial-quart
  "The [Imperial quart](https://en.wikipedia.org/wiki/Quart#Imperial_quart) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :imperial-quart)


(def liter
  "The [liter](https://en.wikipedia.org/wiki/Liter) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: Both `litre` and `liter` are valid keys throughout brewtility to support regional spelling."
  :liter)


(def litre
  "The [liter](https://en.wikipedia.org/wiki/Litre) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: Both `litre` and `liter` are valid keys throughout brewtility to support regional spelling."
  :litre)


(def milliliter
  "The [milliliter](https://en.wikipedia.org/wiki/Milliliter) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: Both `millilitre` and `milliliter` are valid keys throughout brewtility to support regional spelling."
  :milliliter)


(def millilitre
  "The [milliliter](https://en.wikipedia.org/wiki/Millilitre) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: Both `millilitre` and `milliliter` are valid keys throughout brewtility to support regional spelling."
  :millilitre)


(def teaspoon
  "The [teaspoon](https://en.wikipedia.org/wiki/Teaspoon) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :teaspoon)


(def tablespoon
  "The [tablespoon](https://en.wikipedia.org/wiki/Tablespoon) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :tablespoon)


;; Weight/Mass Units

(def gram
  "The [gram](https://en.wikipedia.org/wiki/Gram) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :gram)


(def kilogram
  "The [kilogram](https://en.wikipedia.org/wiki/Kilogram) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :kilogram)


(def milligram
  "The [milligram](https://en.wikipedia.org/wiki/Milligram) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :milligram)


(def ounce
  "The [ounce](https://en.wikipedia.org/wiki/Ounce#International_avoirdupois_ounce) unit of measure; specifically, the International avoirdupois ounce.

   Commonly used with `brewtility.units` and in argument/option maps."
  :ounce)


(def pound
  "The [pound](https://en.wikipedia.org/wiki/Pound_(mass)) unit of measure

   Commonly used with `brewtility.units` and in argument/option maps."
  :pound)


;; Temperature Units

(def celsius
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :celsius)


(def c
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :c)


(def centigrade
  "The [Celsius](https://en.wikipedia.org/wiki/Celsius) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `celsius`, `centigrade`, and `c` are all valid keys throughout brewtility to support multiple conventions."
  :centigrade)


(def fahrenheit
  "The [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `fahrenheit` and `f` are all valid keys throughout brewtility to support multiple conventions."
  :fahrenheit)


(def f
  "The [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `fahrenheit` and `f` are all valid keys throughout brewtility to support multiple conventions."
  :f)


(def kelvin
  "The [Kelvin](https://en.wikipedia.org/wiki/Kelvin) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `kelvin` and `k` are all valid keys throughout brewtility to support multiple conventions."
  :kelvin)


(def k
  "The [Kelvin](https://en.wikipedia.org/wiki/Kelvin) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps.
   Note: `kelvin` and `k` are all valid keys throughout brewtility to support multiple conventions."
  :k)


;; Time Units

(def day
  "The [day](https://en.wikipedia.org/wiki/Day) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :day)


(def hour
  "The [hour](https://en.wikipedia.org/wiki/Hour) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :hour)


(def microsecond
  "The [microsecond](https://en.wikipedia.org/wiki/Microsecond) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :microsecond)


(def millisecond
  "The [millisecond](https://en.wikipedia.org/wiki/Millisecond) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :millisecond)


(def minute
  "The [minute](https://en.wikipedia.org/wiki/Minute) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :minute)


(def nanosecond
  "The [nanosecond](https://en.wikipedia.org/wiki/Nanosecond) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :nanosecond)


(def second
  "The [second](https://en.wikipedia.org/wiki/Second) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :second)


(def week
  "The [week](https://en.wikipedia.org/wiki/Week) unit of measure.

   Commonly used with `brewtility.units` and in argument/option maps."
  :week)


;; Pressure Units

(def pascal
  "The [Pascal](https://en.wikipedia.org/wiki/Pascal_(unit)) unit of measure.

   Commonly used with `brewtility.units.pressure` and in argument maps."
  :pascal)


(def kilopascal
  "The [Kilopascal](https://en.wikipedia.org/wiki/Kilopascal) unit of measure.

   Commonly used with `brewtility.units.pressure` and in argument maps."
  :kilopascal)


(def bar
  "The [Bar](https://en.wikipedia.org/wiki/Bar_(unit)) unit of measure.

   Commonly used with `brewtility.units.pressure` and in argument maps."
  :bar)


(def atmosphere
  "The [Atmosphere](https://en.wikipedia.org/wiki/Atmosphere_(unit)) unit of measure.
   Specifically, this is a [standard atmosphere](https://en.wikipedia.org/wiki/Standard_atmosphere).

   Commonly used with `brewtility.units.pressure` and in argument maps."
  :atmosphere)


(def torr
  "The [Torr](https://en.wikipedia.org/wiki/Torr_(unit)) unit of measure.

   Commonly used with `brewtility.units.pressure` and in argument maps."
  :torr)


(def psi
  "The [Pounds per square inch](https://en.wikipedia.org/wiki/Pound-force_per_square_inch) unit of measure.

   Commonly used with `brewtility.units.pressure` and in argument maps."
  :psi)

