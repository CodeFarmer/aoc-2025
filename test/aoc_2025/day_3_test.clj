(ns aoc-2025.day-3-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-3 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))


(deftest joltage-test
  (is (= 98 (joltage 2 "987654321111111")))
  (is (= 89 (joltage 2 "811111111111119")))
  (is (= 78 (joltage 2 "234234234234278")))
  (is (= 92 (joltage 2 "818181911112111"))))

(def sample-data
  (str/split 
   "987654321111111
811111111111119
234234234234278
818181911112111"
   #"\n"))

(deftest sample-joltage-test
  (is (= 357 (reduce + (map (partial joltage 2) sample-data)))))

(def input-data
  (str/split (slurp "aoc-2025-inputs/day-3.txt") #"\n"))

(deftest part-1-test
  (is (= 17524 (reduce + (map (partial joltage 2) input-data)))))

;; part 2

(deftest bigger-joltage-test
  (is (= 987654321111 (joltage 12 "987654321111111")))
  (is (= 811111111119 (joltage 12 "811111111111119")))
  (is (= 434234234278 (joltage 12 "234234234234278")))
  (is (= 888911112111 (joltage 12 "818181911112111"))))

(deftest part-2-test
  (is (= 173848577117276 (reduce + (map (partial joltage 12) input-data)))))
