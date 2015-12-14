(ns simple-auth-webapp.services.users.routes
  (:require [compojure.core :refer :all]
            [cemerick.friend :as friend]))

(defroutes users-routes
           (ANY "/users" [] "auth!"))
