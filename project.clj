(defproject com.wallbrew/brewtility "1.2.0"
  :description "Utility functions for all of your brewing needs"
  :url "https://github.com/Wall-Brew-Co/brewtility"
  :license {:name "MIT"
            :url  "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.60" :scope "provided"]]

  :plugins [[lein-cljsbuild "1.1.8"]]

  :profiles {:uberjar {:aot :all}
             :dev     {:dependencies [[com.wallbrew/common-beer-data "1.1.0"]
                                      [com.wallbrew/common-beer-format "2.1.0"]
                                      [doo "0.1.11"]]
                       :plugins      [[lein-doo "0.1.11"]]}}

  :aliases {"test-build" ["do" "clean" ["cljsbuild" "once" "test"] ["doo" "once"] ["test"]]}

  :cljsbuild {:builds [{:id           "test"
                        :source-paths ["src" "test"]
                        :compiler     {:main           "brewtility.runner"
                                       :output-to      "target/test/app.js"
                                       :output-dir     "target/test/js/compiled/out"
                                       :optimizations  :none
                                       :parallel-build true}}]}

  :doo {:build "test"
        :alias {:default [:chrome-headless-no-sandbox]}
        :paths {:karma "./node_modules/karma/bin/karma"}
        :karma {:launchers {:chrome-headless-no-sandbox {:plugin "karma-chrome-launcher"
                                                         :name   "ChromeHeadlessNoSandbox"}}
                :config    {"captureTimeout"             210000
                            "browserDisconnectTolerance" 3
                            "browserDisconnectTimeout"   210000
                            "browserNoActivityTimeout"   210000
                            "customLaunchers"            {"ChromeHeadlessNoSandbox" {"base"  "ChromeHeadless"
                                                                                     "flags" ["--no-sandbox" "--disable-dev-shm-usage"]}}}}}

  :min-lein-version "2.5.3")
