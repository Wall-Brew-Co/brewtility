(ns brewtility.enrich.styles
  "Enricher-pattern functions for [styles](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/styles.cljc) maps"
  {:added    "2.3"
   :see-also ["brewtility.enrich.equipment"
              "brewtility.enrich.fermentables"
              "brewtility.enrich.hops"
              "brewtility.enrich.mash"
              "brewtility.enrich.miscs"
              "brewtility.enrich.recipes"
              "brewtility.enrich.waters"
              "brewtility.enrich.yeast"]}
  (:require [brewtility.enrich.impl :as impl]
            [brewtility.units.options :as options]))


(defn enrich-display-og-min
  "An enricher pattern function to render a human-readable display minimum original gravity of a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the minimum gravity into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the minimum gravity Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-og-min-target-units`: The unit to convert the minimum gravity into. Supersedes `:system-of-measure`.
    - `:style-display-og-min-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-og-min-suffix`: The suffix type to append to the minimum gravity. Supersedes `:suffix`."
  {:added "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-display-og-min style {}))
  ([style {:keys [style-display-og-min-target-units
                  style-display-og-min-precision
                  style-display-og-min-suffix]
           :as options}]
   (let [options (merge options {impl/value-key               :og-min
                                 impl/display-key             :display-og-min
                                 impl/fine-grain-target-units style-display-og-min-target-units
                                 impl/fine-grain-precision    style-display-og-min-precision
                                 impl/fine-grain-suffix       style-display-og-min-suffix})]
     (impl/enrich-displayable-units options/specific-gravity style options))))


(defn enrich-display-og-max
  "An enricher pattern function to render a human-readable display maximum original gravity of a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the maximum gravity into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the maximum gravity Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-og-max-target-units`: The unit to convert the maximum gravity into. Supersedes `:system-of-measure`.
    - `:style-display-og-max-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-og-max-suffix`: The suffix type to append to the maximum gravity. Supersedes `:suffix`."
  {:added "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-display-og-max style {}))
  ([style {:keys [style-display-og-max-target-units
                  style-display-og-max-precision
                  style-display-og-max-suffix]
           :as options}]
   (let [options (merge options {impl/value-key               :og-max
                                 impl/display-key             :display-og-max
                                 impl/fine-grain-target-units style-display-og-max-target-units
                                 impl/fine-grain-precision    style-display-og-max-precision
                                 impl/fine-grain-suffix       style-display-og-max-suffix})]
     (impl/enrich-displayable-units options/specific-gravity style options))))


(defn enrich-display-fg-min
  "An enricher pattern function to render a human-readable display minimum final gravity of a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

- `:system-of-measure`: The unit system of measure to convert the minimum gravity into. Defaults to `:us`. Acceptable values are:
  - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
  - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
  - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
  - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.

    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the minimum gravity Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-fg-min-target-units`: The unit to convert the minimum gravity into. Supersedes `:system-of-measure`.
    - `:style-display-fg-min-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-fg-min-suffix`: The suffix type to append to the minimum gravity. Supersedes `:suffix`."
  {:added "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-display-fg-min style {}))
  ([style {:keys [style-display-fg-min-target-units
                  style-display-fg-min-precision
                  style-display-fg-min-suffix]
           :as options}]
   (let [options (merge options {impl/value-key               :fg-min
                                 impl/display-key             :display-fg-min
                                 impl/fine-grain-target-units style-display-fg-min-target-units
                                 impl/fine-grain-precision    style-display-fg-min-precision
                                 impl/fine-grain-suffix       style-display-fg-min-suffix})]
     (impl/enrich-displayable-units options/specific-gravity style options))))


(defn enrich-display-fg-max
  "An enricher pattern function to render a human-readable display maximum final gravity of a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

- `:system-of-measure`: The unit system of measure to convert the maximum gravity into. Defaults to `:us`. Acceptable values are:
  - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
  - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
  - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
  - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.

    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the maximum gravity Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-fg-max-target-units`: The unit to convert the maximum gravity into. Supersedes `:system-of-measure`.
    - `:style-display-fg-max-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-fg-max-suffix`: The suffix type to append to the maximum gravity. Supersedes `:suffix`."
  {:added "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-display-fg-max style {}))
  ([style {:keys [style-display-fg-max-target-units
                  style-display-fg-max-precision
                  style-display-fg-max-suffix]
           :as options}]
   (let [options (merge options {impl/value-key               :fg-max
                                 impl/display-key             :display-fg-max
                                 impl/fine-grain-target-units style-display-fg-max-target-units
                                 impl/fine-grain-precision    style-display-fg-max-precision
                                 impl/fine-grain-suffix       style-display-fg-max-suffix})]
     (impl/enrich-displayable-units options/specific-gravity style options))))


(defn enrich-display-color-min
  "An enricher pattern function to render a human-readable display minimum color of a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

    - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
      - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
      - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
      - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
      - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the minimum color Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-color-min-target-units`: The unit to convert the minimum color into. Supersedes `:system-of-measure`.
    - `:style-display-color-min-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-color-min-suffix`: The suffix type to append to the minimum color. Supersedes `:suffix`."
  {:added "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-display-color-min style {}))
  ([style {:keys [color-system
                  style-display-color-min-target-units
                  style-display-color-min-precision
                  style-display-color-min-suffix]
           :as options}]
   (let [options (merge options {impl/value-key               :color-min
                                 impl/display-key             :display-color-min
                                 impl/fine-grain-target-units (or style-display-color-min-target-units color-system)
                                 impl/fine-grain-precision    style-display-color-min-precision
                                 impl/fine-grain-suffix       style-display-color-min-suffix})]
     (impl/enrich-displayable-units options/color style options))))


(defn enrich-display-color-max
  "An enricher pattern function to render a human-readable display maximum color of a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

    - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
      - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
      - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
      - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
      - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the maximum color Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-color-max-target-units`: The unit to convert the maximum color into. Supersedes `:system-of-measure`.
    - `:style-display-color-max-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-color-max-suffix`: The suffix type to append to the maximum color. Supersedes `:suffix`."
  {:added "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-display-color-max style {}))
  ([style {:keys [color-system
                  style-display-color-max-target-units
                  style-display-color-max-precision
                  style-display-color-max-suffix]
           :as options}]
   (let [options (merge options {impl/value-key               :color-max
                                 impl/display-key             :display-color-max
                                 impl/fine-grain-target-units (or style-display-color-max-target-units color-system)
                                 impl/fine-grain-precision    style-display-color-max-precision
                                 impl/fine-grain-suffix       style-display-color-max-suffix})]
     (impl/enrich-displayable-units options/color style options))))


(defn enrich-og-range
  "An enricher pattern function to render a human-readable display original gravity ranges for a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the gravity range into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the gravity range Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-og-range-target-units`: The unit to convert the gravity range into. Supersedes `:system-of-measure`.
    - `:style-display-og-range-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-og-range-suffix`: The suffix type to append to the gravity range. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-og-range style {}))
  ([style {:keys [style-display-og-range-target-units
                  style-display-og-range-precision
                  style-display-og-range-suffix]
           :as   options}]
   (let [options (merge options {impl/low-value-key           :og-min
                                 impl/high-value-key          :og-max
                                 impl/display-key             :og-range
                                 impl/fine-grain-target-units style-display-og-range-target-units
                                 impl/fine-grain-precision    style-display-og-range-precision
                                 impl/fine-grain-suffix       style-display-og-range-suffix})]
     (impl/enrich-displayable-range options/specific-gravity style options))))


(defn enrich-fg-range
  "An enricher pattern function to render a human-readable display final gravity ranges for a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

   An option map may be passed as an optional second argument to this function to override the default behavior.
   Supported keys include:

   - `:system-of-measure`: The unit system of measure to convert the gravity range into. Defaults to `:us`. Acceptable values are:
        - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
        - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
        - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
        - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
    - `:precision`: The number of significant decimal places to display. Defaults to 3.
    - `:suffix`: The suffix type to append to the gravity range Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
        - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

   To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
    - `:style-display-fg-range-target-units`: The unit to convert the gravity range into. Supersedes `:system-of-measure`.
    - `:style-display-fg-range-precision`: The number of significant decimal places to display. Supersedes `:precision`.
    - `:style-display-fg-range-suffix`: The suffix type to append to the gravity range. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-fg-range style {}))
  ([style {:keys [style-display-fg-range-target-units
                  style-display-fg-range-precision
                  style-display-fg-range-suffix]
           :as   options}]
   (let [options (merge options {impl/low-value-key           :fg-min
                                 impl/high-value-key          :fg-max
                                 impl/display-key             :fg-range
                                 impl/fine-grain-target-units style-display-fg-range-target-units
                                 impl/fine-grain-precision    style-display-fg-range-precision
                                 impl/fine-grain-suffix       style-display-fg-range-suffix})]
     (impl/enrich-displayable-range options/specific-gravity style options))))


