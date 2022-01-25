(ns ui.db
  (:require
   [reagent.core :as r]
   ["@faker-js/faker" :as faker]))

(defn gen-data
  []
  (let
   [name (.-name faker)
    avatar (.-image faker)]
    (map (fn [_] {:fname (.firstName name) :gender (.gender name) :lname (.lastName name) :avatar (.avatar avatar)}) (range 100))))

(defonce app-state (r/atom {:searchInput ""
                            :usersList []
                            :valid-user nil}))

(defonce dev-app-state (r/atom (merge {} @app-state)))