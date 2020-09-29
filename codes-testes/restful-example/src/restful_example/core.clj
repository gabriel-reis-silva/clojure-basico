(ns restful-example.core
  (:gen-class)

  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]))

(defn respond-hello [request]
  {:status 200 :body "Hello, Gabriel Reis"})

(def users (atom [{:id "0":name "Gabriel Reis" :email "Gabriel@alguma.com"}
                  {:id "1":name "Augusto Reis" :email "AR@alguma.com"}
                  {:id "2":name "Juscelino" :email "Terra@alguma.com"}
                  {:id "3":name "Dona" :email "show70@alguma.com"}]))

(defn filter-users [params users]
  (filter (fn [user] (= params (select-keys user (keys params))))
          users))

(defn delete-users-handler [request]
  (let [valor (:params request)]
    (prn "Dado com o" valor "deletado")
    (reset! users (remove (fn [user] (= (:id user) (:id valor))) @users)))
  (http/json-response @users))


(filter-users users {})

(filter (fn [user] (= (:name user) "Juscelino")) @users)

(defn get-users-handler [request]
  (-> request
      (:params {})
      (filter-users @users)
      http/json-response))

(defn post-users-handler [request]
  (let [new-user (:json-params request)]
    (swap! users conj new-user)
    (-> new-user
        http/json-response
        (assoc :status 201))))

(defn put-users-handlers [request]
  (let [new-value (:json-params request)
        id(:id (:json-params request))]
    (swap! users assoc-in [pos] new-value)))

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]
     ["/users" :get get-users-handler :route-name :users]
     ["/users" :post post-users-handler :route-name :create-user]
     ["/users" :delete delete-users-handler :route-name :delete-user]
     ["/users" :put put-users-handler :route-name :list-item-update :constraints]}))

(def pedestal-config
  (-> {::http/routes routes
       ::http/type   :jetty
       ::http/join? false
       ::http/port   3001}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params))))

(def server
  (-> pedestal-config
      (assoc ::http/routes (fn [] routes))
      http/create-server
      #_http/start))


(defn create-server []
  (http/create-server pedestal-config))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
