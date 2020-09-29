(ns atom_teste_compojure.query
  (:import java.util.UUID))

(def usuario (atom {}))
(def id (atom -1))

;; CREATE

(defn criar [nome email senha]
  (swap! usuario assoc (swap! id inc) {:nome nome :email email :senha senha}))



;;Printa usuários
;; (println "Usuário: "@usuario)
(defn mostrar []
  @usuario)
;;"DELETE ALL"
#_(reset! usuario {})

(defn filtro [user id]
  (= id (:id user)))

#_(get @usuario 0)
#_(->> usuario deref (filter  #(filtro % #uuid "7cb2f7e8-3c5b-4781-b652-76af9317c835")))



;;função de apagar o pelo id
(defn apagar-id [id]
  (swap! usuario dissoc @usuario id)
  )


;;pra apagar deve ser feito assim
#_(apagar-id #uuid "ecd2c9f4-0ebc-4b03-a34e-53ccd5c6c577")
#_(apagar-id 2)


;;teste para apagar
#_(let [id #uuid "9384316a-2b06-4c2f-9e99-778986fb1ae3"]
    (= ({:id #uuid "445dd1a9-584e-4ce5-a2b1-291e747011e4", :nome "Adrielli", :email "adri@hotmail.com", :senha "321"}
        {:id #uuid "34f87b37-2ec0-4188-a983-be4468691e36", :nome "Pedrita", :email "Pedrita@hotmail.com", :senha "456"})
       (apagar-id id)))

(defn mudar [id posicao valor]
  (reset! usuario (assoc-in @usuario [0(keyword posicao)] valor))
  )

#_(mudar 0 :nome "Gabriel Reis")


(comment (criar "Gabriel Reis" "gabriel@hotmail.com" "123")
(criar "Adrielli" "adri@hotmail.com" "321")
(criar "Pedrita" "Pedrita@hotmail.com" "456")

(mudar 0 :nome "Gabriel Reis")

(apagar-id 2)
(mostrar)
(reset! usuario {})
)