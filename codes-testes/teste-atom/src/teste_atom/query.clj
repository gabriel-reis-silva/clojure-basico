(ns teste_atom.query
  (:import java.util.UUID))

(def usuario (atom []))

;; CREATE

(defn criar [nome email senha]
  (swap! usuario conj {:id (java.util.UUID/randomUUID) :nome nome :email email :senha senha}))

(criar "Gabriel Reis" "gabriel@hotmail.com" "123")
(criar "Adrielli" "adri@hotmail.com" "321")
(criar "Pedrita" "Pedrita@hotmail.com" "456")

;;Printa usuários
;; (println "Usuário: "@usuario)
@usuario
;;"DELETE ALL"
(reset! usuario [])

(defn filtro [user id]
  (= id (:id user)))

(->> usuario deref (filter  #(filtro % #uuid "9384316a-2b06-4c2f-9e99-778986fb1ae3")))
(->> usuario deref (filter  #(filtro % #uuid "445dd1a9-584e-4ce5-a2b1-291e747011e4")))

(class "445dd1a9-584e-4ce5-a2b1-291e747011e4")

;;função de apagar o pelo id
(defn apagar-id [id]
  (->> usuario
       deref
       (remove  #(filtro % id))
       (reset! usuario)))

;;pra apagar deve ser feito assim
(apagar-id #uuid "9384316a-2b06-4c2f-9e99-778986fb1ae3")



;;teste para apagar
#_(let [id #uuid "9384316a-2b06-4c2f-9e99-778986fb1ae3"]
  (= ({:id #uuid "445dd1a9-584e-4ce5-a2b1-291e747011e4", :nome "Adrielli", :email "adri@hotmail.com", :senha "321"}
      {:id #uuid "34f87b37-2ec0-4188-a983-be4468691e36", :nome "Pedrita", :email "Pedrita@hotmail.com", :senha "456"})
     (apagar-id id)))

