(ns simple-auth-webapp.components.auth
  (:require [com.stuartsierra.component :as component]))

(defrecord Auth [database]
  component/Lifecycle

  (start [auth]
    (prn "Starting authentication service")
    auth)

  (stop [auth]
    (prn "Stopping authentication service")
    auth))

(defn new-auth
  []
  (map->Auth {}))
