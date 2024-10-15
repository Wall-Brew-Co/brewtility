(ns brewtility.predicates.options
  "A namespace of static symbolic keywords and values used in the option maps throughout brewtility.

   Implemented as part of the Symbolic Keyword pattern."
  {:added "2.2"}
  (:require [com.wallbrew.spoon.string :as string]))


;; Option keys

(def uppercase?
  "A keyword used to determine if a string comparison should be cast to UPPERCASE instead of lowercase.

   This option is inherited from the functions in `com.wallbrew.spoon.string`"
  string/cast-to-uppercase?)

