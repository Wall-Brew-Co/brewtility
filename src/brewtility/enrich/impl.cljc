(ns brewtility.enrich.impl
  "Function to help minimize repeated code in enricher functions.

   Not intended for public consumption."
  {:no-doc              true
   :added               "2.1"
   :implementation-only true}
  (:require [brewtility.units.color :as color]
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
  "The key to source data from in `->displayable` functions"
  :value-key)


(def display-key
  "The key to store displayable data in in `->displayable` functions"
  :display-key)


(def fine-grain-target-units
  "The target units to use for fine-grain toggling of displayable units in `->displayable` functions"
  :fine-grain-target-units)


(def fine-grain-precision
  "The suffix to use for fine-grain setting of precision in `->displayable` functions"
  :fine-grain-precision)


(def fine-grain-suffix
  "The suffix to use for fine-grain setting of precision in `->displayable` functions"
  :fine-grain-suffix)


(def default-color-by-system
  "The default color to use for each system in `->displayable` functions."
  {options/imperial             options/srm
   options/metric               options/srm
   options/us-customary         options/srm
   options/international-system options/srm})


(def default-pressure-by-system
  "The default pressure to use for each system in `->displayable` functions."
  {options/imperial             options/psi
   options/metric               options/kilopascal
   options/us-customary         options/psi
   options/international-system options/kilopascal})


(def default-specific-gravity-by-system
  "The default specific gravity to use for each system in `->displayable` functions."
  {options/imperial             options/specific-gravity
   options/metric               options/specific-gravity
   options/us-customary         options/specific-gravity
   options/international-system options/specific-gravity})


(def default-temperature-by-system
  "The default temperature to use for each system in `->displayable` functions."
  {options/imperial             options/fahrenheit
   options/metric               options/celsius
   options/us-customary         options/fahrenheit
   options/international-system options/celsius})


(def default-time-by-system
  "The default time to use for each system in `->displayable` functions."
  {options/imperial             options/minute
   options/metric               options/minute
   options/us-customary         options/minute
   options/international-system options/minute})


(def default-volume-by-system
  "The default volume to use for each system in `->displayable` functions."
  {options/imperial             options/imperial-gallon
   options/metric               options/litre
   options/us-customary         options/american-gallon
   options/international-system options/litre})


(def default-weight-by-system
  "The default weight to use for each system in `->displayable` functions."
  {options/imperial             options/pound
   options/metric               options/kilogram
   options/us-customary         options/pound
   options/international-system options/kilogram})


;; TODO: Pluralize strings
(defn ->displayable-color
  "Convert a color then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-pressure"
              "->displayable-specific-gravity"
              "->displayable-temperature"
              "->displayable-time"
              "->displayable-volume"
              "->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-color source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (color/convert source-units target-units)
       (color/display target-units opts))))


(defn ->displayable-pressure
  "Convert a pressure then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-color"
              "->displayable-specific-gravity"
              "->displayable-temperature"
              "->displayable-time"
              "->displayable-volume"
              "->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-pressure source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (pressure/convert source-units target-units)
       (pressure/display target-units opts))))


(defn ->displayable-specific-gravity
  "Convert a specific gravity then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-color"
              "->displayable-pressure"
              "->displayable-temperature"
              "->displayable-time"
              "->displayable-volume"
              "->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-specific-gravity source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (specific-gravity/convert source-units target-units)
       (specific-gravity/display target-units opts))))


(defn ->displayable-temperature
  "Convert a temperature then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-color"
              "->displayable-pressure"
              "->displayable-specific-gravity"
              "->displayable-time"
              "->displayable-volume"
              "->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-temperature source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (temperature/convert source-units target-units)
       (temperature/display target-units opts))))


(defn ->displayable-time
  "Convert a time then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-color"
              "->displayable-pressure"
              "->displayable-specific-gravity"
              "->displayable-temperature"
              "->displayable-volume"
              "->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-time source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (time/convert source-units target-units)
       (time/display target-units opts))))


(defn ->displayable-volume
  "Convert a volume then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-color"
              "->displayable-pressure"
              "->displayable-specific-gravity"
              "->displayable-temperature"
              "->displayable-time"
              "->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-volume source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (volume/convert source-units target-units)
       (volume/display target-units opts))))