(defn enrich-ibu-range
  "An enricher pattern function to render a human-readable display of IBU ranges for a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

     An option map may be passed as an optional second argument to this function to override the default behavior.
     Supported keys include:

      - `:system-of-measure`: The unit system of measure to convert the bitterness range into. Defaults to `:us`. Acceptable values are:
          - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
          - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
          - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
          - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.
      - `:precision`: The number of significant decimal places to display. Defaults to 3.
      - `:suffix`: The suffix type to append to the gravity range Defaults to `:short`. Acceptable values are:
          - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
          - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

     To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
      - `:style-display-ibu-range-target-units`: The unit to convert the gravity range into. Supersedes `:system-of-measure`.
      - `:style-display-ibu-range-precision`: The number of significant decimal places to display. Supersedes `:precision`.
      - `:style-display-ibu-range-suffix`: The suffix type to append to the gravity range. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-ibu-range style {}))
  ([style {:keys [style-display-ibu-range-target-units
                  style-display-ibu-range-precision
                  style-display-ibu-range-suffix]
           :as   options}]
   (let [options (merge options {impl/low-value-key           :ibu-min
                                 impl/high-value-key          :ibu-max
                                 impl/display-key             :ibu-range
                                 impl/fine-grain-target-units style-display-ibu-range-target-units
                                 impl/fine-grain-precision    style-display-ibu-range-precision
                                 impl/fine-grain-suffix       style-display-ibu-range-suffix})]
     (impl/enrich-displayable-range options/bitterness style options))))


