(ns simple-auth-webapp.services.users.routes
  (:require [compojure.core :refer :all]
            [buddy.auth :refer [authenticated? throw-unauthorized]]))

(defroutes users-routes
           (ANY "/users" [] (fn [req]
                              (if-not (authenticated? req)
                                (throw-unauthorized)
                                "auth!"))))
