(ns aoc-2025.day-5-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-5 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(def sample-input
  "3-5
10-14
16-20
12-18

1
5
8
11
17
32")

(deftest parsing-test
  (is (= [[[ 3  5]
           [10 14]
           [16 20]
           [12 18]]
          [1 5 8 11 17 32]]
         (parse sample-input))))

;; part 1

(deftest fresh-test
  (let [[ranges items] (parse sample-input)
        fresh'? (partial fresh? ranges)]
    (is (not (fresh'? 1)))
    (is (fresh'? 5))
    (is (not (fresh'? 8)))
    (is (fresh'? 11))
    (is (fresh'? 17))
    (is (not (fresh'? 32)))

    (is (= 3 (count (filter fresh'? items))))))

(def input-data (parse (slurp "aoc-2025-inputs/day-5.txt")))

(deftest part-1-test
  (let [[ranges items] input-data
        fresh'? (partial fresh? ranges)]
    (is (= 640 (count (filter fresh'? items))))))

;; part 2

(deftest overlapping-test
  (is (not (overlapping? [3 5] [10 14])))
  (is (overlapping? [10 14] [12 18]))
  (is (overlapping? [16 20] [12 18]))
  (is (overlapping? [16 17] [12 18]))
  (is (overlapping? [12 18] [16 17])))

(deftest merge-ranges-test
  (is (= [10 18] (merge-ranges [10 14] [12 18])))
  (is (= [12 20] (merge-ranges [16 20] [12 18])))
  (is (= [12 18] (merge-ranges [16 17] [12 18]))))

(deftest merge-into-test
  (is (= [[ 3  5]
          [10 14]
          [16 20]
          [12 18]]
         (merge-into [[10 14]
                      [16 20]
                      [12 18]]
                     [3 5])))
  (is (= [[3 5] [16 20] [10 18]]
         (merge-into [[3 5]
                      [16 20]
                      [12 18]]
                     [10 14])))
  (is (= [[3 5] [7 9]]
         (merge-into [[3 5] [7 9]] [3 5]))))

(deftest counting-ranges-test
  (is (= 14 (count-ranges [[3 5] [10 20]]))))

(deftest merging-all-ranges-test
  (is (= [[ 3  5]
          [10 20]]
         (merge-all-ranges [[ 3  5]
                            [10 14]
                            [16 20]
                            [12 18]])))
  ;; this is a bug
  (is (= [[3 5] [7 9]] (merge-all-ranges [[3 5] [3 5] [7 9]]))))

(deftest part-2-test
  (is (= 365804144481581 (count-ranges (merge-all-ranges (first input-data))))))
