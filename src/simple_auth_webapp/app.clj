(ns simple-auth-webapp.app
  (:require [com.stuartsierra.component :as component]
            [simple-auth-webapp.components.db :as db]
            [simple-auth-webapp.components.server :as server]
            [simple-auth-webapp.components.auth :as auth]))

(defn app
  [config]
  (-> (component/system-map
        :db (db/new-database "localhost" 5420)
        :auth (auth/new-auth)
        :app (server/new-server 9001 {}))
      (component/system-using
        {:auth {:database :db}
         :app {:database :db
               :auth :auth}})))

(defn -main
  [& args]
  (component/start (app {})))
