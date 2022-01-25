(ns ui.dev
  (:require
   [ui.app :as app]
   [ui.db :refer [dev-app-state]]))


(defn initApp
  []
  (app/initApp dev-app-state))