(ns ui.app
  (:require
   [ui.views.searchbox :refer [search-box]]
   ;;[reagent.core :as r]
   [ui.db :refer [app-state, gen-data]]
   [reagent.dom :as rdom]))

(defn update-user-data-on-scroll-end
  "
   returns a function Updates the user's list with new data on scroll end
   "
  [app-state]
  (fn [event]
    (let
     [target (.-target event)
      state (merge {} @app-state)
      {:keys [usersList]} state]
      (when (>= (+ (.-offsetHeight target) (.-scrollTop target)) (.-scrollHeight target))
        (do
          (reset! app-state (assoc state :usersList (into usersList (gen-data))))
          ;;(println ((assoc state :usersList (into usersList (gen-data))) :usersList))
          )))))

(defn on-value-change
  "
   updates the user's search input
  "
  [app-state]
  (fn
    [event]
    (let [value (-> event .-target .-value)
          state (merge {} @app-state)]
      (reset! app-state (assoc state :searchInput value))
    ;;updating the state
      )))


(def domroot (->
              js/document
              (.getElementById
               "root")))
(defn app
  [appState]
  [:div
   [search-box {;;:placeholder "User search"
                :value (@appState :searchInput)
                :onchange (on-value-change appState) :label "User name"}]])


(defn initApp
  []
  (rdom/render [app app-state]
               domroot))