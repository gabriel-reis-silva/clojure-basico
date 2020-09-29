(ns restful-example.core-test
  (:require [clojure.test :refer [testing deftest is]]
            [matcher-combinators.core :refer [match?]]
            [restful-example.core :as core]
            [io.pedestal.http :as http]
            [io.pedestal.test :as http-test]
            [cheshire.core :as json]))


(defn make-request! [verb path & args]
  (let [service-fn (::http/service-fn (core/create-server))
        response (apply http-test/response-for service-fn verb path args)]
    (update response :body json/decode true)))



(deftest users-listing-and-creation
  (testing "Listando usuários"
    (reset! core/users [])
    (is (match? {:body [] :status 200}
                (make-request! :get "/users"))))

  (testing "Criando usuário"
    (is (match? {:body {:id "4" :name "Novo usuário" :email "emailnovo@arroba.com"} :status 201}
                (make-request! :post "/users"
                               :headers {"Content-Type" "application/json"}
                               :body (json/encode {:id "4" :name "Novo usuário" :email "emailnovo@arroba.com"})))))
  (testing "Listando usuários depois de adicionar um"
    (is (match? {:body [{:id "4" :name "Novo usuário" :email "emailnovo@arroba.com"}] :status 200}
                (make-request! :get "/users")))))


(users-listing-and-creation)

(let [service-fn
      (->>   ["/teapot" :get (constantly {:status  418
                                          :body    "             ;,'
     _o_    ;:;'
 ,-.'---`.__ ;
((j`=====',-'
 `-\\     /
    `-=-'     "
                                          :headers {"Content-Type" "text/html"}}) :route-name ::tea]
             hash-set
             (hash-map ::http/routes)
             http/default-interceptors
             http/create-servlet
             ::http/service-fn)]
  (println ["Teapot\n" (:body (http-test/response-for service-fn :get "/teapot"))]))