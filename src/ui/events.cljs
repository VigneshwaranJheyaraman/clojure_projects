(ns ui.events
  (:require [re-frame.core :as r]
            [reitit.frontend.controllers :as rfc]
            [ui.db :refer [app-state]]
            [ui.effects :as effects]
            [clojure.string :as str]))

(r/reg-event-db
 ::initialize-db
 (fn [db [_ dev-app-state]]
   (-> db
       (assoc :current-route nil)
       (assoc :db (if dev-app-state  dev-app-state app-state)))))

(r/reg-event-fx
 ::push-state
 (fn [_ [_ route_name]] {::effects/push-state route_name}))

(r/reg-event-db
 ::navigate
 (fn
   [db [_ new_route]]
   (let [old_route (:current-route db)
         new-controller (rfc/apply-controllers (:controllers old_route) new_route)]
     (assoc db :current-route (assoc new_route :controllers new-controller)))))

(r/reg-event-db ::update-users
                (fn [db
                     [_ new-user-list]] (let [db-atom (:db db)]
                                          (swap! db-atom assoc :usersList new-user-list)
                                          db)))

(r/reg-event-db ::update-state
                (fn [db
                     [_ new-state]]
                  (let [db-atom (:db db)]
                    (reset! db-atom new-state))))

(r/reg-event-db ::update-user-search
                (fn [db [_ new-search]]
                  (let [db-atom (:db db)
                        old-string (:searchInput @db-atom)]
                    (when (not= (str/lower-case new-search) (str/lower-case old-string)) (swap! db-atom assoc :searchInput new-search))
                    db)))

(r/reg-event-db ::activate-user
                (fn [db [_ new-user]] (let [db-atom (:db db)]
                                        (swap! db-atom assoc :valid-user new-user)
                                        db)))