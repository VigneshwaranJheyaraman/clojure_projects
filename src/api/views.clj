(ns api.views
  (:require
   [api.db :refer [user-db]]
   [api.utils :refer [responsify, is-valid-user?, get-valid-user]]
   [api.protocols.user :as protocol_user]))


(defrecord Myuser [user]
  protocol_user/IUser
  ;;{:keys [name pwd logged-in] :or {logged-in false?} :as user-data}
  (to-string [_] (:name (:name user)))
  (loggedIn? [_] (true? (:logged-in user)))
  (jsonify [this] {:name (:name user) :logged-in (protocol_user/loggedIn? this)}))

(defn return-user-profile
  [{:keys [userid]}]
  (let
   [users-db (:users @user-db)
    valid-user (first (get-valid-user {:name userid} users-db))
    valid-user-record (->Myuser valid-user)]
    (if (protocol_user/loggedIn? valid-user-record)
      (responsify 200 valid-user-record)
      (responsify 400 "User not logged in"))))

(defn login-user
  [user-to-validate]
  (let [users-list  (:users @user-db)
        valid-users (filter #(= (:pwd %) (:pwd user-to-validate))
                            (get-valid-user user-to-validate users-list))
        valid-user (first valid-users)]
    (when (= 0 (count (:users @user-db))) (responsify 404 "No users available"))
    (if (is-valid-user? valid-user)
      (responsify 200 (protocol_user/jsonify (->Myuser (assoc valid-user :logged-in true))))
      (responsify 404 "No users found"))))


(defn signup-user
  "Adds a new user to the database"
  [user-to-validate]
  (let [users-db (:users @user-db)
        valid-user (get-valid-user user-to-validate users-db)]
    (if (is-valid-user? valid-user)
      (responsify 409 "User name already exists")
      (let [user (->Myuser (last
                            (:users
                             (swap!
                              user-db
                              assoc
                              :users
                              (conj users-db (assoc user-to-validate :logged-in true))))))
            json-user (protocol_user/jsonify user)]
        (responsify 201 json-user)))))

(defn logout-user
  [user-to-logout]
  (let
   [users-db (:users @user-db)
    valid-user (first (get-valid-user user-to-logout users-db))]
    (if (nil? valid-user)
      (responsify 404 "No such users found")
      (responsify 200 (let [other-users (filter #(not= (:name %) (:name valid-user)) users-db)
                            logged-out-valid-user (assoc valid-user :logged-in false)]
                        (swap!
                         user-db
                         assoc :users (conj other-users logged-out-valid-user))
                        (protocol_user/jsonify (->Myuser logged-out-valid-user)))))))