(defn enrich-color-range
  "An enricher pattern function to render a human-readable display of color ranges for a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

     An option map may be passed as an optional second argument to this function to override the default behavior.
     Supported keys include:

      - `:color-system` - The color system to use for the conversion. Default is `:lovibond` for type `:grain`, and `:srm` otherwise. Acceptable values are:
          - `:lovibond` - Use the [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour) system.
          - `:srm` - Use the [Standard Reference Method](https://en.wikipedia.org/wiki/Standard_Reference_Method) system.
          - `:ebc` - Use the [European Brewing Convention](https://www.lovibond.com/en/PC/Colour-Measurement/Colour-Scales-Standards/EBC-European-Brewing-Convention) system.
          - `:rgba` - USe the [RGBa](https://www.w3schools.com/cssref/func_rgba.asp) color system, commonly used in CSS.

      - `:precision`: The number of significant decimal places to display. Defaults to 3.
      - `:suffix`: The suffix type to append to the gravity range Defaults to `:short`. Acceptable values are:
          - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
          - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

     To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
      - `:style-display-color-range-target-units`: The unit to convert the gravity range into. Supersedes `:system-of-measure`.
      - `:style-display-color-range-precision`: The number of significant decimal places to display. Supersedes `:precision`.
      - `:style-display-color-range-suffix`: The suffix type to append to the gravity range. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-color-range style {}))
  ([style {:keys [style-display-color-range-target-units
                  style-display-color-range-precision
                  style-display-color-range-suffix]
           :as   options}]
   (let [options (merge options {impl/low-value-key           :color-min
                                 impl/high-value-key          :color-max
                                 impl/display-key             :color-range
                                 impl/fine-grain-target-units style-display-color-range-target-units
                                 impl/fine-grain-precision    style-display-color-range-precision
                                 impl/fine-grain-suffix       style-display-color-range-suffix})]
     (impl/enrich-displayable-range options/color style options))))


;; NOTE CARB AND ABV MIN/MAXES ARE OPTIONAL
(defn enrich-carb-range
  "An enricher pattern function to render a human-readable display of carbonation ranges for a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

     Carbonation ranges are optional and may not be present in all styles. If the carbonation range is not present, this function will return the `style` map unmodified.
     An option map may be passed as an optional second argument to this function to override the default behavior.
     Supported keys include:

      - `:system-of-measure`: The unit system of measure to convert the bitterness range into. Defaults to `:us`. Acceptable values are:
          - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
          - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
          - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
          - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.

      - `:precision`: The number of significant decimal places to display. Defaults to 3.
      - `:suffix`: The suffix type to append to the gravity range Defaults to `:short`. Acceptable values are:
          - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
          - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

     To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
      - `:style-display-carbonation-range-target-units`: The unit to convert the gravity range into. Supersedes `:system-of-measure`.
      - `:style-display-carbonation-range-precision`: The number of significant decimal places to display. Supersedes `:precision`.
      - `:style-display-carbonation-range-suffix`: The suffix type to append to the gravity range. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-carb-range style {}))
  ([style {:keys [style-display-carbonation-range-target-units
                  style-display-carbonation-range-precision
                  style-display-carbonation-range-suffix]
           :as   options}]
   (let [options (merge options {impl/low-value-key           :carb-min
                                 impl/high-value-key          :carb-max
                                 impl/display-key             :carb-range
                                 impl/fine-grain-target-units style-display-carbonation-range-target-units
                                 impl/fine-grain-precision    style-display-carbonation-range-precision
                                 impl/fine-grain-suffix       style-display-carbonation-range-suffix})]
     (if (and (:carb-min style) (:carb-max style))
       (impl/enrich-displayable-range options/carbonation style options)
       style))))


