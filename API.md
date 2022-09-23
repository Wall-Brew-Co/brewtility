# Table of contents
-  [`brewtility.calculations`](#brewtility.calculations)  - Namespace for handling recipe calculations.
    -  [`calculate-alpha-acid-units`](#brewtility.calculations/calculate-alpha-acid-units) - Calculate the maximum amount of alpha acid released by <code>weight</code> ounce of a hop at <code>percent</code> alpha acid.
    -  [`calculate-ebc-color`](#brewtility.calculations/calculate-ebc-color) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, return the EBC color for a recipe.
    -  [`calculate-equipment-boil-volume`](#brewtility.calculations/calculate-equipment-boil-volume) - Given a <code>common-beer-format</code> conforming <code>equipment</code>, calculate the volume of the wort at the start of the boil.
    -  [`calculate-hop-utilization`](#brewtility.calculations/calculate-hop-utilization) - Calculate the percentage of alpha acid that a hop could release over <code>boil-duration</code> in a wort at a specific <code>gravity</code> Based on: http://howtobrew.com/book/section-1/hops/hop-bittering-calculations.
    -  [`calculate-ibu-per-hop`](#brewtility.calculations/calculate-ibu-per-hop) - Given a <code>common-beer-format</code> conforming <code>hop</code>, <code>batch-size</code>, and <code>potential-gravity</code>, calculate the amount of IBUs generated.
    -  [`calculate-lovibond-color`](#brewtility.calculations/calculate-lovibond-color) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, return the color in degrees Lovibond for a recipe.
    -  [`calculate-malt-color-units`](#brewtility.calculations/calculate-malt-color-units) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, return the overall Malt Color Units for a recipe.
    -  [`calculate-potential-abv`](#brewtility.calculations/calculate-potential-abv) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, estimate the ABV.
    -  [`calculate-potential-final-gravity`](#brewtility.calculations/calculate-potential-final-gravity) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, estimate the Final Gravity.
    -  [`calculate-potential-gravity`](#brewtility.calculations/calculate-potential-gravity) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, calculate the potential original gravity.
    -  [`calculate-recipe-ibus`](#brewtility.calculations/calculate-recipe-ibus) - Given a collection of <code>common-beer-format</code> conforming <code>hops</code>, <code>batch-size</code>, and <code>potential-gravity</code> calculate the amount of IBUs generated.
    -  [`calculate-rgba-color`](#brewtility.calculations/calculate-rgba-color) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, return the RGBA color for a recipe.
    -  [`calculate-srm-color`](#brewtility.calculations/calculate-srm-color) - Given a collection of <code>common-beer-format</code> conforming <code>fermentables</code>, and a conformed <code>batch-size</code> in liters, return the SRM color for a recipe.
    -  [`default-attenuation`](#brewtility.calculations/default-attenuation) - A constant backup attenuation for calculations.
    -  [`gravity->abv-multiplier`](#brewtility.calculations/gravity->abv-multiplier) - The constanct factor used to calculate ABV from potential gravity.
    -  [`gravity-points->potential-gravity`](#brewtility.calculations/gravity-points->potential-gravity) - Given the <code>gravity-points</code> of a recipe, and the <code>volume</code> of the batch, calculate the potential gravity.
    -  [`potential-gravity->gravity-points`](#brewtility.calculations/potential-gravity->gravity-points) - Given the <code>potential-gravity</code> of a fermentable, and the <code>weight</code> of the fermentable, calculate gravity points.
-  [`brewtility.color`](#brewtility.color)  - Namespace for calculating beer colors.
    -  [`color-system->display-name`](#brewtility.color/color-system->display-name) - A map from color system names to their full and short unit names.
    -  [`color-systems`](#brewtility.color/color-systems) - The color systems available across brewtility.
    -  [`ebc->lovibond`](#brewtility.color/ebc->lovibond) - Convert the color described in 'ebc` to the equivalent degrees Lovibond.
    -  [`ebc->rgba`](#brewtility.color/ebc->rgba) - Given <code>ebc-number</code>, return the closest, bounded applicable RGBA color string.
    -  [`ebc->srm`](#brewtility.color/ebc->srm) - Convert the color described by the <code>ebc</code> to the equivalent SRM color.
    -  [`lovibond->ebc`](#brewtility.color/lovibond->ebc) - Convert the color described in degrees 'lovibond` to the equivalent EBC color.
    -  [`lovibond->rgba`](#brewtility.color/lovibond->rgba) - Given <code>lovibond-number</code>, return the closest, bounded applicable RGBA color string.
    -  [`lovibond->srm`](#brewtility.color/lovibond->srm) - Convert the color described in degrees <code>lovibond</code> to the equivalent SRM color.
    -  [`srm-1`](#brewtility.color/srm-1) - An SRM of 1 mapped to an RGBa color code.
    -  [`srm-10`](#brewtility.color/srm-10) - An SRM of 10 mapped to an RGBa color code.
    -  [`srm-11`](#brewtility.color/srm-11) - An SRM of 11 mapped to an RGBa color code.
    -  [`srm-12`](#brewtility.color/srm-12) - An SRM of 12 mapped to an RGBa color code.
    -  [`srm-13`](#brewtility.color/srm-13) - An SRM of 13 mapped to an RGBa color code.
    -  [`srm-14`](#brewtility.color/srm-14) - An SRM of 14 mapped to an RGBa color code.
    -  [`srm-15`](#brewtility.color/srm-15) - An SRM of 15 mapped to an RGBa color code.
    -  [`srm-16`](#brewtility.color/srm-16) - An SRM of 16 mapped to an RGBa color code.
    -  [`srm-17`](#brewtility.color/srm-17) - An SRM of 17 mapped to an RGBa color code.
    -  [`srm-18`](#brewtility.color/srm-18) - An SRM of 18 mapped to an RGBa color code.
    -  [`srm-19`](#brewtility.color/srm-19) - An SRM of 19 mapped to an RGBa color code.
    -  [`srm-2`](#brewtility.color/srm-2) - An SRM of 2 mapped to an RGBa color code.
    -  [`srm-20`](#brewtility.color/srm-20) - An SRM of 20 mapped to an RGBa color code.
    -  [`srm-21`](#brewtility.color/srm-21) - An SRM of 21 mapped to an RGBa color code.
    -  [`srm-22`](#brewtility.color/srm-22) - An SRM of 22 mapped to an RGBa color code.
    -  [`srm-23`](#brewtility.color/srm-23) - An SRM of 23 mapped to an RGBa color code.
    -  [`srm-24`](#brewtility.color/srm-24) - An SRM of 24 mapped to an RGBa color code.
    -  [`srm-25`](#brewtility.color/srm-25) - An SRM of 25 mapped to an RGBa color code.
    -  [`srm-26`](#brewtility.color/srm-26) - An SRM of 26 mapped to an RGBa color code.
    -  [`srm-27`](#brewtility.color/srm-27) - An SRM of 27 mapped to an RGBa color code.
    -  [`srm-28`](#brewtility.color/srm-28) - An SRM of 28 mapped to an RGBa color code.
    -  [`srm-29`](#brewtility.color/srm-29) - An SRM of 29 mapped to an RGBa color code.
    -  [`srm-3`](#brewtility.color/srm-3) - An SRM of 3 mapped to an RGBa color code.
    -  [`srm-30`](#brewtility.color/srm-30) - An SRM of 30 mapped to an RGBa color code.
    -  [`srm-31`](#brewtility.color/srm-31) - An SRM of 31 mapped to an RGBa color code.
    -  [`srm-32`](#brewtility.color/srm-32) - An SRM of 32 mapped to an RGBa color code.
    -  [`srm-33`](#brewtility.color/srm-33) - An SRM of 33 mapped to an RGBa color code.
    -  [`srm-34`](#brewtility.color/srm-34) - An SRM of 34 mapped to an RGBa color code.
    -  [`srm-35`](#brewtility.color/srm-35) - An SRM of 35 mapped to an RGBa color code.
    -  [`srm-36`](#brewtility.color/srm-36) - An SRM of 36 mapped to an RGBa color code.
    -  [`srm-37`](#brewtility.color/srm-37) - An SRM of 37 mapped to an RGBa color code.
    -  [`srm-38`](#brewtility.color/srm-38) - An SRM of 38 mapped to an RGBa color code.
    -  [`srm-39`](#brewtility.color/srm-39) - An SRM of 39 mapped to an RGBa color code.
    -  [`srm-4`](#brewtility.color/srm-4) - An SRM of 4 mapped to an RGBa color code.
    -  [`srm-40`](#brewtility.color/srm-40) - An SRM of 40 mapped to an RGBa color code.
    -  [`srm-5`](#brewtility.color/srm-5) - An SRM of 5 mapped to an RGBa color code.
    -  [`srm-6`](#brewtility.color/srm-6) - An SRM of 6 mapped to an RGBa color code.
    -  [`srm-7`](#brewtility.color/srm-7) - An SRM of 7 mapped to an RGBa color code.
    -  [`srm-8`](#brewtility.color/srm-8) - An SRM of 8 mapped to an RGBa color code.
    -  [`srm-9`](#brewtility.color/srm-9) - An SRM of 9 mapped to an RGBa color code.
    -  [`srm->ebc`](#brewtility.color/srm->ebc) - Convert the color described by the <code>srm</code> to the equivalent EBC color.
    -  [`srm->lovibond`](#brewtility.color/srm->lovibond) - Convert the color described in 'srm` to the equivalent degrees Lovibond.
    -  [`srm->rgba`](#brewtility.color/srm->rgba) - Given <code>srm-number</code>, return the closest, bounded applicable RGBA color string.
    -  [`srm-color-map`](#brewtility.color/srm-color-map) - A map of integer values to their closest SRM value as an RGBa color code.
-  [`brewtility.enrich.equipment`](#brewtility.enrich.equipment)  - Enricher-pattern functions for [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) maps.
    -  [`enrich-calculated-boil-size`](#brewtility.enrich.equipment/enrich-calculated-boil-size) - An enricher pattern function to calculate the boil size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record via [[brewtility.calculations/calculate-equipment-boil-volume]].
    -  [`enrich-display-batch-size`](#brewtility.enrich.equipment/enrich-display-batch-size) - An enricher pattern function to add the displayable batch size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-boil-size`](#brewtility.enrich.equipment/enrich-display-boil-size) - An enricher pattern function to add the displayable boil size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-lauter-deadspace`](#brewtility.enrich.equipment/enrich-display-lauter-deadspace) - An enricher pattern function to add the displayable lauter deadspace to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-top-up-kettle`](#brewtility.enrich.equipment/enrich-display-top-up-kettle) - An enricher pattern function to add the displayable top up kettle to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-top-up-water`](#brewtility.enrich.equipment/enrich-display-top-up-water) - An enricher pattern function to add the displayable top up water to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-trub-chiller-loss`](#brewtility.enrich.equipment/enrich-display-trub-chiller-loss) - An enricher pattern function to add the displayable trub chiller loss to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-tun-volume`](#brewtility.enrich.equipment/enrich-display-tun-volume) - An enricher pattern function to add the displayable tun volume to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-display-tun-weight`](#brewtility.enrich.equipment/enrich-display-tun-weight) - An enricher pattern function to add the displayable tun weight to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.
    -  [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) - An enricher pattern function to derive as many values from an [equipment record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc).
    -  [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper) - An enricher pattern function to derive as many values from an [equipment-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc).
-  [`brewtility.enrich.fermentables`](#brewtility.enrich.fermentables)  - Enricher-pattern functions for [fermentables](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) maps.
    -  [`enrich-add-after-boil`](#brewtility.enrich.fermentables/enrich-add-after-boil) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) was added after the boil.
    -  [`enrich-coarse-fine-diff`](#brewtility.enrich.fermentables/enrich-coarse-fine-diff) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a coarse/fine differential.
    -  [`enrich-diastatic-power`](#brewtility.enrich.fermentables/enrich-diastatic-power) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a listed Diastatic Power.
    -  [`enrich-display-amount`](#brewtility.enrich.fermentables/enrich-display-amount) - An enricher pattern function to render a human-readable display weight of a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is in a given system.
    -  [`enrich-display-color`](#brewtility.enrich.fermentables/enrich-display-color) - An enricher pattern function to determine what color a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is in a given system.
    -  [`enrich-fermentable`](#brewtility.enrich.fermentables/enrich-fermentable) - An enricher pattern function to derive as many values from an [fermentable record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).
    -  [`enrich-fermentable-wrapper`](#brewtility.enrich.fermentables/enrich-fermentable-wrapper) - An enricher pattern function to derive as many values from an [fermentable-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).
    -  [`enrich-fermentables`](#brewtility.enrich.fermentables/enrich-fermentables) - An enricher pattern function to derive as many values from an [fermentables record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).
    -  [`enrich-fermentables-wrapper`](#brewtility.enrich.fermentables/enrich-fermentables-wrapper) - An enricher pattern function to derive as many values from an [fermentables-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).
    -  [`enrich-ibu-gallons-per-pound`](#brewtility.enrich.fermentables/enrich-ibu-gallons-per-pound) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have ibu-gal-per-lb.
    -  [`enrich-moisture`](#brewtility.enrich.fermentables/enrich-moisture) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a moisture content.
    -  [`enrich-protein`](#brewtility.enrich.fermentables/enrich-protein) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have protein.
    -  [`enrich-recommend-mash`](#brewtility.enrich.fermentables/enrich-recommend-mash) - An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have recommend-mash.
-  [`brewtility.enrich.hops`](#brewtility.enrich.hops)  - Enricher-pattern functions for [hops](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) maps.
    -  [`enrich-display-amount`](#brewtility.enrich.hops/enrich-display-amount) - An enricher pattern function to render a human-readable display weight of a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) is in a given system.
    -  [`enrich-display-time`](#brewtility.enrich.hops/enrich-display-time) - An enricher pattern function to render a human-readable display time of a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) is in a given system.
    -  [`enrich-hop`](#brewtility.enrich.hops/enrich-hop) - An enricher pattern function to derive as many values from an [hop record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).
    -  [`enrich-hop-wrapper`](#brewtility.enrich.hops/enrich-hop-wrapper) - An enricher pattern function to derive as many values from an [hop-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).
    -  [`enrich-hops`](#brewtility.enrich.hops/enrich-hops) - An enricher pattern function to derive as many values from an [hops record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).
    -  [`enrich-hops-wrapper`](#brewtility.enrich.hops/enrich-hops-wrapper) - An enricher pattern function to derive as many values from an [hops-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).
-  [`brewtility.enrich.mash`](#brewtility.enrich.mash)  - Enricher-pattern functions for [mashes and mash steps](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps.
    -  [`enrich-display-grain-temperature`](#brewtility.enrich.mash/enrich-display-grain-temperature) - An enricher pattern function to render a human-readable temperature of the grain temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.
    -  [`enrich-display-infuse-amount`](#brewtility.enrich.mash/enrich-display-infuse-amount) - An enricher pattern function to render a human-readable infuse amount of a [mash step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.
    -  [`enrich-display-sparge-temperature`](#brewtility.enrich.mash/enrich-display-sparge-temperature) - An enricher pattern function to render a human-readable temperature of the sparge temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.
    -  [`enrich-display-step-temperature`](#brewtility.enrich.mash/enrich-display-step-temperature) - An enricher pattern function to render a human-readable display temperature of a [mash step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.
    -  [`enrich-display-tun-temperature`](#brewtility.enrich.mash/enrich-display-tun-temperature) - An enricher pattern function to render a human-readable temperature of the tun temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.
    -  [`enrich-display-tun-weight`](#brewtility.enrich.mash/enrich-display-tun-weight) - An enricher pattern function to render a human-readable weight of the tun weight during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.
    -  [`enrich-mash`](#brewtility.enrich.mash/enrich-mash) - An enricher pattern function to derive as many values from an [mash record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).
    -  [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) - An enricher pattern function to derive as many values from an [mash-step record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).
    -  [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper) - An enricher pattern function to derive as many values from an [mash-step wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).
    -  [`enrich-mash-steps`](#brewtility.enrich.mash/enrich-mash-steps) - An enricher pattern function to derive as many values from a collection of [mash-step wrapper records](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).
    -  [`enrich-mash-steps-wrapper`](#brewtility.enrich.mash/enrich-mash-steps-wrapper) - An enricher pattern function to derive as many values from a collection of [mash-steps wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).
    -  [`enrich-mash-wrapper`](#brewtility.enrich.mash/enrich-mash-wrapper) - An enricher pattern function to derive as many values from an [mash wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).
-  [`brewtility.enrich.miscs`](#brewtility.enrich.miscs)  - Enricher-pattern functions for [miscs](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscss.cljc) maps.
    -  [`enrich-amount-is-weight`](#brewtility.enrich.miscs/enrich-amount-is-weight) - An enricher pattern function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) should be measured by weight/volume.
    -  [`enrich-display-amount`](#brewtility.enrich.miscs/enrich-display-amount) - An enricher pattern function to render a human-readable display time of a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is in a given system.
    -  [`enrich-display-time`](#brewtility.enrich.miscs/enrich-display-time) - An enricher pattern function to render a human-readable display time of a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is in a given system.
    -  [`enrich-misc`](#brewtility.enrich.miscs/enrich-misc) - An enricher pattern function to derive as many values from an [misc record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).
    -  [`enrich-misc-wrapper`](#brewtility.enrich.miscs/enrich-misc-wrapper) - An enricher pattern function to derive as many values from an [misc-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).
    -  [`enrich-miscs`](#brewtility.enrich.miscs/enrich-miscs) - An enricher pattern function to derive as many values from an [miscs record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).
    -  [`enrich-miscs-wrapper`](#brewtility.enrich.miscs/enrich-miscs-wrapper) - An enricher pattern function to derive as many values from an [miscs-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).
-  [`brewtility.precision`](#brewtility.precision)  - Namespace for handling numeric precision.
    -  [`->1dp`](#brewtility.precision/->1dp) - Given a decimal <code>x</code>, returns that number rounded to one decimal place.
    -  [`->2dp`](#brewtility.precision/->2dp) - Given a decimal <code>x</code>, returns that number rounded to two decimal places.
    -  [`->3dp`](#brewtility.precision/->3dp) - Given a decimal <code>x</code>, returns that number rounded to three decimal places.
    -  [`->precision`](#brewtility.precision/->precision) - Given a decimal <code>x</code> and the number of decimal places, returns that number rounded to <code>num-decimals</code> precision.
    -  [`approximates?`](#brewtility.precision/approximates?) - Determine if <code>n2</code> approximates <code>n1</code> within <code>variance</code> percent.
-  [`brewtility.predicates.equipment`](#brewtility.predicates.equipment)  - Predicate functions for [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) maps.
    -  [`calculated-boil-volume?`](#brewtility.predicates.equipment/calculated-boil-volume?) - A predicate function to determine if an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) had its boil volume calculated.
-  [`brewtility.predicates.fermentables`](#brewtility.predicates.fermentables)  - Predicate functions for [fermentables](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) maps.
    -  [`add-after-boil?`](#brewtility.predicates.fermentables/add-after-boil?) - A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) was added after the start of the boil.
    -  [`adjunct?`](#brewtility.predicates.fermentables/adjunct?) - A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is an adjunct, non-standard fermentable.
    -  [`dry-extract?`](#brewtility.predicates.fermentables/dry-extract?) - A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is dried malt extract.
    -  [`extract?`](#brewtility.predicates.fermentables/extract?) - A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is a liquid malt extract.
    -  [`grain?`](#brewtility.predicates.fermentables/grain?) - A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is whole grain.
    -  [`sugar?`](#brewtility.predicates.fermentables/sugar?) - A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is a raw sugar.
-  [`brewtility.predicates.hops`](#brewtility.predicates.hops)  - Predicate functions for [hops](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) maps.
    -  [`aroma-type?`](#brewtility.predicates.hops/aroma-type?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for aroma.
    -  [`aroma-use?`](#brewtility.predicates.hops/aroma-use?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added at the end of the boil for aroma.
    -  [`bittering?`](#brewtility.predicates.hops/bittering?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for bittering.
    -  [`boil?`](#brewtility.predicates.hops/boil?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was used during the boil.
    -  [`both?`](#brewtility.predicates.hops/both?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for both bittering and aroma.
    -  [`dry-hop?`](#brewtility.predicates.hops/dry-hop?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was used as a dry-hop addition in secondary fermentation.
    -  [`first-wort?`](#brewtility.predicates.hops/first-wort?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added to the wort at the first possible moment.
    -  [`leaf?`](#brewtility.predicates.hops/leaf?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a whole hop cone.
    -  [`mash?`](#brewtility.predicates.hops/mash?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added during the mash and before the boil.
    -  [`pellet?`](#brewtility.predicates.hops/pellet?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a compressed pellet.
    -  [`plug?`](#brewtility.predicates.hops/plug?) - A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a compressed whole-hop plug.
-  [`brewtility.predicates.mash`](#brewtility.predicates.mash)  - Predicate functions for [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps.
    -  [`adjust-for-equipment?`](#brewtility.predicates.mash/adjust-for-equipment?) - A predicate function to determine if a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) should account for any temperature effects of the equipment used.
    -  [`decoction?`](#brewtility.predicates.mash/decoction?) - A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used to extract and concentrate flavor.
    -  [`infusion?`](#brewtility.predicates.mash/infusion?) - A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used as a basic infusion.
    -  [`temperature?`](#brewtility.predicates.mash/temperature?) - A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used to bring wort up to temperature.
-  [`brewtility.predicates.miscs`](#brewtility.predicates.miscs)  - Predicate functions for [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) maps.
    -  [`boil?`](#brewtility.predicates.miscs/boil?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the boil.
    -  [`bottling?`](#brewtility.predicates.miscs/bottling?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during bottle conditioning.
    -  [`fining?`](#brewtility.predicates.miscs/fining?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is used to remove yeast and protein haze.
    -  [`flavor?`](#brewtility.predicates.miscs/flavor?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is added for flavor.
    -  [`herb?`](#brewtility.predicates.miscs/herb?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a herb.
    -  [`mash?`](#brewtility.predicates.miscs/mash?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the mash.
    -  [`other?`](#brewtility.predicates.miscs/other?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is an unspecified type.
    -  [`primary?`](#brewtility.predicates.miscs/primary?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the primary fermentation.
    -  [`secondary?`](#brewtility.predicates.miscs/secondary?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the secondary fermentation.
    -  [`spice?`](#brewtility.predicates.miscs/spice?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a spice.
    -  [`water-agent?`](#brewtility.predicates.miscs/water-agent?) - A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a water agent.
-  [`brewtility.predicates.recipes`](#brewtility.predicates.recipes)  - Predicate functions for [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) maps.
    -  [`all-grain?`](#brewtility.predicates.recipes/all-grain?) - A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was made entirely of grain.
    -  [`extract?`](#brewtility.predicates.recipes/extract?) - A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was made with extract only.
    -  [`forced-carbonation?`](#brewtility.predicates.recipes/forced-carbonation?) - A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was forcefully carbonated.
    -  [`garetz?`](#brewtility.predicates.recipes/garetz?) - A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the garetz method.
    -  [`partial-mash?`](#brewtility.predicates.recipes/partial-mash?) - A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was partially mashed.
    -  [`rager?`](#brewtility.predicates.recipes/rager?) - A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the Rager method.
    -  [`tinseth?`](#brewtility.predicates.recipes/tinseth?) - A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the tinseth method.
-  [`brewtility.predicates.styles`](#brewtility.predicates.styles)  - Predicate functions for [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) maps.
    -  [`ale?`](#brewtility.predicates.styles/ale?) - A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for ales.
    -  [`cider?`](#brewtility.predicates.styles/cider?) - A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for ciders.
    -  [`lager?`](#brewtility.predicates.styles/lager?) - A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for lagers.
    -  [`mead?`](#brewtility.predicates.styles/mead?) - A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for meads.
    -  [`mixed?`](#brewtility.predicates.styles/mixed?) - A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for blended/mixed beers.
    -  [`wheat?`](#brewtility.predicates.styles/wheat?) - A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for wheat beers.
-  [`brewtility.predicates.waters`](#brewtility.predicates.waters)  - Predicate functions for [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) maps.
    -  [`acidic?`](#brewtility.predicates.waters/acidic?) - A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is acidic.
    -  [`alkaline?`](#brewtility.predicates.waters/alkaline?) - A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is alkaline.
    -  [`neutral?`](#brewtility.predicates.waters/neutral?) - A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is alkaline.
-  [`brewtility.predicates.yeasts`](#brewtility.predicates.yeasts)  - Predicate functions for [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) maps.
    -  [`ale?`](#brewtility.predicates.yeasts/ale?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for ale.
    -  [`champagne?`](#brewtility.predicates.yeasts/champagne?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a common champagne strain.
    -  [`culture?`](#brewtility.predicates.yeasts/culture?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a living culture.
    -  [`dry?`](#brewtility.predicates.yeasts/dry?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in dry form.
    -  [`high-flocculation?`](#brewtility.predicates.yeasts/high-flocculation?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a high amount of floc.
    -  [`lager?`](#brewtility.predicates.yeasts/lager?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for lager.
    -  [`liquid?`](#brewtility.predicates.yeasts/liquid?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in liquid form.
    -  [`low-flocculation?`](#brewtility.predicates.yeasts/low-flocculation?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a low amount of floc.
    -  [`medium-flocculation?`](#brewtility.predicates.yeasts/medium-flocculation?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a medium amount of floc.
    -  [`slant?`](#brewtility.predicates.yeasts/slant?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in slant form.
    -  [`very-high-flocculation?`](#brewtility.predicates.yeasts/very-high-flocculation?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a very high amount of floc.
    -  [`wheat?`](#brewtility.predicates.yeasts/wheat?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for wheat beers.
    -  [`wine?`](#brewtility.predicates.yeasts/wine?) - A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a common wine strain.
-  [`brewtility.string`](#brewtility.string)  - String comparison utilities.
    -  [`->spongebob-case`](#brewtility.string/->spongebob-case) - Take a string <code>s</code> and coerce characters alternatively between lower and upper case.
    -  [`->sporadic-case`](#brewtility.string/->sporadic-case) - Take a string <code>s</code> and randomly coerce characters to either lower or upper case.
    -  [`includes?`](#brewtility.string/includes?) - Checks to see if <code>s1</code> includes <code>s2</code> after each string has been modified by <code>prepare-for-compare</code>.
    -  [`prepare-for-compare`](#brewtility.string/prepare-for-compare) - Takes a string <code>s</code>, trims it, and coerces it to lower case.
    -  [`same?`](#brewtility.string/same?) - Checks to see if <code>s1</code> and <code>s2</code> are equal after each string has been modified by <code>prepare-for-compare</code>.
-  [`brewtility.units`](#brewtility.units)  - Namespace for converting between different units of measure.
    -  [`celsius->temperature-measurement`](#brewtility.units/celsius->temperature-measurement) - A map from measurement names to the implementation function which converts them from degrees celsius.
    -  [`convert-temperature`](#brewtility.units/convert-temperature) - Given a <code>temperature</code> in <code>source-measurement</code>, convert it to the <code>target-measurement</code>.
    -  [`convert-time`](#brewtility.units/convert-time) - Given a <code>time-val</code> in <code>source-measurement</code>, convert it to the <code>target-measurement</code>.
    -  [`convert-volume`](#brewtility.units/convert-volume) - Given a <code>volume</code> in <code>source-measurement</code>, convert it to the <code>target-measurement</code>.
    -  [`convert-weight`](#brewtility.units/convert-weight) - Given a <code>weight</code> in <code>source-measurement</code>, convert it to the <code>target-measurement</code>.
    -  [`suffix-types`](#brewtility.units/suffix-types) - The unit suffix types available across brewtility.
    -  [`systems-of-meaure`](#brewtility.units/systems-of-meaure) - The systems of measure available across brewtility.
    -  [`temperature-measurement->celsius`](#brewtility.units/temperature-measurement->celsius) - A map from measurement names to the implementation function which converts them to degrees celsius.
    -  [`temperature-measurements`](#brewtility.units/temperature-measurements) - The temperature measurements available across brewtility.
    -  [`temperature-measurements->display-name`](#brewtility.units/temperature-measurements->display-name) - A map from measurement names to their full name and abbreviation.
    -  [`time-measurement->minute`](#brewtility.units/time-measurement->minute) - A map from measurement names to doubles representing their fractional value to one minute.
    -  [`time-measurements`](#brewtility.units/time-measurements) - The time measurements available across brewtility.
    -  [`time-measurements->display-name`](#brewtility.units/time-measurements->display-name) - A map from measurement names to their full name and abbreviation.
    -  [`volume-measurement->litre`](#brewtility.units/volume-measurement->litre) - A map from measurement names to doubles representing their fractional value to one liter.
    -  [`volume-measurements`](#brewtility.units/volume-measurements) - The volume measurements available across brewtility.
    -  [`volume-measurements->display-name`](#brewtility.units/volume-measurements->display-name) - A map from measurement names to their full name and abbreviation.
    -  [`weight-measurement->kilogram`](#brewtility.units/weight-measurement->kilogram) - A map from measurement names to doubles representing their fractional value to one kilogram.
    -  [`weight-measurements`](#brewtility.units/weight-measurements) - The weight measurements available across brewtility.
    -  [`weight-measurements->display-name`](#brewtility.units/weight-measurements->display-name) - A map from measurement names to their full name and abbreviation.
-  [`brewtility.wrapping`](#brewtility.wrapping)  - Namespace for wrapping and unwrapping common-beer-format maps.
    -  [`unwrap-equipment`](#brewtility.wrapping/unwrap-equipment) - Unwrap an <code>equipment-wrapper</code> map into an <code>equipment</code> map.
    -  [`unwrap-fermentable`](#brewtility.wrapping/unwrap-fermentable) - Unwrap a <code>fermentable-wrapper</code> map into a <code>fermentable</code> map.
    -  [`unwrap-fermentables`](#brewtility.wrapping/unwrap-fermentables) - Unwrap a <code>fermentables-wrapper</code> map into a <code>fermentables</code> collection.
    -  [`unwrap-hop`](#brewtility.wrapping/unwrap-hop) - Unwrap a <code>hop-wrapper</code> map into a <code>hop</code> map.
    -  [`unwrap-hops`](#brewtility.wrapping/unwrap-hops) - Unwrap a <code>hops-wrapper</code> map into a <code>hops</code> collection.
    -  [`unwrap-mash`](#brewtility.wrapping/unwrap-mash) - Unwrap a <code>mash-wrapper</code> map into a <code>mash</code> map.
    -  [`unwrap-mash-step`](#brewtility.wrapping/unwrap-mash-step) - Unwrap a <code>mash-step-wrapper</code> map into a <code>mash-step</code> map.
    -  [`unwrap-mash-steps`](#brewtility.wrapping/unwrap-mash-steps) - Unwrap a <code>mash-steps-wrapper</code> map into a <code>mash-steps</code> collection.
    -  [`unwrap-misc`](#brewtility.wrapping/unwrap-misc) - Unwrap a <code>misc-wrapper</code> map into a <code>misc</code> map.
    -  [`unwrap-miscs`](#brewtility.wrapping/unwrap-miscs) - Unwrap a <code>miscs-wrapper</code> map into a <code>miscs</code> collection.
    -  [`unwrap-recipe`](#brewtility.wrapping/unwrap-recipe) - Unwrap a <code>recipe-wrapper</code> map into a <code>recipe</code> map.
    -  [`unwrap-recipes`](#brewtility.wrapping/unwrap-recipes) - Unwrap a <code>recipes-wrapper</code> map into a <code>recipes</code> collection.
    -  [`unwrap-style`](#brewtility.wrapping/unwrap-style) - Unwrap a <code>style-wrapper</code> map into a <code>style</code> map.
    -  [`unwrap-styles`](#brewtility.wrapping/unwrap-styles) - Unwrap a <code>styles-wrapper</code> map into a <code>styles</code> collection.
    -  [`unwrap-water`](#brewtility.wrapping/unwrap-water) - Unwrap a <code>water-wrapper</code> map into a <code>water</code> map.
    -  [`unwrap-waters`](#brewtility.wrapping/unwrap-waters) - Unwrap a <code>waters-wrapper</code> map into a <code>waters</code> collection.
    -  [`unwrap-yeast`](#brewtility.wrapping/unwrap-yeast) - Unwrap a <code>yeast-wrapper</code> map into a <code>yeast</code> map.
    -  [`unwrap-yeasts`](#brewtility.wrapping/unwrap-yeasts) - Unwrap a <code>yeasts-wrapper</code> map into a <code>yeasts</code> collection.
    -  [`wrap-equipment`](#brewtility.wrapping/wrap-equipment) - Wrap an <code>equipment</code> map into an <code>equipment-wrapper</code> map.
    -  [`wrap-fermentable`](#brewtility.wrapping/wrap-fermentable) - Wrap a <code>fermentable</code> map into a <code>fermentable-wrapper</code> map.
    -  [`wrap-fermentables`](#brewtility.wrapping/wrap-fermentables) - Wrap a <code>fermentables</code> collection into a <code>fermentables-wrapper</code> map.
    -  [`wrap-hop`](#brewtility.wrapping/wrap-hop) - Wrap a <code>hop</code> map into a <code>hop-wrapper</code> map.
    -  [`wrap-hops`](#brewtility.wrapping/wrap-hops) - Wrap a <code>hops</code> collection into a <code>hops-wrapper</code> map.
    -  [`wrap-mash`](#brewtility.wrapping/wrap-mash) - Wrap a <code>mash</code> map into a <code>mash-wrapper</code> map.
    -  [`wrap-mash-step`](#brewtility.wrapping/wrap-mash-step) - Wrap a <code>mash-step</code> map into a <code>mash-step-wrapper</code> map.
    -  [`wrap-mash-steps`](#brewtility.wrapping/wrap-mash-steps) - Wrap a <code>mash-steps</code> collection into a <code>mash-steps-wrapper</code> map.
    -  [`wrap-misc`](#brewtility.wrapping/wrap-misc) - Wrap a <code>misc</code> map into a <code>misc-wrapper</code> map.
    -  [`wrap-miscs`](#brewtility.wrapping/wrap-miscs) - Wrap a <code>miscs</code> collection into a <code>miscs-wrapper</code> map.
    -  [`wrap-recipe`](#brewtility.wrapping/wrap-recipe) - Wrap a <code>recipe</code> map into a <code>recipe-wrapper</code> map.
    -  [`wrap-recipes`](#brewtility.wrapping/wrap-recipes) - Wrap a <code>recipes</code> collection into a <code>recipes-wrapper</code> map.
    -  [`wrap-style`](#brewtility.wrapping/wrap-style) - Wrap a <code>style</code> map into a <code>style-wrapper</code> map.
    -  [`wrap-styles`](#brewtility.wrapping/wrap-styles) - Wrap a <code>styles</code> collection into a <code>styles-wrapper</code> map.
    -  [`wrap-water`](#brewtility.wrapping/wrap-water) - Wrap a <code>water</code> map into a <code>water-wrapper</code> map.
    -  [`wrap-waters`](#brewtility.wrapping/wrap-waters) - Wrap a <code>waters</code> collection into a <code>waters-wrapper</code> map.
    -  [`wrap-yeast`](#brewtility.wrapping/wrap-yeast) - Wrap a <code>yeast</code> map into a <code>yeast-wrapper</code> map.
    -  [`wrap-yeasts`](#brewtility.wrapping/wrap-yeasts) - Wrap a <code>yeasts</code> collection into a <code>yeasts-wrapper</code> map.

-----
# <a name="brewtility.calculations">brewtility.calculations</a>


Namespace for handling recipe calculations.
   This namespace assumes ingredients that conform to the [common-beer-format](https://github.com/Wall-Brew-Co/common-beer-format).




## <a name="brewtility.calculations/calculate-alpha-acid-units">`calculate-alpha-acid-units`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-alpha-acid-units"></a>
``` clojure

(calculate-alpha-acid-units weight alpha)
```


Calculate the maximum amount of alpha acid released by `weight` ounce of a hop at `percent` alpha acid

## <a name="brewtility.calculations/calculate-ebc-color">`calculate-ebc-color`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-ebc-color"></a>

Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the EBC color for a recipe

## <a name="brewtility.calculations/calculate-equipment-boil-volume">`calculate-equipment-boil-volume`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-equipment-boil-volume"></a>
``` clojure

(calculate-equipment-boil-volume {:keys [batch-size top-up-water trub-chiller-loss boil-time evap-rate]})
```


Given a `common-beer-format` conforming `equipment`, calculate the volume of the wort at the start of the boil.
   If insufficient data is provided, this function will throw an exception.

## <a name="brewtility.calculations/calculate-hop-utilization">`calculate-hop-utilization`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-hop-utilization"></a>
``` clojure

(calculate-hop-utilization gravity boil-duration)
```


Calculate the percentage of alpha acid that a hop could release over `boil-duration` in a wort at a specific `gravity`
   Based on: http://howtobrew.com/book/section-1/hops/hop-bittering-calculations

## <a name="brewtility.calculations/calculate-ibu-per-hop">`calculate-ibu-per-hop`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-ibu-per-hop"></a>
``` clojure

(calculate-ibu-per-hop hop batch-size potential-gravity)
```


Given a `common-beer-format` conforming `hop`, `batch-size`, and `potential-gravity`, calculate the amount of IBUs generated

## <a name="brewtility.calculations/calculate-lovibond-color">`calculate-lovibond-color`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-lovibond-color"></a>

Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the color in degrees Lovibond for a recipe

## <a name="brewtility.calculations/calculate-malt-color-units">`calculate-malt-color-units`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-malt-color-units"></a>
``` clojure

(calculate-malt-color-units fermentables batch-size)
```


Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the overall Malt Color Units for a recipe

## <a name="brewtility.calculations/calculate-potential-abv">`calculate-potential-abv`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-potential-abv"></a>
``` clojure

(calculate-potential-abv fermentables batch-size)
(calculate-potential-abv fermentables batch-size attenuation)
```


Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, estimate the ABV.
   The primary fermentation yeast's `attenuation` may also be passed, otherwise 75% is assumed.

## <a name="brewtility.calculations/calculate-potential-final-gravity">`calculate-potential-final-gravity`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-potential-final-gravity"></a>
``` clojure

(calculate-potential-final-gravity fermentables batch-size)
(calculate-potential-final-gravity fermentables batch-size attenuation)
```


Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, estimate the Final Gravity.
   The primary fermentation yeast's `attenuation` may also be passed, otherwise 75% is assumed.

## <a name="brewtility.calculations/calculate-potential-gravity">`calculate-potential-gravity`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-potential-gravity"></a>
``` clojure

(calculate-potential-gravity fermentables batch-size)
```


Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, calculate the potential original gravity.

## <a name="brewtility.calculations/calculate-recipe-ibus">`calculate-recipe-ibus`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-recipe-ibus"></a>
``` clojure

(calculate-recipe-ibus hops batch-size potential-gravity)
```


Given a collection of `common-beer-format` conforming `hops`, `batch-size`, and `potential-gravity` calculate the amount of IBUs generated

## <a name="brewtility.calculations/calculate-rgba-color">`calculate-rgba-color`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-rgba-color"></a>

Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the RGBA color for a recipe

## <a name="brewtility.calculations/calculate-srm-color">`calculate-srm-color`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/calculate-srm-color"></a>
``` clojure

(calculate-srm-color fermentables batch-size)
```


Given a collection of `common-beer-format` conforming `fermentables`, and a conformed `batch-size` in liters, return the SRM color for a recipe

## <a name="brewtility.calculations/default-attenuation">`default-attenuation`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/default-attenuation"></a>

A constant backup attenuation for calculations.
   Set to 75% a common threshold for home brewing

## <a name="brewtility.calculations/gravity->abv-multiplier">`gravity->abv-multiplier`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/gravity->abv-multiplier"></a>

The constanct factor used to calculate ABV from potential gravity

## <a name="brewtility.calculations/gravity-points->potential-gravity">`gravity-points->potential-gravity`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/gravity-points->potential-gravity"></a>
``` clojure

(gravity-points->potential-gravity gravity-points volume)
```


Given the `gravity-points` of a recipe, and the `volume` of the batch, calculate the potential gravity

## <a name="brewtility.calculations/potential-gravity->gravity-points">`potential-gravity->gravity-points`</a> [:page_facing_up:](null)
<a name="brewtility.calculations/potential-gravity->gravity-points"></a>
``` clojure

(potential-gravity->gravity-points potential-gravity weight)
```


Given the `potential-gravity` of a fermentable, and the `weight` of the fermentable, calculate gravity points

-----
# <a name="brewtility.color">brewtility.color</a>


Namespace for calculating beer colors




## <a name="brewtility.color/color-system->display-name">`color-system->display-name`</a> [:page_facing_up:](null)
<a name="brewtility.color/color-system->display-name"></a>

A map from color system names to their full and short unit names

## <a name="brewtility.color/color-systems">`color-systems`</a> [:page_facing_up:](null)
<a name="brewtility.color/color-systems"></a>

The color systems available across brewtility

## <a name="brewtility.color/ebc->lovibond">`ebc->lovibond`</a> [:page_facing_up:](null)
<a name="brewtility.color/ebc->lovibond"></a>

Convert the color described in 'ebc` to the equivalent degrees Lovibond

## <a name="brewtility.color/ebc->rgba">`ebc->rgba`</a> [:page_facing_up:](null)
<a name="brewtility.color/ebc->rgba"></a>

Given `ebc-number`, return the closest, bounded applicable RGBA color string.

## <a name="brewtility.color/ebc->srm">`ebc->srm`</a> [:page_facing_up:](null)
<a name="brewtility.color/ebc->srm"></a>
``` clojure

(ebc->srm ebc)
```


Convert the color described by the `ebc` to the equivalent SRM color

## <a name="brewtility.color/lovibond->ebc">`lovibond->ebc`</a> [:page_facing_up:](null)
<a name="brewtility.color/lovibond->ebc"></a>

Convert the color described in degrees 'lovibond` to the equivalent EBC color

## <a name="brewtility.color/lovibond->rgba">`lovibond->rgba`</a> [:page_facing_up:](null)
<a name="brewtility.color/lovibond->rgba"></a>

Given `lovibond-number`, return the closest, bounded applicable RGBA color string.

## <a name="brewtility.color/lovibond->srm">`lovibond->srm`</a> [:page_facing_up:](null)
<a name="brewtility.color/lovibond->srm"></a>
``` clojure

(lovibond->srm lovibond)
```


Convert the color described in degrees `lovibond` to the equivalent SRM color

## <a name="brewtility.color/srm-1">`srm-1`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-1"></a>

An SRM of 1 mapped to an RGBa color code

## <a name="brewtility.color/srm-10">`srm-10`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-10"></a>

An SRM of 10 mapped to an RGBa color code

## <a name="brewtility.color/srm-11">`srm-11`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-11"></a>

An SRM of 11 mapped to an RGBa color code

## <a name="brewtility.color/srm-12">`srm-12`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-12"></a>

An SRM of 12 mapped to an RGBa color code

## <a name="brewtility.color/srm-13">`srm-13`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-13"></a>

An SRM of 13 mapped to an RGBa color code

## <a name="brewtility.color/srm-14">`srm-14`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-14"></a>

An SRM of 14 mapped to an RGBa color code

## <a name="brewtility.color/srm-15">`srm-15`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-15"></a>

An SRM of 15 mapped to an RGBa color code

## <a name="brewtility.color/srm-16">`srm-16`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-16"></a>

An SRM of 16 mapped to an RGBa color code

## <a name="brewtility.color/srm-17">`srm-17`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-17"></a>

An SRM of 17 mapped to an RGBa color code

## <a name="brewtility.color/srm-18">`srm-18`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-18"></a>

An SRM of 18 mapped to an RGBa color code

## <a name="brewtility.color/srm-19">`srm-19`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-19"></a>

An SRM of 19 mapped to an RGBa color code

## <a name="brewtility.color/srm-2">`srm-2`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-2"></a>

An SRM of 2 mapped to an RGBa color code

## <a name="brewtility.color/srm-20">`srm-20`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-20"></a>

An SRM of 20 mapped to an RGBa color code

## <a name="brewtility.color/srm-21">`srm-21`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-21"></a>

An SRM of 21 mapped to an RGBa color code

## <a name="brewtility.color/srm-22">`srm-22`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-22"></a>

An SRM of 22 mapped to an RGBa color code

## <a name="brewtility.color/srm-23">`srm-23`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-23"></a>

An SRM of 23 mapped to an RGBa color code

## <a name="brewtility.color/srm-24">`srm-24`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-24"></a>

An SRM of 24 mapped to an RGBa color code

## <a name="brewtility.color/srm-25">`srm-25`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-25"></a>

An SRM of 25 mapped to an RGBa color code

## <a name="brewtility.color/srm-26">`srm-26`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-26"></a>

An SRM of 26 mapped to an RGBa color code

## <a name="brewtility.color/srm-27">`srm-27`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-27"></a>

An SRM of 27 mapped to an RGBa color code

## <a name="brewtility.color/srm-28">`srm-28`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-28"></a>

An SRM of 28 mapped to an RGBa color code

## <a name="brewtility.color/srm-29">`srm-29`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-29"></a>

An SRM of 29 mapped to an RGBa color code

## <a name="brewtility.color/srm-3">`srm-3`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-3"></a>

An SRM of 3 mapped to an RGBa color code

## <a name="brewtility.color/srm-30">`srm-30`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-30"></a>

An SRM of 30 mapped to an RGBa color code

## <a name="brewtility.color/srm-31">`srm-31`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-31"></a>

An SRM of 31 mapped to an RGBa color code

## <a name="brewtility.color/srm-32">`srm-32`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-32"></a>

An SRM of 32 mapped to an RGBa color code

## <a name="brewtility.color/srm-33">`srm-33`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-33"></a>

An SRM of 33 mapped to an RGBa color code

## <a name="brewtility.color/srm-34">`srm-34`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-34"></a>

An SRM of 34 mapped to an RGBa color code

## <a name="brewtility.color/srm-35">`srm-35`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-35"></a>

An SRM of 35 mapped to an RGBa color code

## <a name="brewtility.color/srm-36">`srm-36`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-36"></a>

An SRM of 36 mapped to an RGBa color code

## <a name="brewtility.color/srm-37">`srm-37`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-37"></a>

An SRM of 37 mapped to an RGBa color code

## <a name="brewtility.color/srm-38">`srm-38`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-38"></a>

An SRM of 38 mapped to an RGBa color code

## <a name="brewtility.color/srm-39">`srm-39`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-39"></a>

An SRM of 39 mapped to an RGBa color code

## <a name="brewtility.color/srm-4">`srm-4`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-4"></a>

An SRM of 4 mapped to an RGBa color code

## <a name="brewtility.color/srm-40">`srm-40`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-40"></a>

An SRM of 40 mapped to an RGBa color code

## <a name="brewtility.color/srm-5">`srm-5`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-5"></a>

An SRM of 5 mapped to an RGBa color code

## <a name="brewtility.color/srm-6">`srm-6`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-6"></a>

An SRM of 6 mapped to an RGBa color code

## <a name="brewtility.color/srm-7">`srm-7`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-7"></a>

An SRM of 7 mapped to an RGBa color code

## <a name="brewtility.color/srm-8">`srm-8`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-8"></a>

An SRM of 8 mapped to an RGBa color code

## <a name="brewtility.color/srm-9">`srm-9`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-9"></a>

An SRM of 9 mapped to an RGBa color code

## <a name="brewtility.color/srm->ebc">`srm->ebc`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm->ebc"></a>
``` clojure

(srm->ebc srm)
```


Convert the color described by the `srm` to the equivalent EBC color

## <a name="brewtility.color/srm->lovibond">`srm->lovibond`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm->lovibond"></a>
``` clojure

(srm->lovibond srm)
```


Convert the color described in 'srm` to the equivalent degrees Lovibond

## <a name="brewtility.color/srm->rgba">`srm->rgba`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm->rgba"></a>
``` clojure

(srm->rgba srm-number)
```


Given `srm-number`, return the closest, bounded applicable RGBA color string.
   OPINIONATED: The provided `srm-number` will be bound to the common range from 1 to 40
                Decimal-like values are trimmed, not rounded.

## <a name="brewtility.color/srm-color-map">`srm-color-map`</a> [:page_facing_up:](null)
<a name="brewtility.color/srm-color-map"></a>

A map of integer values to their closest SRM value as an RGBa color code

-----
# <a name="brewtility.enrich.equipment">brewtility.enrich.equipment</a>


Enricher-pattern functions for [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) maps




## <a name="brewtility.enrich.equipment/enrich-calculated-boil-size">`enrich-calculated-boil-size`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-calculated-boil-size"></a>
``` clojure

(enrich-calculated-boil-size equipment)
(enrich-calculated-boil-size
 {:keys [calc-boil-volume], :as equipment}
 {:keys [safe-calculating-boil-size precision], :or {precision 3}})
```


An enricher pattern function to calculate the boil size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record via [[brewtility.calculations/calculate-equipment-boil-volume]].
   In the BeerXML spec, this behavior is controlled by the `:calc-boil-volume` field.
   When `:calc-boil-volume` is set to `true`, the `:boil-size` field is calculated as:
   `(batch-size - top-up-water - trub-chiller-loss) * (1 + (boil-time * evap-rate))`

   When `:calc-boil-volume` is set to `false`, the `:boil-size` field is left unmodified.
   As a note, both the BeerXML spec and common-beer-format only require the `boil-size` field to be a number.
   Neither the specification nor the implementation will check to ensure the value is correct given the other fields.

   An option map may be passed as an optional second argument.
   The following keys are supported:

     - `:safe-calculating-boil-size` - If this value is true, the program will not throw an exception if data is missing for the calculation.
                                       In that case, the exception will be caught internally and the `:boil-size` will not be modified from its original value.
                                       Defaults to `false`.
     - `:precision` - The number of decimal places to round the calculated value to.
                     Defaults to `3`.

## <a name="brewtility.enrich.equipment/enrich-display-batch-size">`enrich-display-batch-size`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-batch-size"></a>
``` clojure

(enrich-display-batch-size equipment)
(enrich-display-batch-size equipment {:keys [batch-size-target-units batch-size-precision batch-size-suffix], :as opts})
```


An enricher pattern function to add the displayable batch size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the batch size into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the batch size. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:batch-size-target-units`: The unit to convert the batch size into. Supersedes `:system-of-measure`.
    - `:batch-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:batch-size-suffix`: The suffix type to append to the batch size. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-boil-size">`enrich-display-boil-size`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-boil-size"></a>
``` clojure

(enrich-display-boil-size equipment)
(enrich-display-boil-size equipment {:keys [boil-size-target-units boil-size-precision boil-size-suffix], :as opts})
```


An enricher pattern function to add the displayable boil size to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the boil size into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:boil-size-target-units`: The unit to convert the boil size into. Supersedes `:system-of-measure`.
    - `:boil-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:boil-size-suffix`: The suffix type to append to the boil size. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-lauter-deadspace">`enrich-display-lauter-deadspace`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-lauter-deadspace"></a>
``` clojure

(enrich-display-lauter-deadspace equipment)
(enrich-display-lauter-deadspace
 equipment
 {:keys [lauter-deadspace-target-units lauter-deadspace-precision lauter-deadspace-suffix], :as opts})
```


An enricher pattern function to add the displayable lauter deadspace to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the lauter deadspace into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the lauter deadspace. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:lauter-deadspace-target-units`: The unit to convert the lauter deadspace into. Supersedes `:system-of-measure`.
    - `:lauter-deadspace-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:lauter-deadspace-suffix`: The suffix type to append to the lauter deadspace. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-top-up-kettle">`enrich-display-top-up-kettle`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-top-up-kettle"></a>
``` clojure

(enrich-display-top-up-kettle equipment)
(enrich-display-top-up-kettle
 equipment
 {:keys [top-up-kettle-target-units top-up-kettle-precision top-up-kettle-suffix], :as opts})
```


An enricher pattern function to add the displayable top up kettle to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the top up kettle into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the top up kettle. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:top-up-kettle-target-units`: The unit to convert the top up kettle into. Supersedes `:system-of-measure`.
    - `:top-up-kettle-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:top-up-kettle-suffix`: The suffix type to append to the top up kettle. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-top-up-water">`enrich-display-top-up-water`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-top-up-water"></a>
``` clojure

(enrich-display-top-up-water equipment)
(enrich-display-top-up-water
 equipment
 {:keys [top-up-water-target-units top-up-water-precision top-up-water-suffix], :as opts})
```


An enricher pattern function to add the displayable top up water to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the top up water into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the top up water. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:top-up-water-target-units`: The unit to convert the top up water into. Supersedes `:system-of-measure`.
    - `:top-up-water-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:top-up-water-suffix`: The suffix type to append to the top up water. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-trub-chiller-loss">`enrich-display-trub-chiller-loss`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-trub-chiller-loss"></a>
``` clojure

(enrich-display-trub-chiller-loss equipment)
(enrich-display-trub-chiller-loss
 equipment
 {:keys [trub-chiller-loss-target-units trub-chiller-loss-precision trub-chiller-loss-suffix], :as opts})
```


An enricher pattern function to add the displayable trub chiller loss to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
   The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the trub chiller loss into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the trub chiller loss. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:trub-chiller-loss-target-units`: The unit to convert the trub chiller loss into. Supersedes `:system-of-measure`.
    - `:trub-chiller-loss-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:trub-chiller-loss-suffix`: The suffix type to append to the trub chiller loss. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-tun-volume">`enrich-display-tun-volume`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-tun-volume"></a>
``` clojure

(enrich-display-tun-volume equipment)
(enrich-display-tun-volume equipment {:keys [tun-volume-target-units tun-volume-precision tun-volume-suffix], :as opts})
```


An enricher pattern function to add the displayable tun volume to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
     The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the tun volume into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the tun volume. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:tun-volume-target-units`: The unit to convert the tun volume into. Supersedes `:system-of-measure`.
    - `:tun-volume-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:tun-volume-suffix`: The suffix type to append to the tun volume. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-display-tun-weight">`enrich-display-tun-weight`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-display-tun-weight"></a>
``` clojure

(enrich-display-tun-weight equipment)
(enrich-display-tun-weight equipment {:keys [tun-weight-target-units tun-weight-precision tun-weight-suffix], :as opts})
```


An enricher pattern function to add the displayable tun weight to an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) record.

   An option map may be passed as an optional second argument.
     The following keys are supported:

    - `:system-of-measure`: The unit system of measure to convert the tun weight into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the tun weight. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-equipment`](#brewtility.enrich.equipment/enrich-equipment) and [`enrich-equipment-wrapper`](#brewtility.enrich.equipment/enrich-equipment-wrapper), this function also supports the following keys:
    - `:tun-weight-target-units`: The unit to convert the tun weight into. Supersedes `:system-of-measure`.
    - `:tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:tun-weight-suffix`: The suffix type to append to the tun weight. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-equipment">`enrich-equipment`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-equipment"></a>
``` clojure

(enrich-equipment equipment)
(enrich-equipment equipment opts)
```


An enricher pattern function to derive as many values from an [equipment record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

    - `:system-of-measure`: The unit system of measure to convert the displayable fields into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-calculated-boil-size]]
        - `:safe-calculating-boil-size` - If this value is true, the program will not throw an exception if data is missing for the calculation.
                                          In that case, the exception will be caught internally and the `:boil-size` will not be modified from its original value.
                                          Defaults to `false`.
    - [[enrich-display-boil-size]]
        - `:boil-size-target-units`: The unit to convert the boil size into. Supersedes `:system-of-measure`.
        - `:boil-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:boil-size-suffix`: The suffix type to append to the boil size. Supersedes `:suffix`.
    - [[enrich-display-batch-size]]
        - `:batch-size-target-units`: The unit to convert the batch size into. Supersedes `:system-of-measure`.
        - `:batch-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:batch-size-suffix`: The suffix type to append to the batch size. Supersedes `:suffix`.
    - [[enrich-diplay-tun-volume]]
        - `:tun-volume-target-units`: The unit to convert the tun volume into. Supersedes `:system-of-measure`.
        - `:tun-volume-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-volume-suffix`: The suffix type to append to the tun volume. Supersedes `:suffix`.
    - [[enrich-display-tun-weight]]
        - `:tun-weight-target-units`: The unit to convert the tun weight into. Supersedes `:system-of-measure`.
        - `:tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-weight-suffix`: The suffix type to append to the tun weight. Supersedes `:suffix`.
    - [[enrich-display-top-up-water]]
        - `:top-up-water-target-units`: The unit to convert the top up water into. Supersedes `:system-of-measure`.
        - `:top-up-water-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-water-suffix`: The suffix type to append to the top up water. Supersedes `:suffix`.
    - [[enrich-display-trub-chiller-loss]]
        - `:trub-chiller-loss-target-units`: The unit to convert the trub chiller loss into. Supersedes `:system-of-measure`.
        - `:trub-chiller-loss-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:trub-chiller-loss-suffix`: The suffix type to append to the trub chiller loss. Supersedes `:suffix`.
    - [[enrich-display-lauter-deadspace]]
        - `:lauter-deadspace-target-units`: The unit to convert the lauter deadspace into. Supersedes `:system-of-measure`.
        - `:lauter-deadspace-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:lauter-deadspace-suffix`: The suffix type to append to the lauter deadspace. Supersedes `:suffix`.
    - [[enrich-display-top-up-kettle]]
        - `:top-up-kettle-target-units`: The unit to convert the top up kettle into. Supersedes `:system-of-measure`.
        - `:top-up-kettle-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-kettle-suffix`: The suffix type to append to the top up kettle. Supersedes `:suffix`.

## <a name="brewtility.enrich.equipment/enrich-equipment-wrapper">`enrich-equipment-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.equipment/enrich-equipment-wrapper"></a>
``` clojure

(enrich-equipment-wrapper equipment-wrapper)
(enrich-equipment-wrapper equipment-wrapper opts)
```


An enricher pattern function to derive as many values from an [equipment-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

    - `:system-of-measure`: The unit system of measure to convert the displayable fields into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"gal"` for `" US gallons"`.
        - `:full`: The full name of the selected unit. For example, `"teaspoon"` for `"teaspoon"`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-calculated-boil-size]]
        - `:safe-calculating-boil-size` - If this value is true, the program will not throw an exception if data is missing for the calculation.
                                          In that case, the exception will be caught internally and the `:boil-size` will not be modified from its original value.
                                          Defaults to `false`.
    - [[enrich-display-boil-size]]
        - `:boil-size-target-units`: The unit to convert the boil size into. Supersedes `:system-of-measure`.
        - `:boil-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:boil-size-suffix`: The suffix type to append to the boil size. Supersedes `:suffix`.
    - [[enrich-display-batch-size]]
        - `:batch-size-target-units`: The unit to convert the batch size into. Supersedes `:system-of-measure`.
        - `:batch-size-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:batch-size-suffix`: The suffix type to append to the batch size. Supersedes `:suffix`.
    - [[enrich-diplay-tun-volume]]
        - `:tun-volume-target-units`: The unit to convert the tun volume into. Supersedes `:system-of-measure`.
        - `:tun-volume-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-volume-suffix`: The suffix type to append to the tun volume. Supersedes `:suffix`.
    - [[enrich-display-tun-weight]]
        - `:tun-weight-target-units`: The unit to convert the tun weight into. Supersedes `:system-of-measure`.
        - `:tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:tun-weight-suffix`: The suffix type to append to the tun weight. Supersedes `:suffix`.
    - [[enrich-display-top-up-water]]
        - `:top-up-water-target-units`: The unit to convert the top up water into. Supersedes `:system-of-measure`.
        - `:top-up-water-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-water-suffix`: The suffix type to append to the top up water. Supersedes `:suffix`.
    - [[enrich-display-trub-chiller-loss]]
        - `:trub-chiller-loss-target-units`: The unit to convert the trub chiller loss into. Supersedes `:system-of-measure`.
        - `:trub-chiller-loss-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:trub-chiller-loss-suffix`: The suffix type to append to the trub chiller loss. Supersedes `:suffix`.
    - [[enrich-display-lauter-deadspace]]
        - `:lauter-deadspace-target-units`: The unit to convert the lauter deadspace into. Supersedes `:system-of-measure`.
        - `:lauter-deadspace-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:lauter-deadspace-suffix`: The suffix type to append to the lauter deadspace. Supersedes `:suffix`.
    - [[enrich-display-top-up-kettle]]
        - `:top-up-kettle-target-units`: The unit to convert the top up kettle into. Supersedes `:system-of-measure`.
        - `:top-up-kettle-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:top-up-kettle-suffix`: The suffix type to append to the top up kettle. Supersedes `:suffix`.

-----
# <a name="brewtility.enrich.fermentables">brewtility.enrich.fermentables</a>


Enricher-pattern functions for [fermentables](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) maps




## <a name="brewtility.enrich.fermentables/enrich-add-after-boil">`enrich-add-after-boil`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-add-after-boil"></a>
``` clojure

(enrich-add-after-boil fermentable)
(enrich-add-after-boil fermentable _opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) was added after the boil.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :add-after-boil field is not present, this function will explicitly set it to false.

## <a name="brewtility.enrich.fermentables/enrich-coarse-fine-diff">`enrich-coarse-fine-diff`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-coarse-fine-diff"></a>
``` clojure

(enrich-coarse-fine-diff fermentable)
(enrich-coarse-fine-diff fermentable opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a coarse/fine differential.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:coarse-fine-diff` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.enrich.fermentables/enrich-diastatic-power">`enrich-diastatic-power`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-diastatic-power"></a>
``` clojure

(enrich-diastatic-power fermentable)
(enrich-diastatic-power fermentable opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a listed Diastatic Power.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:diastatic-power` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.enrich.fermentables/enrich-display-amount">`enrich-display-amount`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-display-amount"></a>
``` clojure

(enrich-display-amount fermentable)
(enrich-display-amount
 fermentable
 {:keys [fermentable-amount-target-units fermentable-amount-precision fermentable-amount-suffix], :as opts})
```


An enricher pattern function to render a human-readable display weight of a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is in a given system.
   In the BeerXML spec, the amount of a liquid extract is computed by its weight.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-fermentable`](#brewtility.enrich.fermentables/enrich-fermentable) and [`enrich-fermentable-wrapper`](#brewtility.enrich.fermentables/enrich-fermentable-wrapper), this function also supports the following keys:
    - `:fermentable-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:fermentable-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:fermentable-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.fermentables/enrich-display-color">`enrich-display-color`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-display-color"></a>
``` clojure

(enrich-display-color fermentable)
(enrich-display-color fermentable {:keys [color-system suffix], :as opts})
```


An enricher pattern function to determine what color a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is in a given system.
   In the BeerXML spec, color is assumed to be in Lovibond for the `:type` of `grain`, and SRM for all other fermentables.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:precision`: The number of significant decimal places to display. Defaults to 3.
     - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
         - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
         - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
         - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
         - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.
     - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
         - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
         - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained selections within the context of [`enrich-fermentable`](#brewtility.enrich.fermentables/enrich-fermentable) and [`enrich-fermentable-wrapper`](#brewtility.enrich.fermentables/enrich-fermentable-wrapper), this function also supports the following keys:
    - `:fermentable-color-target-units`: The unit to convert the color into. Supersedes `:color-system`.
    - `:fermentable-color-precision`: The number of significant decimal places to display. Supersedes `:color`.
    - `:fermentable-color-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.fermentables/enrich-fermentable">`enrich-fermentable`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-fermentable"></a>
``` clojure

(enrich-fermentable fermentable)
(enrich-fermentable fermentable opts)
```


An enricher pattern function to derive as many values from an [fermentable record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.
     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.
    - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
        - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
        - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
        - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
        - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:fermentable-color-target-units`: The unit to convert the color into. Supersedes `:color-system`.
        - `:fermentable-color-precision`: The number of significant decimal places to display. Supersedes `:color`.
        - `:fermentable-color-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
    - [[enrich-display-amount]]
        - `:fermentable-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:fermentable-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:fermentable-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.fermentables/enrich-fermentable-wrapper">`enrich-fermentable-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-fermentable-wrapper"></a>
``` clojure

(enrich-fermentable-wrapper fermentable)
(enrich-fermentable-wrapper fermentable opts)
```


An enricher pattern function to derive as many values from an [fermentable-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.
     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.
    - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
        - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
        - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
        - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
        - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:fermentable-color-target-units`: The unit to convert the color into. Supersedes `:color-system`.
        - `:fermentable-color-precision`: The number of significant decimal places to display. Supersedes `:color`.
        - `:fermentable-color-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
    - [[enrich-display-amount]]
        - `:fermentable-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:fermentable-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:fermentable-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.fermentables/enrich-fermentables">`enrich-fermentables`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-fermentables"></a>
``` clojure

(enrich-fermentables fermentables)
(enrich-fermentables fermentables opts)
```


An enricher pattern function to derive as many values from an [fermentables record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.
     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.
    - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
        - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
        - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
        - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
        - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:fermentable-color-target-units`: The unit to convert the color into. Supersedes `:color-system`.
        - `:fermentable-color-precision`: The number of significant decimal places to display. Supersedes `:color`.
        - `:fermentable-color-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
    - [[enrich-display-amount]]
        - `:fermentable-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:fermentable-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:fermentable-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.fermentables/enrich-fermentables-wrapper">`enrich-fermentables-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-fermentables-wrapper"></a>
``` clojure

(enrich-fermentables-wrapper fermentables)
(enrich-fermentables-wrapper fermentables opts)
```


An enricher pattern function to derive as many values from an [fermentables-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.
     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.
    - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
        - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
        - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
        - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
        - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:fermentable-color-target-units`: The unit to convert the color into. Supersedes `:color-system`.
        - `:fermentable-color-precision`: The number of significant decimal places to display. Supersedes `:color`.
        - `:fermentable-color-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
    - [[enrich-display-amount]]
        - `:fermentable-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:fermentable-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:fermentable-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.fermentables/enrich-ibu-gallons-per-pound">`enrich-ibu-gallons-per-pound`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-ibu-gallons-per-pound"></a>
``` clojure

(enrich-ibu-gallons-per-pound fermentable)
(enrich-ibu-gallons-per-pound fermentable opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have ibu-gal-per-lb.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `extract`.
   When the fermetable is not an extract, this function will dissoc `:ibu-gal-per-lb` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.enrich.fermentables/enrich-moisture">`enrich-moisture`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-moisture"></a>
``` clojure

(enrich-moisture fermentable)
(enrich-moisture fermentable opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a moisture content.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:moisture` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.enrich.fermentables/enrich-protein">`enrich-protein`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-protein"></a>
``` clojure

(enrich-protein fermentable)
(enrich-protein fermentable opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have protein.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:protein` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.enrich.fermentables/enrich-recommend-mash">`enrich-recommend-mash`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.fermentables/enrich-recommend-mash"></a>
``` clojure

(enrich-recommend-mash fermentable)
(enrich-recommend-mash fermentable opts)
```


An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have recommend-mash.
   In the BeerXML spec, this field should only be `true` if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will set `:recommend-mash` to false in the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.enrich.hops">brewtility.enrich.hops</a>


Enricher-pattern functions for [hops](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) maps




## <a name="brewtility.enrich.hops/enrich-display-amount">`enrich-display-amount`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.hops/enrich-display-amount"></a>
``` clojure

(enrich-display-amount hop)
(enrich-display-amount hop {:keys [hop-amount-target-units hop-amount-precision hop-amount-suffix], :as opts})
```


An enricher pattern function to render a human-readable display weight of a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-hop`](#brewtility.enrich.hops/enrich-hop) and [`enrich-hop-wrapper`](#brewtility.enrich.hops/enrich-hop-wrapper), this function also supports the following keys:
    - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.hops/enrich-display-time">`enrich-display-time`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.hops/enrich-display-time"></a>
``` clojure

(enrich-display-time hop)
(enrich-display-time hop {:keys [hop-time-target-units hop-time-precision hop-time-suffix], :as opts})
```


An enricher pattern function to render a human-readable display time of a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the time into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the time Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-hop`](#brewtility.enrich.hops/enrich-hop) and [`enrich-hop-wrapper`](#brewtility.enrich.hops/enrich-hop-wrapper), this function also supports the following keys:
    - `:hop-time-target-units`: The unit to convert the time into. Supersedes `:system-of-measure`.
    - `:hop-time-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:hop-time-suffix`: The suffix type to append to the time. Supersedes `:suffix`.

## <a name="brewtility.enrich.hops/enrich-hop">`enrich-hop`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.hops/enrich-hop"></a>
``` clojure

(enrich-hop hop)
(enrich-hop hop opts)
```


An enricher pattern function to derive as many values from an [hop record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.hops/enrich-hop-wrapper">`enrich-hop-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.hops/enrich-hop-wrapper"></a>
``` clojure

(enrich-hop-wrapper hop)
(enrich-hop-wrapper hop opts)
```


An enricher pattern function to derive as many values from an [hop-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.hops/enrich-hops">`enrich-hops`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.hops/enrich-hops"></a>
``` clojure

(enrich-hops hops)
(enrich-hops hops opts)
```


An enricher pattern function to derive as many values from an [hops record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.hops/enrich-hops-wrapper">`enrich-hops-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.hops/enrich-hops-wrapper"></a>
``` clojure

(enrich-hops-wrapper hops)
(enrich-hops-wrapper hops opts)
```


An enricher pattern function to derive as many values from an [hops-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:hop-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:hop-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:hop-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

-----
# <a name="brewtility.enrich.mash">brewtility.enrich.mash</a>


Enricher-pattern functions for [mashes and mash steps](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps




## <a name="brewtility.enrich.mash/enrich-display-grain-temperature">`enrich-display-grain-temperature`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-display-grain-temperature"></a>
``` clojure

(enrich-display-grain-temperature mash)
(enrich-display-grain-temperature
 mash
 {:keys [display-grain-temperature-target-units display-grain-temperature-precision display-grain-temperature-suffix],
  :as opts})
```


An enricher pattern function to render a human-readable temperature of the grain temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) and [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper), this function also supports the following keys:
    - `:display-grain-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-grain-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-grain-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-display-infuse-amount">`enrich-display-infuse-amount`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-display-infuse-amount"></a>
``` clojure

(enrich-display-infuse-amount mash-step)
(enrich-display-infuse-amount
 mash-step
 {:keys [display-infuse-amount-target-units display-infuse-amount-precision display-infuse-amount-suffix], :as opts})
```


An enricher pattern function to render a human-readable infuse amount of a [mash step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) and [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper), this function also supports the following keys:
    - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-display-sparge-temperature">`enrich-display-sparge-temperature`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-display-sparge-temperature"></a>
``` clojure

(enrich-display-sparge-temperature mash)
(enrich-display-sparge-temperature
 mash
 {:keys
  [display-sparge-temperature-target-units display-sparge-temperature-precision display-sparge-temperature-suffix],
  :as opts})
```


An enricher pattern function to render a human-readable temperature of the sparge temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-spargeed selections within the context of [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) and [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper), this function also supports the following keys:
    - `:display-sparge-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-sparge-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-sparge-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-display-step-temperature">`enrich-display-step-temperature`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-display-step-temperature"></a>
``` clojure

(enrich-display-step-temperature mash-step)
(enrich-display-step-temperature
 mash-step
 {:keys [display-temperature-target-units display-temperature-precision display-temperature-suffix], :as opts})
```


An enricher pattern function to render a human-readable display temperature of a [mash step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) and [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper), this function also supports the following keys:
    - `:display-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-display-tun-temperature">`enrich-display-tun-temperature`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-display-tun-temperature"></a>
``` clojure

(enrich-display-tun-temperature mash)
(enrich-display-tun-temperature
 mash
 {:keys [display-tun-temperature-target-units display-tun-temperature-precision display-tun-temperature-suffix],
  :as opts})
```


An enricher pattern function to render a human-readable temperature of the tun temperature during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-tuned selections within the context of [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) and [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper), this function also supports the following keys:
    - `:display-tun-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-tun-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-tun-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-display-tun-weight">`enrich-display-tun-weight`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-display-tun-weight"></a>
``` clojure

(enrich-display-tun-weight mash)
(enrich-display-tun-weight
 mash
 {:keys [display-tun-weight-target-units display-tun-weight-precision display-tun-weight-suffix], :as opts})
```


An enricher pattern function to render a human-readable weight of the tun weight during a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the amount. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-tuned selections within the context of [`enrich-mash-step`](#brewtility.enrich.mash/enrich-mash-step) and [`enrich-mash-step-wrapper`](#brewtility.enrich.mash/enrich-mash-step-wrapper), this function also supports the following keys:
    - `:display-tun-weight-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
    - `:display-tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:display-tun-weight-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-mash">`enrich-mash`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-mash"></a>
``` clojure

(enrich-mash mash)
(enrich-mash mash opts)
```


An enricher pattern function to derive as many values from an [mash record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-grain-temperature]]
        - `:display-grain-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-grain-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-grain-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-tun-temperature]]
        - `:display-tun-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-sparge-temperature]]
        - `:display-sparge-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-sparge-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-sparge-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`
    - [[enrich-display-tun-weight]]
        - `:display-tun-weight-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-weight-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-mash-step">`enrich-mash-step`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-mash-step"></a>
``` clojure

(enrich-mash-step mash-step)
(enrich-mash-step mash-step opts)
```


An enricher pattern function to derive as many values from an [mash-step record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-mash-step-wrapper">`enrich-mash-step-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-mash-step-wrapper"></a>
``` clojure

(enrich-mash-step-wrapper mash-step)
(enrich-mash-step-wrapper mash-step opts)
```


An enricher pattern function to derive as many values from an [mash-step wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-mash-steps">`enrich-mash-steps`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-mash-steps"></a>
``` clojure

(enrich-mash-steps mash-steps)
(enrich-mash-steps mash-steps opts)
```


An enricher pattern function to derive as many values from a collection of [mash-step wrapper records](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-mash-steps-wrapper">`enrich-mash-steps-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-mash-steps-wrapper"></a>
``` clojure

(enrich-mash-steps-wrapper mash)
(enrich-mash-steps-wrapper mash opts)
```


An enricher pattern function to derive as many values from a collection of [mash-steps wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.mash/enrich-mash-wrapper">`enrich-mash-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.mash/enrich-mash-wrapper"></a>
``` clojure

(enrich-mash-wrapper mash)
(enrich-mash-wrapper mash opts)
```


An enricher pattern function to derive as many values from an [mash wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-step-temperature]]
        - `:display-temperature-target-units`: The unit to convert the temperature into. Supersedes `:system-of-measure`.
        - `:display-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-temperature-suffix`: The suffix type to append to the temperature. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:display-infuse-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-infuse-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-infuse-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-grain-temperature]]
        - `:display-grain-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-grain-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-grain-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-tun-temperature]]
        - `:display-tun-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-sparge-temperature]]
        - `:display-sparge-temperature-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-sparge-temperature-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-sparge-temperature-suffix`: The suffix type to append to the amount. Supersedes `:suffix`
    - [[enrich-display-tun-weight]]
        - `:display-tun-weight-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:display-tun-weight-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:display-tun-weight-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

-----
# <a name="brewtility.enrich.miscs">brewtility.enrich.miscs</a>


Enricher-pattern functions for [miscs](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscss.cljc) maps




## <a name="brewtility.enrich.miscs/enrich-amount-is-weight">`enrich-amount-is-weight`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-amount-is-weight"></a>
``` clojure

(enrich-amount-is-weight misc)
(enrich-amount-is-weight misc _)
```


An enricher pattern function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) should be measured by weight/volume.
   When this feild is not present, it is assumed to be false.

## <a name="brewtility.enrich.miscs/enrich-display-amount">`enrich-display-amount`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-display-amount"></a>
``` clojure

(enrich-display-amount misc)
(enrich-display-amount misc {:keys [misc-time-target-units misc-time-precision misc-time-suffix], :as opts})
```


An enricher pattern function to render a human-readable display time of a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is in a given system.
   If the `amount-is-weight` key evaluates truthy, the `amount` will be treated as a weight in kilograms.
   Otherwise, it will be treated as a volume in liters.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the time into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the time Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-misc`](#brewtility.enrich.miscs/enrich-misc) and [`enrich-misc-wrapper`](#brewtility.enrich.miscs/enrich-misc-wrapper), this function also supports the following keys:
    - `:misc-amount-target-units`: The unit to convert the time into. Supersedes `:system-of-measure`.
    - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:misc-amount-suffix`: The suffix type to append to the time. Supersedes `:suffix`.

## <a name="brewtility.enrich.miscs/enrich-display-time">`enrich-display-time`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-display-time"></a>
``` clojure

(enrich-display-time misc)
(enrich-display-time misc {:keys [misc-time-target-units misc-time-precision misc-time-suffix], :as opts})
```


An enricher pattern function to render a human-readable display time of a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the time into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the time Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"lb"` for `"pounds"`.
        - `:full`: The full name of the selected unit. For example, `"gram"` for `"gram"`.

   To support fine-grained selections within the context of [`enrich-misc`](#brewtility.enrich.miscs/enrich-misc) and [`enrich-misc-wrapper`](#brewtility.enrich.miscs/enrich-misc-wrapper), this function also supports the following keys:
    - `:misc-time-target-units`: The unit to convert the time into. Supersedes `:system-of-measure`.
    - `:misc-time-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:misc-time-suffix`: The suffix type to append to the time. Supersedes `:suffix`.

## <a name="brewtility.enrich.miscs/enrich-misc">`enrich-misc`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-misc"></a>
``` clojure

(enrich-misc misc)
(enrich-misc misc opts)
```


An enricher pattern function to derive as many values from an [misc record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.miscs/enrich-misc-wrapper">`enrich-misc-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-misc-wrapper"></a>
``` clojure

(enrich-misc-wrapper misc)
(enrich-misc-wrapper misc opts)
```


An enricher pattern function to derive as many values from an [misc-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.miscs/enrich-miscs">`enrich-miscs`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-miscs"></a>
``` clojure

(enrich-miscs miscs)
(enrich-miscs miscs opts)
```


An enricher pattern function to derive as many values from an [miscs record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

## <a name="brewtility.enrich.miscs/enrich-miscs-wrapper">`enrich-miscs-wrapper`</a> [:page_facing_up:](null)
<a name="brewtility.enrich.miscs/enrich-miscs-wrapper"></a>
``` clojure

(enrich-miscs-wrapper miscs)
(enrich-miscs-wrapper miscs opts)
```


An enricher pattern function to derive as many values from an [miscs-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:system-of-measure`: The unit system of measure to convert the amount into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to displayable units. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `"°L"` for `:lovibond`.
        - `:full`: The full name of the selected unit. For example, `"° Lovibond"` for `:lovibond`.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-amount]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.
   - [[enrich-display-time]]
        - `:misc-amount-target-units`: The unit to convert the amount into. Supersedes `:system-of-measure`.
        - `:misc-amount-precision`: The number of significant decimal places to display. Supersedes `:precision`.
        - `:misc-amount-suffix`: The suffix type to append to the amount. Supersedes `:suffix`.

-----
# <a name="brewtility.precision">brewtility.precision</a>


Namespace for handling numeric precision




## <a name="brewtility.precision/->1dp">`->1dp`</a> [:page_facing_up:](null)
<a name="brewtility.precision/->1dp"></a>
``` clojure

(->1dp x)
```


Given a decimal `x`, returns that number rounded to one decimal place.

## <a name="brewtility.precision/->2dp">`->2dp`</a> [:page_facing_up:](null)
<a name="brewtility.precision/->2dp"></a>
``` clojure

(->2dp x)
```


Given a decimal `x`, returns that number rounded to two decimal places.

## <a name="brewtility.precision/->3dp">`->3dp`</a> [:page_facing_up:](null)
<a name="brewtility.precision/->3dp"></a>
``` clojure

(->3dp x)
```


Given a decimal `x`, returns that number rounded to three decimal places.

## <a name="brewtility.precision/->precision">`->precision`</a> [:page_facing_up:](null)
<a name="brewtility.precision/->precision"></a>
``` clojure

(->precision x num-decimals)
```


Given a decimal `x` and the number of decimal places, returns that number rounded to `num-decimals` precision

## <a name="brewtility.precision/approximates?">`approximates?`</a> [:page_facing_up:](null)
<a name="brewtility.precision/approximates?"></a>
``` clojure

(approximates? n1 n2 variance)
```


Determine if `n2` approximates `n1` within `variance` percent

-----
# <a name="brewtility.predicates.equipment">brewtility.predicates.equipment</a>


Predicate functions for [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) maps




## <a name="brewtility.predicates.equipment/calculated-boil-volume?">`calculated-boil-volume?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.equipment/calculated-boil-volume?"></a>
``` clojure

(calculated-boil-volume? equipment)
```


A predicate function to determine if an [equipment](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/equipment.cljc) had its boil volume calculated.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :calc-boil-volume field is not present, this function will explicitly return false.

-----
# <a name="brewtility.predicates.fermentables">brewtility.predicates.fermentables</a>


Predicate functions for [fermentables](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) maps




## <a name="brewtility.predicates.fermentables/add-after-boil?">`add-after-boil?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.fermentables/add-after-boil?"></a>
``` clojure

(add-after-boil? fermentable)
```


A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) was added after the start of the boil.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :add-after-boil field is not present, this function will explicitly return false.

## <a name="brewtility.predicates.fermentables/adjunct?">`adjunct?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.fermentables/adjunct?"></a>
``` clojure

(adjunct? fermentable)
(adjunct? fermentable opts)
```


A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is an adjunct, non-standard fermentable.
   This function will throw an exception if the `type` field is not present.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.fermentables/dry-extract?">`dry-extract?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.fermentables/dry-extract?"></a>
``` clojure

(dry-extract? fermentable)
(dry-extract? fermentable opts)
```


A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is dried malt extract.
   This function will throw an exception if the `type` field is not present.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.fermentables/extract?">`extract?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.fermentables/extract?"></a>
``` clojure

(extract? fermentable)
(extract? fermentable opts)
```


A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is a liquid malt extract.
   This function will throw an exception if the `type` field is not present.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.fermentables/grain?">`grain?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.fermentables/grain?"></a>
``` clojure

(grain? fermentable)
(grain? fermentable opts)
```


A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is whole grain.
   This function will throw an exception if the `type` field is not present.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.fermentables/sugar?">`sugar?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.fermentables/sugar?"></a>
``` clojure

(sugar? fermentable)
(sugar? fermentable opts)
```


A predicate function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is a raw sugar.
   This function will throw an exception if the `type` field is not present.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.predicates.hops">brewtility.predicates.hops</a>


Predicate functions for [hops](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) maps




## <a name="brewtility.predicates.hops/aroma-type?">`aroma-type?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/aroma-type?"></a>
``` clojure

(aroma-type? hop)
(aroma-type? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for aroma.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/aroma-use?">`aroma-use?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/aroma-use?"></a>
``` clojure

(aroma-use? hop)
(aroma-use? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added at the end of the boil for aroma.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/bittering?">`bittering?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/bittering?"></a>
``` clojure

(bittering? hop)
(bittering? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for bittering.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/boil?">`boil?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/boil?"></a>
``` clojure

(boil? hop)
(boil? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was used during the boil.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/both?">`both?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/both?"></a>
``` clojure

(both? hop)
(both? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added for both bittering and aroma.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/dry-hop?">`dry-hop?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/dry-hop?"></a>
``` clojure

(dry-hop? hop)
(dry-hop? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was used as a dry-hop addition in secondary fermentation.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/first-wort?">`first-wort?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/first-wort?"></a>
``` clojure

(first-wort? hop)
(first-wort? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added to the wort at the first possible moment.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/leaf?">`leaf?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/leaf?"></a>
``` clojure

(leaf? hop)
(leaf? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a whole hop cone.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:form` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:form` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/mash?">`mash?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/mash?"></a>
``` clojure

(mash? hop)
(mash? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was added during the mash and before the boil.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/pellet?">`pellet?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/pellet?"></a>
``` clojure

(pellet? hop)
(pellet? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a compressed pellet.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:form` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:form` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.hops/plug?">`plug?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.hops/plug?"></a>
``` clojure

(plug? hop)
(plug? hop opts)
```


A predicate function to determine if a [hop](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/hops.cljc) was a compressed whole-hop plug.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:form` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:form` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.predicates.mash">brewtility.predicates.mash</a>


Predicate functions for [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) maps




## <a name="brewtility.predicates.mash/adjust-for-equipment?">`adjust-for-equipment?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.mash/adjust-for-equipment?"></a>
``` clojure

(adjust-for-equipment? mash)
```


A predicate function to determine if a [mash](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) should account for any temperature effects of the equipment used.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the `:equip-adjust` field is not present, this function will return false.

## <a name="brewtility.predicates.mash/decoction?">`decoction?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.mash/decoction?"></a>
``` clojure

(decoction? mash-step)
(decoction? mash-step opts)
```


A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used to extract and concentrate flavor.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.mash/infusion?">`infusion?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.mash/infusion?"></a>
``` clojure

(infusion? mash-step)
(infusion? mash-step opts)
```


A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used as a basic infusion.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.mash/temperature?">`temperature?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.mash/temperature?"></a>
``` clojure

(temperature? mash-step)
(temperature? mash-step opts)
```


A predicate function to determine if a [mash-step](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/mash.cljc) is used to bring wort up to temperature.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.predicates.miscs">brewtility.predicates.miscs</a>


Predicate functions for [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) maps




## <a name="brewtility.predicates.miscs/boil?">`boil?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/boil?"></a>
``` clojure

(boil? misc)
(boil? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the boil.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/bottling?">`bottling?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/bottling?"></a>
``` clojure

(bottling? misc)
(bottling? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during bottle conditioning.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/fining?">`fining?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/fining?"></a>
``` clojure

(fining? misc)
(fining? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is used to remove yeast and protein haze.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/flavor?">`flavor?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/flavor?"></a>
``` clojure

(flavor? misc)
(flavor? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is added for flavor.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/herb?">`herb?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/herb?"></a>
``` clojure

(herb? misc)
(herb? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a herb.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/mash?">`mash?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/mash?"></a>
``` clojure

(mash? misc)
(mash? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the mash.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/other?">`other?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/other?"></a>
``` clojure

(other? misc)
(other? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is an unspecified type.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/primary?">`primary?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/primary?"></a>
``` clojure

(primary? misc)
(primary? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the primary fermentation.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/secondary?">`secondary?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/secondary?"></a>
``` clojure

(secondary? misc)
(secondary? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) was added during the secondary fermentation.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:use` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:use` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/spice?">`spice?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/spice?"></a>
``` clojure

(spice? misc)
(spice? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a spice.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.miscs/water-agent?">`water-agent?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.miscs/water-agent?"></a>
``` clojure

(water-agent? misc)
(water-agent? misc opts)
```


A predicate function to determine if a [misc](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/miscs.cljc) is a water agent.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.predicates.recipes">brewtility.predicates.recipes</a>


Predicate functions for [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) maps




## <a name="brewtility.predicates.recipes/all-grain?">`all-grain?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/all-grain?"></a>
``` clojure

(all-grain? recipe)
(all-grain? recipe opts)
```


A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was made entirely of grain.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.recipes/extract?">`extract?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/extract?"></a>
``` clojure

(extract? recipe)
(extract? recipe opts)
```


A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was made with extract only.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.recipes/forced-carbonation?">`forced-carbonation?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/forced-carbonation?"></a>
``` clojure

(forced-carbonation? recipe)
```


A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was forcefully carbonated.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :forced-carbonation field is not present, this function will explicitly return false.

## <a name="brewtility.predicates.recipes/garetz?">`garetz?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/garetz?"></a>
``` clojure

(garetz? recipe)
(garetz? recipe opts)
```


A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the garetz method.
   This method was originally developed by Mark Garetz, and is most accurate for High IBU recipes.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:ibu-method` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:ibu-method` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.recipes/partial-mash?">`partial-mash?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/partial-mash?"></a>
``` clojure

(partial-mash? recipe)
(partial-mash? recipe opts)
```


A predicate function to determine if a [recipe](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) was partially mashed.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.recipes/rager?">`rager?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/rager?"></a>
``` clojure

(rager? recipe)
(rager? recipe opts)
```


A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the Rager method.
   This method was originally developed by Jackie Rager, and is most accurate for partial mash recipes.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:ibu-method` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:ibu-method` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.recipes/tinseth?">`tinseth?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.recipes/tinseth?"></a>
``` clojure

(tinseth? recipe)
(tinseth? recipe opts)
```


A predicate function to determine if a [recipe's](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/recipes.cljc) IBU was calculated with the tinseth method.
   This method was originally developed by Glenn Tinseth, and is most accurate for all grain recipes.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:ibu-method` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:ibu-method` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.predicates.styles">brewtility.predicates.styles</a>


Predicate functions for [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) maps




## <a name="brewtility.predicates.styles/ale?">`ale?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.styles/ale?"></a>
``` clojure

(ale? style)
(ale? style opts)
```


A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for ales.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.styles/cider?">`cider?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.styles/cider?"></a>
``` clojure

(cider? style)
(cider? style opts)
```


A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for ciders.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.styles/lager?">`lager?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.styles/lager?"></a>
``` clojure

(lager? style)
(lager? style opts)
```


A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for lagers.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.styles/mead?">`mead?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.styles/mead?"></a>
``` clojure

(mead? style)
(mead? style opts)
```


A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for meads.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.styles/mixed?">`mixed?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.styles/mixed?"></a>
``` clojure

(mixed? style)
(mixed? style opts)
```


A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for blended/mixed beers.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.styles/wheat?">`wheat?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.styles/wheat?"></a>
``` clojure

(wheat? style)
(wheat? style opts)
```


A predicate function to determine if a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) is for wheat beers.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.predicates.waters">brewtility.predicates.waters</a>


Predicate functions for [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) maps.




## <a name="brewtility.predicates.waters/acidic?">`acidic?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.waters/acidic?"></a>
``` clojure

(acidic? water)
```


A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is acidic.

## <a name="brewtility.predicates.waters/alkaline?">`alkaline?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.waters/alkaline?"></a>
``` clojure

(alkaline? water)
```


A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is alkaline.

## <a name="brewtility.predicates.waters/neutral?">`neutral?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.waters/neutral?"></a>
``` clojure

(neutral? water)
```


A predicate function to determine if a [water](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/waters.cljc) profile is alkaline.

-----
# <a name="brewtility.predicates.yeasts">brewtility.predicates.yeasts</a>


Predicate functions for [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) maps




## <a name="brewtility.predicates.yeasts/ale?">`ale?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/ale?"></a>
``` clojure

(ale? yeast)
(ale? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for ale.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/champagne?">`champagne?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/champagne?"></a>
``` clojure

(champagne? yeast)
(champagne? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a common champagne strain.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/culture?">`culture?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/culture?"></a>
``` clojure

(culture? yeast)
(culture? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a living culture.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/dry?">`dry?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/dry?"></a>
``` clojure

(dry? yeast)
(dry? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in dry form.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/high-flocculation?">`high-flocculation?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/high-flocculation?"></a>
``` clojure

(high-flocculation? yeast)
(high-flocculation? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a high amount of floc.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/lager?">`lager?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/lager?"></a>
``` clojure

(lager? yeast)
(lager? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for lager.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/liquid?">`liquid?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/liquid?"></a>
``` clojure

(liquid? yeast)
(liquid? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in liquid form.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/low-flocculation?">`low-flocculation?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/low-flocculation?"></a>
``` clojure

(low-flocculation? yeast)
(low-flocculation? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a low amount of floc.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/medium-flocculation?">`medium-flocculation?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/medium-flocculation?"></a>
``` clojure

(medium-flocculation? yeast)
(medium-flocculation? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a medium amount of floc.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/slant?">`slant?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/slant?"></a>
``` clojure

(slant? yeast)
(slant? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is in slant form.
   Typically, this is a solid growth of yeast cells in a test tube.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/very-high-flocculation?">`very-high-flocculation?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/very-high-flocculation?"></a>
``` clojure

(very-high-flocculation? yeast)
(very-high-flocculation? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) generates a very high amount of floc.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/wheat?">`wheat?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/wheat?"></a>
``` clojure

(wheat? yeast)
(wheat? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is for wheat beers.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

## <a name="brewtility.predicates.yeasts/wine?">`wine?`</a> [:page_facing_up:](null)
<a name="brewtility.predicates.yeasts/wine?"></a>
``` clojure

(wine? yeast)
(wine? yeast opts)
```


A predicate function to determine if a [yeast](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/yeasts.cljc) is a common wine strain.

   An option map can be passed to this function as an optional second parameter.
   Supported keys are:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

-----
# <a name="brewtility.string">brewtility.string</a>


String comparison utilities




## <a name="brewtility.string/->spongebob-case">`->spongebob-case`</a> [:page_facing_up:](null)
<a name="brewtility.string/->spongebob-case"></a>
``` clojure

(->spongebob-case s)
```


Take a string `s` and coerce characters alternatively between lower and upper case.

   For example:

   ```clj
    (->spongebob-case "spongebob") ;; => "sPoNgEbOb"
   ```

## <a name="brewtility.string/->sporadic-case">`->sporadic-case`</a> [:page_facing_up:](null)
<a name="brewtility.string/->sporadic-case"></a>
``` clojure

(->sporadic-case s)
```


Take a string `s` and randomly coerce characters to either lower or upper case.

   For example:

   ```clj
    (->sporadic-case "hello world") ;; => "hElLo wOrLd"
   ```

## <a name="brewtility.string/includes?">`includes?`</a> [:page_facing_up:](null)
<a name="brewtility.string/includes?"></a>
``` clojure

(includes? s1 s2)
(includes? s1 s2 opts)
```


Checks to see if `s1` includes `s2` after each string has been modified by [`prepare-for-compare`](#brewtility.string/prepare-for-compare).

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s1` and `s2` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s1` and `s2` will be cast to a string via `str`. Defaults to false.

## <a name="brewtility.string/prepare-for-compare">`prepare-for-compare`</a> [:page_facing_up:](null)
<a name="brewtility.string/prepare-for-compare"></a>
``` clojure

(prepare-for-compare s)
(prepare-for-compare s {:keys [uppercase? coerce?]})
```


Takes a string `s`, trims it, and coerces it to lower case.

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s` will be cast to a string via `str`. Defaults to false.

## <a name="brewtility.string/same?">`same?`</a> [:page_facing_up:](null)
<a name="brewtility.string/same?"></a>
``` clojure

(same? s1 s2)
(same? s1 s2 opts)
```


Checks to see if `s1` and `s2` are equal after each string has been modified by [`prepare-for-compare`](#brewtility.string/prepare-for-compare).

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s1` and `s2` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s1` and `s2` will be cast to a string via `str`. Defaults to false.

-----
# <a name="brewtility.units">brewtility.units</a>


Namespace for converting between different units of measure




## <a name="brewtility.units/celsius->temperature-measurement">`celsius->temperature-measurement`</a> [:page_facing_up:](null)
<a name="brewtility.units/celsius->temperature-measurement"></a>

A map from measurement names to the implementation function which converts them from degrees celsius

## <a name="brewtility.units/convert-temperature">`convert-temperature`</a> [:page_facing_up:](null)
<a name="brewtility.units/convert-temperature"></a>
``` clojure

(convert-temperature temperature source-measurement target-measurement)
```


Given a `temperature` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[temperature-measurements]].

   This function will throw an exception if unsupported measurement values are passed.

## <a name="brewtility.units/convert-time">`convert-time`</a> [:page_facing_up:](null)
<a name="brewtility.units/convert-time"></a>
``` clojure

(convert-time time-val source-measurement target-measurement)
```


Given a `time-val` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[time-measurements]].

   This function will throw an exception if unsupported measurement values are passed.

## <a name="brewtility.units/convert-volume">`convert-volume`</a> [:page_facing_up:](null)
<a name="brewtility.units/convert-volume"></a>
``` clojure

(convert-volume volume source-measurement target-measurement)
```


Given a `volume` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[volume-measurements]].

   This function will throw an exception if unsupported measurement values are passed.

## <a name="brewtility.units/convert-weight">`convert-weight`</a> [:page_facing_up:](null)
<a name="brewtility.units/convert-weight"></a>
``` clojure

(convert-weight weight source-measurement target-measurement)
```


Given a `weight` in `source-measurement`, convert it to the `target-measurement`.
   Supported values for `source-measurement` and `target-measurement` are enumerated in [[weight-measurements]].

   This function will throw an exception if unsupported measurement values are passed.

## <a name="brewtility.units/suffix-types">`suffix-types`</a> [:page_facing_up:](null)
<a name="brewtility.units/suffix-types"></a>

The unit suffix types available across brewtility

## <a name="brewtility.units/systems-of-meaure">`systems-of-meaure`</a> [:page_facing_up:](null)
<a name="brewtility.units/systems-of-meaure"></a>

The systems of measure available across brewtility

## <a name="brewtility.units/temperature-measurement->celsius">`temperature-measurement->celsius`</a> [:page_facing_up:](null)
<a name="brewtility.units/temperature-measurement->celsius"></a>

A map from measurement names to the implementation function which converts them to degrees celsius

## <a name="brewtility.units/temperature-measurements">`temperature-measurements`</a> [:page_facing_up:](null)
<a name="brewtility.units/temperature-measurements"></a>

The temperature measurements available across brewtility

## <a name="brewtility.units/temperature-measurements->display-name">`temperature-measurements->display-name`</a> [:page_facing_up:](null)
<a name="brewtility.units/temperature-measurements->display-name"></a>

A map from measurement names to their full name and abbreviation

## <a name="brewtility.units/time-measurement->minute">`time-measurement->minute`</a> [:page_facing_up:](null)
<a name="brewtility.units/time-measurement->minute"></a>

A map from measurement names to doubles representing their fractional value to one minute

## <a name="brewtility.units/time-measurements">`time-measurements`</a> [:page_facing_up:](null)
<a name="brewtility.units/time-measurements"></a>

The time measurements available across brewtility

## <a name="brewtility.units/time-measurements->display-name">`time-measurements->display-name`</a> [:page_facing_up:](null)
<a name="brewtility.units/time-measurements->display-name"></a>

A map from measurement names to their full name and abbreviation

## <a name="brewtility.units/volume-measurement->litre">`volume-measurement->litre`</a> [:page_facing_up:](null)
<a name="brewtility.units/volume-measurement->litre"></a>

A map from measurement names to doubles representing their fractional value to one liter

## <a name="brewtility.units/volume-measurements">`volume-measurements`</a> [:page_facing_up:](null)
<a name="brewtility.units/volume-measurements"></a>

The volume measurements available across brewtility

## <a name="brewtility.units/volume-measurements->display-name">`volume-measurements->display-name`</a> [:page_facing_up:](null)
<a name="brewtility.units/volume-measurements->display-name"></a>

A map from measurement names to their full name and abbreviation

## <a name="brewtility.units/weight-measurement->kilogram">`weight-measurement->kilogram`</a> [:page_facing_up:](null)
<a name="brewtility.units/weight-measurement->kilogram"></a>

A map from measurement names to doubles representing their fractional value to one kilogram

## <a name="brewtility.units/weight-measurements">`weight-measurements`</a> [:page_facing_up:](null)
<a name="brewtility.units/weight-measurements"></a>

The weight measurements available across brewtility

## <a name="brewtility.units/weight-measurements->display-name">`weight-measurements->display-name`</a> [:page_facing_up:](null)
<a name="brewtility.units/weight-measurements->display-name"></a>

A map from measurement names to their full name and abbreviation

-----
# <a name="brewtility.wrapping">brewtility.wrapping</a>


Namespace for wrapping and unwrapping common-beer-format maps




## <a name="brewtility.wrapping/unwrap-equipment">`unwrap-equipment`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-equipment"></a>
``` clojure

(unwrap-equipment equipment-wrapper)
```


Unwrap an `equipment-wrapper` map into an `equipment` map.

## <a name="brewtility.wrapping/unwrap-fermentable">`unwrap-fermentable`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-fermentable"></a>
``` clojure

(unwrap-fermentable fermentable-wrapper)
```


Unwrap a `fermentable-wrapper` map into a `fermentable` map.

## <a name="brewtility.wrapping/unwrap-fermentables">`unwrap-fermentables`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-fermentables"></a>
``` clojure

(unwrap-fermentables fermentables-wrapper)
```


Unwrap a `fermentables-wrapper` map into a `fermentables` collection.

## <a name="brewtility.wrapping/unwrap-hop">`unwrap-hop`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-hop"></a>
``` clojure

(unwrap-hop hop-wrapper)
```


Unwrap a `hop-wrapper` map into a `hop` map.

## <a name="brewtility.wrapping/unwrap-hops">`unwrap-hops`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-hops"></a>
``` clojure

(unwrap-hops hops-wrapper)
```


Unwrap a `hops-wrapper` map into a `hops` collection.

## <a name="brewtility.wrapping/unwrap-mash">`unwrap-mash`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-mash"></a>
``` clojure

(unwrap-mash mash-wrapper)
```


Unwrap a `mash-wrapper` map into a `mash` map.

## <a name="brewtility.wrapping/unwrap-mash-step">`unwrap-mash-step`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-mash-step"></a>
``` clojure

(unwrap-mash-step mash-step-wrapper)
```


Unwrap a `mash-step-wrapper` map into a `mash-step` map.

## <a name="brewtility.wrapping/unwrap-mash-steps">`unwrap-mash-steps`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-mash-steps"></a>
``` clojure

(unwrap-mash-steps mash-steps-wrapper)
```


Unwrap a `mash-steps-wrapper` map into a `mash-steps` collection.

## <a name="brewtility.wrapping/unwrap-misc">`unwrap-misc`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-misc"></a>
``` clojure

(unwrap-misc misc-wrapper)
```


Unwrap a `misc-wrapper` map into a `misc` map.

## <a name="brewtility.wrapping/unwrap-miscs">`unwrap-miscs`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-miscs"></a>
``` clojure

(unwrap-miscs miscs-wrapper)
```


Unwrap a `miscs-wrapper` map into a `miscs` collection.

## <a name="brewtility.wrapping/unwrap-recipe">`unwrap-recipe`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-recipe"></a>
``` clojure

(unwrap-recipe recipe-wrapper)
```


Unwrap a `recipe-wrapper` map into a `recipe` map.

## <a name="brewtility.wrapping/unwrap-recipes">`unwrap-recipes`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-recipes"></a>
``` clojure

(unwrap-recipes recipes-wrapper)
```


Unwrap a `recipes-wrapper` map into a `recipes` collection.

## <a name="brewtility.wrapping/unwrap-style">`unwrap-style`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-style"></a>
``` clojure

(unwrap-style style-wrapper)
```


Unwrap a `style-wrapper` map into a `style` map.

## <a name="brewtility.wrapping/unwrap-styles">`unwrap-styles`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-styles"></a>
``` clojure

(unwrap-styles styles-wrapper)
```


Unwrap a `styles-wrapper` map into a `styles` collection.

## <a name="brewtility.wrapping/unwrap-water">`unwrap-water`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-water"></a>
``` clojure

(unwrap-water water-wrapper)
```


Unwrap a `water-wrapper` map into a `water` map.

## <a name="brewtility.wrapping/unwrap-waters">`unwrap-waters`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-waters"></a>
``` clojure

(unwrap-waters waters-wrapper)
```


Unwrap a `waters-wrapper` map into a `waters` collection.

## <a name="brewtility.wrapping/unwrap-yeast">`unwrap-yeast`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-yeast"></a>
``` clojure

(unwrap-yeast yeast-wrapper)
```


Unwrap a `yeast-wrapper` map into a `yeast` map.

## <a name="brewtility.wrapping/unwrap-yeasts">`unwrap-yeasts`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/unwrap-yeasts"></a>
``` clojure

(unwrap-yeasts yeasts-wrapper)
```


Unwrap a `yeasts-wrapper` map into a `yeasts` collection.

## <a name="brewtility.wrapping/wrap-equipment">`wrap-equipment`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-equipment"></a>
``` clojure

(wrap-equipment equipment)
```


Wrap an `equipment` map into an `equipment-wrapper` map.

## <a name="brewtility.wrapping/wrap-fermentable">`wrap-fermentable`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-fermentable"></a>
``` clojure

(wrap-fermentable fermentable)
```


Wrap a `fermentable` map into a `fermentable-wrapper` map.

## <a name="brewtility.wrapping/wrap-fermentables">`wrap-fermentables`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-fermentables"></a>
``` clojure

(wrap-fermentables fermentables)
```


Wrap a `fermentables` collection into a `fermentables-wrapper` map.

## <a name="brewtility.wrapping/wrap-hop">`wrap-hop`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-hop"></a>
``` clojure

(wrap-hop hop)
```


Wrap a `hop` map into a `hop-wrapper` map.

## <a name="brewtility.wrapping/wrap-hops">`wrap-hops`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-hops"></a>
``` clojure

(wrap-hops hops)
```


Wrap a `hops` collection into a `hops-wrapper` map.

## <a name="brewtility.wrapping/wrap-mash">`wrap-mash`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-mash"></a>
``` clojure

(wrap-mash mash)
```


Wrap a `mash` map into a `mash-wrapper` map.

## <a name="brewtility.wrapping/wrap-mash-step">`wrap-mash-step`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-mash-step"></a>
``` clojure

(wrap-mash-step mash-step)
```


Wrap a `mash-step` map into a `mash-step-wrapper` map.

## <a name="brewtility.wrapping/wrap-mash-steps">`wrap-mash-steps`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-mash-steps"></a>
``` clojure

(wrap-mash-steps mash-steps)
```


Wrap a `mash-steps` collection into a `mash-steps-wrapper` map.

## <a name="brewtility.wrapping/wrap-misc">`wrap-misc`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-misc"></a>
``` clojure

(wrap-misc misc)
```


Wrap a `misc` map into a `misc-wrapper` map.

## <a name="brewtility.wrapping/wrap-miscs">`wrap-miscs`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-miscs"></a>
``` clojure

(wrap-miscs miscs)
```


Wrap a `miscs` collection into a `miscs-wrapper` map.

## <a name="brewtility.wrapping/wrap-recipe">`wrap-recipe`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-recipe"></a>
``` clojure

(wrap-recipe recipe)
```


Wrap a `recipe` map into a `recipe-wrapper` map.

## <a name="brewtility.wrapping/wrap-recipes">`wrap-recipes`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-recipes"></a>
``` clojure

(wrap-recipes recipes)
```


Wrap a `recipes` collection into a `recipes-wrapper` map.

## <a name="brewtility.wrapping/wrap-style">`wrap-style`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-style"></a>
``` clojure

(wrap-style style)
```


Wrap a `style` map into a `style-wrapper` map.

## <a name="brewtility.wrapping/wrap-styles">`wrap-styles`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-styles"></a>
``` clojure

(wrap-styles styles)
```


Wrap a `styles` collection into a `styles-wrapper` map.

## <a name="brewtility.wrapping/wrap-water">`wrap-water`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-water"></a>
``` clojure

(wrap-water water)
```


Wrap a `water` map into a `water-wrapper` map.

## <a name="brewtility.wrapping/wrap-waters">`wrap-waters`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-waters"></a>
``` clojure

(wrap-waters waters)
```


Wrap a `waters` collection into a `waters-wrapper` map.

## <a name="brewtility.wrapping/wrap-yeast">`wrap-yeast`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-yeast"></a>
``` clojure

(wrap-yeast yeast)
```


Wrap a `yeast` map into a `yeast-wrapper` map.

## <a name="brewtility.wrapping/wrap-yeasts">`wrap-yeasts`</a> [:page_facing_up:](null)
<a name="brewtility.wrapping/wrap-yeasts"></a>
``` clojure

(wrap-yeasts yeasts)
```


Wrap a `yeasts` collection into a `yeasts-wrapper` map.
