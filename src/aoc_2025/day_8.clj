(ns aoc-2025.day-8
  (:require [aoc-2025.core :refer :all]
            [clojure.math.combinatorics :as c]
            [clojure.math :as m]
            [clojure.set :as s]
            [clojure.string :as str]))

(defn parse [astr]
  ;; there has to be a nicer way to do this
  (map intify-seq
       (map #(str/split % #",")
            (str/split astr #"\n"))))

(defn distance [a b]
  (m/sqrt
   (reduce + 
           (map #(* % %)
                (map - a b)))))

;; return a seq of all pairs of points, ordered by their distance
;; shortest to longest
;; this can be made faster by keeping only the closest n pairs and insertion-sorting
;; I guess the other thing is... binary space partitions?
;; meh, fast enough for government work
(defn make-pairs [points]
  (sort-by #(apply distance %) (c/combinations points 2)))

(defn join-pair [circuits [a b]]
  (let [ac (or (some #(and (% a) %) circuits) #{a})
        bc (or (some #(and (% b) %) circuits) #{b})]
    (conj (disj circuits ac bc) (s/union ac bc))))

(defn make-circuits
  ([n pairs]
   (make-circuits #{} n pairs))
  ([circuits n pairs]
   (if (zero? n) circuits
       (recur (join-pair circuits (first pairs))
              (dec n)
              (rest pairs)))))

(defn bigness [circuits]
  (apply * (take 3 (map count (sort-by #(- (count %)) circuits)))))

;; part 2

;; return the first pair that merges the circuits into a single circuit
(defn one-big-circuit [points pairs]
  (loop [circuits (set (map (fn [point] #{point}) points))
         pairs pairs]
    (let [pair (first pairs)
          new-circuits (join-pair circuits pair)]
      (cond (empty? pairs) nil
            (= 1 (count new-circuits)) pair
            :default (recur new-circuits (rest pairs))))))
