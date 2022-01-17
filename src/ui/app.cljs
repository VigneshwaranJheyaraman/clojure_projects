(ns ui.app
  (:require
   ;;[reagent.core :as r]
   [reagent.dom :as rdom]))


(defn initApp
  []
  (rdom/render [:div "Hi I am a" [:h2 "COMPONENT"]]
               (->
                js/document
                (.getElementById
                 "root"))))