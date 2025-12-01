(ns aoc-2025.day-1-test
  (:require [clojure.test :refer :all]
            [aoc-2025.day-1 :refer :all]
            [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(deftest instruction-delta-test
  (is (= -68 (instruction-delta "L68")))
  (is (= -30 (instruction-delta "L30")))
  (is (=  48 (instruction-delta "R48"))))

(def sample-data
  (map instruction-delta
       (str/split
        "L68
L30
R48
L5
R60
L55
L1
L99
R14
L82"
        #"\n")))

(deftest dial-rotation-test
  (let [dial-positions (rotate-dial-iteratively 50 sample-data)]
    (is (= [50 82 52 0 95 55 0 99 0 14 32]
           dial-positions))
    (is (= 3 (count
              (filter #(= 0 %) dial-positions))))))

(def input-data
  (map instruction-delta
       (str/split
        (slurp "aoc-2025-inputs/day-1.txt")
        #"\n")))

(deftest part-1-test
  (let [dial-positions (rotate-dial-iteratively 50 input-data)]
    (is (= 1141 (count (filter #(= 0 %) dial-positions))))))

;; part 2

(deftest count-zero-crossings-test
  (is (= 10 (count-zero-crossings 50 1000)))
  (is (= 10 (count-zero-crossings 50 -1000)))
  (is (= 1 (count-zero-crossings 50 -50)))
  (is (= 1 (count-zero-crossings 50 50))))

(deftest sum-zero-crossings-test
  (is (= 6 (sum-zero-crossings 50 sample-data))))

(deftest part-2-test
  (is (= 6634 (sum-zero-crossings 50 input-data))))
