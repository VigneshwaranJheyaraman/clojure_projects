(ns ui.routes
  (:require [ui.views.app-page :refer [app]]
            [ui.db :refer [gen-data]]
            [re-frame.core :as r]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :refer [start!]]
            [ui.views.login-page :refer [login]]
            [ui.subscribers :as sub]
            [reitit.coercion.spec :as coercion_spec]))

(def app-routes ["/"
                 [""
                  {:name ::main
                   :view app
                   :controllers [{:start (fn [& _] (r/dispatch [:ui.events/update-users (gen-data)]))}]}]
                 ["login" {:name ::login
                           :view login
                           :controllers [{:start (fn [& param] (js/console.log param))}]}]])

(defn on-navigation
  [new-route _]
  (when new-route (r/dispatch [:ui.events/navigate new-route])))

(defn route-component
  []
  (let [current-route  @(r/subscribe [::sub/current-route])
        router  (rf/router
                 app-routes {:data {:coercion coercion_spec/coercion}})
        current-view (-> current-route :data :view)]
    (start! router
            on-navigation
            {:use-fragment false})
    (if current-view
      [current-view]
      nil)))