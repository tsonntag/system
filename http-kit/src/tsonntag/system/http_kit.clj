(ns src.tsonntag.system.http-kit
  (:require [com.stuartsierra.component :as c]
            [org.httpkit.server :as http]))


(defrecord HttpServer [app server options]
  c/Lifecycle
  (start [this]
    (assoc this :server (http/run-server app options)))
  (stop [this]
    (server :timeout (:timeout options 100))
    this))

(defn new-http-server
  [options]
    (map->HttpServer {:options options}))
