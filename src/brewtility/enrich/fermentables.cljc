(ns brewtility.enrich.fermentables
  "Enricher-pattern functions for [fermentables](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) maps"
  {:added    "1.3"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.hops"
              "brewtility.enrich.mash"
              "brewtility.enrich.miscs"
              "brewtility.enrich.recipes"
              "brewtility.enrich.styles"
              "brewtility.enrich.waters"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.color :as color]
            [brewtility.predicates.fermentables :as fermentables.predicate]))


(defn enrich-add-after-boil
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) was added after the boil.
   In the BeerXML spec, this behavior is implicitly falsey.
   Therefore, if the :add-after-boil field is not present, this function will explicitly set it to false."
  {:added    "1.3"
   :see-also ["enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-add-after-boil fermentable {}))
  ([fermentable _opts]
   (if (contains? fermentable :add-after-boil)
     fermentable
     (assoc fermentable :add-after-boil false))))


(defn enrich-coarse-fine-diff
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a coarse/fine differential.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:coarse-fine-diff` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-coarse-fine-diff fermentable {}))
  ([fermentable opts]
   (if (or (fermentables.predicate/grain? fermentable opts)
           (fermentables.predicate/adjunct? fermentable opts))
     fermentable
     (dissoc fermentable :coarse-fine-diff))))


(defn enrich-moisture
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a moisture content.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:moisture` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-moisture fermentable {}))
  ([fermentable opts]
   (if (or (fermentables.predicate/grain? fermentable opts)
           (fermentables.predicate/adjunct? fermentable opts))
     fermentable
     (dissoc fermentable :moisture))))


(defn enrich-diastatic-power
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have a listed Diastatic Power.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:diastatic-power` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-diastatic-power fermentable {}))
  ([fermentable opts]
   (if (or (fermentables.predicate/grain? fermentable opts)
           (fermentables.predicate/adjunct? fermentable opts))
     fermentable
     (dissoc fermentable :diastatic-power))))


(defn enrich-protein
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have protein.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:protein` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-protein fermentable {}))
  ([fermentable opts]
   (if (or (fermentables.predicate/grain? fermentable opts)
           (fermentables.predicate/adjunct? fermentable opts))
     fermentable
     (dissoc fermentable :protein))))


