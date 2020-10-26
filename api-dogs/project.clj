(defproject api-dogs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service  "0.5.7"]
                 [io.pedestal/pedestal.route    "0.5.7"]
                 [io.pedestal/pedestal.jetty    "0.5.7"]
                 [clj-http "0.6.0"]
                 [org.clojure/data.json "1.0.0"]]

  :main ^:skip-aot api-dogs.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :dev {:dependencies []
        :source-paths ["dev"]})

