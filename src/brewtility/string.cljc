(ns brewtility.string
  "String comparison utilities"
  {:added "1.3"}
  (:require [clojure.string :as str]))


(defn prepare-for-compare
  "Takes a string `s`, trims it, and coerces it to lower case.

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s` will be cast to a string via `str`. Defaults to false."
  {:added    "1.3"
   :see-also ["same?" "includes?"]}
  ([s] (prepare-for-compare s {}))

  ([s {:keys [uppercase? coerce?]}]
   (let [casing-fn (if uppercase? str/upper-case str/lower-case)
         s'        (if coerce? (str s) s)]
     (-> s' str/trim casing-fn))))


(defn same?
  "Checks to see if `s1` and `s2` are equal after each string has been modified by `prepare-for-compare`.

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s1` and `s2` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s1` and `s2` will be cast to a string via `str`. Defaults to false."
  {:added    "1.3"
   :see-also ["includes?"]}
  ([s1 s2] (same? s1 s2 {}))

  ([s1 s2 opts]
   (= (prepare-for-compare s1 opts)
      (prepare-for-compare s2 opts))))


(defn includes?
  "Checks to see if `s1` includes `s2` after each string has been modified by `prepare-for-compare`.

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s1` and `s2` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s1` and `s2` will be cast to a string via `str`. Defaults to false."
  {:added    "1.3"
   :see-also ["includes?"]}
  ([s1 s2] (includes? s1 s2 {}))

  ([s1 s2 opts]
   (str/includes? (prepare-for-compare s1 opts) (prepare-for-compare s2 opts))))
