# Symbolic Keyword Pattern

In many clojure libraries, the behavior of complex functions may be controlled by a map.
For example:

```clojure
(:require [brewtility.string :as b-str])

(sut/prepare-for-compare "  100 lines of CODE") ;; => "100 lines of code"
(sut/prepare-for-compare "  100 lines of CODE" {:uppercase? true}) ;; => "100 LINES OF CODE"
```

This allows us to easily extend the definition of a single function to fulfil multiple complex needs; however, option maps come with considerable drawbacks.
When a map is keyed with keywords, it is easy to introduce subtle, hard-to-detect errors.
Since most of these functions select default values for keys not present, typos can lead to large differences in behavior.
For example:

```clojure
(:require [brewtility.string :as b-str])

(sut/prepare-for-compare "  100 lines of CODE") ;; => "100 lines of code"
(sut/prepare-for-compare "  100 lines of CODE" {:uppercased? true}) ;; => "100 lines of code"
```

Because keywords aren't picked up by the auto-complete features of most editors, they're vulnerable to all classes of transpositional errors.
Further, the options themselves are unable to carry metadata and documentation with them, making them little more than magic values.

For this reason, it is often helpful for libraries to include symbols which point to the magic keywords used in option maps.
This has several benefits:

- Misspelled or incorrect options are compile-time errors, instead of runtime bugs
- Symbols can carry metadata like documentation, deprecation notices, and point to alternative options
- Context-aware editors can auto-complete options

Here's can example:

```clojure
(:require [brewtility.string :as b-str])

(def compare-with-uppercase?
  "An option map key to cast strings to UPPER CASE in `prepare-for-compare`.
   This option will be enabled if this key's value is truthy."
  :uppercase?)

(sut/prepare-for-compare "  100 lines of CODE") ;; => "100 lines of code"
(sut/prepare-for-compare "  100 lines of CODE" {compare-with-uppercase? true}) ;; => "100 LINES OF CODE"
```

Most option maps in `brewtility` support symbolic keywords.
Options relevant to only one namespace are colocated with the functionality they control.
High-level options which control behavior throughout brewtility can be found in `brewtility.static`.
