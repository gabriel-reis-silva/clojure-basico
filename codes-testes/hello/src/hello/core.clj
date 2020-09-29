(ns hello.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(println "Olá")

(defn explain-defcon-level [exercise-term]
  (case exercise-term
    :fade-out          :you-and-what-army
    :double-take       :call-me-when-its-important
    :round-house       :o-rly
    :fast-pace         :thats-pretty-bad
    :cocked-pistol     :sirens
    :say-what?))


(let [x 5] (= :your-road (cond (= x 5) :road-not-taken 
                               (= x 0) :another-road-not-taken :else :your-road )))

(map (fn [x] (* 4 x)) [1 2 3])

(map (fn [x] (* x x)) [1 2 3 4 5])

(map nil? [:a :b nil :c :d])

(filter (fn [x] false) '(:anything :goes :here))
 (filter (fn [x] true) '(:anything :goes :here))

(filter (fn [x] (< x 31) ) [10 20 30 40 50 60 70 80])

(= [10 20 30] (map (fn [x](* x 10))
                   (filter (fn [x] (< x 4)) [1 2 3 4 5 6 7 8])))

(reduce (fn [a b] (* a b)) [1 2 3 4])
(reductions (fn [a b] (* a b)) [1 2 3 4])

(reduce (fn [a b] (* a b)) (* 100) [1 2 3 4])

(reduce (fn [a b] (if (< ) b a)) ["which" "is" "the" "longest" "word"])