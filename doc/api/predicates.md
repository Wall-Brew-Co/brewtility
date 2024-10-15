# Predicates

While working with beer data, it is frequently necessary to control program logic differently for different types of ingredients.
For example, calculating the final color of a beer depends on the color of the ingredients in the beer.
Both in the BeerXML spec, and in industry conventions, malted grains and other fermentables have their colors measured in separate systems.
For that reason, we would need a function to be able to determine if a `common-beer-format` fermentable is a grain or not.

These functions are unique to each `common-beer-format` type, which are individual namespaces in the `brewtility.predicates.*` set.
Each function expects a record of the appropriate type, and returns a boolean value.

## Equipment

Equipment records currently have only one predicate:

- `calculated-boil-volume?`: Returns true if the equipment record's `:boil-size` was calculated. Returns false if the `:calc-boil-volume` key is absent or if the value was set by the user.

```clj
(:require [brewtility.predicates.equipment :as equipment]
          [brewtility.data.equipment :as data])

(equipment/calculated-boil-volume? (:dissoc data/sample-equipment :calc-boil-volume))
;; => false
```

## Fermentables

Fermentable records currently support six predicates:

- `add-after-boil?`: Returns true if the fermentable should be added after the start of the boil. Returns false if the `:add-after-boil` key is absent or if the value was set by the user. This generally assumes the fermentable was part of the mash.
- `grain?`: Returns true if the fermentable's `:type` is "grain".
- `sugar?`: Returns true if the fermentable's `:type` is "sugar".
- `extract?`: Returns true if the fermentable's `:type` is "extract".
- `dry-extract?`: Returns true if the fermentable's `:type` is "dry extract".
- `adjunct?`: Returns true if the fermentable's `:type` is "adjunct".

```clj
(:require [brewtility.predicates.fermentables :as fermentables]
          [common-beer-data.core :as data])

(fermentables/add-after-boil? (:amber-malt data/all-fermentables))
;; => false

(fermentables/grain? (:amber-malt data/all-fermentables))
;; => true

(fermentables/sugar? (:amber-malt data/all-fermentables))
;; => false

(fermentables/extract? (:wheat-liquid-extract data/all-fermentables))
;; => true

(fermentables/dry-extract? (:wheat-liquid-extract data/all-fermentables))
;; => false

(fermentables/adjunct? (:rice-hulls data/all-fermentables))
;; => true
```

## Hops

Hop records currently support eleven predicates in three categories:

- Predicates against a hop's `:use`:
  - `boil?`: Returns true if the hop's `:use` is "boil".
  - `dry-hop?`: Returns true if the hop's `:use` is "dry hop".
  - `mash?`: Returns true if the hop's `:use` is "mash".
  - `first-wort?`: Returns true if the hop's `:use` is "first wort".
  - `aroma-use?`: Returns true if the hop's `:use` is "aroma". Note that this function includes the `-use?` suffix to avoid a naming conflict with the `aroma-type?` predicate.
- Predicates against a hop's `:type`:
  - `bittering?`: Returns true if the hop's `:type` is "bittering".
  - `aroma-type?`: Returns true if the hop's `:type` is "aroma". Note that this function includes the `-type?` suffix to avoid a naming conflict with the `aroma-use?` predicate.
  - `both?`: Returns true if the hop's `:type` is "both".
- Predicates against a hop's `:form`:
  - `pellet?`: Returns true if the hop's `:form` is "pellet".
  - `plug?`: Returns true if the hop's `:form` is "plug".
  - `leaf?`: Returns true if the hop's `:form` is "leaf".

```clj
(:require [brewtility.predicates.hops :as hops]
          [common-beer-data.core :as data])

(hops/boil? (:cascade data/all-hops))
;; => true

(hops/dry-hop? (:cascade data/all-hops))
;; => false

(hops/mash? (:cascade data/all-hops))
;; => false

(hops/first-wort? (:newport data/all-hops))
;; => false

(hops/aroma-use? (:newport data/all-hops))
;; => false

(hops/bittering? (:newport data/all-hops))
;; => true

(hops/aroma-type? (:simcoe data/all-hops))
;; => true

(hops/both? (:topaz data/all-hops))
;; => true

(hops/pellet? (:topaz data/all-hops))
;; => true

(hops/plug? (:topaz data/all-hops))
;; => false

(hops/leaf? (:topaz data/all-hops))
;; => false
```

## Mash

Top-level mash records currently have only one predicate:

- `adjust-for-equipment?`: Returns true if the mash profile should be adjusted for the equipment. Returns false if the `:equip-adjust` key is absent or if the value was set by the user.

Additionally, mash step records currently have three predicates:

- `infusion?`: Returns true if the mash step's `:type` is "infusion".
- `temperature?`: Returns true if the mash step's `:type` is "temperature".
- `decoction?`: Returns true if the mash step's `:type` is "decoction".

```clj
(:require [brewtility.predicates.mash :as mash]
          [brewtility.data.mash :as data])

(mash/adjust-for-equipment? (dissoc dats/sample-mash :equip-adjust))
;; => false

(mash/infusion? data/sample-mash-step)
;; => true

(mash/temperature? data/sample-mash-step)
;; => false

(mash/decoction? data/sample-mash-step)
;; => false
```

## Misc

Miscellaneous ingredient (misc) records currently support eleven predicates in two categories:

- Predicates against a misc's `:type`:
  - `spice?`: Returns true if the misc's `:type` is "spice".
  - `water-agent?`: Returns true if the misc's `:type` is "water agent".
  - `fining?`: Returns true if the misc's `:type` is "fining".
  - `herb?`: Returns true if the misc's `:type` is "herb".
  - `flavor?`: Returns true if the misc's `:type` is "flavor".
  - `other?`: Returns true if the misc's `:type` is "other".
- Predicates against a misc's `:use`:
  - `boil?`: Returns true if the misc's `:use` is "boil".
  - `mash?`: Returns true if the misc's `:use` is "mash".
  - `primary?`: Returns true if the misc's `:use` is "primary".
  - `secondary?`: Returns true if the misc's `:use` is "secondary".
  - `bottling?`: Returns true if the misc's `:use` is "bottling".

```clj
(:require [brewtility.predicates.misc :as misc]
          [brewtility.data.miscs :as data])

(misc/spice? data/sample-misc)
;; => false

(misc/water-agent? data/sample-misc)
;; => false

(misc/fining? data/sample-misc)
;; => true

(misc/herb? data/sample-misc)
;; => false

(misc/flavor? data/sample-misc)
;; => false

(misc/other? data/sample-misc)
;; => false

(misc/boil? data/sample-misc)
;; => true

(misc/mash? data/sample-misc)
;; => false

(misc/primary? data/sample-misc)
;; => false

(misc/secondary? data/sample-misc)
;; => false

(misc/bottling? data/sample-misc)
;; => false
```

## Recipes
