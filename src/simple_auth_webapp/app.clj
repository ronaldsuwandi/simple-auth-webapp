(ns simple-auth-webapp.app
  (:require [com.stuartsierra.component :as component]
            [simple-auth-webapp.db.component :as db]
            [simple-auth-webapp.server.component :as server]))

(defn app
  [config]
  (-> (component/system-map
        :db (db/new-database "localhost" 5420)
        :app (server/new-server 8080 nil))
      (component/system-using
        {:app {:database :db}})))
