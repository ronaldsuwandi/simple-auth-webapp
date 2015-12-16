(ns simple-auth-webapp.db.dummy-db
  )

(def ^:private users {"root" {:username "root"
                              :password "admin_password"}
                      "jane" {:username "jane"
                              :password "user_password"}})

(defn get-users
  [db]
  (prn "get users - db" db)
  users)
