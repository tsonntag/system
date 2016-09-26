(ns tsonntag.system.queue)

(defprotocol Queue
  (name  [this])
  (push   [this obj])
  (pop    [this])
  (delete [this])
  (length [this]))
