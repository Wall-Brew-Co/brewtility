(ns brewtility.units.color
  "A namespace for converting between different units of color.
    
   In the BeerXML spec, color is measured in different units in different contexts.
   This namespace provides a way to convert between SRM and other units of color.
   
   Currently, brewtility supports the following types of color:
     - [SRM](https://en.wikipedia.org/wiki/Standard_Reference_Method)
     - [EBC](https://en.wikipedia.org/wiki/European_Brewery_Convention)
     - [Lovibond](https://en.wikipedia.org/wiki/Beer_measurement#Colour)
     - [RGBa](https://en.wikipedia.org/wiki/RGBA_color_model)"
  {:added "2.0"}
  (:require [brewtility.precision :as precision]
            [brewtility.units.options :as options]))


(def ^:const measurements
  "The color systems available across brewtility."
  #{options/srm
    options/ebc
    options/lovibond
    options/rgba})


(def ^:const measurements->display-name
  "A map from color system names to their full and short unit names"
  {options/srm      {options/full  "standard reference method"
                     options/short "srm"}
   options/ebc      {options/full  "ebc"
                     options/short "ebc"}
   options/lovibond {options/full  "degrees lovibond"
                     options/short "°L"}
   options/rgba     {options/full  ""
                     options/short ""}})


;;
;; SRM Beer Colors (https://en.wikipedia.org/wiki/Standard_Reference_Method)
;;
(def srm-1
  "An SRM of 1 mapped to an RGBa color code."

  "rgba(255,230,153,1)")


(def srm-2
  "An SRM of 2 mapped to an RGBa color code."

  "rgba(255,216,120,1)")


(def srm-3
  "An SRM of 3 mapped to an RGBa color code."

  "rgba(255,202,90,1)")


(def srm-4
  "An SRM of 4 mapped to an RGBa color code."
  "rgba(255,191,66,1)")


(def srm-5
  "An SRM of 5 mapped to an RGBa color code."
  "rgba(251,177,35,1)")


(def srm-6
  "An SRM of 6 mapped to an RGBa color code."
  "rgba(248,166,0,1)")


(def srm-7
  "An SRM of 7 mapped to an RGBa color code."
  "rgba(243,156,0,1)")


(def srm-8
  "An SRM of 8 mapped to an RGBa color code."
  "rgba(234,143,0,1)")


(def srm-9
  "An SRM of 9 mapped to an RGBa color code."
  "rgba(229,133,0,1)")


(def srm-10
  "An SRM of 10 mapped to an RGBa color code."
  "rgba(222,124,0,1)")


(def srm-11
  "An SRM of 11 mapped to an RGBa color code."
  "rgba(215,114,0,1)")


(def srm-12
  "An SRM of 12 mapped to an RGBa color code."
  "rgba(207,105,0,1)")


(def srm-13
  "An SRM of 13 mapped to an RGBa color code."
  "rgba(203,98,0,1)")


(def srm-14
  "An SRM of 14 mapped to an RGBa color code."
  "rgba(195,89,0,1)")


(def srm-15
  "An SRM of 15 mapped to an RGBa color code."
  "rgba(187,81,0,1)")


(def srm-16
  "An SRM of 16 mapped to an RGBa color code."
  "rgba(181,76,0,1)")


(def srm-17
  "An SRM of 17 mapped to an RGBa color code."
  "rgba(176,69,0,1)")


(def srm-18
  "An SRM of 18 mapped to an RGBa color code."
  "rgba(166,62,0,1)")


(def srm-19
  "An SRM of 19 mapped to an RGBa color code."
  "rgba(161,55,0,1)")


(def srm-20
  "An SRM of 20 mapped to an RGBa color code."
  "rgba(155,50,0,1)")


(def srm-21
  "An SRM of 21 mapped to an RGBa color code."
  "rgba(149,45,0,1)")


(def srm-22
  "An SRM of 22 mapped to an RGBa color code."
  "rgba(142,41,0,1)")


(def srm-23
  "An SRM of 23 mapped to an RGBa color code."
  "rgba(136,35,0,1)")


(def srm-24
  "An SRM of 24 mapped to an RGBa color code."
  "rgba(130,30,0,1)")


(def srm-25
  "An SRM of 25 mapped to an RGBa color code."
  "rgba(123,26,0,1)")


(def srm-26
  "An SRM of 26 mapped to an RGBa color code."
  "rgba(119,25,0,1)")


(def srm-27
  "An SRM of 27 mapped to an RGBa color code."
  "rgba(112,20,0,1)")


(def srm-28
  "An SRM of 28 mapped to an RGBa color code."
  "rgba(106,14,0,1)")


(def srm-29
  "An SRM of 29 mapped to an RGBa color code."
  "rgba(102,13,0,1)")


(def srm-30
  "An SRM of 30 mapped to an RGBa color code."
  "rgba(94,11,0,1)")


(def srm-31
  "An SRM of 31 mapped to an RGBa color code."
  "rgba(90,10,2,1)")


(def srm-32
  "An SRM of 32 mapped to an RGBa color code."
  "rgba(96,9,3,1)")


(def srm-33
  "An SRM of 33 mapped to an RGBa color code."
  "rgba(82,9,7,1)")


(def srm-34
  "An SRM of 34 mapped to an RGBa color code."
  "rgba(76,5,5,1)")


(def srm-35
  "An SRM of 35 mapped to an RGBa color code."
  "rgba(71,6,6,1)")


(def srm-36
  "An SRM of 36 mapped to an RGBa color code."
  "rgba(68,6,7,1)")


(def srm-37
  "An SRM of 37 mapped to an RGBa color code."
  "rgba(63,7,8,1)")


(def srm-38
  "An SRM of 38 mapped to an RGBa color code."
  "rgba(59,6,7,1)")


(def srm-39
  "An SRM of 39 mapped to an RGBa color code."
  "rgba(58,7,11,1)")


(def srm-40
  "An SRM of 40 mapped to an RGBa color code."
  "rgba(3,4,3,1)")


(def srm-color-map
  "A map of integer values to their closest SRM value as an RGBa color code."
  {1	 srm-1
   2	 srm-2
   3	 srm-3
   4	 srm-4
   5	 srm-5
   6	 srm-6
   7	 srm-7
   8	 srm-8
   9	 srm-9
   10	 srm-10
   11	 srm-11
   12	 srm-12
   13	 srm-13
   14	 srm-14
   15	 srm-15
   16	 srm-16
   17	 srm-17
   18	 srm-18
   19	 srm-19
   20	 srm-20
   21	 srm-21
   22	 srm-22
   23	 srm-23
   24	 srm-24
   25	 srm-25
   26	 srm-26
   27	 srm-27
   28	 srm-28
   29	 srm-29
   30	 srm-30
   31	 srm-31
   32	 srm-32
   33	 srm-33
   34	 srm-34
   35	 srm-35
   36	 srm-36
   37	 srm-37
   38	 srm-38
   39	 srm-39
   40	 srm-40})


(defn- lovibond->srm
  "Convert the color described in degrees `lovibond` to the equivalent SRM color."
  {:added "2.0"}
  [degrees-lovibond]
  (- (* degrees-lovibond 1.3546) 0.76))


(defn- srm->ebc
  "Convert the color described by the `srm` to the equivalent EBC color."
  {:added "2.0"}
  [srm-value]
  (* srm-value 1.97))


(defn- ebc->srm
  "Convert the color described by the `ebc` to the equivalent SRM color."
  {:added "2.0"}
  [ebc-value]
  (* ebc-value 0.508))


(defn- srm->lovibond
  "Convert the color described in 'srm` to the equivalent degrees Lovibond."
  {:added "2.0"}
  [srm-value]
  (/ (+ srm-value 0.76) 1.3546))


(defn- srm->rgba
  "Given `srm-number`, return the closest, bounded applicable RGBA color string.
   OPINIONATED: The provided `srm-number` will be bound to the common range from 1 to 40.
                Decimal-like values are trimmed, not rounded."
  {:added "2.0"}
  [srm-number]
  (let [srm-color (min (max (int srm-number) 1) 40)]
    (get srm-color-map srm-color)))


(def measurement->srm
  "A map from color systems to the implementation function that converts to SRM.
   
   Note: RGBa is not included because it is not a color system, but a color representation."
  {options/ebc      ebc->srm
   options/lovibond lovibond->srm
   options/srm      identity})


(def srm->measurement
  "A map from color systems to the implementation function that converts from SRM"
  {options/ebc      srm->ebc
   options/lovibond srm->lovibond
   options/srm      identity
   options/rgba     srm->rgba})


(defn convert
  "Given a `color-val` in `source-system`, convert it to the `target-system`.

   Supported values for `source-system` and `target-system` are enumerated in [[color-systems]].
   This function will throw an exception if unsupported measurement values are passed."
  {:added "2.0"}
  [color-val source-system target-system]
  (if (and (contains? measurement->srm source-system)
           (contains? measurements target-system)
           (number? color-val))
    (if (= source-system target-system)
      color-val
      (let [source->srm-fn (measurement->srm source-system)
            srm->target-fn (srm->measurement target-system)]
        (-> color-val
            source->srm-fn
            srm->target-fn)))
    (throw (ex-info "Unsupported color conversion units"
                    {:source-system  source-system
                     :target-system  target-system
                     :allowed-values (-> measurement->srm keys set)
                     :color          color-val}))))


(defn display
  "A function to render a human-readable `color` in `source-units`.

   For example,
   ```clj
    (display 1.5 :lovibond) ;; => \"1.5 °L\"
   ```

   This function accepts an option map as an optional third argument. The following options are available:
     - `precision`: The number of decimal places to round to. Defaults to 3.
     - `suffix`: The suffix type to append to the displayable fields. Defaults to `:short`. Acceptable values are:
        - `:short`: A customary abbreviation for the selected unit. For example, `\"°L\"` for `\"Degrees Lovibond\"`.
        - `:full`: The full name of the selected unit. For example, `\"teaspoon\"` for `\"teaspoon\"`."
  {:added "2.0"}
  ([color source-units]
   (display color source-units {}))
  ([color source-units {:keys [precision suffix]
                        :or   {precision options/default-precision
                               suffix    options/short}}]
   (if (= source-units :rgba)
     color
     (if (and (contains? measurements source-units)
              (number? color)
              (integer? precision)
              (contains? options/supported-suffixes suffix))

       (let [display-name (get-in measurements->display-name [source-units suffix])]
         (-> color
             (precision/->precision precision)
             (str " " display-name)))
       (throw (ex-info "Unsupported color display options"
                       {:source-units     source-units
                        :allowed-values   measurements
                        :color            color
                        :precision        precision
                        :suffix           suffix
                        :allowed-suffixes options/supported-suffixes}))))))
