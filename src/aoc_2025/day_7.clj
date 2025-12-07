(ns aoc-2025.day-7
  (:require [aoc-2025.core :refer :all]
            [clojure.set :as s]
            [clojure.string :as str]))


;; return a pair containing the number of times the beam was split, and the set of current beams
(defn extend-beam [beam-set line]
  (if (empty? beam-set) ;; assume the first lime always contains the start
    [0 #{(str/index-of line \S)}]
    (let [splits (filter #(= \^ (nth line %)) beam-set)
          unsplit (s/difference beam-set splits)
          new-beams (reduce #(into %1 [(dec %2) (inc %2)])
                     unsplit
                     splits)]
      [(count splits) new-beams])))

(defn count-splits [lines]
  (first 
   (reduce (fn [[an abeams] line]
             (let [[n beams] (extend-beam abeams line)]
               [(+ an n) beams]))
           [0 #{}]
           lines)))

