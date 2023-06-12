(ns brewtility.enrich.impl
  {:no-doc              true
   :added               "2.1"
   :implementation-only true}
  (:require [brewtility.units.color :as color]
            [brewtility.units.options :as options]
            [brewtility.units.temperature :as temperature]
            [brewtility.units.time :as time]
            [brewtility.units.volume :as volume]
            [brewtility.units.weight :as weight]))


(def default-display-options
  "The default display options used for all enrichers."
  {options/precision 3
   options/suffix    options/short})


(def ^:const value-key
  "The key to source data from in `->displayable` functions"
  :value-key)


(def ^:const display-key
  "The key to store displayable data in in `->displayable` functions"
  :display-key)


(def ^:const fine-grain-target-units
  "The target units to use for fine-grain toggling of displayable units in `->displayable` functions"
  :fine-grain-target-units)


(def ^:const fine-grain-precision
  "The suffix to use for fine-grain setting of precision in `->displayable` functions"
  :fine-grain-precision)


(def ^:const fine-grain-suffix
  "The suffix to use for fine-grain setting of precision in `->displayable` functions"
  :fine-grain-suffix)


;; TODO: Pluralize strings
(defn ->displayable-volume
  "Convert a volume then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-weight"
              "->displayable-time"
              "->displayable-temperature"
              "->displayable-color"]}
  ([source-value source-units target-units]
   (->displayable-volume source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (volume/convert source-units target-units)
       (volume/display target-units opts))))


;; TODO: Pluralize strings
(defn ->displayable-weight
  "Convert a weight then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-volume"
              "->displayable-time"
              "->displayable-temperature"
              "->displayable-color"]}
  ([source-value source-units target-units]
   (->displayable-weight source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (weight/convert source-units target-units)
       (weight/display target-units opts))))


;; TODO: Pluralize strings
(defn ->displayable-time
  "Convert a time then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-volume"
              "->displayable-weight"
              "->displayable-temperature"
              "->displayable-color"]}
  ([source-value source-units target-units]
   (->displayable-time source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (time/convert source-units target-units)
       (time/display target-units opts))))


;; TODO: Pluralize strings
(defn ->displayable-temperature
  "Convert a temperature then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-volume"
              "->displayable-weight"
              "->displayable-time"
              "->displayable-color"]}
  ([source-value source-units target-units]
   (->displayable-temperature source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (temperature/convert source-units target-units)
       (temperature/display target-units opts))))

;; TODO: Pluralize strings
(defn ->displayable-color
  "Convert a color then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-volume"
              "->displayable-weight"
              "->displayable-time"
              "->displayable-temperature"]}
  ([source-value source-units target-units]
   (->displayable-color source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (color/convert source-units target-units)
       (color/display target-units opts))))


(def ^:private default-volume-by-system
  {options/imperial             options/imperial-gallon
   options/metric               options/litre
   options/us-customary         options/american-gallon
   options/international-system options/litre})


(def ^:private default-weight-by-system
  {options/imperial             options/pound
   options/metric               options/kilogram
   options/us-customary         options/pound
   options/international-system options/kilogram})


(def ^:private ^:const default-time-by-system
  {options/imperial             options/minute
   options/metric               options/minute
   options/us-customary         options/minute
   options/international-system options/minute})


(def ^:private ^:const default-temperature-by-system
  {options/imperial             options/fahrenheit
   options/metric               options/celsius
   options/us-customary         options/fahrenheit
   options/international-system options/celsius})


(defn target-unit-error
  "A function to accumulate error messages in `error-map` if `target-units` is not a valid unit for `conversion-type`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["systems-of-meaure-error" "precision-error" "suffix-error"]}
  [error-map conversion-type target-units]
  (let [allowed-values (case conversion-type
                         :color       color/measurements
                         :volume      volume/measurements
                         :weight      weight/measurements
                         :time        time/measurements
                         :temperature temperature/measurements)
        error-msg      (str "Invalid unit for "
                            (name conversion-type)
                            " conversion : `"
                            target-units
                            "`. Allowed values are: "
                            allowed-values)]
    (assoc error-map :units error-msg)))


