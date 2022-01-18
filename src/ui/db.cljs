(ns ui.db
  (:require
   [reagent.core :as r]
   ["@faker-js/faker" :as faker]))

(defonce app-state (r/atom {:searchInput ""}))

(defn gen-data
  []
  (let
   [name (.-name faker)
    avatar (.-image faker)]
    (map (fn [_] {:fname (.firstName name) :gender (.gender name) :lname (.lastName name) :avatar (.avatar avatar)}) (range 100))))

(defonce dev-app-state (r/atom {:searchInput ""
                                :usersList (gen-data)}))