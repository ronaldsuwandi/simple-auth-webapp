(ns simple-auth-webapp.auth.jws-cookie-backend
  (:require [buddy.auth.backends.token :refer :all]
            [buddy.auth.protocols :as proto]))

(defn jws-cookie-backend
  [jws-backend]
  {:pre [(satisfies? proto/IAuthentication jws-backend)
         (satisfies? proto/IAuthorization jws-backend)]}

  (reify
    proto/IAuthentication
    (-parse [_ request]
      (proto/-parse jws-backend request))

    (-authenticate [_ request data]
      (proto/-authenticate jws-backend request data))

    proto/IAuthorization
    (-handle-unauthorized [_ request metadata]
      (proto/-handle-unauthorized jws-backend request metadata))))
