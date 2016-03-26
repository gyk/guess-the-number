(ns guess-the-number.helper
  (:import AB))

;;; Helper functions
; "-idiot" stands for "idiomatic".

;; ==== choice ====
(defn choice-idiot [n k]
  "All unique ways to choose k numbers from [0, n)"
  (let [as (-> n range vec)
        xs (nth (iterate #(for [x %, a as] (conj x a)) [[]]) k)]
    (filter #(-> % set count (= k)) xs)))

(defn choice-tricky [n k]
  (let [^"[I" indices (into-array Integer/TYPE (range n))
        ^"[I" cycles (into-array Integer/TYPE (range n (- n k) -1))
        extract (fn [^"[I" indices]
                  (let [a (int-array k)]
                    (dotimes [i k]
                      (aset a i (aget indices i)))
                    a))
        rotate (fn [^"[I" indices from]
                  (let [fst (aget indices from)
                        lst (dec n)]
                    (doseq [i (range from lst)]
                      (aset indices i (aget indices (inc i))))
                    (aset indices lst fst)))]
    (loop [ret (transient [(extract indices)])
           i (dec k)]
      (if (= -1 i) (persistent! ret)
        (do
          (aset cycles i (dec (aget cycles i)))
          (if (= 0 (aget cycles i))
            (do
              (rotate indices i)
              (aset cycles i ^int (- n i))
              (recur ret (dec i)))
            (let [j (- n (aget cycles i))
                  iv (aget indices i)
                  jv (aget indices j)]
              (aset indices i jv)
              (aset indices j iv)
              (recur (conj! ret (extract indices)) (dec k)))))))))

;; ==== ab ====
(defn ab-idiot [^ints x ^ints y]
  (let [a (->> (map == x y) (filter identity) count)
        xy (into (set x) y)
        b (- 8 (count xy) a)]
    [a b]))

(defn ab-tricky [^ints x ^ints y]
  (let [a (atom 0)
        m (boolean-array 10)
        b' (atom 0)]
    (dotimes [i 4]
      (aset m (aget x i) true)
      (when (= (aget x i) (aget y i)) (swap! a inc)))
    (dotimes [i 4]
      (when (aget m (aget y i)) (swap! b' inc)))
      [@a (- @b' @a)]))

(def ^:private ^"[I"
  ret-val-ab (into-array Integer/TYPE [0 0]))
(defn ab-java [^"[I" x ^"[I" y]
  (do
    (AB/ab x y ret-val-ab)
    [(aget ret-val-ab 0) (aget ret-val-ab 1)]))

;; ==== Misc ====
(defn vectors->arrays [vs]
  (map #(into-array Integer/TYPE %) vs))

(defn arrays->vectors [as]
  (map vec as))
