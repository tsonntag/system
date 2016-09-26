(ns tsonntag.system.dir
 (:require
  [clojure.java.io            :as io :only [file]]
  [com.stuartsierra.component :as c]
  [tsonntag.system.file-store :as fs]))


(defn to-path
  "creates file from args and returns path as string"
  [& args]
  (.getPath (apply io/file args)))

(defrecord Dir [root-dir]
  c/Lifecycle
  (start [this]
    this)

  (stop [this]
    this)

  fs/FileStore
  (read [{:keys [path encoding] :as this}]
    (slurp (apply io/file root-dir path) :encoding encoding))

  (write [{:keys [root] :as this} {:keys [content path encoding] :as file} {:key [tmped] :as opts}]
    (let [path (apply io/file root path)]
      (if tmped
        (let [tmp-path (apply io/file root path ".tmp")]
          (spit content tmp-path :encoding encoding)
          (.rename tmp-path path))
        (split content path :encoding encoding))))
