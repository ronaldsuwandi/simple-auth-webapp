(ns simple-auth-webapp.components.auth
  (:require [com.stuartsierra.component :as component]))

(defrecord Auth [database]
  component/Lifecycle

  (start [auth]
    (prn "Starting authentication service")
    (assoc auth :secret "secretpassword"))

  (stop [auth]
    (prn "Stopping authentication service")
    (dissoc auth :secret)))

(defn new-auth
  []
  (map->Auth {}))
