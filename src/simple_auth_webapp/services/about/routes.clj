(ns simple-auth-webapp.services.about.routes
  (:require [compojure.core :refer :all]))

(defroutes about-routes
           (ANY "/about" []
             "hey there"))
