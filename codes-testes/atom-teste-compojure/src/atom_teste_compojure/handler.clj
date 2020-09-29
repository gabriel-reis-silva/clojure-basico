(ns atom-teste-compojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response content-type]]
            [org.httpkit.server :refer [run-server]]
            [cheshire.core :as json]
            [atom_teste_compojure.query :as query]))
            
(defroutes app-routes
  (GET "/" []  (response (query/mostrar)))
  (POST "/" [] (response (query/criar "Gabriel Reis" "gabriel@hotmail.com" "123")))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response)) 
    
   
