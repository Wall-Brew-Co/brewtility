(ns brewtility.static
  "A namespace of static symbolic keywords and values used in the option maps throughout brewtility.
   
   Implemented as part of the Symbolic Keyword pattern"
  {:added "1.3"}
  (:refer-clojure :exclude [short]))


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
