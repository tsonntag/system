(defproject tsonntag.system "1.1.0"
  :description "Components for Stuart Sierra's component library"
  :url "http://github.com/tsonntag/system"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [tsonntag.system/app "1.1.0"]
                 [tsonntag.system/http-client "1.1.0"]
                 [tsonntag.system/http-kit "1.1.0"]
                 [tsonntag.system/jetty "1.1.0"]
                 [tsonntag.system/korma "1.1.0"]
                 [tsonntag.system/redis "1.1.0"]]
  :plugins [[lein-sub "0.3.0"]]
  :sub ["app" "http-client" "http-kit" "jdbc" "jetty" "korma" "queue" "redis"])
