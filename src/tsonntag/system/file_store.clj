(ns tsonntag.system.file-store
  (refer-clojure :exclude [read]))


(defrecord File [content name encoding])

(defprotocol FileStore
  (read
    [this path name encoding]
    [this path name encoding opts]
    "Returns File with name, relative path and encoding where path is an array of strings.
     opts: :done    <suffix>|:delete   Appends suffix resp. deletes files after file has been read.")

  (write
    [this file path]
    [this file path opts]
    "Writes File to to path of fileStore. path is an array of strings.
     opts: :tmp <tmp>  writes as path.tmp and then rename to path")

  (rename
    [this path from to]
    "Renames file with given path and filename 'from' to 'to'
     where path is an array of strings")

  (delete
    [this path name]
    "Deletes file with given path and name
     where path is an array of strings"))