(defn source-unit-error
  "A function to accumulate error messages in `error-map` if `source-units` is not a valid unit for `conversion-type`.
   
   Note: This is only used for color conversion at this time.
         BeerXML prescribes the system of measure for all other units."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["systems-of-meaure-error"
              "precision-error"
              "suffix-error"
              "target-unit-error"]}
  [error-map conversion-type source-units]
  (let [allowed-values (case conversion-type
                         :color       color/measurements
                         :temperature temperature/measurements
                         :time        time/measurements
                         :volume      volume/measurements
                         :weight      weight/measurements)
        error-msg      (str "Invalid unit for "
                            (name conversion-type)
                            " conversion : `"
                            source-units
                            "`. Allowed values are: "
                            allowed-values)]
    (assoc error-map :units error-msg)))


(defn systems-of-meaure-error
  "A function to accumulate error messages in `error-map` if `system-of-measure` is not valid for `conversion-type`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["target-unit-error" "precision-error" "suffix-error"]}
  [error-map conversion-type system-of-measure]
  (let [error-msg (format "Invalid system of measure for %s conversion : %s. Allowed values are: %s"
                          (name conversion-type)
                          system-of-measure
                          options/systems-of-measure)]
    (assoc error-map :system-of-measure error-msg)))


(defn precision-error
  "A function to accumulate error messages in `error-map` if `precision` is not an integer"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["target-unit-error" "systems-of-meaure-error" "suffix-error"]}
  [error-map conversion-type precision]
  (let [error-msg (str "Invalid precision for "
                       (name conversion-type)
                       " conversion : "
                       precision
                       ". Must be an integer.")]
    (assoc error-map options/precision error-msg)))


(defn suffix-error
  "A function to accumulate error messages in `error-map` if `suffix` is not a valid choice"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["target-unit-error" "systems-of-meaure-error" "precision-error"]}
  [error-map conversion-type suffix]
  (let [error-msg (format "Invalid suffix type for %s conversion : %s. Allowed values are: %s"
                          (name conversion-type)
                          suffix
                          options/supported-suffixes)]
    (assoc error-map :suffix error-msg)))


(defn verify-enrich-displayable-volume-opts
  "A function to verify the options map passed to `->displayable-volume`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, an Exception is thrown with information on the invalid options."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-volume"]}
  [{:keys [target-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? volume/measurements target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :volume target-units)
                           (not valid-system?)    (systems-of-meaure-error :volume system-of-measure)
                           (not valid-precision?) (precision-error :volume precision)
                           (not valid-suffix?)    (suffix-error :volume suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable volume enrichment options: " errors)))))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-volume
  "A function to enrich a map with a human-readable version of a volume at `value-key`.
   If invalid options are passed, the function throws an Exception with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `impl/fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the volume to the target units and return the original value with the new value added at `display-key`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-weight"
              "enrich-displayable-temperature"
              "enrich-displayable-time"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]

  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-volume (get default-volume-by-system system-of-measure)
          target-units             (or fine-grain-target-units system-of-measure-volume)
          precision                (or fine-grain-precision precision)
          suffix                   (or fine-grain-suffix suffix)
          opts                     (verify-enrich-displayable-volume-opts
                                     {:target-units      target-units
                                      options/system-of-measure system-of-measure
                                      options/precision         precision
                                      options/suffix            suffix})]
      (assoc source-data display-key (->displayable-volume source-value :liter target-units opts)))
    source-data))


(defn verify-enrich-displayable-weight-opts
  "A function to verify the options map passed to `->displayable-weight`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, an Exception is thrown with information on the invalid options."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-weight"]}
  [{:keys [target-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? weight/measurements target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :weight target-units)
                           (not valid-system?)    (systems-of-meaure-error :weight system-of-measure)
                           (not valid-precision?) (precision-error :weight precision)
                           (not valid-suffix?)    (suffix-error :weight suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable weight enrichment options: " errors)))))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-weight
  "A function to enrich a map with a human-readable version of a weight at `value-key`.
   If invalid options are passed, the function throws an Exception with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `impl/fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the weight to the target units and return the original value with the new value added at `display-key`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-volume"
              "enrich-displayable-color"
              "enrich-displayable-temperature"
              "enrich-displayable-time"]}
  [source-data
   {:keys [value-key display-key system-of-measure suffix precision fine-grain-target-units fine-grain-precision fine-grain-suffix]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         3}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-weight (get default-weight-by-system system-of-measure)
          target-units             (or fine-grain-target-units system-of-measure-weight)
          precision                (or fine-grain-precision precision)
          suffix                   (or fine-grain-suffix suffix)
          opts                     (verify-enrich-displayable-weight-opts
                                     {:target-units      target-units
                                      options/system-of-measure system-of-measure
                                      options/precision         precision
                                      options/suffix            suffix})]
      (assoc source-data display-key (->displayable-weight source-value :kilogram target-units opts)))
    source-data))


