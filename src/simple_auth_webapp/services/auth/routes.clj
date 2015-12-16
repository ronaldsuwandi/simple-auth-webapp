(ns simple-auth-webapp.services.auth.routes
  (:require [compojure.core :refer :all]
            [simple-auth-webapp.auth.credential :refer :all]))

(defn login
  [auth]
  (fn [req]
    (let [username (get-in req [:form-params "username"])
          password (get-in req [:form-params "password"])
          session (:session req)
          valid? (valid-credential? auth {:username username :password password})]
      (if valid?
        (let [updated-session (assoc session :identity (keyword username))]
          (prn (assoc {:status 200} :session updated-session))
          (assoc {:status 200} :session updated-session))
        {:status 401}))))

(defn auth-routes
  [auth]
  (routes
    (POST "/login" [] (login auth))))
