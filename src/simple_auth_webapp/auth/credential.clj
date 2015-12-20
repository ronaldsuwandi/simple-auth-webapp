(ns simple-auth-webapp.auth.credential
  (:require [simple-auth-webapp.db.dummy-db :refer [get-user]]
            [buddy.hashers :as hash]))

(defn valid-credential?
  [auth cred]
  (let [user (get-user (:database auth) (:username cred))
        valid? (hash/check (:password cred) (:password user))]
    valid?))
