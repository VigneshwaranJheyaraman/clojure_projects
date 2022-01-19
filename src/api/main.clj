(ns api.main
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            ;;[ring.middleware.reload :refer [wrap-reload]]
            [api.middleware.content-response :refer [add-content-type]]))
(defn route-handler
  [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (str "<h1>" (request :query-params) "</h1>")})

(def app (->
          route-handler
          (add-content-type "text/html")
          (wrap-params)))

(defonce server-config {:port 8080
                        :join? false})

(defn- start-server
  ([]
   (run-jetty app server-config))
  ([port]
   (run-jetty app (assoc server-config :port port))))


(defn server
  ([]
   (let [s (start-server)]
     {:s s
      :stop (fn [] (.stop s))
      :restart (fn [] (when (nil? (.stop s)) (server)))}))
  ([port]
   (let [server (start-server port)] {:stop (fn [] (.stop server))
                                      :restart (fn [] (.stop server) (server port))})))