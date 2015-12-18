(ns simple-auth-webapp.auth.jws-cookie-backend
  (:require [buddy.auth.backends.token :refer :all]
            [buddy.auth.protocols :as proto]))

(defn- parse-cookie
  [request cookie-name]
  (some-> (:cookies request)
          (get cookie-name)
          (:value)))

(defn jws-cookie-backend
  "Create a cookie-based wrapper of existing instance of jws
  (json web signature) based authentication backend.

  This backends simply uses cookie to check for the token rather
  than HTTP header.

  For authenticate and authorization it simply calls the
  underlying backend's original function."
  [jws-backend {:keys [cookie-name]
                :or {cookie-name "Token"}}]
  {:pre [(satisfies? proto/IAuthentication jws-backend)
         (satisfies? proto/IAuthorization jws-backend)]}
  (reify
    proto/IAuthentication
    (-parse [_ request]
      (parse-cookie request cookie-name))

    (-authenticate [_ request data]
      (proto/-authenticate jws-backend request data))

    proto/IAuthorization
    (-handle-unauthorized [_ request metadata]
      (proto/-handle-unauthorized jws-backend request metadata))))
