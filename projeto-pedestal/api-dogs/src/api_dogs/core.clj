(ns api-dogs.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [clj-http.client :as client]
            [clojure.data.json :as json]))


(def dogs (atom [{:id "0" :breed "pug" :name "Noseless" :url-img "https://dog.ceo/api/breed/pug/images/random"}
                 {:id "1" :breed "Husky" :name "Fumaça" :url-img "https://dog.ceo/api/breed/husky/images/random"}
                 {:id "2" :breed "Akita" :name "rogerinho" :url-img "https://dog.ceo/api/breed/akita/images/random"}]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})



(defn pegaImagem []
  (->> {:url "https://dog.ceo/api/breeds/image/random" :method :get}
       client/request
       :body))

(json/read-str (pegaImagem) :key-fn keyword)

(defn pegaRaca [_req]
  (-> "https://dog.ceo/api/breeds/image/random"
      client/get
      :body
      (json/read-str :key-fn keyword)
      http/json-response))

#_(pegaRaca nil)

(defn get-breed-handler [_req]
  (-> "https://dog.ceo/api/breeds/list/all"
      client/get
      :body
      (json/read-str :key-fn keyword)
      http/json-response))

#_(get-breed-handler [])


(defn filter-users [params dogs]
  (filter (fn [user] (= params (select-keys user (keys params))))
          dogs))

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


(defn get-all-breed [_req]
   (http/json-response (:message (json/read-str 
                        (:body (client/get 
                                (str "https://dog.ceo/api/breed/"x"/images/random"))) 
                        :key-fn keyword))))

(str "https://dog.ceo/api/breed/"x"/images/random")
;; ??? COMO PEGAR POR RAÇA??

#_(get-all-breed (str "african"))

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]
     ["/dogs" :get get-users-handler :route-name :racas]
     ["/dogs1" :get get-by-breed-handler :route-name :dogs]}))


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

#_(restart)
#_(start-dev)
#_(stop-dev)

#_(def clients (atom '(:a :b :c :d)))
#_(swap! clients (fn [s] (remove #{:a} s)))