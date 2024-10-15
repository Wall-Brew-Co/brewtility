# Predicates

While working with beer data, it is frequently necessary to control program logic differently for different types of ingredients.
For example, calculating the final color of a beer depends on the color of the ingredients in the beer.
Both in the BeerXML spec, and in industry conventions, malted grains and other fermentables have their colors measured in separate systems.
For that reason, we would need a function to be able to determine if a `common-beer-format` fermentable is a grain or not.

These functions are unique to each `common-beer-format` type, which are individual namespaces in the `brewtility.predicates.*` set.
Each function expects a record of the appropriate type, and returns a boolean value.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Equipment](#equipment)
- [Fermentables](#fermentables)
- [Hops](#hops)
- [Mash](#mash)
- [Misc](#misc)
- [Recipes](#recipes)
- [Styles](#styles)
- [Waters](#waters)
- [Yeasts](#yeasts)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Equipment

Equipment records currently have only one predicate:

- `calculated-boil-volume?`: Returns true if the equipment record's `:boil-size` was calculated. Returns false if the `:calc-boil-volume` key is absent or if the value was set by the user.

## Fermentables

Fermentable records currently support six predicates:

- `add-after-boil?`: Returns true if the fermentable should be added after the start of the boil. Returns false if the `:add-after-boil` key is absent or if the value was set by the user. This generally assumes the fermentable was part of the mash.
- `grain?`: Returns true if the fermentable's `:type` is "grain".
- `sugar?`: Returns true if the fermentable's `:type` is "sugar".
- `extract?`: Returns true if the fermentable's `:type` is "extract".
- `dry-extract?`: Returns true if the fermentable's `:type` is "dry extract".
- `adjunct?`: Returns true if the fermentable's `:type` is "adjunct".

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

## Mash

Top-level mash records currently have only one predicate:

- `adjust-for-equipment?`: Returns true if the mash profile should be adjusted for the equipment. Returns false if the `:equip-adjust` key is absent or if the value was set by the user.

Additionally, mash step records currently have three predicates:

- `infusion?`: Returns true if the mash step's `:type` is "infusion".
- `temperature?`: Returns true if the mash step's `:type` is "temperature".
- `decoction?`: Returns true if the mash step's `:type` is "decoction".

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

## Recipes

Recipe records currently have seven predicates in three categories:

- Predicates against optional fields:
  - `forced-carbonation?`: Returns true if the recipe was forcefully carbonated, rather than naturally carbonated as a byproduct of additional fermentation. Returns false if the `:forced-carbonation` key is absent or if the value was set by the user.
- Predicates against a recipe's `:type`:
  - `extract?`: Returns true if the recipe's `:type` is "extract".
  - `partial-mash?`: Returns true if the recipe's `:type` is "partial mash".
  - `all-grain?`: Returns true if the recipe's `:type` is "all grain".
- Predicates against a recipe's `:ibu-method`:
  - `rager?`: Returns true if the recipe's `:ibu-method` is "rager".
  - `tinseth?`: Returns true if the recipe's `:ibu-method` is "tinseth".
  - `garetz?`: Returns true if the recipe's `:ibu-method` is "garetz".

## Styles

## Waters

## Yeasts
