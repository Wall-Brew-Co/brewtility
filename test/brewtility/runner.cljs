(ns brewtility.runner
  "The ClojureScript test runner for brewtility.

   This namespace is responsible for running all of the ClojureScript tests.
   To add new test namespaces, add them to the `:require` and `doo-tests` clauses below."
  (:require [brewtility.calculations-test]
            [brewtility.data.equipment]
            [brewtility.data.fermentables]
            [brewtility.data.hops]
            [brewtility.data.mash]
            [brewtility.data.miscs]
            [brewtility.data.recipes]
            [brewtility.data.styles]
            [brewtility.data.waters]
            [brewtility.data.yeasts]
            [brewtility.enrich.equipment-test]
            [brewtility.enrich.fermentables-test]
            [brewtility.enrich.hops-test]
            [brewtility.enrich.mash-test]
            [brewtility.enrich.miscs-test]
            [brewtility.enrich.waters-test]
            [brewtility.enrich.yeasts-test]
            [brewtility.precision-test]
            [brewtility.predicates.equipment-test]
            [brewtility.predicates.fermentables-test]
            [brewtility.predicates.hops-test]
            [brewtility.predicates.impl-test]
            [brewtility.predicates.mash-test]
            [brewtility.predicates.miscs-test]
            [brewtility.predicates.options-test]
            [brewtility.predicates.recipes-test]
            [brewtility.predicates.styles-test]
            [brewtility.predicates.waters-test]
            [brewtility.predicates.yeasts-test]
            [brewtility.units-test]
            [brewtility.units.alcohol-content-test]
            [brewtility.units.bitterness-test]
            [brewtility.units.carbonation-test]
            [brewtility.units.color-test]
            [brewtility.units.pressure-test]
            [brewtility.units.specific-gravity-test]
            [brewtility.units.temperature-test]
            [brewtility.units.time-test]
            [brewtility.units.volume-test]
            [brewtility.units.weight-test]
            [brewtility.wrapping-test]
            [doo.runner :refer-macros [doo-tests]]))


;; This should match the :require above (aside from doo itself)
(doo-tests 'brewtility.calculations-test
           'brewtility.data.equipment
           'brewtility.data.fermentables
           'brewtility.data.hops
           'brewtility.data.mash
           'brewtility.data.miscs
           'brewtility.data.recipes
           'brewtility.data.styles
           'brewtility.data.waters
           'brewtility.data.yeasts
           'brewtility.enrich.equipment-test
           'brewtility.enrich.fermentables-test
           'brewtility.enrich.hops-test
           'brewtility.enrich.mash-test
           'brewtility.enrich.miscs-test
           'brewtility.enrich.waters-test
           'brewtility.enrich.yeasts-test
           'brewtility.precision-test
           'brewtility.predicates.equipment-test
           'brewtility.predicates.fermentables-test
           'brewtility.predicates.hops-test
           'brewtility.predicates.impl-test
           'brewtility.predicates.mash-test
           'brewtility.predicates.miscs-test
           'brewtility.predicates.options-test
           'brewtility.predicates.recipes-test
           'brewtility.predicates.styles-test
           'brewtility.predicates.waters-test
           'brewtility.predicates.yeasts-test
           'brewtility.units.alcohol-content-test
           'brewtility.units.bitterness-test
           'brewtility.units.carbonation-test
           'brewtility.units-test
           'brewtility.units.bitterness-test
           'brewtility.units.carbonation-test
           'brewtility.units.color-test
           'brewtility.units.pressure-test
           'brewtility.units.specific-gravity-test
           'brewtility.units.temperature-test
           'brewtility.units.time-test
           'brewtility.units.volume-test
           'brewtility.units.weight-test
           'brewtility.wrapping-test)
