(ns hobbit (:gen-class))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

;;Isso é um vetor de mapas com as características de um hobbit
;;Note que até agora só temos o lado esquerdo 

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

;;As duas funções acima vão deixar o hobbit simetrico.

;;Veja:
(symmetrize-body-parts asym-hobbit-body-parts)

;;let 
;;let associa nomes a valores. Pense como a música let it be e nunca esquecerá.

(let [x 3]
  x)

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])

(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)

(def x 0);;define x referenciado ao 0 em contexto global
(let [x 1] x);;define x referenciado ao 0 no contexto onde o let foi aplicado

(def x 0)
(let [x (inc x)] x)

;;pode ser usar també o rest params no let
(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])
;; Notice that the value of a let form is the last form in its body that is evaluated. let forms follow all the destructuring rules introduced in “Calling Functions” on page 48. In this case, [pongo & dalmatians] destructured dalmatian-list, binding the string "Pongo" to the name pongo and the list of the rest of the dalmatians to dalmatians. The vector [pongo dalmatians] is the last expression in let, so it’s the value of the let form.

;; let forms have two main uses. First, they provide clarity by allowing you to name things. Second, they allow you to evaluate an expression only once and reuse the result. This is especially important when you need to reuse the result of an expensive function call, like a network API call. It’s also important when the expression has side effects.

;; (let [[part & remaining] remaining-asym-parts]
;;   (recur remaining
;;          (into final-body-parts
;;                (set [part (matching-part part)]))))
;; This code tells Clojure, “Create a new scope. Within it, associate part with the first element of remaining-asym-parts. Associate remaining with the rest of the elements in remaining-asym-parts.”

(into [] (set [:a :a]))

;;________loop________
(loop [iteration 0];;define a iteração com o valor 0
  (println (str "Iteration " iteration));;printa a iteração e seu valor
  (if (> iteration 3);;condição para continuar a recursividade
    (println "Goodbye!");;print que ocorre quando a condição é satisfeita
    (recur (inc iteration))));;recursividade que acrescenta +1 no valor da iteração até a condição ser satisfeita

;;pode se fazer isso criando uma função 

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 50)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

(recursive-printer)

;; mesmo podendo fazer dessa forma, utilizar o loop >>>>>> rs

#"regular-expression"

;; ARRUMAR ESSES TEXTOS
;; In the function matching-part in Listing 3-1, clojure.string/replace uses the regular expression #"^left-" to match strings starting with "left-" in order to replace "left-" with "right-". The carat, ^, is how the regular expression signals that it will match the text "left-" only if it’s at the beginning of the string, which ensures that something like "cleft-chin" won’t match. You can test this with re-find, which checks whether a string matches the pattern described by a regular expression, returning the matched text or nil if there is no match:
(re-find #"^left-" "left-eye")

(re-find #"^left-" "cleft-chin")

(re-find #"^left-" "wongleblart")


(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(matching-part {:name "left-eye" :size 1})

(matching-part {:name "head" :size 3})

;;note que o nome "head" permaneceu o mesmo


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

 (defn symmetrize-body-parts
    "Expects a seq of maps that have a :name and :size"
    [asym-body-parts]
      (loop [remaining-asym-parts asym-body-parts
               final-body-parts []]
              (if (empty? remaining-asym-parts)
                  final-body-parts
                        (let [[part & remaining] remaining-asym-parts]
                                    (recur remaining
                                             (into final-body-parts
                                                   (set [part (matching-part part)])))))))


;; A função simetrizar partes do corpo (começando em ➊) emprega uma estratégia geral que é comum na programação funcional. Dada uma sequência (neste caso, um vetor de partes do corpo e seus tamanhos), a função divide continuamente a sequência em uma cabeça e uma cauda. Em seguida, ele processa a cabeça, adiciona-a a algum resultado e usa a recursão para continuar o processo com a cauda. Começamos o looping sobre as partes do corpo em ➋. A cauda da sequência será ligada às partes assimétricas restantes. Inicialmente, ele é vinculado à sequência completa passada para a função: asym-body-parts. Também criamos uma sequência de resultados, partes do corpo final; seu valor inicial é um vetor vazio. Se as partes restantes assimétricas estiver vazio em ➌, isso significa que processamos a sequência inteira e podemos retornar o resultado, partes do corpo final. Caso contrário, em ➍ dividimos a lista em uma cabeça, uma parte e uma cauda, ​​restantes. Em ➎, recorremos com o restante, uma lista que fica menor em um elemento em cada iteração do loop, e a expressão (em), que constrói nosso vetor de partes do corpo simetrizadas. Se você é novo neste tipo de programação, este código pode levar algum tempo para decifrar. Fique com isso! Depois de entender o que está acontecendo, você se sentirá com um milhão de dólares!


;;Reduce - processa cada elemento em sequencia e retorna um resultado

(reduce + [1 2 3 4])
;; é a mesma coisa que (+ (+ (+ 1 2) 3) 4)

(reduce + 15 [1 2 3 4])

(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts);;pegando um vetor de partes do corpo assimétricas, simetrizando-o
        body-part-size-sum (reduce + (map :size sym-parts));;somando os tamanhos das partes
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts;;um desses números é escolhido aleatoriamente, e então usamos o loop para retornar  a parte do corpo que corresponde ao número
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)