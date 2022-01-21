(ns api.protocols.user)

(defprotocol IUser
  (to-string [this] "toString method")
  (loggedIn? [this] "Is user logged in")
  (jsonify [this] "json convertor"))
