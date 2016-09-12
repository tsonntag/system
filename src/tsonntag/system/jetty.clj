(ns tsonntag.system.jetty
    (:require
      [com.stuartsierra.component  :as c]
      [ring.adapter.jetty :refer [run-jetty]]))

(defrecord WebServer [port server app]
  c/Lifecycle
  (start [this]
    (assoc this
           :server
           (run-jetty (:handler app) {:port port :join? false})))
  (stop [this]
    (when server
      (.stop server)
      this)))

(defn new-web-server [port]
  (map->WebServer {:port (Integer. port)}))
