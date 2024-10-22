# Predicates

While working with beer data, it is frequently necessary to control program logic differently for different types of ingredients.
For example, calculating the final color of a beer depends on the color of the ingredients in the beer.
Both in the BeerXML spec, and in industry conventions, malted grains and other fermentables have their colors measured in separate systems.
For that reason, we would need a function to be able to determine if a `common-beer-format` fermentable is a grain or not.

These functions are unique to each `common-beer-format` type, which are individual namespaces in the `brewtility.predicates.*` set.
Each function expects a record of the appropriate type, and returns a boolean value.
In the case no conclusion can be drawn (for example, determining if a water profile is acidic without a pH value), the function will throw an Exception indicating the missing data.

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

Style records currently support six predicates in one category:

- Predicates against the `:type` field:
  - `ale?`: Returns true if the style's `:type` is "ale".
  - `lager?`: Returns true if the style's `:type` is "lager".
  - `mead?`: Returns true if the style's `:type` is "mead".
  - `cider?`: Returns true if the style's `:type` is "cider".
  - `wine?`: Returns true if the style's `:type` is "wine".
  - `mixed?`: Returns true if the style's `:type` is "mixed".

## Waters

Water records currently support three predicates in one category:

- Predicates against the `:ph` field:
  - `acidic?`: Returns true if the water's `:ph` is less than 7.
  - `neutral?`: Returns true if the water's `:ph` is equal to 7.
  - `alkaline?`: Returns true if the water's `:ph` is greater than 7.

## Yeasts

Yeast records currently support thirteen predicates in three categories:

- Predicates against the `:type` field:
  - `ale?`: Returns true if the yeast's `:type` is "ale".
  - `lager?`: Returns true if the yeast's `:type` is "lager".
  - `wheat?`: Returns true if the yeast's `:type` is "wheat".
  - `wine?`: Returns true if the yeast's `:type` is "wine".
  - `champagne?`: Returns true if the yeast's `:type` is "champagne".
- Predicates against the `:form` field:
  - `liquid?`: Returns true if the yeast's `:form` is "liquid".
  - `dry?`: Returns true if the yeast's `:form` is "dry".
  - `slant?`: Returns true if the yeast's `:form` is "slant".
  - `culture?`: Returns true if the yeast's `:form` is "culture".
- Predicates against the `:flocculation` field:
  - `low-flocculation?`: Returns true if the yeast's `:flocculation` is "low".
  - `medium-flocculation?`: Returns true if the yeast's `:flocculation` is "medium".
  - `high-flocculation?`: Returns true if the yeast's `:flocculation` is "high".
  -` very-high-flocculation?`: Returns true if the yeast's `:flocculation` is "very high".
