(ns simple-auth-webapp.services.auth.routes
  (:require [compojure.core :refer :all]
            [simple-auth-webapp.auth.credential :refer :all]
            [simple-auth-webapp.auth.token :refer :all]
            [buddy.core.hash :as hash]
            [buddy.core.codecs :as codecs]))


(defn login
  [auth]
  (fn [req]
    (let [username (get-in req [:form-params "username"])
          password (get-in req [:form-params "password"])
          session (:session req)
          valid? (valid-credential? auth {:username username :password password})]
      (if valid?
        (let [updated-session (assoc session :identity (keyword username))
              token (generate-token)
              hashed-token (codecs/bytes->base64 (hash/md5 token))]
          (prn "token=" token)
          (prn "hashtoken= " hashed-token)

          (prn (assoc {:status 200} :session updated-session))
          (assoc {:status 200} :session updated-session))
        {:status 401}))))

(defn auth-routes
  [auth]
  (routes
    (POST "/login" [] (login auth))))
