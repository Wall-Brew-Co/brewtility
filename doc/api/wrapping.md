# Wrapping

The BeerXML specification wraps ingredients, processes, etc. into XML nodes indicating their type.
In the case of a hop, this looks like:

```xml
<HOP>
    <NAME>Goldings, East Kent</NAME>
    <VERSION>1</VERSION>
    <ALPHA>5.0</ALPHA>
    <AMOUNT>0.0638</AMOUNT>
    <USE>Boil</USE>
    <TIME>60.0</TIME>
    <NOTES>Great all purpose UK hop for ales, stouts, porters</NOTES>
</HOP>
```

When parsed into a clojure data structure that adheres to the `common-beer-format` specification, the resulting map looks like this:

```clj
{:hop 
  {:name    "Goldings, East Kent"
   :version "1"
   :alpha   "5.0"
   :amount  "0.0638"
   :use     "Boil"
   :time    "60.0"
   :notes   "Great all purpose UK hop for ales, stouts, porters"}}
```

This nesting often leads to code that either needs to remove or add the "wrapping" `:hop` map.
Brewtility contains functions for both sides of this operation in the `brewtility.wrapping` namespace.

```clj
(:require [brewtility.wrapping :refer :all])

(unwrap-hop 
  {:hop 
    {:name    "Goldings, East Kent"
     :version "1"
     :alpha   "5.0"
     :amount  "0.0638"
     :use     "Boil"
     :time    "60.0"
     :notes   "Great all purpose UK hop for ales, stouts, porters"}})
; => {:name    "Goldings, East Kent"
;     :version "1"
;     :alpha   "5.0"
;     :amount  "0.0638"
;     :use     "Boil"
;     :time    "60.0"
;     :notes   "Great all purpose UK hop for ales, stouts, porters"}

(wrap-hop
  {:name    "Goldings, East Kent"
   :version "1"
   :alpha   "5.0"
   :amount  "0.0638"
   :use     "Boil"
   :time    "60.0"
   :notes   "Great all purpose UK hop for ales, stouts, porters"})
; =>   {:hop 
;        {:name    "Goldings, East Kent"
;        :version "1"
;        :alpha   "5.0"
;        :amount  "0.0638"
;        :use     "Boil"
;        :time    "60.0"
;        :notes   "Great all purpose UK hop for ales, stouts, porters"}}
```
