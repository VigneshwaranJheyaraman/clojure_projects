(ns api.main
  (:require
   [reitit.ring :as ring]
   [reitit.swagger :as swagger]
   [ring.adapter.jetty :refer [run-jetty]]
   [api.routes :refer [user-routes]]
   [reitit.coercion.spec]
   [reitit.swagger-ui :as swagger-ui]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [muuntaja.core :as m]
   [reitit.dev.pretty :as pretty]
   [reitit.ring.middleware.exception :as exception]
   [api.middleware.exception-handler :refer [json-exception-handler]]
   [mount.core :as mount]))

(def api-handler
  (ring/ring-handler
   (ring/router
    (conj [["/swagger.json"
            {:name ::swagger.json
             :get {:no-doc true
                   :swagger {:info {:title "User API using clojure"
                                    :description "Simple api"}}
                   :handler (swagger/create-swagger-handler)}}]] user-routes)
    {:exception pretty/exception
     :data {:coercion reitit.coercion.spec/coercion
            :muuntaja m/instance

            :middleware [swagger/swagger-feature
                         parameters/parameters-middleware
                         muuntaja/format-negotiate-middleware
                         (exception/create-exception-middleware
                          {::exception/default (partial json-exception-handler "exception")})
                         muuntaja/format-response-middleware
                         muuntaja/format-request-middleware
                         coercion/coerce-response-middleware
                         coercion/coerce-request-middleware]}})
   (ring/routes
    (swagger-ui/create-swagger-ui-handler {:path "/swagger-ui"})
    (ring/create-default-handler
     {:not-found (constantly {:status 200 :body "Oops not found"})}))))

(def server (run-jetty api-handler {:port 8080 :join? false}))