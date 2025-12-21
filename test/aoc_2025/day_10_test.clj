(ns aoc-2025.day-10-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-10 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(def sample-data
  "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}")

(deftest parse-test
  (is (= [[\. \# \# \.]
          [[3] [1 3] [2] [2 3] [0 2] [0 1]]
          [3 5 4 7]]
         (first (parse sample-data)))))

(deftest minimum-presses-test
  (let [machines (parse sample-data)]
    (is (= 2 (apply minimum-presses (first machines))))
    (is (= 3 (apply minimum-presses (second machines))))
    ;; TODO how to do this with a transducer?
    (is (= 7 (reduce + (map #(apply minimum-presses %) machines))))))

;; part 1

(def input-machines (parse (slurp "aoc-2025-inputs/day-10.txt")))

(deftest part-1-test
    (is (= 441 (reduce + (map #(apply minimum-presses %) input-machines)))))
