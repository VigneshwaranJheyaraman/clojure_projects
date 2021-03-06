(ns api.middleware.content-response)

(defn add-content-type
  [handler content-type]
  (fn
    [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type))))