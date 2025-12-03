(ns aoc-2025.day-3
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(defn find-highest-digit
  ([astr start end]
   (find-highest-digit -1 -1 astr start end))
  ([midx max astr start end]
   (if (= start end) [midx max]
       (let [d (ctoi (nth astr start))]
         (if (> d max)
           (recur start d astr (inc start) end)
           (recur midx max astr (inc start) end))))))

(defn joltage
  ([n astr]
   (joltage n 0 [] astr))
  ([n start acc astr]
   (if (zero? n)
     (parse-long (apply str (map second acc)))
     (let [a (find-highest-digit astr start (- (count astr) (dec n)))]
       (recur (dec n) (inc (first a)) (conj acc a) astr)))))
