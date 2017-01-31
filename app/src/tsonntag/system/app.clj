(ns tsonntag.system.app
  (:require
    [com.stuartsierra.component  :as c]))

(defn- wrap [handler args]
  (fn [req]
    (handler (merge req args))))

(defrecord App [handler]
    c/Lifecycle
    (start [this]
      (assoc this
             :handler
             (-> handler
                 ; we wrap everything (typically all injected keys, e.g. databases)
                 (wrap (dissoc this :handler)))))

    (stop [this] this))

(defn new-app [handler]
  (map->App {:handler handler}))
