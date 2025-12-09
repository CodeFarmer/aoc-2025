(ns aoc-2025.day-9-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-9 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(def sample-data
  "7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3")

(def sample-points (parse sample-data))

(deftest parse-test
  (is (= [7 1] (first sample-points)))
  (is (= 8 (count sample-points))))

(deftest area-test
  (is (= 24 (area [2 5] [ 9 7])))
  (is (= 35 (area [7 1] [11 7])))
  (is (=  6 (area [7 3] [ 2 3]))))

(deftest max-area-test
  (is (= 50 (max-area sample-points))))

(def input-points (parse (slurp "aoc-2025-inputs/day-9.txt")))

(deftest part-1-test
  (is (= 4746238001 (max-area input-points))))

;; part 2

