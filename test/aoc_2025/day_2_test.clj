(ns aoc-2025.day-2-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-2 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(deftest invalid-id-test
  (is (invalid-id? 123123))
  (is (not (invalid-id? 123124)))
  (is (not (invalid-id? 101))))

(def sample-data
  (str/split
   "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
   #","))

(deftest range-parsing-test
  (is (= [2 3 4 5] (parse-range "2-5"))))

(deftest sum-invalid-ids-test
  (is (= 1227775554 (sum-invalid-ids sample-data))))

(def input-data
  (str/split (slurp "aoc-2025-inputs/day-2.txt")
             #","))

(deftest part-1-test
  (is (= 44854383294 (sum-invalid-ids input-data))))

;; part 2

(deftest more-invalid-id-test
  (is (= 2 (count (filter invalid-id'? (parse-range "11-22"))))))

(deftest sum-more-invalid-ids-test
  (is (= 4174379265  (sum-invalid-ids' sample-data))))

(deftest part-1-test
  (is (= 55647141923 (sum-invalid-ids' input-data))))
