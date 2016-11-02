(ns tsonntag.system.redis
 (:require
  [com.stuartsierra.component :as c]
  [tsonntag.system.queue      :as q]
  [taoensso.carmine           :as car :refer (wcar)]))


(defrecord Redis [name host port conn]
  c/Lifecycle
  (start [this]
    (assoc this :conn {:pool {}
                       :spec {:host host :port port}}))
  (stop [this]
    this)

  q/Queue
  (name [this]
    (:name this))

  (push [{:keys [name conn]} obj]
    (debug "redis: push" obj)
    (wcar conn (car/lpush name obj)))

  (pop [{:keys [name conn]}]
    (wcar conn (car/rpop name)))

  (delete [{:keys [name conn]}]
    (wcar conn (car/del name)))

  (length [{:keys [name conn]}]
    (wcar conn (car/llen name))))

(defn new-redis-queue [name host port]
  (map->Redis {:name name :host host :port port}))
