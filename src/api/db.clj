(ns api.db
;;   (:require
;;    [mount.core :refer [defstate]])
  )

(def user-db (atom {:users []}))

(defprotocol User
  (toString [this] "toString method")
  (loggedIn? [this] "Is user logged in")
  (json [this]))

(defrecord Myuser [user]
  ;;{:keys [name pwd logged-in] :or {logged-in false?} :as user-data}
  User
  (toString [this] (str (:user this)))
  (loggedIn? [this] (:logged-in (:user this)))
  (json [this] {:name (:name (:user this))}))

;; (defstate user-db
;;   "The user database for the api for time being"
;;   :start '{:users []})