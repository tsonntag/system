(ns tsonntag.system.directory
  (refer-clojure :exclude [read])
  (:require
   [clojure.java.io            :as io :only [file]]
   [com.stuartsierra.component :as c]
   [tsonntag.system.file-store :as fs]))


(defn- to-file
  "creates file from root-dir path and file-name and returns java.io.File where path is an array of strings"
  [root-dir path name]
  (->> [root-dir path name]
       flatten
       (apply io/file)))

(defrecord Directory [root-dir]
  c/Lifecycle
  (start [this]
    this)

  (stop [this]
    this)

  fs/FileStore
  (read [{:keys [root-dir] :as this} path name encoding {:keys [done]}]
    (let [content (slurp (to-file root-dir path name) :encoding encoding)]
      (if done
        (cond
          (string? done)   (fs/rename this path name (str name done))
          (= :delete done) (fs/delete this path name)
          :else            (throw (ex-info (str "invalid option " done))))
        )
      content))

  (read [this path name encoding]
    (fs/read this path name encoding {}))

  (write [{:keys [root-dir] :as this} {:keys [content name encoding]} path {:keys [tmp]}]
    (println "WWWWW" content (to-file root-dir path name))
    (if tmp
      (let [tmp-name (str name tmp)]
        (spit (to-file root-dir path tmp-name) content encoding encoding)
        (fs/rename this path tmp-name name))
      (spit (to-file root-dir path name) content :encoding encoding)))

  (write [this file path]
    (fs/write this file path {}))

  (rename [{:keys [root-dir]} path from to]
    (.renameTo (to-file root-dir path from) (to-file root-dir path to)))

  (delete [{:keys [root-dir]} path name]
    (.delete (to-file root-dir path name))))


(defn new-directory [root-dir]
  (map->Directory {:root-dir root-dir}))
