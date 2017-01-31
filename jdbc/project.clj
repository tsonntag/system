(defproject tsonntag.system/jdbc "1.1.0"
  :description "jdbc component for Stuart Sierra's component library"
  :url "http://github.com/tsonntag/system"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.stuartsierra/component "0.2.2"]
                 [com.mchange/c3p0 "0.9.5.2"]
                 [org.clojure/java.jdbc "0.3.3"]]
  :profiles {:dev {:dependencies [[org.postgresql/postgresql "9.4-1212"]]}})