(defn verify-enrich-displayable-time-opts
  "A function to verify the options map passed to `->displayable-time`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, an Exception is thrown with information on the invalid options."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-time"]}
  [{:keys [precision suffix system-of-measure target-units]
    :as   opts}]
  (let [valid-target?    (contains? time/measurements target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :time target-units)
                           (not valid-system?)    (systems-of-meaure-error :time system-of-measure)
                           (not valid-precision?) (precision-error :time precision)
                           (not valid-suffix?)    (suffix-error :time suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable time enrichment options: " errors)))))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-time
  "A function to enrich a map with a human-readable version of a time at `value-key`.
   If invalid options are passed, the function throws an Exception with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `impl/fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the time to the target units and return the original value with the new value added at `display-key`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-volume"
              "enrich-displayable-weight"
              "enrich-displayable-temperature"
              "enrich-displayable-color"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-time (get default-time-by-system system-of-measure)
          target-units           (or fine-grain-target-units system-of-measure-time)
          precision              (or fine-grain-precision precision)
          suffix                 (or fine-grain-suffix suffix)
          opts                   (verify-enrich-displayable-time-opts
                                   {:target-units      target-units
                                    options/system-of-measure system-of-measure
                                    options/precision         precision
                                    options/suffix            suffix})]
      (assoc source-data display-key (->displayable-time source-value options/minute target-units opts)))
    source-data))


(defn verify-enrich-displayable-temperature-opts
  "A function to verify the options map passed to `->displayable-temperature`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, an Exception is thrown with information on the invalid options."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-temperature"]}
  [{:keys [precision suffix system-of-measure target-units]
    :as   opts}]
  (let [valid-target?    (contains? temperature/measurements target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :temperature target-units)
                           (not valid-system?)    (systems-of-meaure-error :temperature system-of-measure)
                           (not valid-precision?) (precision-error :temperature precision)
                           (not valid-suffix?)    (suffix-error :temperature suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable temperature enrichment options: " errors)))))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-temperature
  "A function to enrich a map with a human-readable version of a temperature at `value-key`.
   If invalid options are passed, the function throws an Exception with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `impl/fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the temperature to the target units and return the original value with the new value added at `display-key`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-volume"
              "enrich-displayable-weight"
              "enrich-displayable-color"
              "enrich-displayable-time"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-temperature (get default-temperature-by-system system-of-measure)
          target-units           (or fine-grain-target-units system-of-measure-temperature)
          precision              (or fine-grain-precision precision)
          suffix                 (or fine-grain-suffix suffix)
          opts                   (verify-enrich-displayable-temperature-opts
                                   {:target-units      target-units
                                    options/system-of-measure system-of-measure
                                    options/precision         precision
                                    options/suffix            suffix})]
      (assoc source-data display-key (->displayable-temperature source-value :celsius target-units opts)))
    source-data))


(defn verify-enrich-displayable-color-opts
  "A function to verify the options map passed to `->displayable-color`
   This requires the user to supply valid values for: `target-units`, `precision`, and `suffix`.
   If any of these are invalid, an Exception is thrown with information on the invalid options."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-color"]}
  [{:keys [target-units source-units precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? color/srm->measurement target-units)
        valid-source?    (contains? color/measurement->srm source-units)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :color target-units)
                           (not valid-source?)    (source-unit-error :color source-units)
                           (not valid-precision?) (precision-error :color precision)
                           (not valid-suffix?)    (suffix-error :color suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable color enrichment options: " errors)))))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-color
  "A function to enrich a map with a human-readable version of a color at `value-key`.
   If invalid options are passed, the function throws an Exception with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the color to the target system and return the original value with the new value added at `display-key`"
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-volume"
              "enrich-displayable-weight"
              "enrich-displayable-temperature"
              "enrich-displayable-time"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix value-key source-units]
    :or   {suffix    options/short
           precision options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [target-units fine-grain-target-units
          precision    (or fine-grain-precision precision)
          suffix       (or fine-grain-suffix suffix)
          opts         (verify-enrich-displayable-color-opts
                         {:target-units    target-units
                          :source-units    source-units
                          options/precision precision
                          options/suffix    suffix})]
      (assoc source-data display-key (->displayable-color source-value source-units target-units opts)))
    source-data))
