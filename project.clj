(defproject l22 "v2.3.585"
  :description "for literacy 2022 classes"
  :url "https://l22.melt.kyutech.ac.jp"
  :dependencies
  [[buddy/buddy-auth "3.0.323"]
   [buddy/buddy-core "1.11.423"]
   [buddy/buddy-hashers "2.0.167"]
   [buddy/buddy-sign "3.5.351"]
   [ch.qos.logback/logback-classic "1.5.6"]
   [clojure.java-time "1.4.2"]
   [conman "0.9.6"]
   [cprop "0.1.20"]
   [expound "0.9.0"]
   [funcool/struct "1.4.0"]
   [json-html "0.4.7"]
   [luminus-migrations "0.7.5"]
   [luminus-transit "0.1.6"]
   [luminus-undertow "0.1.18"]
   [luminus/ring-ttl-session "0.3.3"]
   [markdown-clj "1.12.1"]
   [metosin/muuntaja "0.6.10"]
   [metosin/reitit "0.7.0"]
   [metosin/ring-http-response "0.9.3"]
   [mount "0.1.18"]
   [nrepl "1.1.1"]
   [org.clojure/clojure "1.11.3"]
   [org.clojure/tools.cli "1.1.230"]
   [org.clojure/tools.logging "1.3.0"]
   [org.postgresql/postgresql "42.7.3"]
   [org.webjars.npm/bulma "0.9.4"] ;; 1.0.0
   [org.webjars.npm/material-icons "1.13.2"]
   [org.webjars/webjars-locator "0.52"]
   [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
   [ring-cors "0.1.13"]
   [ring-webjars "0.2.0"]
   [ring/ring-core "1.12.1"]
   [ring/ring-defaults "0.5.0"]
   [selmer "1.12.59"]]
  :min-lein-version "2.0.0"
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot l22.core
  :plugins []
  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "l22.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}
   :dev [:project/dev :profiles/dev]
   :test [:project/dev :project/test :profiles/test]
   :project/dev
   {:jvm-opts ["-Dconf=dev-config.edn"]
    :dependencies
    [[org.clojure/tools.namespace "1.5.0"]
     [pjstadig/humane-test-output "0.11.0"]
     [prone "2021-04-23"]
     [ring/ring-devel "1.12.1"]
     [ring/ring-mock "0.4.0"]]
    :plugins
    [[com.jakemccrary/lein-test-refresh "0.25.0"]
     [jonase/eastwood "1.4.2"]
     [cider/cider-nrepl "0.47.1"]]
    :source-paths ["env/dev/clj"]
    :resource-paths ["env/dev/resources"]
    :repl-options {:init-ns user
                   :timeout 120000}
    :injections
    [(require 'pjstadig.humane-test-output)
     (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn"]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
