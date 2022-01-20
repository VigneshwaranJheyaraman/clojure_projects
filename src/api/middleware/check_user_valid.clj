(ns api.middleware.check-user-valid)

(defn check-if-user-is-valid
  "This function will check before every request and check if the url query params has the session id"
  [handler]
  (fn
    [request]
    (let
     [session_id (get (-> request :query-params) "session_id")]
      (if
       (nil? session_id)
        {:status 200 :body "Not a valid user"}
        (handler request)))))