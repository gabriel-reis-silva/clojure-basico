(ns my-rest-app.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json])
  (:gen-class))

; (defn -main
;   "I don't do a whole lot ... yet."
;   [& args]
;   (println "Hello, World!"))



(json/parse-string
 (-> {:url "https://www.dnd5eapi.co/api/classes/warlock" :method :get}
     client/request (get :body)))

(let [my-pretty-printer (json/create-pretty-printer
                         (assoc json/default-pretty-print-options
                                :indent-arrays? true))]
  (json/generate-string (-> {:url "https://www.dnd5eapi.co/api/classes/warlock" :method :get}
                            client/request (get :body)) {:pretty my-pretty-printer}))


;; (:body (client/get "https://www.dnd5eapi.co/api/classes/warlock" {:as :clojure}) )


;; (= "longest" (reduce (fn [a b]
;;                        (if (< (count a) (count b) ) b a))
;;                      ["which" "is" "the" "longest" "word"]))
;;                      
;;                      


(defn add [param1 param2] (+ param1 param2))
(defn centuryFromYear [year] (if (= 0 (mod year 100))
                               (/ year 100)
                               (+ 1 (int (/ year 100)))))

(centuryFromYear 2000)

(defn checkPalindrome [inputString] (if (= (seq inputString) (into '() (seq inputString)))
                                      true
                                      false))

(defn checkPalindrome [inputString] (if (= (reverse inputString) (reverse (reverse inputString)))
                                      true
                                      false))

(checkPalindrome [101])




(defn adjacentElementsProduct [inputArray] (def multi (* (get inputArray 0) (get inputArray 1)))
  (def multi2 (* (get inputArray 2) (get inputArray 3))) (if (> multi multi2) true false))

(defn adjacentElementsProduct [inputArray] (* inputArray))


(defn adjacentElementsProduct [inputArray] (apply max (map (fn [v0 v1] (* v0 v1)) inputArray (next inputArray))))
(adjacentElementsProduct [3, 6, -2, -5, 7, 3])


(defn shapeArea [n] (+ (* n n) (* (- n 1) (- n 1))))

(shapeArea 3)

(defn makeArrayConsecutive2 [statues] (fn [a b] ((if (= (+ a 1) b) true false) statues)))

(defn makeArrayConsecutive2 [statues] (- (+ 1 (count
                                               (range (first (sort statues))
                                                      (last (sort statues))))) (count statues)))

(makeArrayConsecutive2 [6, 2, 3, 8])

(defn almostIncreasingSequence [sequence] (map (fn [a b] (if (< a b) true false)) sequence))

(almostIncreasingSequence [1 2 3 4])


(defn almostIncreasingSequence [sequence]
  (loop [i (dec (count sequence))]
    (and (>= i 0) (or (apply < (keep-indexed #(if (not= i %1) %2) sequence))
                      (recur (dec i)))))) 1


(let [sequence1 [1 3 2 1] ;; false 
      sequence2 [3, 6, 5, 8, 10, 20, 15] ;; false
      sequence3 [1 2 1 2] ;; false
      sequence4 [3, 5, 67, 98, 3] ;; true
      sequence5 [3 6 5 8 10 20 15] ;; false
      ]
  (letfn [(almostIncreasingSequence [s]
            (->> (partition 2 1 s);; pega de dois em dois e se faltar um ele repete o ultimo 
                 #_(map (fn [[a b]] (>= a b)))
                 #_(filter false?)
                 #_count
                 #_(>= 1)))]
    #_(prn (almostIncreasingSequence sequence1))
    #_(prn (almostIncreasingSequence sequence2))
    (prn (almostIncreasingSequence [1, 1, 1, 2, 3]))
    #_(prn (almostIncreasingSequence sequence4))
    #_(prn (almostIncreasingSequence sequence5))))


(defn almostIncreasingSequence [sequence] (->> (partition 2 1 sequence)
                                               (map (fn [[a b]] (<= a b)))
                                               (filter false?)
                                               count
                                               (>= 1)))


(defn almostIncreasingSequence [sequence]
  (loop [i (dec (count sequence))] ;;define o loop como i e 
    (and (>= i 0) (or (apply < (keep-indexed #(if (not= i %1) %2) sequence))
                      (recur (dec i))))))

(defn allLongestStrings [inputArray]
  (filter (fn [a] (= (count a) (apply max
                                      (map (fn [a] (count a))
                                           inputArray))))
          inputArray))


(defn commonCharacterCount [s1 s2] (fn [a] (sort (clojure.string/split s1 s2 #""))))

(commonCharacterCount "aa" "ab")


(fn [a] (sort (clojure.string/split "" #"")))

(clojure.string/split "abacaxi" #"")


(defn example []
  (def myatom (atom 1))
  (println @myatom))
(example)


(defn example2 []
  (def myatom (atom 1))
  (println @myatom)

  (reset! myatom 2)
  (println @myatom))
(example2)

(defn example3 []
  (def myatom (atom 1))
  (println @myatom)

  (compare-and-set! myatom 0 3)
  (println @myatom)

  (compare-and-set! myatom 1 3)
  (println @myatom))
(example3)

(defn test []
  (def meuatomo (atom 1))
  (prn @meuatomo)
  (reset! meuatomo 2)
  (prn @meuatomo)
  (swap! meuatomo inc)
  )
(test)