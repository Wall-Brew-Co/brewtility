(ns brewtility.enrich.impl
  {:no-doc true}
  (:require [brewtility.units :as units]
            [brewtility.units.options :as options]
            [brewtility.units.volume :as volume]
            [brewtility.units.weight :as weight]))

(def default-display-options
  "The default display options used for all enrichers."
  {options/precision 3
   options/suffix    options/short})

;; TODO: Pluralize strings
(defn ->displayable-volume
  "Convert a volume then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-volume source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (volume/convert source-units target-units)
       (volume/display target-units opts))))


(defn ->displayable-weight
  "Convert a weight then render it to a displayable value."
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["->displayable-volume"]}
  ([source-value source-units target-units]
   (->displayable-weight source-value source-units target-units default-display-options))
  ([source-value source-units target-units opts]
   (-> source-value
       (weight/convert source-units target-units)
       (weight/display target-units opts))))


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


(defn target-unit-error
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["systems-of-meaure-error" "precision-error" "suffix-error"]}
  [error-map conversion-type target-units]
  (let [error-msg (format "Invalid unit for %s conversion : %s. Allowed values are: %s"
                          (name conversion-type)
                          target-units
                          volume/measurements)]
    (assoc error-map :units error-msg)))


(defn systems-of-meaure-error
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
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["target-unit-error" "systems-of-meaure-error" "suffix-error"]}
  [error-map conversion-type precision]
  (let [error-msg (format "Invalid precision for %s conversion : %s. Must be an integer."
                          (name conversion-type)
                          precision)]
    (assoc error-map :precision error-msg)))


(defn suffix-error
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
      (throw (ex-info "Invalid enrichment options: " errors)))))


(defn enrich-displayable-volume
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-weight"]}
  [source-data
   {:keys [value-key display-key system-of-measure suffix precision fine-grain-target-units fine-grain-precision fine-grain-suffix]
    :or   {system-of-measure :us
           suffix            :short
           precision         3}}]

  (if-let [source-value (get source-data value-key)]
    (let [system-of-measure-volume (get default-volume-by-system system-of-measure)
          target-units             (or fine-grain-target-units system-of-measure-volume)
          precision                (or fine-grain-precision precision)
          suffix                   (or fine-grain-suffix suffix)
          opts                     (verify-enrich-displayable-volume-opts
                                     {:target-units      target-units
                                      :system-of-measure system-of-measure
                                      :precision         precision
                                      :suffix            suffix})]
      (assoc source-data display-key (->displayable-volume source-value :liter target-units opts)))
    source-data))


(defn verify-enrich-displayable-weight-opts
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
      (throw (ex-info "Invalid enrichment options: " errors)))))


(defn enrich-displayable-weight
  {:added    "1.3.0"
   :no-doc   true
   :see-also ["enrich-displayable-volume"]}
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
                                      :system-of-measure system-of-measure
                                      :precision         precision
                                      :suffix            suffix})]
      (assoc source-data display-key (->displayable-weight source-value :kilogram target-units opts)))
    source-data))
