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

;; part 2

(def -count-timelines
  (memoize
   (fn [i lines]
     (if (empty? lines) 1
       (let [line (first lines)]
         (if (= \^ (nth line i))
           (+ (-count-timelines (dec i) (rest lines))
              (-count-timelines (inc i) (rest lines)))
           (-count-timelines i (rest lines))))))))

(defn count-timelines [lines]
  (-count-timelines (str/index-of (first lines) \S) (rest lines)))
