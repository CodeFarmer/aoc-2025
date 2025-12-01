(ns aoc-2025.day-1
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(defn instruction-delta [astr]
  (let [[d & numstr] astr
        n (Integer/parseInt (apply str numstr))]
    (* (case d
         \L -1
         \R  1)
       n)))

(defn rotate-dial-iteratively
  ([i aseq]
   (rotate-dial-iteratively i aseq [i]))
  ([i aseq acc]
   (if (empty? aseq)
     acc
     (let [n (mod (+ (first aseq) i) 100)]
       (recur n (rest aseq) (conj acc n))))))

;; part 2

(defn count-zero-crossings [start delta]
  (let [new-pos (+ start delta)
        q (quot (+ start delta) 100)]
    (if (<= new-pos 0)
      (+ (if (zero? start) 0 1) (abs q))
      q)))

(defn sum-zero-crossings
  ([i aseq]
   (sum-zero-crossings 0 i aseq))
  ([acc i aseq]
   (if (empty? aseq) acc
       (let [n (mod (+ (first aseq) i) 100)]
         (recur (+ acc (count-zero-crossings i (first aseq))) n (rest aseq))))))
