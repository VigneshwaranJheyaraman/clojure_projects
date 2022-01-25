(ns ui.views.app-page
  (:require
   [ui.views.searchbox :refer [search-box]]
   [ui.views.userlist :refer [user-list]]
   [re-frame.core :as r]
   [ui.db :refer [gen-data]]))



(defn update-user-data-on-scroll-end
  "
   updates the userslist returning a new function which will add the new users to the list
   "
  [user-list]
  (fn [event]
    (let
     [target (.-target event)]
      (when (>= (+ (.-offsetHeight target) (.-scrollTop target)) (.-scrollHeight target))
        (do
          (r/dispatch [:ui.events/update-users (into user-list (gen-data))])
          ;;(println ((assoc state :usersList (into usersList (gen-data))) :usersList))
          )))))

(defn on-value-change
  "
   updates the user's search input
  "
  [event]
  (let [value (-> event .-target .-value)]
    (r/dispatch [:ui.events/update-user-search value])))

(defn app
  []
  (let [searchInput @(r/subscribe [:ui.subscribers/search-input])
        usersList @(r/subscribe [:ui.subscribers/users-list])]
    [:<>
     [search-box {;;:placeholder "User search"
                  :value searchInput
                  :onchange on-value-change :label "User name"}]

     [user-list {:users usersList :on-scroll (update-user-data-on-scroll-end usersList) :filterInput searchInput}]]))