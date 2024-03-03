(ns brewtility.enrich.impl
  "Function to help minimize repeated code in enricher functions.

   Not intended for public consumption."
  {:no-doc              true
   :added               "2.1"
   :implementation-only true}
  (:require [brewtility.units :as units]
            [brewtility.units.color :as color]
            [brewtility.units.options :as options]
            [brewtility.units.pressure :as pressure]
            [brewtility.units.specific-gravity :as specific-gravity]
            [brewtility.units.temperature :as temperature]
            [brewtility.units.time :as time]
            [brewtility.units.volume :as volume]
            [brewtility.units.weight :as weight]))


(def default-display-options
  "The default display options used for all enrichers."
  {options/precision 3
   options/suffix    options/short})


(def value-key
  "The key to source data from in `enrich-displayable-*` functions"
  :value-key)


(def low-value-key
  "The key to source data from in `enrich-displayable-*` functions for the lower end of the range"
  :low-value-key)


(def high-value-key
  "The key to source data from in `enrich-displayable-*` functions for the higher end of the range"
  :high-value-key)


(def display-key
  "The key to store displayable data in in `-enrich-displayable-*` functions"
  :display-key)


(def fine-grain-target-units
  "The target units to use for fine-grain toggling of displayable units in `enrich-displayable-*` functions"
  :fine-grain-target-units)


(def fine-grain-precision
  "The suffix to use for fine-grain setting of precision in `-enrich-displayable-*` functions"
  :fine-grain-precision)


(def fine-grain-suffix
  "The suffix to use for fine-grain setting of precision in `enrich-displayable-*` functions"
  :fine-grain-suffix)


(def default-color-by-system
  "The default color to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/srm
   options/metric               options/srm
   options/us-customary         options/srm
   options/international-system options/srm})


(def default-pressure-by-system
  "The default pressure to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/psi
   options/metric               options/kilopascal
   options/us-customary         options/psi
   options/international-system options/kilopascal})


(def default-specific-gravity-by-system
  "The default specific gravity to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/specific-gravity
   options/metric               options/specific-gravity
   options/us-customary         options/specific-gravity
   options/international-system options/specific-gravity})


(def default-temperature-by-system
  "The default temperature to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/fahrenheit
   options/metric               options/celsius
   options/us-customary         options/fahrenheit
   options/international-system options/celsius})


(def default-time-by-system
  "The default time to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/minute
   options/metric               options/minute
   options/us-customary         options/minute
   options/international-system options/minute})


(def default-volume-by-system
  "The default volume to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/imperial-gallon
   options/metric               options/litre
   options/us-customary         options/american-gallon
   options/international-system options/litre})


(def default-weight-by-system
  "The default weight to use for each system in `enrich-displayable-*` functions."
  {options/imperial             options/pound
   options/metric               options/kilogram
   options/us-customary         options/pound
   options/international-system options/kilogram})


(def beer-xml-standard-units
  "The standard units for each measurement type in BeerXML."
  {options/color            options/srm
   options/pressure         options/kilopascal
   options/specific-gravity options/specific-gravity
   options/temperature      options/celsius
   options/time             options/minute
   options/volume           options/liter
   options/weight           options/kilogram})


;; TODO: Pluralize strings
(defn ->displayable-units
  "Convert a unit then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true}
  ([measurement-type source-value source-units target-units]
   (->displayable-units measurement-type source-value source-units target-units default-display-options))
  ([measurement-type source-value source-units target-units opts]
   (let [converted-value (units/convert measurement-type source-value source-units target-units)]
     (units/display measurement-type converted-value target-units opts))))


(defn target-unit-error
  "A function to accumulate error messages in `error-map` if `target-units` is not a valid unit for `conversion-type`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["systems-of-meaure-error"
              "precision-error"
              "suffix-error"]}
  [error-map conversion-type target-units]
  (let [allowed-values (case conversion-type
                         :color            color/measurements
                         :pressure         pressure/measurements
                         :specific-gravity specific-gravity/measurements
                         :temperature      temperature/measurements
                         :time             time/measurements
                         :volume           volume/measurements
                         :weight           weight/measurements)
        error-msg      (str "Invalid target unit for "
                            (name conversion-type)
                            " conversion : `"
                            target-units
                            "`. Allowed values are: "
                            allowed-values)]
    (assoc error-map :target-units error-msg)))


