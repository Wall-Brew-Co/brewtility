# Enricher Pattern

In [BeerXML](http://www.beerxml.com/ "The XML standard to encode beer data"), and, by extension, [common-beer-format](https://github.com/Wall-Brew-Co/common-beer-format "A clojure library/spec for BeerXML"), there are specifications which ultimately represent and encode the same concept.
For a concrete example, consider a sample [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc "The specification for a piece of brewing equipment") record.

```clj
{:name              "8 Gal pot with 5 gal Igloo Cooler"
 :boil-size         22.71
 :trub-chiller-loss 0.95
 :boil-time         60.0
 :version           1
 :tun-specific-heat 0.3
 :batch-size        18.93
 :display-boil-size "6.0 US Gallons"}
```

In the above, you can find two representations of the pre-boil volume that piece of equipment held.
By the standard, the `:boil-size` is standardized to be the volume in litres, and the `:display-boil-size` is meant to represent that value in a region and human friendly way.
This is great for users; however, it can be unwieldy in applications for a few to store and transmit data which may only be used in the display layer.
Programmatically, we are often more interested in a smaller subset of the equipment record and will defer computing display values until they are needed.

While the unit conversion is simple, it can often lead to front-end code like this:

```clj
(defn display-equipment
  [equipment system-of-measure]
  [:span
    [:h1 (:name equipment)]
    [:ul
      [:li (str "Boil volume: " (->display (convert-units (:boil-size equipment) system-of-measure)))]
      (when (:tun-weight equipment)
        [:li (str "Mash Tun Weight: " (->display (convert-units (:tun-weight equipment) system-of-measure)))])
      ...
    ]])
```

Helper functions may be extracted, but the above code would certainly benefit from the `:display-boil-size` and `:display-tun-weight` fields which may optionally exist on the `equipment` record.
As a utility library for beer data, brewtility comes with the functionality needed to compute these display fields; however, keeping them as loose datums isn't fully desireable either.
In the contexts we want display data available for an equipment record, it's much easier to provide that data with the equipment record.
To that end, this library implements an enrichment pattern.

## Enrichers

An enrichment function is a function from maps to maps, which non-destructively adds to the source value derived information.
For example, if we know the user's location, we could infer wether Metric or Imperial measurements would be more appropriate to display.
With that information, we could leverage an enricher to modify the prior equipment example:

```clj
(def igloo-cooler
  {:name              "8 Gal pot with 5 gal Igloo Cooler"
   :boil-size         22.71
   :trub-chiller-loss 0.95
   :boil-time         60.0
   :version           1
   :tun-specific-heat 0.3
   :batch-size        18.93})

(require '[brewtility.enrich.equipment :as enrich])

(enrich/enrich-display-boil-size igloo-cooler {:system-of-measure :imperial})
;; => {:name              "8 Gal pot with 5 gal Igloo Cooler"
;;     :boil-size         22.71
;;     :trub-chiller-loss 0.95
;;     :boil-time         60.0
;;     :version           1
;;     :tun-specific-heat 0.3
;;     :batch-size        18.93
;;     :display-boil-size "6.0 US Gallons"}
;;
```

If we wanted to calculate multiple display values in serial, we can also perform a best-effort enrichment of the entire record.

```clj
(enrich/enrich-equipment igloo-cooler {:system-of-measure :imperial})
;; => {:name                      "8 Gal pot with 5 gal Igloo Cooler"
;;     :boil-size                 22.71
;;     :trub-chiller-loss         0.95
;;     :boil-time                 60.0
;;     :version                   1
;;     :tun-specific-heat         0.3
;;     :batch-size                18.93
;;     :display-boil-size         "6.0 US Gallons"
;;     :display-trub-chiller-loss "0.251 US Gallons"
;;     :display-batch-size        "5.0 US Gallons"}
;;
```

Or, in our example front-end:

```clj
(defn fetch-equipment!
  [equipment-id]
  (-> (http/get (str some-url "/equipment/" equipment-id))
    :body
    json/decode
    enrich/enrich-equipment {:system-of-measure :imperial})))

(defn display-equipment
  [equipment]
  [:span
    [:h1 (:name equipment)]
    [:ul
      [:li (str "Boil volume: " (:display-boil-size equipment))))]
      (when (:display-tun-weight equipment)
        [:li (str "Mash Tun Weight: " (display-tun-weight equipment))])
      ...
    ]])

(defn fetch-and-display-equipment!
  [equipment-id]
  (-> equipment-id fetch-equipment! display-equipment))
```

In the above, we're able to calculate display values for the boil size, batch size, and trub chiller loss since the data sources for that information.
While the equipment record does specify display values for the tun's volume, the source of that data is missing- and therefore excluded.

## Implementations

Enricher functions exist for each of the core specifications supported by BeerXML and common-beer-format.

- [Equipment](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/equipment.cljc "Equipment enrichers")
- [Fermentables](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/fermentables.cljc "Fermentable enrichers")
- [Hops](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/hops.cljc "Hop enrichers")
- [Mash](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/mash.cljc "Mash enrichers")
- [Miscs](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/miscs.cljc "Misc enrichers")
- [Recipes](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/recipes.cljc "Recipe enrichers")
- [Styles](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/styles.cljc "Style enrichers")
- [Waters](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/waters.cljc "Water enrichers")
- [yeasts](https://github.com/Wall-Brew-Co/brewtility/blob/master/src/brewtility/enrich/yeasts.cljc "Yeast enrichers")

## Inspiration

- [Maybe Not - Rich Hickey](https://www.youtube.com/watch?v=YR5WdGrpoug&ab_channel=ClojureTV "A great presentation on the types of problems type systems try to solve")
