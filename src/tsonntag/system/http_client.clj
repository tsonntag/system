(ns tsonntag.system.http-client
    (:refer-clojure :exclude [get])
      (:require
        [com.stuartsierra.component   :as c]
        [clojure.string :as str]
        [clj-http.client :as client]))

(defrecord HttpClient [url]
  c/Lifecycle
  (start [this] this)
  (stop  [this] this))

(defn new-http-client [url]
  (map->HttpClient {:url url}))

(defn- -url [client path]
  (str/join "/" [(:url client) path]))

(defn GET [client path params]
  (->
    (client/get (-url client path) {:query-params params :as :json})
    (:body)))

(defn POST [client path params]
  (client/post (-url client path) params))
