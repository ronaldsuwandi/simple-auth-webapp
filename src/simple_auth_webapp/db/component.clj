(ns simple-auth-webapp.db.component
  (:require [com.stuartsierra.component :as component]))

(defrecord Database [host port connection]
  component/Lifecycle

  (start [component]
    (prn "Starting database")
    (assoc component :connection "con"))

  (stop [component]
    (prn "Stopping database")
    (assoc component :connection nil)))

(defn new-database [host port]
  (map->Database {:host host :port port}))

