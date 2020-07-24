(ns brewtility.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [brewtility.calculations-test]
            [brewtility.color-test]
            [brewtility.core-test]
            [brewtility.precision-test]
            [brewtility.units-test]))

(doo-tests 'brewtility.calculations-test
           'brewtility.color-test
           'brewtility.core-test
           'brewtility.precision-test
           'brewtility.units-test)
