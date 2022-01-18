(ns ui.views.userlist
  (:require [clojure.string :as str]
            [ui.views.usercard :refer [user-card]]))
(defn user-list [{:keys [state
                         on-scroll]}]
  [:div
   {:style {:overflow "auto"
            :width "100%"
            :height "100%"}
    :on-scroll on-scroll}
   (doall
    (map-indexed (fn [idx item]
                   [user-card {:user item
                               :key idx}])
                 (if (empty? (@state :searchInput))
                   (@state :usersList)
                   (filter (fn [user] (str/includes? (str/lower-case (user :fname)) (str/lower-case (@state :searchInput)))) (@state :usersList)))))])