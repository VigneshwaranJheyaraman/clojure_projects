(ns api.routes
  (:require
   [api.middleware.check-user-valid :refer [check-if-user-is-valid]]
   [ring.middleware.params :refer [wrap-params]]
   [clojure.spec.alpha :as s]
   [api.views :refer [login-user, signup-user, logout-user, return-user-profile]]))

(defn map-route-context
  ([context endpoint]
   (str "/" context "/" endpoint))
  ([endpoint]
   (str "/" endpoint)))

(s/def ::user_agent string?)
(s/def ::session_id string?)

(def user-routes (let [user-context "user"]
                   [(map-route-context user-context)
                    [(map-route-context "login")
                     {:name ::user.login
                      :summary "Logins a user"
                      :post {:parameters {:body {:name string? :pwd string?}}
                             :handler (fn [R] (login-user (-> R :parameters :body)))}}]
                    [(map-route-context ":userid/profile")
                     {:summary "returns a profile details based on the "
                      :name ::user.profile
                      :get {:middleware [check-if-user-is-valid
                                         wrap-params]
                            :parameters {:path {:userid string?}
                                         :query (s/keys :opt-un [::user_agent])}
                            :handler (fn [r] (return-user-profile (-> r :parameters :path)))}}]
                    [(map-route-context "signup")
                     {:name ::user.signup
                      :summary "Adds a new user to the DB"
                      :post {:parameters {:body {:name string?
                                                 :pwd string?}}
                             :handler #(signup-user (-> % :parameters :body))}}]
                    [(map-route-context "logout")
                     {:name ::user.logout
                      :summary "Logging out user"
                      :post {:parameters {:body {:name string?}}
                             :handler #(logout-user (-> % :parameters :body))}}]]))