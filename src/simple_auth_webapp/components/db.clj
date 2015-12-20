(ns simple-auth-webapp.components.db
  (:require [com.stuartsierra.component :as component]
            [simple-auth-webapp.db.dummy-db :as dummy-db]))

(defrecord Database [host port connection]
  component/Lifecycle

  (start [component]
    (prn "Starting database")
    (let [component (assoc component :connection "con")]
      (dummy-db/create-user! component {:username "root" :password "admin_password"})
      component))

  (stop [component]
    (prn "Stopping database")
    (dummy-db/delete-user! component "root")
    (assoc component :connection nil)))

(defn new-database [host port]
  (map->Database {:host host :port port}))
