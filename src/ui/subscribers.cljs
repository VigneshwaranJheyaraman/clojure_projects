(ns ui.subscribers
  (:require [re-frame.core :as r]))

(r/reg-sub ::current-route
           (fn [db]
             (:current-route db)))

(r/reg-sub ::search-input
           (fn [db]
             (let [app-db (:db db)]
               (:searchInput @app-db))))

(r/reg-sub ::users-list
           (fn [db]
             (let [app-db (:db db)]
               (:usersList @app-db []))))

(r/reg-sub ::current-user
           (fn [db]
             (let [app-db (:db db)]
               (:valid-user @app-db))))