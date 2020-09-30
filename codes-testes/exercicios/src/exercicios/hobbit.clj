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

(let [[part & remaining] remaining-asym-parts]
  (recur remaining
         (into final-body-parts
               (set [part (matching-part part)]))))
;; This code tells Clojure, “Create a new scope. Within it, associate part with the first element of remaining-asym-parts. Associate remaining with the rest of the elements in remaining-asym-parts.”

(into [] (set [:a :a]))
