(ns brewtility.precision
  "Namespace for handling numeric precision, rounding, and approximation."
  {:added "1.0"}
  #?(:clj (:import [java.math RoundingMode])))


(defn approximates?
  "Determine if `n2` approximates `n1` within `variance` percent."
  {:added "1.0"}
  [n1 n2 variance]
  (if (and (number? n1)
           (number? n2)
           (number? variance))
    (let [upper-bound (* n1 (+ 1.0 variance))
          lower-bound (* n1 (- 1.0 variance))]
      (<= lower-bound n2 upper-bound))
    (throw (ex-info "Cannot approximate using non-numeric values" {:n1       n1
                                                                   :n2       n2
                                                                   :variance variance}))))


(defn ->precision
  "Given a decimal `x` and the number of decimal places, returns that number rounded to `num-decimals` precision."
  {:added    "1.0"
   :see-also ["->1dp" "->2dp" "->3dp"]}
  [^double x ^long num-decimals]
  (double
   #?(:clj (.setScale (bigdec x) num-decimals RoundingMode/HALF_UP)
      :cljs (let [denominator (Math/pow 10.0 (double num-decimals))
                  numerator   (Math/round (* x denominator))]
              (/ numerator denominator)))))


(defn ->1dp
  "Given a decimal `x`, returns that number rounded to one decimal place."
  {:added    "1.0"
   :see-also ["->precision" "->2dp" "->3dp"]}
  [^double x]
  (->precision x 1))


(defn ->2dp
  "Given a decimal `x`, returns that number rounded to two decimal places."
  {:added    "1.0"
   :see-also ["->precision" "->1dp" "->3dp"]}
  [^double x]
  (->precision x 2))


(defn ->3dp
  "Given a decimal `x`, returns that number rounded to three decimal places."
  {:added    "1.0"
   :see-also ["->precision" "->1dp" "->2dp"]}
  [^double x]
  (->precision x 3))
