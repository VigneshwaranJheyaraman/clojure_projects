(ns ui.effects
  (:require
   [re-frame.core :as r]
   [reitit.frontend.easy :refer [push-state]]))

(r/reg-fx ::push-state (fn [new-route] (push-state new-route)))