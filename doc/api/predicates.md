# Predicates

While working with beer data, it is frequently necessary to control program logic differently for different types of ingredient.
For example, calculating the final color of a beer depends on the color of the ingredients in the beer.
Both in the BeerXML spec, and in industry conventions, malted grains and other fermentables have their colors measured in separate systems.
For this reason, we need a function to be able to determine if a `common-beer-format` fermentable is a grain or not.

These functions are unique to each `common-beer-format` type, which are individual namespaces in the `brewtility.predicates.*` set.
