(ns brewtility.runner
  (:require [brewtility.calculations-test]
            [brewtility.color-test]
            [brewtility.data.equipment]
            [brewtility.data.fermentables]
            [brewtility.data.hops]
            [brewtility.data.mash]
            [brewtility.data.miscs]
            [brewtility.data.recipes]
            [brewtility.data.styles]
            [brewtility.data.waters]
            [brewtility.data.yeasts]
            [brewtility.precision-test]
            [brewtility.predicates.equipment-test]
            [brewtility.predicates.fermentables-test]
            [brewtility.predicates.hops-test]
            [brewtility.predicates.impl-test]
            [brewtility.predicates.mash-test]
            [brewtility.predicates.miscs-test]
            [brewtility.predicates.recipes-test]
            [brewtility.predicates.styles-test]
            [brewtility.predicates.waters-test]
            [brewtility.predicates.yeasts-test]
            [brewtility.units-test]
            [brewtility.wrapping-test]
            [doo.runner :refer-macros [doo-tests]]))


(doo-tests 'brewtility.calculations-test
           'brewtility.color-test
           'brewtility.data.equipment
           'brewtility.data.fermentables
           'brewtility.data.hops
           'brewtility.data.mash
           'brewtility.data.miscs
           'brewtility.data.recipes
           'brewtility.data.styles
           'brewtility.data.waters
           'brewtility.data.yeasts
           'brewtility.precision-test
           'brewtility.predicates.equipment-test
           'brewtility.predicates.fermentables-test
           'brewtility.predicates.hops-test
           'brewtility.predicates.impl-test
           'brewtility.predicates.mash-test
           'brewtility.predicates.miscs-test
           'brewtility.predicates.recipes-test
           'brewtility.predicates.styles-test
           'brewtility.predicates.waters-test
           'brewtility.predicates.yeasts-test
           'brewtility.units-test
           'brewtility.wrapping-test)