(defn enrich-recommend-mash
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have recommend-mash.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `grain` or `adjunct`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:recommend-mash` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-recommend-mash fermentable {}))
  ([fermentable opts]
   (if (or (fermentables.predicate/grain? fermentable opts)
           (fermentables.predicate/adjunct? fermentable opts))
     fermentable
     (dissoc fermentable :recommend-mash))))


(defn enrich-ibu-gallons-per-pound
  "An enricher pattern function to determine if a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) should have ibu-gal-per-lb.
   In the BeerXML spec, this field is only valid if the `:type` of the fermentable is `extract`.
   When the fermetable is not a grain or adjunct, this function will dissoc `:ibu-gal-per-lb` from the fermentable.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-ibu-gallons-per-pound fermentable {}))
  ([fermentable opts]
   (if (fermentables.predicate/extract? fermentable opts)
     fermentable
     (dissoc fermentable :ibu-gal-per-lb))))


(defn enrich-display-color
  "An enricher pattern function to determine what color a [fermentable](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc) is in a given system.
   In the BeerXML spec, color is assumed to be in Lovibond for the `:type` of `grain`, and SRM for all other fermentables.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.
     - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
         - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
         - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
         - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
         - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.
     - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
         - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
         - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`."
  {:added    "1.3"
   :see-also ["brewtility.string/same?"
              "enrich-fermentable"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-display-color fermentable {}))
  ([fermentable {:keys [color-system suffix]
                 :as   opts}]
   (let [source-color-system (if (fermentables.predicate/grain? fermentable opts)
                               :lovibond
                               :srm)
         target-color-system (or color-system source-color-system)
         suffix              (or suffix :short)]
     (if (and (contains? color/color-systems target-color-system)
              (contains? #{:full :short} suffix))
       (let [source-color  (if (fermentables.predicate/grain? fermentable opts)
                             (:color fermentable)
                             (color/srm->lovibond (:color fermentable)))
             target-color  (case target-color-system ; TODO: Add this functionality to the `units` namespace
                             :lovibond source-color
                             :srm      (color/lovibond->srm source-color)
                             :ebc      (color/lovibond->ebc source-color)
                             :rgba     (color/lovibond->rgba source-color))
             suffix-string (get-in color/color-system->display-name [target-color-system suffix])
             display-value (str target-color suffix-string)]
         (assoc fermentable :display-color display-value))
       (throw (ex-info "Invalid options for displaying fermentable color" {:color-system          target-color-system
                                                                           :allowed-color-systems color/color-systems
                                                                           :suffix                suffix
                                                                           :allowed-suffixes      #{:full :short}}))))))


(defn enrich-fermentable
  "An enricher pattern function to derive as many values from an [fermentable record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
            - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
            - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
            - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
            - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.
        - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
            - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
            - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`."
  {:added    "1.3"
   :see-also ["enrich-add-after-boil"
              "enrich-coarse-fine-diff"
              "enrich-moisture"
              "enrich-diastatic-power"
              "enrich-protein"
              "enrich-recommend-mash"
              "enrich-ibu-gallons-per-pound"
              "enrich-display-color"
              "enrich-fermentable-wrapper"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-fermentable fermentable {}))
  ([fermentable opts]
   (-> fermentable
       (enrich-add-after-boil opts)
       (enrich-coarse-fine-diff opts)
       (enrich-moisture opts)
       (enrich-diastatic-power opts)
       (enrich-protein opts)
       (enrich-recommend-mash opts)
       (enrich-ibu-gallons-per-pound opts)
       (enrich-display-color opts))))


(defn enrich-fermentable-wrapper
  "An enricher pattern function to derive as many values from an [fermentable-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
            - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
            - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
            - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
            - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.
        - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
            - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
            - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`."
  {:added    "1.3"
   :see-also ["enrich-add-after-boil"
              "enrich-coarse-fine-diff"
              "enrich-moisture"
              "enrich-diastatic-power"
              "enrich-protein"
              "enrich-recommend-mash"
              "enrich-ibu-gallons-per-pound"
              "enrich-display-color"
              "enrich-fermentable"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentable] (enrich-fermentable-wrapper fermentable {}))
  ([fermentable opts]
   (update fermentable :fermentable enrich-fermentable opts)))


(defn enrich-fermentables
  "An enricher pattern function to derive as many values from an [fermentables record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
            - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
            - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
            - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
            - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.
        - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
            - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
            - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`."
  {:added    "1.3"
   :see-also ["enrich-add-after-boil"
              "enrich-coarse-fine-diff"
              "enrich-moisture"
              "enrich-diastatic-power"
              "enrich-protein"
              "enrich-recommend-mash"
              "enrich-ibu-gallons-per-pound"
              "enrich-display-color"
              "enrich-fermentable-wrapper"
              "enrich-fermentable"
              "enrich-fermentables-wrapper"]}
  ([fermentables] (enrich-fermentables fermentables {}))
  ([fermentables opts]
   (map #(enrich-fermentable-wrapper % opts) fermentables)))


(defn enrich-fermentables-wrapper
  "An enricher pattern function to derive as many values from an [fermentables-wrapper record](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/fermentables.cljc).

   An option map may be passed as an optional second argument.
   The following keys are supported for controlling high-level behavior:

     - `:uppercase?` - If the string comparison for the `:type` should be cast to UPPERCASE instead of lowercase. Default is false.
     - `:coerce` - If the `:type` field should be coerced to a string for comparison. Default is false.

   To support fine-grained control of behavior, this function also supports the following keys inherited from the other field-specific enrichers:
    - [[enrich-display-color]]
        - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
            - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
            - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
            - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
            - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.
        - `:suffix`: The suffix type to append to the boil size. Defaults to `:short`. Acceptable values are:
            - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `:lovibond`.
            - `:full`: The full name of the selected unit. For example, `\"° Lovibond\"` for `:lovibond`."
  {:added    "1.3"
   :see-also ["enrich-add-after-boil"
              "enrich-coarse-fine-diff"
              "enrich-moisture"
              "enrich-diastatic-power"
              "enrich-protein"
              "enrich-recommend-mash"
              "enrich-ibu-gallons-per-pound"
              "enrich-display-color"
              "enrich-fermentable"
              "enrich-fermentables"
              "enrich-fermentables-wrapper"]}
  ([fermentables] (enrich-fermentables-wrapper fermentables {}))
  ([fermentables opts]
   (update fermentables :fermentables enrich-fermentables opts)))
