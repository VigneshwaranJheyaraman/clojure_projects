(ns ui.dev
  (:require
   ;;[reagent.core :as r]
   [ui.views.userlist :refer [user-list]]
   [reagent.dom :as rdom]
   [ui.views.searchbox :refer [search-box]]
   [ui.app :refer [app
                   domroot
                   update-user-data-on-scroll-end]]
   [ui.db :refer [dev-app-state]]))


(defn initApp
  []
  (rdom/render [:<>
                [app dev-app-state]
                [user-list {:state dev-app-state :on-scroll (update-user-data-on-scroll-end dev-app-state)}]]
               domroot))