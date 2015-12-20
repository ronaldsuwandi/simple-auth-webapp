(ns simple-auth-webapp.db.dummy-db
  (:require [buddy.hashers :as hash]))

(def ^:private users (atom {}))

(defn create-user!
  [db {:keys [username password] :as user}]
  (let [hashed-user (assoc user :password (hash/encrypt password {:alg :bcrypt+blake2b-512}))]
    (swap! users assoc username hashed-user)))

(defn delete-user!
  [db username]
  (swap! users dissoc username))

(defn get-user
  [db username]
  (get @users username))
