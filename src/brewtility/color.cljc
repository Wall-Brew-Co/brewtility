(ns brewtility.color
  "Namespace for calculating beer colors"
  {:added "1.0"}
  (:require [brewtility.static :as static]))


(def ^:const color-systems
  "The color systems available across brewtility"
  #{static/srm
    static/ebc
    static/lovibond
    static/rgba})


(def ^:const color-system->display-name
  "A map from color system names to their full and short unit names"
  {static/srm      {static/full  "standard reference method"
                    static/short "srm"}
   static/ebc      {static/full  "ebc"
                    static/short "ebc"}
   static/lovibond {static/full  "degrees lovibond"
                    static/short "°L"}
   static/rgba     {static/full  ""
                    static/short ""}})


;;
;; SRM Beer Colors (https://en.wikipedia.org/wiki/Standard_Reference_Method)
;;
(def ^:const srm-1
  "An SRM of 1 mapped to an RGBa color code"

  "rgba(255,230,153,1)")


(def ^:const srm-2
  "An SRM of 2 mapped to an RGBa color code"

  "rgba(255,216,120,1)")


(def ^:const srm-3
  "An SRM of 3 mapped to an RGBa color code"

  "rgba(255,202,90,1)")


(def ^:const srm-4
  "An SRM of 4 mapped to an RGBa color code"
  "rgba(255,191,66,1)")


(def ^:const srm-5
  "An SRM of 5 mapped to an RGBa color code"
  "rgba(251,177,35,1)")


(def ^:const srm-6
  "An SRM of 6 mapped to an RGBa color code"
  "rgba(248,166,0,1)")


(def ^:const srm-7
  "An SRM of 7 mapped to an RGBa color code"
  "rgba(243,156,0,1)")


(def ^:const srm-8
  "An SRM of 8 mapped to an RGBa color code"
  "rgba(234,143,0,1)")


(def ^:const srm-9
  "An SRM of 9 mapped to an RGBa color code"
  "rgba(229,133,0,1)")


(def ^:const srm-10
  "An SRM of 10 mapped to an RGBa color code"
  "rgba(222,124,0,1)")


(def ^:const srm-11
  "An SRM of 11 mapped to an RGBa color code"
  "rgba(215,114,0,1)")


(def ^:const srm-12
  "An SRM of 12 mapped to an RGBa color code"
  "rgba(207,105,0,1)")


(def ^:const srm-13
  "An SRM of 13 mapped to an RGBa color code"
  "rgba(203,98,0,1)")


(def ^:const srm-14
  "An SRM of 14 mapped to an RGBa color code"
  "rgba(195,89,0,1)")


(def ^:const srm-15
  "An SRM of 15 mapped to an RGBa color code"
  "rgba(187,81,0,1)")


(def ^:const srm-16
  "An SRM of 16 mapped to an RGBa color code"
  "rgba(181,76,0,1)")


(def ^:const srm-17
  "An SRM of 17 mapped to an RGBa color code"
  "rgba(176,69,0,1)")


(def ^:const srm-18
  "An SRM of 18 mapped to an RGBa color code"
  "rgba(166,62,0,1)")


(def ^:const srm-19
  "An SRM of 19 mapped to an RGBa color code"
  "rgba(161,55,0,1)")


(def ^:const srm-20
  "An SRM of 20 mapped to an RGBa color code"
  "rgba(155,50,0,1)")


(def ^:const srm-21
  "An SRM of 21 mapped to an RGBa color code"
  "rgba(149,45,0,1)")


(def ^:const srm-22
  "An SRM of 22 mapped to an RGBa color code"
  "rgba(142,41,0,1)")


(def ^:const srm-23
  "An SRM of 23 mapped to an RGBa color code"
  "rgba(136,35,0,1)")


(def ^:const srm-24
  "An SRM of 24 mapped to an RGBa color code"
  "rgba(130,30,0,1)")


(def ^:const srm-25
  "An SRM of 25 mapped to an RGBa color code"
  "rgba(123,26,0,1)")


(def ^:const srm-26
  "An SRM of 26 mapped to an RGBa color code"
  "rgba(119,25,0,1)")


(def ^:const srm-27
  "An SRM of 27 mapped to an RGBa color code"
  "rgba(112,20,0,1)")


(def ^:const srm-28
  "An SRM of 28 mapped to an RGBa color code"
  "rgba(106,14,0,1)")


(def ^:const srm-29
  "An SRM of 29 mapped to an RGBa color code"
  "rgba(102,13,0,1)")


(def ^:const srm-30
  "An SRM of 30 mapped to an RGBa color code"
  "rgba(94,11,0,1)")


(def ^:const srm-31
  "An SRM of 31 mapped to an RGBa color code"
  "rgba(90,10,2,1)")


(def ^:const srm-32
  "An SRM of 32 mapped to an RGBa color code"
  "rgba(96,9,3,1)")


(def ^:const srm-33
  "An SRM of 33 mapped to an RGBa color code"
  "rgba(82,9,7,1)")


(def ^:const srm-34
  "An SRM of 34 mapped to an RGBa color code"
  "rgba(76,5,5,1)")


(def ^:const srm-35
  "An SRM of 35 mapped to an RGBa color code"
  "rgba(71,6,6,1)")


(def ^:const srm-36
  "An SRM of 36 mapped to an RGBa color code"
  "rgba(68,6,7,1)")


(def ^:const srm-37
  "An SRM of 37 mapped to an RGBa color code"
  "rgba(63,7,8,1)")


(def ^:const srm-38
  "An SRM of 38 mapped to an RGBa color code"
  "rgba(59,6,7,1)")


(def ^:const srm-39
  "An SRM of 39 mapped to an RGBa color code"
  "rgba(58,7,11,1)")


(def ^:const srm-40
  "An SRM of 40 mapped to an RGBa color code"
  "rgba(3,4,3,1)")


(def ^:const srm-color-map
  "A map of integer values to their closest SRM value as an RGBa color code"
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


(defn lovibond->srm
  "Convert the color described in degrees `lovibond` to the equivalent SRM color"
  {:added "1.0"}
  [lovibond]
  (- (* lovibond 1.3546) 0.76))


(defn srm->ebc
  "Convert the color described by the `srm` to the equivalent EBC color"
  {:added "1.0"}
  [srm]
  (* srm 1.97))


(defn ebc->srm
  "Convert the color described by the `ebc` to the equivalent SRM color"
  {:added "1.0"}
  [ebc]
  (* ebc 0.508))


(defn srm->lovibond
  "Convert the color described in 'srm` to the equivalent degrees Lovibond"
  {:added "1.0"}
  [srm]
  (/ (+ srm 0.76) 1.3546))


(def lovibond->ebc
  "Convert the color described in degrees 'lovibond` to the equivalent EBC color"
  (comp srm->ebc lovibond->srm))


(def ebc->lovibond
  "Convert the color described in 'ebc` to the equivalent degrees Lovibond"
  (comp srm->lovibond ebc->srm))


(defn srm->rgba
  "Given `srm-number`, return the closest, bounded applicable RGBA color string.
   OPINIONATED: The provided `srm-number` will be bound to the common range from 1 to 40
                Decimal-like values are trimmed, not rounded."
  {:added "1.0"}
  [srm-number]
  (let [srm-color (min (max (int srm-number) 1) 40)]
    (get srm-color-map srm-color)))


(def lovibond->rgba
  "Given `lovibond-number`, return the closest, bounded applicable RGBA color string."
  (comp srm->rgba lovibond->srm))


(def ebc->rgba
  "Given `ebc-number`, return the closest, bounded applicable RGBA color string."
  (comp srm->rgba ebc->srm))
