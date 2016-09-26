(ns tsonntag.system.file-store)


(defrecord File [content path encoding])

(defprotocol FileStore
  (read [this path encoding]
    "Returns File for given relative path and encoding")

  (write [this file &opts]
    "Writes File to fileStore.
     opts:
       :tmped write as path.tmp and then rename to path"))
