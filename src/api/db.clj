(ns api.db
;;   (:require
;;    [mount.core :refer [defstate]])
  )

(def user-db (atom {:users []}))

;; (defstate user-db
;;   "The user database for the api for time being"
;;   :start '{:users []})