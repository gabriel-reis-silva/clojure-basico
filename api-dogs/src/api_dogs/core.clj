(ns api-dogs.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [io.pedestal.http.body-params :as body-params]))


(def dogs (atom [{:id "0" :breed "pug" :name "Noseless" :url-img "https://dog.ceo/api/breed/pug/images/random"}
                 {:id "1" :breed "husky" :name "Fumaça" :url-img "https://dog.ceo/api/breed/husky/images/random"}
                 {:id "2" :breed "akita" :name "rogerinho" :url-img "https://dog.ceo/api/breed/akita/images/random"}]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn respond-hello [request]
  {:status 200 :body "O servidor local está ativo"})

; pega qualquer imagem de qualquer raça
(defn pegaImagem [_req]
    (:message http/json-response
    (json/read-str (->> {:url "https://dog.ceo/api/breeds/image/random" :method :get}
       client/request
       :body) :key-fn keyword)))

#_(pegaImagem nil)

(defn get-all-breed-handler [_req]
  (-> "https://dog.ceo/api/breeds/list/all"
      client/get
      :body
      (json/read-str :key-fn keyword)
      http/json-response))

#_(get-all-breed-handler [])

(defn filter-users [params dogs]
  (filter (fn [user] (= params (select-keys user (keys params)))) dogs))

(defn get-users-handler [request]
  (-> request
      (:params {})
      (filter-users @dogs)
      http/json-response))


(defn get-by-breed-handler [_req]
  (let [raca (:breed (:query-params _req))]
    (-> (str "https://dog.ceo/api/breed/" raca "/images/random")
        client/get
        :body
        (json/read-str :key-fn keyword)
        :message
        http/json-response)))

(defn post-users-handler [request]
  (let [new-user (:json-params request)]
    (prn request)
    (swap! dogs conj new-user)
    (-> new-user
        http/json-response
        )))

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]
     ["/dogs" :get get-users-handler :route-name :racas]
     ["/dogs1" :get get-by-breed-handler :route-name :dogs]
     ["/dogs" :post post-users-handler :route-name :create-dog]}))


(def pedestal-config
  {::http/routes routes
   ::http/type   :jetty
   ::http/port   3000})

(defn start []
  (http/start (http/create-server pedestal-config)))

(defonce server (atom nil))

(defn start-dev []
  (reset! server
          (http/start (http/create-server
                       (assoc pedestal-config
                              ::http/join? false)))))


(defn stop-dev []
  (http/stop @server))


(defn restart []
  (stop-dev)
  (start-dev))

(restart)
#_(start-dev)
#_(stop-dev)

#_(def clients (atom '(:a :b :c :d)))
#_(swap! clients (fn [s] (remove #{:a} s)))