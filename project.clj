(defproject simple-auth-webapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot simple-auth-webapp.app
  :profiles {
             :dev {:source-paths ["dev"]}
             }
  :source-paths ["src"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.namespace "0.2.11"]

                 ; logging
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]

                 ; sql
                 [korma "0.4.2"]

                 ;db engine
                 [org.clojure/java.jdbc "0.4.2"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]

                 ; component
                 [com.stuartsierra/component "0.3.1"]

                 ; webapp
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 [buddy/buddy-auth "0.8.2"]
                 [compojure "1.4.0"]

                 ; cryptography
                 [buddy/buddy-core "0.8.1"]
                 [buddy/buddy-hashers "0.9.1"]

                 ; http server
                 [org.immutant/web "2.1.1"]])
