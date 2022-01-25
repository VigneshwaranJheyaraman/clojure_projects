(ns ui.app
  (:require
   [re-frame.core :as r]
   [ui.db :refer [app-state]]
   [reagent.dom :as rdom]
   [ui.events]
   [ui.routes :refer [route-component]]))

(def domroot (->
              js/document
              (.getElementById
               "root")))

(defn initApp
  ([]
   (r/clear-subscription-cache!)
   (r/dispatch-sync [:ui.events/initialize-db nil])
   (rdom/render [route-component app-state]
                domroot))
  ([app-state]
   (r/clear-subscription-cache!)
   (r/dispatch-sync [:ui.events/initialize-db app-state])
   (rdom/render [:<>
                 [route-component]]
                domroot)))