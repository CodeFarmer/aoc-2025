(ns aoc-2025.day-6-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-6 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))
(def sample-string
  "123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +  ")

(deftest parse-test
  (is (= '[[* [123 45 6]]
           [+ [328 64 98]]
           [* [51 387 215]]
           [+ [64 23 314]]]
         (parse sample-string))))

(deftest solve-test
  (is (= 33210 (solve '[* [123 45 6]])))
  (is (= 490 (solve '[+ [328 64 98]]))))

(deftest sum-solutions-test
  (is (= 4277556 (reduce + (map solve (parse sample-string))))))

(def input-string (slurp "aoc-2025-inputs/day-6.txt"))
(deftest part-1-test
  (is (= 4076006202939 (reduce + (map solve (parse input-string))))))
