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
          valid? (valid-credential? auth {:username username :password password})]
      (if valid?
        (let [token (generate-token)
              hashed-token (codecs/bytes->base64 (hash/md5 token))]
          (prn "token=" token)
          (prn "hashtoken= " hashed-token)
          (prn {:status 200 :body {:token hashed-token}})
          ;{:status 200
          ; :body   {:token hashed-token}})
          {:status 200
           :body {:token hashed-token}})
        {:status 401}))))

(defn auth-routes
  [auth]
  (routes
    (POST "/login" [] (login auth))))
