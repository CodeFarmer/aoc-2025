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
