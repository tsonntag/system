(ns tsonntag.system.watcher
  (:require
   [com.stuartsierra.component :as component]
   [clojure-watch.core :refer [start-watch]]))

(extend-protocol component/Lifecycle
    Watcher
    (component/start [watcher]
      (start-watch watcher)
      watcher)

    (component/stop [watcher]
      watcher))

(defn new-watcher
  "Create new watcher from map with keys
   :path          string
   :event-types   collection of:create :modify :delete
   :bootstrap     function called after start. argument: path
   :callback      function called when event occurs. arguments: event, path
   :options       map. currently only: :recursive"
  [config]
  config)
