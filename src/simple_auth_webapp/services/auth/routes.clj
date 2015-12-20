(ns simple-auth-webapp.services.auth.routes
  (:require [compojure.core :refer :all]
            [simple-auth-webapp.auth.credential :refer :all]
            [clj-time.core :as time]
            [buddy.sign.jws :as jws]))

(defn login
  [auth]
  (fn [req]
    (let [username (get-in req [:body :username])
          password (get-in req [:body :password])
          remember? (get-in req [:body :remember])
          valid? (valid-credential? auth {:username username :password password})]
      (if valid?
        (let [token-expiry (if remember? (* 60 24 60 60)
                                         (* 24 60 60))
              claims {:user (keyword (:user username))
                      :exp  (time/plus (time/now) (time/seconds token-expiry))}
              token (jws/sign claims (:secret auth))
              cookie-value {:value token :max-age token-expiry :http-only true}]
          {:status  200
           :body    {:token token}
           :cookies {"token" cookie-value}})
        {:status 401}))))

(defn auth-routes
  [auth]
  (routes
    (POST "/login" [] (login auth))))
