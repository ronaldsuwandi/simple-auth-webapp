(ns simple-auth-webapp.server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [immutant.web :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [simple-auth-webapp.services.about.routes :refer [about-routes]]
            [simple-auth-webapp.services.users.routes :refer [users-routes]]
            [simple-auth-webapp.services.auth.routes :refer [auth-routes]]
            [simple-auth-webapp.db.dummy-db :as db]
            [compojure.handler :refer :all]
            [buddy.auth.backends.token :refer [jws-backend]]
            [simple-auth-webapp.auth.jws-cookie-backend :refer [jws-cookie-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [buddy.auth.accessrules :refer [restrict]]
            [buddy.auth :refer [authenticated?]]))


(defn- not-found-route
  []
  (route/not-found "Not found"))

(defn authenticated-routes
  []
  (restrict
    (routes
      users-routes)
    {:handler authenticated?}))

(defn create-handler
  [{:keys [auth]}]
  (let [all-routes (routes
                     about-routes
                     (auth-routes auth)
                     (authenticated-routes)
                     (not-found-route))
        header-backend (jws-backend {:token-name "token"
                                     :secret     (:secret auth)})
        cookie-backend (jws-cookie-backend header-backend {:cookie-name "token"})]
    (-> all-routes
        (wrap-authentication header-backend cookie-backend)
        (wrap-authorization cookie-backend)
        (wrap-defaults (assoc site-defaults :security {:anti-forgery false}
                                            :session false))
        (wrap-json-response)
        (wrap-json-body {:keywords? true :bigdecimals? true}))))

(defn start-server [server]
  (run (create-handler server) (:options server)))

(defn stop-server [server]
  (stop (:options server)))
