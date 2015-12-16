(ns simple-auth-webapp.components.server
  (:require [com.stuartsierra.component :as component]
            [simple-auth-webapp.server.handler :as handler]))

(defrecord Server [options database auth]
  component/Lifecycle

  (start [server]
    (prn "Starting server")
    (handler/start-server server)
    server)

  (stop [server]
    (prn "Stopping server")
    (handler/stop-server server)
    server))

(defn new-server
  [port options]
  (map->Server {:options (merge {:port port} options)}))
