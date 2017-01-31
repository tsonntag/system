(defproject tsonntag.system/redis "1.1.0"
  :description "redis component for Stuart Sierra's component library"
  :url "http://github.com/tsonntag/system"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.taoensso/carmine "2.11.1"]
                 [com.stuartsierra/component "0.3.1"]]
  :profiles {:dev {:source-paths ["dev"]}})
