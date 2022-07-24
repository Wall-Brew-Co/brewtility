(ns brewtility.enrich.impl
  {:no-doc true}
  (:require [brewtility.precision :as precision]
            [brewtility.units :as units]))


;; TODO: Pluralize strings
(defn ->displayable-volume
  {:added "1.3.0"
   :see-also ["->displayable-weight"]}
  ([source-value source-units target-units]
   (->displayable-volume source-value source-units target-units {:precision 3
                                                                 :suffix :short}))
  ([source-value source-units target-units {:keys [precision suffix]}]
   (-> source-value
       (units/convert-volume source-units target-units)
       (precision/->precision precision)
       (str " " (get-in units/volume-measurements->display-name [target-units suffix])))))


(defn ->displayable-weight
  ([source-value source-units target-units]
   (->displayable-weight source-value source-units target-units {:precision 3
                                                                 :suffix :short}))
  ([source-value source-units target-units {:keys [precision suffix]}]
   (-> source-value
       (units/convert-weight source-units target-units)
       (precision/->precision precision)
       (str " " (get-in units/weight-measurements->display-name [target-units suffix])))))


(def ^:private default-volume-by-system
  {:imperial :imperial-gallon
   :metric   :litre
   :us       :american-gallon
   :si       :litre})


(def ^:private default-weight-by-system
  {:imperial :pound
   :metric   :kilogram
   :us       :pound
   :si       :kilogram})


(defn target-unit-error
  [error-map conversion-type target-units]
  (let [error-msg (format "Invalid unit for %s conversion : %s. Allowed values are: %s"
                          (name conversion-type)
                          target-units
                          units/volume-measurements)]
    (assoc error-map :units error-msg)))


(defn systems-of-meaure-error
  [error-map conversion-type system-of-measure]
  (let [error-msg (format "Invalid system of measure for %s conversion : %s. Allowed values are: %s"
                          (name conversion-type)
                          system-of-measure
                          units/systems-of-meaure)]
    (assoc error-map :system-of-measure error-msg)))


(defn precision-error
  [error-map conversion-type precision]
  (let [error-msg (format "Invalid precision for %s conversion : %s. Must be an integer."
                          (name conversion-type)
                          precision)]
    (assoc error-map :precision error-msg)))


(defn suffix-error
  [error-map conversion-type suffix]
  (let [error-msg (format "Invalid suffix type for %s conversion : %s. Allowed values are: %s"
                          (name conversion-type)
                          suffix
                          units/suffix-types)]
    (assoc error-map :suffix error-msg)))


(defn verify-enrich-displayable-volume-opts
  [{:keys [target-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? units/volume-measurements target-units)
        valid-system?    (contains? units/systems-of-meaure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? units/suffix-types suffix)
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
  [{:keys [target-units system-of-measure precision suffix]
    :as   opts}]
  (let [valid-target?    (contains? units/weight-measurements target-units)
        valid-system?    (contains? units/systems-of-meaure system-of-measure)
        valid-precision? (int? precision)
        valid-suffix?    (contains? units/suffix-types suffix)
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
   :see-also ["enrich-displayable-volume"]}
  [source-data
   {:keys [value-key display-key system-of-measure suffix precision fine-grain-target-units fine-grain-precision fine-grain-suffix]
    :or   {system-of-measure :us
           suffix            :short
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
