(ns ui.views.usercard
  (:require
   ["@material-ui/core" :as mui]))

(defn user-card
  [{:keys
    [user]}]
  [:> mui/Card
   {:style {:display "flex"
            :margin "0.35em auto"
            :width "50%"
            :justify-content "center"}}
   [:> mui/Box
    {:style {:display "flex"
             :flexDirection "column"
             :flex "1 0 auto"
             :justify-content "center"
             :align-items "center"}}
    [:> mui/CardContent
     [:> mui/Typography
      {:component "h5"
       :variant "h5"}
      [user :fname]]
     [:> mui/Typography
      {:component "div"
       :variant "subtitle1"
       :color "textSecondary"}
      [user :lname]]]]
   [:> mui/CardMedia
    {:component "img"
     :style {:width 151}
     :image (user :avatar)
     :alt (user :fname)}]])