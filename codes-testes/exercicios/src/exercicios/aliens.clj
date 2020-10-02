(ns aliens (:gen class))

(def asym-alien-body-parts [{:name "head" :size 3}
                             {:name "primeiro-eye" :size 1}
                             {:name "primeiro-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "primeiro-shoulder" :size 3}
                             {:name "primeiro-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "primeiro-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "primeiro-kidney" :size 1}
                             {:name "primeiro-hand" :size 2}
                             {:name "primeiro-knee" :size 2}
                             {:name "primeiro-thigh" :size 4}
                             {:name "primeiro-lower-leg" :size 3}
                             {:name "primeiro-achilles" :size 1}
                             {:name "primeiro-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^primeiro-" "segundo-")
   :size (:size part)})

(defn matching-part2
  [part]
  {:name (clojure.string/replace (:name part) #"^segundo-" "terceiro-")
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

;; (defn symmetrize-body-parts
;;   [asym-body-parts]
;;   (reduce (fn [final-body-parts part]
;;             (conj final-body-parts part (matching-part part)))
;;           #{}
;;           asym-body-parts))
(symmetrize-body-parts asym-alien-body-parts)

