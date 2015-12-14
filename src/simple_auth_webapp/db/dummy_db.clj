(ns simple-auth-webapp.db.dummy-db
  (:require [cemerick.friend.credentials :as creds]))

(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "admin_password")
                    :roles    #{:roles.admin}}
            "jane" {:username "jane"
                    :password (creds/hash-bcrypt "user_password")
                    :roles    #{:roles.user}}})
