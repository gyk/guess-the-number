(ns guess-the-number.evaluator)

(defn gini-impurity-naive [xs]
  (let [cnts (map count xs)
        sqr #(* % %)
        sum (partial apply +)]
    (- 1.0 (/ (->> cnts (map sqr) sum) (->> cnts sum sqr)))))

(defn gini-impurity [xs]
  (apply + (map (comp - #(* % %) count) xs)))

(def ^:const ^:private log2inv (/ 1 (Math/log 2)))

(defn info-gain [xs]
  (let [cnts (map count xs)]
    (apply + (map #(* (- %) (Math/log %) log2inv) cnts))))
