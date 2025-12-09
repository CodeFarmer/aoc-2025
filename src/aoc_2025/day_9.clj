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
