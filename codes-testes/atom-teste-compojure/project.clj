(defproject atom-teste-compojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [http-kit "2.1.16"]
                 [cheshire "5.10.0"]
                 [ring/ring-json "0.5.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler atom-teste-compojure.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
