(ns brewtility.string
  "String comparison utilities"
  {:added "1.3"}
  (:require [clojure.string :as str]))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def cast-to-uppercase?
  "An option map key to cast strings to UPPER CASE in `prepare-for-compare`.
   Commonly, this is set for the `options` argument of `same?` and `includes?`.
   This option will be enabled if this key's value is truthy, and is disabled by default."
  :uppercase?)

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def coerce-to-compare?
  "An option map key to coerce values to strings in `prepare-for-compare`.
   Commonly, this is set for the `options` argument of `same?` and `includes?`.
   This option will be enabled if this key's value is truthy, and is disabled by default."
  :coerce?)


(defn prepare-for-compare
  "Takes a string `s`, trims it, and coerces it to lower case.

   An option map may be passed as an optional second argument.
     The following keys are supported:

   - `:uppercase?` - If true, `s` will be coerced to upper case. Defaults to false.
   - `:coerce?` - If true, `s` will be cast to a string via `str`. Defaults to false."
  {:added    "1.3"
   :see-also ["same?"
              "includes?"
              "cast-to-uppercase?"
              "coerce-to-compare?"]}
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
   :see-also ["includes?"
              "prepare-for-compare"
              "cast-to-uppercase?"
              "coerce-to-compare?"]}
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
   :see-also ["includes?"
              "prepare-for-compare"
              "cast-to-uppercase?"
              "coerce-to-compare?"]}
  ([s1 s2] (includes? s1 s2 {}))

  ([s1 s2 opts]
   (str/includes? (prepare-for-compare s1 opts) (prepare-for-compare s2 opts))))


(defn ->sporadic-case
  "Take a string `s` and randomly coerce characters to either lower or upper case.

   For example:

   ```clj
    (->sporadic-case \"hello world\") ;; => \"hElLo wOrLd\"
   ```"
  {:added    "1.3"
   :see-also ["->spongebob-case"]}
  [^String s]
  (letfn [(random-case
            [l]
            (if (rand-nth [true false])
              (str/upper-case l)
              (str/lower-case l)))]
    (->> s
         seq
         (map random-case)
         (apply str))))


(defn ->spongebob-case
  "Take a string `s` and coerce characters alternatively between lower and upper case.

   For example:

   ```clj
    (->spongebob-case \"spongebob\") ;; => \"sPoNgEbOb\"
   ```"
  {:added    "1.3"
   :see-also ["->sporadic-case"]}
  [^String s]
  (letfn [(spongebob-case
            [acc l]
            (let [casing-fn (if (odd? (count acc)) str/upper-case str/lower-case)]
              (str acc (casing-fn l))))]
    (reduce spongebob-case "" (seq s))))