(defn source-unit-error
  "A function to accumulate error messages in `error-map` if `source-units` is not a valid unit for `conversion-type`.

   Note: This is only used for color conversion at this time.
         BeerXML prescribes the system of measure for all other units."
  {:added    "2.1"
   :no-doc   true
   :see-also ["systems-of-meaure-error"
              "precision-error"
              "suffix-error"
              "target-unit-error"]}
  [error-map conversion-type source-units]
  (let [allowed-values (case conversion-type
                         :color            color/measurements
                         :pressure         pressure/measurements
                         :specific-gravity specific-gravity/measurements
                         :temperature      temperature/measurements
                         :time             time/measurements
                         :volume           volume/measurements
                         :weight           weight/measurements)
        error-msg      (str "Invalid source unit for "
                            (name conversion-type)
                            " conversion : `"
                            source-units
                            "`. Allowed values are: "
                            allowed-values)]
    (assoc error-map :source-units error-msg)))


(defn systems-of-meaure-error
  "A function to accumulate error messages in `error-map` if `system-of-measure` is not valid for `conversion-type`."
  {:added    "2.1"
   :no-doc   true
   :see-also ["target-unit-error"
              "precision-error"
              "suffix-error"]}
  [error-map conversion-type system-of-measure]
  (let [error-msg (str "Invalid system of measure for "
                       (name conversion-type)
                       " conversion : "
                       system-of-measure
                       ". Allowed values are:"
                       options/systems-of-measure)]
    (assoc error-map options/system-of-measure error-msg)))


(defn precision-error
  "A function to accumulate error messages in `error-map` if `precision` is not an integer."
  {:added    "2.1"
   :no-doc   true
   :see-also ["target-unit-error"
              "systems-of-meaure-error"
              "suffix-error"]}
  [error-map conversion-type precision]
  (let [error-msg (str "Invalid precision for "
                       (name conversion-type)
                       " conversion : "
                       precision
                       ". Must be an integer.")]
    (assoc error-map options/precision error-msg)))


(defn suffix-error
  "A function to accumulate error messages in `error-map` if `suffix` is not a valid choice."
  {:added    "2.1"
   :no-doc   true
   :see-also ["target-unit-error"
              "systems-of-meaure-error"
              "precision-error"]}
  [error-map conversion-type suffix]
  (let [error-msg (str "Invalid suffix type for "
                       (name conversion-type)
                       " conversion : "
                       suffix
                       ". Allowed values are: "
                       options/supported-suffixes)]
    (assoc error-map :suffix error-msg)))


;; Enricher argument validation functions

(defn valid-unit-for-measurement-type?
  "A functions that confirms if a given `unit` is valid for a given `measurement-type`.
   If the `unit` is not valid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
   :no-doc   false}
  [measurement-type unit]
  (case measurement-type
    :color            (contains? color/measurements unit)
    :pressure         (contains? pressure/measurements unit)
    :specific-gravity (contains? specific-gravity/measurements unit)
    :temperature      (contains? temperature/measurements unit)
    :time             (contains? time/measurements unit)
    :volume           (contains? volume/measurements unit)
    :weight           (contains? weight/measurements unit)
    (throw (ex-info "Unsupported unit system"
                    {:measurement-type measurement-type
                     :allowed-values   options/measurement-types}))))


