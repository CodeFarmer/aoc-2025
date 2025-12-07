(ns aoc-2025.day-7-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-7 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(def sample-data
  (str/split
   ".......S.......
...............
.......^.......
...............
......^.^......
...............
.....^.^.^.....
...............
....^.^...^....
...............
...^.^...^.^...
...............
..^...^.....^..
...............
.^.^.^.^.^...^.
..............."
   #"\n"))

(deftest beam-test
  (is (= [0 #{7}]   (extend-beam #{}      (first sample-data))))
  (is (= [0 #{7}]   (extend-beam #{7}     (second sample-data))))
  (is (= [1 #{6 8}] (extend-beam #{7}     (nth sample-data 2))))
  (is (= [0 #{6 8}] (extend-beam #{6 8}   (nth sample-data 3))))
  (is (= [2 #{5 7 9}] (extend-beam #{6 8} (nth sample-data 4))))) 

(deftest count-splits-test
  (is (= 21 (count-splits sample-data))))

(def input-data
  (str/split (slurp "aoc-2025-inputs/day-7.txt") #"\n"))

(deftest part-1-test
  (is (= 1533 (count-splits input-data))))

;; part 2

(deftest timeline-counting-test
  (is (= 40 (count-timelines sample-data))))

(deftest part-2-test
  (is (= 10733529153890 (count-timelines input-data))))

