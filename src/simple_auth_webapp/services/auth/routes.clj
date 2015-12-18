(ns simple-auth-webapp.services.auth.routes
  (:require [compojure.core :refer :all]
            [simple-auth-webapp.auth.credential :refer :all]
            [simple-auth-webapp.auth.token :refer :all]
            [clj-time.core :as time]
            [buddy.sign.jws :as jws]))

(defn login
  [auth]
  (fn [req]
    (let [username (get-in req [:body :username])
          password (get-in req [:body :password])
          valid? (valid-credential? auth {:username username :password password})]
      (if valid?
        (let [claims {:user (keyword (:user username))
                      :exp (time/plus (time/now) (time/seconds 3600))}
              token (jws/sign claims (:secret auth))]
          {:status 200
           :body   {:token token}})
        {:status 401}))))

(defn auth-routes
  [auth]
  (routes
    (POST "/login" [] (login auth))))
