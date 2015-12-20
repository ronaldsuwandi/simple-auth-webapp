(ns simple-auth-webapp.services.users.routes
  (:require [compojure.core :refer :all]
            [buddy.auth.accessrules :refer [restrict]]
            [buddy.auth :refer [authenticated? throw-unauthorized]]))

(defn users
  [req]
  "auth endpoint!")

(defroutes users-routes
           (ANY "/users" [] users))
