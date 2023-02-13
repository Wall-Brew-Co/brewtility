(ns brewtility.predicates.impl
  "Function to help minimize repeated code in predicate functions.

   Not intended for public consumption."
  {:added               "1.5"
   :no-doc              true
   :implementation-only true})


(defn fetch-or-throw!
  "A helper function to fetch a value from a map or throw an exception.
   Many predicates require information that is not automatically inferable, and no sane default may be assumed.
   Exception metadata is added to indicate the missing key and the map that was searched.

   Not intended for public consumption."
  {:added  "1.5"
   :no-doc true}
  [map' key' message]
  (let [value (get map' key' ::not-found)]
    (if (and (some? value) (not= value ::not-found))
      value
      (throw (ex-info message {:error-type     :missing-key
                               :missing-key    key'
                               :original-value map'})))))
