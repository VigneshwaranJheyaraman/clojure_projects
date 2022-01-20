(ns api.middleware.exception-handler)

(defn json-exception-handler
  [message exception request]
  {:status 500
   :body {:type message
          :exception (str exception)
          :uri (request :uri)
          :cause (.getMessage exception)}
   :headers {"Content-Type" "application/json"}})