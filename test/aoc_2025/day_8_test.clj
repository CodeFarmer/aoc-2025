(ns aoc-2025.day-8-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-8 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(def sample-data
  "162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689
")

(def sample-points (parse sample-data))

(deftest parsing-test
  (is (= 20 (count sample-points)))
  (is (= [162 817 812] (first sample-points))))

(def sample-pairs (make-pairs sample-points))

(deftest find-nearest-pair-test
  (is (= [[162 817 812] [425 690 689]]
         (first sample-pairs))))

(deftest circuit-making-test
  (is (= #{#{[162 817 812] [425 690 689] [431 825 988]}}
         (make-circuits 2 sample-pairs)))
  (is (= [5 4 2] (take 3 (map count (reverse (sort-by count (make-circuits 10 sample-pairs))))))))

(deftest bigness-test
  (is (= 40 (bigness (make-circuits 10 sample-pairs)))))

(def input-points (parse (slurp "aoc-2025-inputs/day-8.txt")))
;; this part is most of the runtime, consider doing something else
(def input-pairs (make-pairs input-points))

(deftest part-1-test
  (is (= 171503 (bigness (make-circuits 1000 input-pairs)))))

;; part 2

(deftest one-big-circuit-test
  (is (= [[216 146 977] [117 168 530]]
         (one-big-circuit sample-points sample-pairs))))

(deftest part-2-test
  (is (= 9069509600 (apply * (map first (one-big-circuit input-points input-pairs))))))
