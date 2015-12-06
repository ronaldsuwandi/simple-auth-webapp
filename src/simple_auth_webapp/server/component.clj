(ns simple-auth-webapp.server.component
  (:require [com.stuartsierra.component :as component]
            [simple-auth-webapp.server.handler :as handler]))

(defrecord Server [port database]
  component/Lifecycle

  (start [component]
    (prn "Starting server")
    (handler/start-server component))

  (stop [this]
    (prn "Stopping " this)))

(defn new-server
  [port options]
  (map->Server {:port port
                :options options}))