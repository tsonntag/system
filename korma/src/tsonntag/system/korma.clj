(ns tsonntag.system.korma
  (:require
    [com.stuartsierra.component :as c]
    [korma.db :refer [create-db default-connection get-connection]]))

(defrecord KormaDb [conn spec]
  c/Lifecycle
  (start [this]
    (assoc this :conn (default-connection (create-db spec))))

  (stop [this]
    (some-> this :conn :pool get-connection :datasource .close)
    this))

(defn new-db [spec]
  (map->KormaDb {:spec spec}))