(defn enrich-abv-range
  "An enricher pattern function to render a human-readable display of alcohol by volume ranges for a [style](https://github.com/Wall-Brew-Co/common-beer-format/blob/master/src/common_beer_format/style.cljc) in a given system.

     ABV ranges are optional and may not be present in all styles. If the ABV range is not present, this function will return the `style` map unmodified.
     An option map may be passed as an optional second argument to this function to override the default behavior.
     Supported keys include:

      - `:system-of-measure`: The unit system of measure to convert the bitterness range into. Defaults to `:us`. Acceptable values are:
          - `:imperial`: The [British imperial](https://en.wikipedia.org/wiki/Imperial_units) system of measure.
          - `:metric`: The [metric system](https://en.wikipedia.org/wiki/Metric_system) of measure.
          - `:us`: The [United States Customary Units](https://en.wikipedia.org/wiki/United_States_customary_units) system of measure.
          - `:si`: The [International System of Units](https://en.wikipedia.org/wiki/International_System_of_Units) system of measure.

      - `:precision`: The number of significant decimal places to display. Defaults to 3.
      - `:suffix`: The suffix type to append to the gravity range Defaults to `:short`. Acceptable values are:
          - `:short`: A customary abbreviation for the selected unit. For example, `\"lb\"` for `\"pounds\"`.
          - `:full`: The full name of the selected unit. For example, `\"gram\"` for `\"gram\"`.

     To support fine-grained selections within the context of `enrich-style` and `enrich-style-wrapper`, this function also supports the following keys:
      - `:style-display-abv-range-target-units`: The unit to convert the gravity range into. Supersedes `:system-of-measure`.
      - `:style-display-abv-range-precision`: The number of significant decimal places to display. Supersedes `:precision`.
      - `:style-display-abv-range-suffix`: The suffix type to append to the gravity range. Supersedes `:suffix`."
  {:added    "2.3"
   :see-also ["enrich-style"
              "enrich-style-wrapper"
              "enrich-styles"
              "enrich-styles-wrapper"]}
  ([style] (enrich-abv-range style {}))
  ([style {:keys [style-display-abv-range-target-units
                  style-display-abv-range-precision
                  style-display-abv-range-suffix]
           :as   options}]
   (let [options (merge options {impl/low-value-key           :abv-min
                                 impl/high-value-key          :abv-max
                                 impl/display-key             :abv-range
                                 impl/fine-grain-target-units style-display-abv-range-target-units
                                 impl/fine-grain-precision    style-display-abv-range-precision
                                 impl/fine-grain-suffix       style-display-abv-range-suffix})]
     (if (and (:abv-min style) (:abv-max style))
       (impl/enrich-displayable-range options/abv style options)
       style))))