(defn ->displayable-weight
  "Convert a weight then render it to a displayable value."
  {:added    "2.1"
   :no-doc   true
   :see-also ["->displayable-color"
              "->displayable-pressure"
              "->displayable-specific-gravity"
              "->displayable-temperature"
              "->displayable-time"
              "->displayable-volume"]}
  ([source-value source-units target-units]
   (->displayable-weight source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (weight/convert source-units target-units)
       (weight/display target-units opts))))


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
    (assoc error-map :units error-msg)))


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
    (assoc error-map :units error-msg)))


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
    (assoc error-map :system-of-measure error-msg)))


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

(defn parse-enrich-displayable-color-opts
  "A function to parse the options map passed to `->displayable-color`
   This requires the user to supply valid values for: `target-units`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
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


(defn parse-enrich-displayable-pressure-opts
  "A function to parse the options map passed to `->displayable-pressure`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-pressure"]}
  [{:keys [target-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? pressure/measurements target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :pressure target-units)
                           (not valid-system?)    (systems-of-meaure-error :pressure system-of-measure)
                           (not valid-precision?) (precision-error :pressure precision)
                           (not valid-suffix?)    (suffix-error :pressure suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable pressure enrichment options: " errors)))))


(defn parse-enrich-displayable-specific-gravity-opts
  "A function to parse the options map passed to `->displayable-specific-gravity`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-specific-gravity"]}
  [{:keys [target-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? specific-gravity/measurements target-units)
        valid-system?    (contains? options/systems-of-measure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? options/supported-suffixes suffix)
        errors           (cond-> {}
                           (not valid-target?)    (target-unit-error :specific-gravity target-units)
                           (not valid-system?)    (systems-of-meaure-error :specific-gravity system-of-measure)
                           (not valid-precision?) (precision-error :specific-gravity precision)
                           (not valid-suffix?)    (suffix-error :specific-gravity suffix))]
    (if (empty? errors)
      opts
      (throw (ex-info "Invalid displayable specific gravity enrichment options: " errors)))))


(defn parse-enrich-displayable-temperature-opts
  "A function to parse the options map passed to `->displayable-temperature`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
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


(defn parse-enrich-displayable-time-opts
  "A function to parse the options map passed to `->displayable-time`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
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


(defn parse-enrich-displayable-volume-opts
  "A function to parse the options map passed to `->displayable-volume`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
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


(defn parse-enrich-displayable-weight-opts
  "A function to parse the options map passed to `->displayable-weight`
   This requires the user to supply valid values for: `target-units`, `system-of-measure`, `precision`, and `suffix`.
   If any of these are invalid, a Java Exception or Javascript Error is thrown with information on the invalid options."
  {:added    "2.1"
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


(defn enrich-displayable-color
  "A function to enrich a map with a human-readable version of a color at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the color to the target system and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-temperature"
              "enrich-displayable-time"
              "enrich-displayable-volume"
              "enrich-displayable-weight"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key source-units]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-color (get default-color-by-system system-of-measure)
          target-units            (or fine-grain-target-units system-of-measure-color)
          precision               (or fine-grain-precision precision)
          suffix                  (or fine-grain-suffix suffix)
          opts                    (parse-enrich-displayable-color-opts
                                    {:target-units             target-units
                                     :source-units             source-units
                                     options/system-of-measure system-of-measure
                                     options/precision         precision
                                     options/suffix            suffix})]
      (assoc source-data display-key (->displayable-color source-value source-units target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-pressure
  "A function to enrich a map with a human-readable version of a pressure at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the pressure to the target system and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-temperature"
              "enrich-displayable-time"
              "enrich-displayable-volume"
              "enrich-displayable-weight"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix value-key system-of-measure]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-pressure (get default-pressure-by-system system-of-measure)
          target-units               (or fine-grain-target-units system-of-measure-pressure)
          precision                  (or fine-grain-precision precision)
          suffix                     (or fine-grain-suffix suffix)
          opts                       (parse-enrich-displayable-pressure-opts
                                       {:target-units             target-units
                                        options/system-of-measure system-of-measure
                                        options/precision         precision
                                        options/suffix            suffix})]
      (assoc source-data display-key (->displayable-pressure source-value options/kilopascal target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-specific-gravity
  "A function to enrich a map with a human-readable version of a specific gravity at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the specific gravity to the target system and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-pressure"
              "enrich-displayable-temperature"
              "enrich-displayable-time"
              "enrich-displayable-volume"
              "enrich-displayable-weight"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-sg (get default-specific-gravity-by-system system-of-measure)
          target-units         (or fine-grain-target-units system-of-measure-sg)
          precision            (or fine-grain-precision precision)
          suffix               (or fine-grain-suffix suffix)
          opts                 (parse-enrich-displayable-specific-gravity-opts
                                 {:target-units             target-units
                                  options/system-of-measure system-of-measure
                                  options/precision         precision
                                  options/suffix            suffix})]
      (assoc source-data display-key (->displayable-specific-gravity source-value options/specific-gravity target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-temperature
  "A function to enrich a map with a human-readable version of a temperature at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the temperature to the target units and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-pressure"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-time"
              "enrich-displayable-volume"
              "enrich-displayable-weight"]}
  [source-data
   {:keys [display-key fine-grain-precision fine-grain-suffix fine-grain-target-units precision suffix system-of-measure value-key]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-temperature (get default-temperature-by-system system-of-measure)
          target-units                  (or fine-grain-target-units system-of-measure-temperature)
          precision                     (or fine-grain-precision precision)
          suffix                        (or fine-grain-suffix suffix)
          opts                          (parse-enrich-displayable-temperature-opts
                                          {:target-units             target-units
                                           options/system-of-measure system-of-measure
                                           options/precision         precision
                                           options/suffix            suffix})]
      (assoc source-data display-key (->displayable-temperature source-value options/celsius target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-time
  "A function to enrich a map with a human-readable version of a time at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the time to the target units and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-pressure"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-temperature"
              "enrich-displayable-volume"
              "enrich-displayable-weight"]}
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
          opts                   (parse-enrich-displayable-time-opts
                                   {:target-units      target-units
                                    options/system-of-measure system-of-measure
                                    options/precision         precision
                                    options/suffix            suffix})]
      (assoc source-data display-key (->displayable-time source-value options/minute target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-volume
  "A function to enrich a map with a human-readable version of a volume at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the volume to the target units and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-pressure"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-temperature"
              "enrich-displayable-time"
              "enrich-displayable-weight"]}
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
          opts                     (parse-enrich-displayable-volume-opts
                                     {:target-units      target-units
                                      options/system-of-measure system-of-measure
                                      options/precision         precision
                                      options/suffix            suffix})]
      (assoc source-data display-key (->displayable-volume source-value options/liter target-units opts)))
    source-data))


#_{:clj-kondo/ignore [:shadowed-var]}


(defn enrich-displayable-weight
  "A function to enrich a map with a human-readable version of a weight at `value-key`.
   If invalid options are passed, the function throws a Java Exception or Javascript Error with information on the invalid options.

   Since many enrichers can leverage the same options (for example, `:precision`) this function will check for common options.
   However, it will defer to more selective values passed in with the following precedence:

   `:fine-grain-target-units` > `system-of-measure`
   `:fine-grain-precision` > `precision`
   `:fine-grain-suffix` > `suffix`

   If these options are valid, the function will convert the weight to the target units and return the original value with the new value added at `display-key`"
  {:added    "2.1"
   :no-doc   true
   :see-also ["enrich-displayable-color"
              "enrich-displayable-pressure"
              "enrich-displayable-specific-gravity"
              "enrich-displayable-temperature"
              "enrich-displayable-time"
              "enrich-displayable-volume"]}
  [source-data
   {:keys [value-key display-key system-of-measure suffix precision fine-grain-target-units fine-grain-precision fine-grain-suffix]
    :or   {system-of-measure options/us-customary
           suffix            options/short
           precision         options/default-precision}}]
  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-weight (get default-weight-by-system system-of-measure)
          target-units             (or fine-grain-target-units system-of-measure-weight)
          precision                (or fine-grain-precision precision)
          suffix                   (or fine-grain-suffix suffix)
          opts                     (parse-enrich-displayable-weight-opts
                                     {:target-units      target-units
                                      options/system-of-measure system-of-measure
                                      options/precision         precision
                                      options/suffix            suffix})]
      (assoc source-data display-key (->displayable-weight source-value options/kilogram target-units opts)))
    source-data))
