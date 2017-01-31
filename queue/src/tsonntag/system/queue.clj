(ns tsonntag.system.queue
  (:refer-clojure :exclude [name pop]))

(defprotocol Queue
  (name  [this])
  (push   [this obj])
  (pop    [this])
  (delete [this])
  (length [this]))
