(ns ui.views.searchbox)

(defn search-box
  [{value :value
    onchange :onchange}]
  [:input
   {:value @value
    :on-change onchange}])