(defn parse-enrich-displayable-units-opts
  "A function to parse the options map passed to `->displayable-units`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-pressure"]}
  [measurement-type
   {:keys [target-units source-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-source?    (valid-unit-for-measurement-type? measurement-type source-units)
        valid-target?    (valid-unit-for-measurement-type? measurement-type target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error measurement-type target-units)
                           (not valid-source?)    (source-unit-error measurement-type source-units)
                           (not valid-system?)    (systems-of-meaure-error measurement-type system-of-measure)
                           (not valid-precision?) (precision-error measurement-type precision)
                           (not valid-suffix?)    (suffix-error measurement-type suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid enrichment options for ->displayable-units: " errors)))))


(defn get-default-unit-by-measurement-type-and-system
  "A function to get the default unit for a given `measurement-type` and `system-of-measure`.
   If the measurement type is not supported, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-pressure"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-temperature"
              "enrich-displayable-time"
              "enrich-displayable-volume"
              "enrich-displayable-weight"
              "enrich-displayable-range"]}
  [measurement-type system-of-measure]
  (case measurement-type
    :color            (get default-color-by-system system-of-measure)
    :pressure         (get default-pressure-by-system system-of-measure)
    :specific-gravity (get default-specific-gravity-by-system system-of-measure)
    :temperature      (get default-temperature-by-system system-of-measure)
    :time             (get default-time-by-system system-of-measure)
    :volume           (get default-volume-by-system system-of-measure)
    :weight           (get default-weight-by-system system-of-measure)
    (throw (ex-info "Unsupported unit system"
                    {:measurement-type measurement-type
                     :allowed-values   options/measurement-types}))))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-units
  "A function to enrich a `source-data` map with a displayable value for a given `measurement-type`.
   This function will convert the `source-value` from `source-units` to `target-units` and store the result in `display-key`.
   If the `source-value` is not present, the `source-data` is returned unmodified.
   The function will default to the following display options:

   - `system-of-measure` : `:us-customary`
   - `suffix` : `:short`
   - `precision` : `3`
   - `source-units` : The BeerXML default system of measure for the given `measurement-type` (e.g. `:kilogram` for `:weight`)

   Customization of function behavior is handled by the following keys in the `opts` map, and will always take precedence over defaults:

   - `fine-grain-precsion` : The precision to use for this displayable value. Enricher functions control their behavior off of uniquely named keys, so behavior can be customized per field
   - `fine-grain-suffix` : The suffix to use for this displayable value. Enricher functions control their behavior off of uniquely named keys, so behavior can be customized per field
   - `fine-grain-target-units` : The target units to use for this displayable value. Enricher functions control their behavior off of uniquely named keys, so behavior can be customized per field"
  {:added "2.1"
   :no-doc true}
  [measurement-type
   source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key source-units]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-units (get-default-unit-by-measurement-type-and-system measurement-type system-of-measure)
          beer-xml-source-units   (get beer-xml-standard-units measurement-type)
          source-units            (or source-units beer-xml-source-units)
          target-units            (or fine-grain-target-units system-of-measure-units)
          precision               (or fine-grain-precision precision)
          suffix                  (or fine-grain-suffix suffix)
          opts                    (parse-enrich-displayable-units-opts
                                    measurement-type
                                    {:target-units             target-units
                                     :source-units             source-units
                                     options/system-of-measure system-of-measure
                                     options/precision         precision
                                     options/suffix            suffix})]
      (assoc source-data display-key (->displayable-units measurement-type source-value source-units target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-range
  "A function to enrich a `source-data` map with a displayable range for a given `measurement-type`.
     This function will convert the `low-source-value` and `high-source-value` from `source-units` to `target-units` and store the result in `display-key`.
     If the `source-value` is not present, the `source-data` is returned unmodified.
     The function will default to the following display options:

     - `system-of-measure` : `:us-customary`
     - `suffix` : `:short`
     - `precision` : `3`
     - `source-units` : The BeerXML default system of measure for the given `measurement-type` (e.g. `:kilogram` for `:weight`)

     Customization of function behavior is handled by the following keys in the `opts` map, and will always take precedence over defaults:

     - `fine-grain-precsion` : The precision to use for this displayable range. Enricher functions control their behavior off of uniquely named keys, so behavior can be customized per field
     - `fine-grain-suffix` : The suffix to use for this displayable range. Enricher functions control their behavior off of uniquely named keys, so behavior can be customized per field
     - `fine-grain-target-units` : The target units to use for this displayable range. Enricher functions control their behavior off of uniquely named keys, so behavior can be customized per field"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-units"]}
  [measurement-type
   source-data
   {:keys [low-value-key high-value-key display-key system-of-measure suffix precision fine-grain-target-units fine-grain-precision fine-grain-suffix source-units]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (let [low-source-value  (get source-data low-value-key)
        high-source-value (get source-data high-value-key)]
    (if (and low-source-value high-source-value)
      (let [system-of-measure-units (get-default-unit-by-measurement-type-and-system measurement-type system-of-measure)
            beer-xml-source-units   (get beer-xml-standard-units measurement-type)
            source-units            (or source-units beer-xml-source-units)
            target-units            (or fine-grain-target-units system-of-measure-units)
            precision               (or fine-grain-precision precision)
            suffix                  (or fine-grain-suffix suffix)
            opts                    (parse-enrich-displayable-units-opts
                                      measurement-type
                                      {:target-units             target-units
                                       :source-units             source-units
                                       options/system-of-measure system-of-measure
                                       options/precision         precision
                                       options/suffix            suffix})
            converted-low-value     (units/convert measurement-type low-source-value source-units target-units {options/precision precision})
            displayable-high-value  (->displayable-units measurement-type high-source-value source-units target-units opts)
            displayable-range       (str converted-low-value " - " displayable-high-value)]
        (assoc source-data display-key displayable-range))
      source-data)))
