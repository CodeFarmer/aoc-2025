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
(defn make-pairs [points]
  (sort-by #(apply distance %) (c/combinations points 2)))

(defn make-circuits
  ([n pairs]
   (make-circuits #{} n pairs))
  ([circuits n pairs]
   (if (zero? n) circuits
       (let [[a b] (first pairs)
             ; this (some (and ...)) thing is apparently idiomatic
             ac (or (some #(and (% a) %) circuits) #{a})
             bc (or (some #(and (% b) %) circuits) #{b})]
         (recur (conj (disj circuits ac bc) (s/union ac bc))
                (dec n)
                (rest pairs))))))

(defn bigness [circuits]
  (apply * (take 3 (map count (sort-by #(- (count %)) circuits)))))
