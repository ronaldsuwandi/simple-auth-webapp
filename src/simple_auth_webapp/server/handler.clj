(ns simple-auth-webapp.server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [immutant.web :refer :all]
            [ring.middleware.session.cookie :as cookie]
            [ring.middleware.defaults :refer :all]
            [simple-auth-webapp.services.about.routes :refer [about-routes]]
            [simple-auth-webapp.services.users.routes :refer [users-routes]]
            [simple-auth-webapp.services.auth.routes :refer [auth-routes]]
            [simple-auth-webapp.db.dummy-db :as db]
            [compojure.handler :refer :all]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]))


(defn- not-found-route
  []
  (route/not-found "Not found hurrdurr"))

(def backend (session-backend))

(defn create-handler
  [{:keys [auth]}]
  (let [all-routes (routes
                     about-routes
                     users-routes
                     (auth-routes auth)
                     (not-found-route))]
    (-> all-routes
        (wrap-authentication backend)
        (wrap-defaults (assoc site-defaults :security {:anti-forgery false}
                                            :session {:store       (cookie/cookie-store {:key "0123456789abcdef"})
                                                      :cookie-name "session"}))
        )))

(defn -main
  [& args]
  (run create-handler {:port 9001}))

(defn start-server [server]
  (run (create-handler server) (:options server)))

(defn stop-server [server]
  (stop (:options server)))
