(ns ui.dev
  (:require
   [reagent.core :as r]
   [ui.views.searchbox :refer [search-box]]
   [reagent.dom :as rdom]))

(defonce value (r/atom "hi"))

(defn on-value-change
  [event]
  (reset! value (-> event .-target .-value)))

(defn initApp
  []
  (rdom/render [:div
                [:div "Hi I am a" [:h2 {:style {:color "red"}} "COMPONENT"]]
                [search-box {:value value :onchange on-value-change}]]
               (->
                js/document
                (.getElementById
                 "root"))))