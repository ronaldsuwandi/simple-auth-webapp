(defproject simple-auth-webapp "0.1.0-SNAPSHOT"
  :description "An attempt to use Stuart Sierra's component framework in a ring-based web server using compjure for
  routing and buddy for authentication "
  :url "https://github.com/ronaldsuwandi/simple-auth-webapp"
  :license {:name "MIT License"
            :url  "https://opensource.org/licenses/MIT"}
  :main ^:skip-aot simple-auth-webapp.app
  :source-paths ["src"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.namespace "0.2.11"]

                 ; logging
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]

                 ; component
                 [com.stuartsierra/component "0.3.1"]

                 ; webapp
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 [buddy/buddy-auth "0.8.2"]
                 [compojure "1.4.0"]

                 ; cryptography
                 [buddy/buddy-hashers "0.9.1"]

                 ; http server
                 [org.immutant/web "2.1.1"]

                 ; time
                 [clj-time "0.11.0"]])
