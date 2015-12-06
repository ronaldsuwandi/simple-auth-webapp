(ns simple-auth-webapp.server.handler
  (:require [cemerick.friend :as friend]
            [immutant.web :refer :all]
            ;[simple-auth-webapp.server.component :as component]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])))

; a dummy in-memory user "database"
(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "admin_password")
                    :roles    #{::admin}}
            "jane" {:username "jane"
                    :password (creds/hash-bcrypt "user_password")
                    :roles    #{::user}}})

;(def ring-app                                               ; ... assemble routes however you like ...
;  )
;
;(def secured-app
;  (-> ring-app
;      (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn users)
;                            :workflows     [(workflows/interactive-form)]})
;      ; ...required Ring middlewares ...
;      ))
;
;

(defn handler
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello there"})

(defn -main
  [& args]
  (run handler))

(defn start-server [component]
  (prn "comp=" component)
  (run handler))