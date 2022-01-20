(ns api.utils)

(defn responsify
  ([status response-body]
   {:status status
    :body (if (string? response-body) {:msg response-body} {:data response-body})})
  ([status response-body headers] (merge (responsify status response-body) {:headers headers})))


(defn is-valid-user?
  "Checks if the user is valid or not"
  [valid-users]
  (not= 0 (count valid-users)))

(defn get-valid-user
  "Returns a valid user"
  [user-to-validate users-db]
  (filter #(= (:name %) (:name user-to-validate)) users-db))