(defproject com.wallbrew/brewtility "2.2.1"
  :description "Utility functions for all of your brewing needs."
  :url "https://github.com/Wall-Brew-Co/brewtility"
  :license {:name         "MIT"
            :url          "https://opensource.org/licenses/MIT"
            :distribution :repo
            :comments     "Same-as all Wall-Brew projects"}
  :scm {:name "git"
        :url  "https://github.com/Wall-Brew-Co/brewtility"}
  :pom-addition [:organization
                 [:name "Wall Brew Co."]
                 [:url "https://wallbrew.com"]]
  :dependencies [[com.wallbrew/spoon "1.4.0"]
                 [org.clojure/clojure "1.12.0"]
                 [org.clojure/clojurescript "1.11.132" :scope "provided"]]

  :plugins [[com.github.clj-kondo/lein-clj-kondo "2024.09.27"]
            [com.wallbrew/lein-sealog "1.8.0"]
            [com.wallbrew/bouncer "1.1.1"]
            [lein-cljsbuild/lein-cljsbuild "1.1.8"]
            [mvxcvi/cljstyle "0.16.630"]]

  :deploy-repositories [["clojars" {:url           "https://clojars.org/repo"
                                    :username      :env/clojars_user
                                    :password      :env/clojars_pass
                                    :sign-releases false}]]
  :deploy-branches ["master"]

  :profiles {:uberjar {:aot :all}
             :dev     {:dependencies [[cider/cider-nrepl "0.50.2"]
                                      [com.wallbrew/common-beer-data "1.6.0"]
                                      [com.wallbrew/common-beer-format "2.4.1"]
                                      [doo/doo "0.1.11"]
                                      [mvxcvi/cljstyle "0.16.630"]
                                      [nrepl/nrepl "1.3.0"]]
                       :plugins      [[lein-doo/lein-doo "0.1.11"]]}}

  :aliases {"cljstyle"   ["run" "-m" "cljstyle.main" "fix"]
            "test-build" ["do" "clean" ["cljsbuild" "once" "test"] ["doo" "once"] ["test"]]}

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
