(ns simple-auth-webapp.app
  (:require [com.stuartsierra.component :as component]
            [simple-auth-webapp.components.db :as db]
            [simple-auth-webapp.components.server :as server]))

(defn app
  [config]
  (-> (component/system-map
        :db (db/new-database "localhost" 5420)
        :app (server/new-server 9001 {}))
      (component/system-using
        {:app {:database :db}})))
