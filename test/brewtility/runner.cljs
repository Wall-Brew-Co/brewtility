(ns brewtility.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [brewtility.core-test]))

(doo-tests 'brewtility.core-test)
