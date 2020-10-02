(ns exercises (:gen-class))



;; 1 Use the str, vector, list, hash-map, and hash-set functions.
;; 2 Write a function that takes a number and adds 100 to it.
;; 3 Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
;; 

(defn mensagem [nome]
  (str nome ", olá!"))

(mensagem "Robson")

(defn vectest [letra]
  (conj [letra] "Essa é uma letra" ))

(vectest "a")

(defn listar [numero]
   (list (range 1 numero))
  )

(listar 20)

(hash-map :a "aviao" :b "banheira" :c "iate")
(hash-set 1 5 78 4)


(def acres100ta 
  "Recebe um valor e adiciona 100"
  (fn [valor](+ 100 valor))
  )

(acres100ta 100)


(defn dec-maker
 "Funciona subtraindo"
  [valor]
  #(- % valor))

(def dec9 (dec-maker 9))
(dec9 10)


;; Write a function, mapset, that works like map except the return value is a set

(def mapset
  (fn [] (set(map inc [1 1 2 3 3])))
  )

(mapset)