(ns user
  (:require [restful-example.core :as core]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]))


(defonce server (atom nil))


(def dev-pedestal-config
  (-> {::http/routes (fn [] core/routes)
       ::http/type   :jetty
       ::http/join? false
       ::http/port   3001}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params))))

(defn start! []
  (when (nil? @server)
  (reset! server (-> dev-pedestal-config
      (assoc ::http/routes (fn [] core/routes))
      http/create-server
      http/start))))


(defn stop! []
  (when @server
    (http/stop @server)
    (reset! server nil)))

(start!)
(stop!) 

