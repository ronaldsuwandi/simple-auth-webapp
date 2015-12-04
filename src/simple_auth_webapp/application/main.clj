(ns simple-auth-webapp.application.main
  (:require [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])))

; a dummy in-memory user "database"
(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "admin_password")
                    :roles    #{::admin}}
            "jane" {:username "jane"
                    :password (creds/hash-bcrypt "user_password")
                    :roles    #{::user}}})

(def ring-app                                               ; ... assemble routes however you like ...
  )

(def secured-app
  (-> ring-app
      (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn users)
                            :workflows     [(workflows/interactive-form)]})
      ; ...required Ring middlewares ...
      ))
