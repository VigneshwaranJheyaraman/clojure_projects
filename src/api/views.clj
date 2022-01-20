(ns api.views
  (:require
   [api.db :refer [user-db, Myuser]]
   [api.utils :refer [responsify, is-valid-user?, get-valid-user]]))


(defn login-user
  [user-to-validate]
  (let [users-list  (:users @user-db)
        valid-users (filter #(= (:pwd %) (:pwd user-to-validate))
                            (get-valid-user user-to-validate users-list))]
    (when (= 0 (count (:users @user-db))) (responsify 404 "No users available"))
    (if (is-valid-user? valid-users)
      (responsify 200 (first valid-users))
      (responsify 404 "No users found"))))


(defn signup-user
  "Adds a new user to the database"
  [user-to-validate]
  (let [users-db (:users @user-db)
        valid-user (get-valid-user user-to-validate users-db)]
    (if (is-valid-user? valid-user)
      (responsify 409 "User name already exists")
      (responsify 201 (json (Myuser.
                             (last
                              (:users
                               (swap!
                                user-db
                                assoc
                                :users
                                (conj users-db user-to-validate))))))))))