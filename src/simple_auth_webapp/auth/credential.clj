(ns simple-auth-webapp.auth.credential
  (:require [simple-auth-webapp.db.dummy-db :refer [get-users]]))

(defn valid-credential?
  [auth cred]
  (let [user (get (get-users (:database auth)) (:username cred))
        valid? (= (:password cred) (:password user))]
    valid?))
