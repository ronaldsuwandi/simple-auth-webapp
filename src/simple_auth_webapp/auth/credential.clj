(ns simple-auth-webapp.auth.credential
  (:require [simple-auth-webapp.db.dummy-db :refer [get-users]]
            [simple-auth-webapp.components.auth :as auth]
            [buddy.hashers :as hash]))

(defn valid-credential?
  [auth cred]
  (let [user (get (get-users (:database auth)) (:username cred))
        valid? (= (:password cred) (:password user))
        db (:database auth)]
    valid?))
