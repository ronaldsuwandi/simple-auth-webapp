(ns simple-auth-webapp.server.handler
  (:require [cemerick.friend :as friend]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [immutant.web :refer :all]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.session.cookie :as cookie]
            [simple-auth-webapp.services.about.routes :refer [about-routes]]
            [simple-auth-webapp.services.users.routes :refer [users-routes]]
            [cemerick.friend.credentials :as creds]
            [cemerick.friend.workflows :as workflows]
            [simple-auth-webapp.db.dummy-db :as db]))


(defn- not-found-route
  []
  (route/not-found "Not found hurrdurr"))

(defn create-handler
  []
  (let [all-routes (routes
                     about-routes
                     (friend/wrap-authorize users-routes #{:roles.admin})

                     ;(not-found-route)
                     )]
    (-> all-routes
        (friend/authenticate {:allow-anon?             true
                              :unathorized-handler     (constantly {:status 401})
                              :unauthenticated-handler (constantly {:status 401})
                              :workflows               [(workflows/interactive-form
                                                          :redirect-on-auth? false
                                                          :credential-fn #(creds/bcrypt-credential-fn
                                                                           db/users %))]})
        (wrap-params)
        (wrap-keyword-params)
        (wrap-session {:store (cookie/cookie-store {:key "0123456789abcdef"})}))))

(defn -main
  [& args]
  (run create-handler {:port 9001}))

(defn start-server [server]
  (run (create-handler) (:options server)))

(defn stop-server [server]
  (stop (:options server)))
