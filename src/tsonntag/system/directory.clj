(ns tsonntag.system.directory
  (refer-clojure :exclude [read])
  (:require
   [clojure.java.io            :as io :only [file]]
   [com.stuartsierra.component :as c]
   [tsonntag.system.file-store :as fs]))


(defn- to-file
  "creates file from root-dir path and file-name and returns java.io.File where path is an array of strings"
  [root-dir path file-name]
  (->> [root-dir path file-name]
       flatten
       (apply io/file)))

(defrecord Directory [root-dir]
  c/Lifecycle
  (start [this]
    this)

  (stop [this]
    this)

  fs/FileStore
  (read [{:keys [root-dir] :as this} path file-name encoding {:keys [done]}]
    (let [content (slurp (to-file root-dir path file-name) :encoding encoding)]
      (if done
        (cond
          (string? done)   (fs/rename this path name (str name done))
          (= :delete done) (fs/delete this path file-name)
          :else            (throw (ex-info (str "invalid option " done))))
        )
      content))

  (read [this path file-name encoding]
    (fs/read this path file-name encoding {}))

  (write [{:keys [root-dir] :as this} {:keys [content file-name encoding]} path {:keys [tmp]}]
    (if tmp
      (let [tmp-file-name (str name tmp)]
        (spit (to-file root-dir path tmp-file-name) content encoding encoding)
        (fs/refile-name this path tmp-name name))
      (spit (to-file root-dir path file-name) content :encoding encoding)))

  (write [this file path]
    (fs/write this file path {}))

  (rename [{:keys [root-dir]} path from to]
    (.renameTo (to-file root-dir path from) (to-file root-dir path to)))

  (delete [{:keys [root-dir]} path file-name]
    (.delete (to-file root-dir path file-name))))


(defn new-directory [root-dir]
  (map->Directory {:root-dir root-dir}))
