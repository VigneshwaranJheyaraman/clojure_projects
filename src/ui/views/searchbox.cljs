(ns ui.views.searchbox
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as mui]))


(def text-field (r/adapt-react-class mui/TextField))

(defn search-box
  [{:keys [value
           onchange
           placeholder
           label]}]
  [text-field {:value value
               :style {:display "flex"
                       :width "100%"}
               :onChange onchange
               :variant "outlined"
               :placeholder (when (not= empty? placeholder) placeholder :else nil)
               :label label}])