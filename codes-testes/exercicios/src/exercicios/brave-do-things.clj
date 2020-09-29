(ns brave-do-things  (:gen-class))

;; o "if" funciona como no operador tenário, primeira condição é a verdadeira e edpois é a falsa

(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")


(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!")

(if false
  "By Odin's Elbow!")

;; o "do" faz eu poder embrulhar várias coiasas nos parenteses executar cada uma delas
(if true
  (do (println "Success!")
      (println "Pode ser qualquer fita no mesmo if")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))


;; o "when" executa tudo que eu mandar se a condição for verdadeira, ou atender o que pedi
;; caso nçao atenda minha condição, retonra nil
(when true
  (println "Success!")
  "ibra cadabra")

;; em clojure, quando não se tem um valor ele é representado por "nil"
;; verifique aqui a diferença de nil para zero
(nil? 0)

(nil? nil)

;;valores verdadeiros, nulos e falsos
;; quando se coloca um if e depois um valor, ele está comparando se aquele valor é verdadeiro ou falso ou nulo

(if "bears eat beets"
  "bears beets Battlestar Galactica")


(if nil
  "This won't be the result because nil is falsey"
  "nil is falsey")


;; A igualdade em clojure é a mesma para qualquer formato. Sempre se usa o operador "="

(= 1 1)

(= nil nil)

(= nil 0)

(= "Café" "Café")

(= 1 2)

(= "Arroz" "Feijão")

;; Outros operadores booleanos são o "or" e o "and"
;; "or" retorna o primeiro valor verdadeiro ou o último valor

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)

(or (= 0 1) (= "yes" "no"))

(or nil)

;; "and" retorna o primeiro valor falso, caso não exista valor falso, retorna o último verdadeiro

(and :free_wifi :hot_coffee)

(and :feelin_super_cool nil false)

;; Em clojure, é utilizado o "def" para definir um symbol, um valor e afins.

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

failed-protagonist-names

;;Esse código tenta alterar o valor dos símbolos dado uma condição. Porém não se alteram dados no clojure
;;O clojure trabalha com mutabilidade. Então não se utilizam codigos como este: 
;; (def severity :mild)
;; (def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
;; (if (= severity :mild)
;;   (def error-message (str error-message "MILDLY INCONVENIENCED!"))
;;   (def error-message (str error-message "DOOOOOOOMED!")))

;; Para a mesma tarefa, porém não "alterando" os valores fazemos:
;; Assim, definimos uma função "error-message"

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)

;;Interpolação no clojure é feita quando se utiliza o "str"

(def name "Chewbacca")
(str "\"Uggllglglglglglglglll\" - " name)

;; Mapas. Em clojure, mapas são utilizados para associar um valor a outro. {} isso é um mapa vazio

{:first-name "Charlie"
 :last-name "McFishwich"}

{"string-key" +} ;;Mapa que associa a string à uma função "+"

{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}};;Mapas podem ser aninhados

;;Podemos usar a função hash-map também

(hash-map :a 1 :b 2)

;;Pegar valores nos mapas com a função "get".

(get {:a 0 :b 1} :b)
(get {:a 0 :b {:c "ho hum"}} :b)

;;Caso não ache sua busca, retorna "nil". Ou retorna o valor que você pré-definir para tal situação

(get {:a 0 :b 1} :c)
(get {:a 0 :b 1} :c "unicorns?")

;;Com a função "get-in" você pode encontrar valores dentro de mapas aninhados

(get-in {:a 0 :b {:c "ho hum"}} [:b :c])

;;Você também pode procurar um valor dentro do mapa utilizando uma keyword como arg.

({:name "The Human Coffeepot" :id 1} :name)

(:a {:a 1 :b 2 :c 3}) ;; é igual a (get {:a 1 :b 2 :c 3} :a)
(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows");;coloca um valor padrão caso não ache a keyword

;;Vetores 

[3 2 1]

(get [3 2 1] 0);;também podemos utilizar o get
(get ["a" {:name "Pugsley Winterbottom" :age 20} "c"] 1)

;;Criar vetores também é possível através da função "vector"
(vector "creepy" "full" "moon")

;;Para adicionar um valor ao final do vetor utilizamos o "conj"

(conj [1 2 3] 4)

;;Listas 
'(1 2 3 4)
;;Apesar de ser parecido com o vetor, não se pode utilizar o "get" em uma lista
;;Para pegar um valor de uma lista utilizamos o "nth"

(nth '(:a :b :c) 0)
(nth '(:a :b :c) 2)

;;utilizar o nth para recuperar um valor de uma lista é mais lento que utilizar o 
;;get de um vetor. Isso por questão de performance.

;;Listas aceitam diferentes tipos 
(list 1 "two" {3 4})

;;Utilizando o "conj" em uma lista, acidionamos o valor no início da lista
(conj '(1 2 3) 4)

;; Sets: são coleções de valores únicos. Existem os hash sets e sorted sets.
;; Os hash sets:

#{"kurt vonnegut" 20 :icicle}
(hash-set 1 1 2 2 3 3 8 9 10)

;;Note que ele sempre retorna valores unicos, somente um de cada número no caso.
;;Caso tente adicionar um valor que já existe no hash set, ele simplesmente retorna o valor somente uma vez
(conj #{:a :b} :b)

;;Criar sets de vetores já existentes
(set [3 3 3 4 4])

;;Utilizando o "contains?" você pode verificar se um valor existe
(contains? #{:a :b} :a)

(contains? #{:a :b} 3)

(contains? #{nil} nil)

;;Pode usar get e uma keyword também

(get #{:a {:c "asd"} :b} :a)
(get #{:a nil} nil)
(get #{:a :b} "kurt vonnegut")

(:a #{:a "oi" :b})


;; chamando funções 

(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

;;Nesse caso, ele retorna o resutlado dentro do "or" e isso serve de função para os números
;;Então ele retorna "+" e utiliza isso para somar os números 1 2 3
((or + -) 1 2 3)

;;Outros casos e utilidades
((and (= 1 1) +) 1 2 3)
((first [+ 0]) 1 2 3)

;; Funções podem pegar outras funções como argumento ou retornar outras funções, elas são chamadas de
;;  higher-order functions

(inc 1.1)

(map inc [0 1 2 3]);;adiciona 1 em cada valor do vetor retornando uma lista

;; (+ (inc 199) (/ 100 (- 7 2)))
;; (+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
;; (+ 200 (/ 100 5)) ; evaluated (- 7 2)
;; (+ 200 20) ; evaluated (/ 100 5)
;; 220 ; final evaluation

;;Definindo funções
;; defn
;; Function name
;; A docstring describing the function (optional)
;; Parameters listed in brackets
;; Function body

;;Docstring é uma string que serve para ser como uma explicação de uma função
;;Geralmente ela mostra o que a função faz. Para ver basta usar (map fn-name)
(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic";; essa é uma docstring
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(too-enthusiastic "Zelda")


;; Funções podem receber nenhum parametro ou mais. 
;; Funções com um parametro são chamadas de 1-arity
;; Com zero: 0-arity

(defn no-params
  []
  "I take no parameters!")
(defn one-param
  [x]
  (str "I take one parameter: " x))
(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
       "together to spite you! " x y))

(clojure.set/difference (set [1 2 3 4 5 8 9])  (set [2 3 4 5 6 7 1 8]))



