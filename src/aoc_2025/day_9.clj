(ns aoc-2025.day-9
  (:require [aoc-2025.core :refer :all]
            [clojure.math :as m]
            [clojure.math.combinatorics :as c]
            [clojure.string :as str]))

(defn parse [astr]
  (map intify-seq (map #(str/split % #",") (str/split astr #"\n"))))

(defn area [a b]
  (reduce * (map #(inc (abs (- %1 %2)))  a b)))

(defn max-area [points]
  (reduce max (map #(apply area %) (c/combinations points 2))))

;; visualisation

(defn get-limits [points]
  (reduce (fn [[[minx miny] [maxx maxy]] [px py]]
            [[(min minx px) (min miny py)] [(max maxx px) (max maxy py)]])
          [[Integer/MAX_VALUE Integer/MAX_VALUE]
           [Integer/MIN_VALUE Integer/MIN_VALUE]]
          points))

  ;; part 2
  
