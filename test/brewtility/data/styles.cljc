(ns brewtility.data.styles
  "Namespace for static and generative test data for `common-beer-format.stylespec/*` specs."
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test :refer [deftest is testing]]
            [com.wallbrew.spoon.spec :as spoon.spec]
            [common-beer-format.styles :as style.format]))


(def sample-style
  "A hard-coded sample style for static unit tests"
  {styles.format/abv-max         5.5
   styles.format/abv-min         3.2
   styles.format/carb-max        2.1
   styles.format/carb-min        1.6
   styles.format/category        "Stout"
   styles.format/category-number "16"
   styles.format/color-max       200.0
   styles.format/color-min       35.0
   styles.format/fg-max          1.011
   styles.format/fg-min          1.007
   styles.format/ibu-max         50.0
   styles.format/ibu-min         30.0
   styles.format/name            "Dry Stout"
   styles.format/notes           "Famous Irish Stout. Dry, roasted, almost coffee like flavor."
   styles.format/og-max          1.050
   styles.format/og-min          1.035
   styles.format/style-guide     "BJCP"
   styles.format/style-letter    "A"
   styles.format/type            styles.format/ale
   styles.format/version         1})


(def sample-style-wrapper
  "A hard-coded sample style-wrapper for static unit tests"
  {styles.format/style sample-style})


(def sample-styles
  "A hard-coded sample styles for static unit tests"
  [sample-style-wrapper])


(def sample-styles-wrapper
  "A hard-coded sample styles-wrapper for static unit tests"
  {styles.format/styles sample-styles})


(defn generate-style
  "Generate a random style object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::styles.format/style)))


(defn generate-style-wrapper
  "Generate a random style-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::styles.format/style-wrapper)))


(defn generate-styles
  "Generate a random styles object"
  {:added  "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::styles.format/styles)))


(defn generate-styles-wrapper
  "Generate a random styles-wrapper object"
  {:added "1.4"
   :no-doc true}
  []
  (gen/generate (spec/gen ::styles.format/styles-wrapper)))


(deftest static-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure static test data conforms"
    (is (spoon.spec/test-valid? ::styles.format/style sample-style)
        "Static test data should conform to common-beer-format.style/style")
    (is (spoon.spec/test-valid? ::styles.format/style-wrapper sample-style-wrapper)
        "Static test data should conform to common-beer-format.style/style-wrapper")
    (is (spoon.spec/test-valid? ::styles.format/styles sample-styles)
        "Static test data should conform to common-beer-format.style/style")
    (is (spoon.spec/test-valid? ::styles.format/styles-wrapper sample-styles-wrapper)
        "Static test data should conform to common-beer-format.style/style-wrapper")))


(deftest generative-test-data-check
  (testing "Since this library assumes common-beer-format data is utilized, make sure generative test data conforms"
    (is (spoon.spec/test-valid? ::styles.format/style (generate-style))
        "Generative test data should conform to common-beer-format.style/style")
    (is (spoon.spec/test-valid? ::styles.format/style-wrapper (generate-style-wrapper))
        "Generative test data should conform to common-beer-format.style/style-wrapper")
    (is (spoon.spec/test-valid? ::styles.format/styles (generate-styles))
        "Generative test data should conform to common-beer-format.style/style")
    (is (spoon.spec/test-valid? ::styles.format/styles-wrapper (generate-styles-wrapper))
        "Generative test data should conform to common-beer-format.style/style-wrapper")))
