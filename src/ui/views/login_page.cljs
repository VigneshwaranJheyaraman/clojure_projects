(ns ui.views.login-page
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["@material-ui/core" :as mui]))

(defonce
  username (r/atom ""))
(defonce
  password (r/atom ""))

(defn login
  []
  (let [valid-user @(rf/subscribe [:ui.subscribers/current-user])
        updateUsername #(reset! username (-> %1 .-target .-value))
        updatePassword #(reset! password (-> %1 .-target .-value))]
    (when (not valid-user)
      [:> mui/Card {:style {:display "flex"
                            :flexDirection "column"
                            :justify-content "center"}}
       [:> mui/TextField
        {:value @username :label "Username" :onChange updateUsername}]
       [:> mui/TextField {:value @password
                          :label "Password"
                          :onChange updatePassword}]])))