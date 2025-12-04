(ns aoc-2025.day-4-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-4 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(def sample-data
  (str/split
   "..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@."
   #"\n"))

(def sample-marked
  (str/split
   "..xx.xx@x.
x@@.@.@.@@
@@@@@.x.@@
@.@@@@..@.
x@.@@@@.@x
.@@@@@@@.@
.@.@.@.@@@
x.@@@.@@@@
.@@@@@@@@.
x.x.@@@.x."
   #"\n"))

(deftest accessible-rolls-test
  (let [rolls (accessible-rolls sample-data)]
    ;; this first assertion works, but it should really be sets
    (is (= (tmap-find-locations sample-marked \x) rolls))
    (is (= 13 (count rolls)))))

;; part 1

(def input-data
  (lines-as-vector "aoc-2025-inputs/day-4.txt"))

(deftest part-1-test
  (is (= 1356 (count (accessible-rolls input-data)))))

;; part 2

(deftest roll-removal-test
  (is (= (str/split
          ".......@..
.@@.@.@.@@
@@@@@...@@
@.@@@@..@.
.@.@@@@.@.
.@@@@@@@.@
.@.@.@.@@@
..@@@.@@@@
.@@@@@@@@.
....@@@..."
          #"\n")
         (remove-rolls sample-data (accessible-rolls sample-data)))))

(deftest repeated-roll-removal-test
  (is (= (str/split
          "..........
..........
..........
....@@....
...@@@@...
...@@@@@..
...@.@.@@.
...@@.@@@.
...@@@@@..
....@@@..."
          #"\n")
         (remove-rolls-iteratively sample-data))))

(deftest part-2-test
  (let [roll-count (count (tmap-find-locations input-data \@))
        iterated (remove-rolls-iteratively input-data)
        final-count (count (tmap-find-locations iterated \@))]
    (is (= 8713 (- roll-count final-count)))))
