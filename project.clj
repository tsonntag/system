(defproject tsonntag/system "1.0.0"
  :description "Components for Stuart Sierra's component library"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.2.0"]
                 [korma "0.4.3"]
                 [ring/ring-jetty-adaptor "1.5.0"]
                 [com.taoensso/carmine "2.11.1"]
                 [com.stuartsierra/component "0.3.1"]]
  :profiles {:dev {:source-paths ["dev"]}}
  :target-path "target/%s")
