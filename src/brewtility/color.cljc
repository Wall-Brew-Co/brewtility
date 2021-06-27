(ns brewtility.color
  "Namespace for calculating beer colors")

;;
;; SRM Beer Colors (https://en.wikipedia.org/wiki/Standard_Reference_Method)
;;
(def ^:const srm-1  "rgba(255,230,153,1)")
(def ^:const srm-2  "rgba(255,216,120,1)")
(def ^:const srm-3  "rgba(255,202,90,1)")
(def ^:const srm-4  "rgba(255,191,66,1)")
(def ^:const srm-5  "rgba(251,177,35,1)")
(def ^:const srm-6  "rgba(248,166,0,1)")
(def ^:const srm-7  "rgba(243,156,0,1)")
(def ^:const srm-8  "rgba(234,143,0,1)")
(def ^:const srm-9  "rgba(229,133,0,1)")
(def ^:const srm-10 "rgba(222,124,0,1)")
(def ^:const srm-11 "rgba(215,114,0,1)")
(def ^:const srm-12 "rgba(207,105,0,1)")
(def ^:const srm-13 "rgba(203,98,0,1)")
(def ^:const srm-14 "rgba(195,89,0,1)")
(def ^:const srm-15 "rgba(187,81,0,1)")
(def ^:const srm-16 "rgba(181,76,0,1)")
(def ^:const srm-17 "rgba(176,69,0,1)")
(def ^:const srm-18 "rgba(166,62,0,1)")
(def ^:const srm-19 "rgba(161,55,0,1)")
(def ^:const srm-20 "rgba(155,50,0,1)")
(def ^:const srm-21 "rgba(149,45,0,1)")
(def ^:const srm-22 "rgba(142,41,0,1)")
(def ^:const srm-23 "rgba(136,35,0,1)")
(def ^:const srm-24 "rgba(130,30,0,1)")
(def ^:const srm-25 "rgba(123,26,0,1)")
(def ^:const srm-26 "rgba(119,25,0,1)")
(def ^:const srm-27 "rgba(112,20,0,1)")
(def ^:const srm-28 "rgba(106,14,0,1)")
(def ^:const srm-29 "rgba(102,13,0,1)")
(def ^:const srm-30 "rgba(94,11,0,1)")
(def ^:const srm-31 "rgba(90,10,2,1)")
(def ^:const srm-32 "rgba(96,9,3,1)")
(def ^:const srm-33 "rgba(82,9,7,1)")
(def ^:const srm-34 "rgba(76,5,5,1)")
(def ^:const srm-35 "rgba(71,6,6,1)")
(def ^:const srm-36 "rgba(68,6,7,1)")
(def ^:const srm-37 "rgba(63,7,8,1)")
(def ^:const srm-38 "rgba(59,6,7,1)")
(def ^:const srm-39 "rgba(58,7,11,1)")
(def ^:const srm-40 "rgba(3,4,3,1)")


(def srm-color-map
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
  [lovibond]
  (- (* lovibond 1.3546) 0.76))


(defn srm->ebc
  "Convert the color described by the `srm` to the equivalent EBC color"
  [srm]
  (* srm 1.97))


(defn ebc->srm
  "Convert the color described by the `ebc` to the equivalent SRM color"
  [ebc]
  (* ebc 0.508))


(defn srm->lovibond
  "Convert the color described in 'srm` to the equivalent degrees Lovibond"
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
  [srm-number]
  (let [srm-color (min (max (int srm-number) 1) 40)]
    (get srm-color-map srm-color)))


(def lovibond->rgba
  "Given `lovibond-number`, return the closest, bounded applicable RGBA color string."
  (comp srm->rgba lovibond->srm))


(def ebc->rgba
  "Given `ebc-number`, return the closest, bounded applicable RGBA color string."
  (comp srm->rgba ebc->srm))
