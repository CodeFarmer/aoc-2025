(ns aoc-2025.day-2
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(comment
  (defn invalid-id? [i]
    (re-find (re-matcher #"^(\d){2}$" (str i)))))

(defn invalid-id? [i]
  (let [s (str i)
        len (count s)
        repeatable (subs s 0 (/ len 2))]
    (= s (str repeatable repeatable))))

(defn parse-range [astr]
  (let [[a b] (map parse-long
                   (rest (re-matches #"(\d+)-(\d+)" (str/trim astr))))]
    (range a (inc b))))

(defn sum-invalid-ids [aseq]
  (reduce + (filter invalid-id? (flatten (map parse-range aseq)))))

(defn invalid-id'? [i]
  (let [s (str i)
        len (count s)
        divisors (filter #(zero? (mod len %)) (range 2 (inc len)))]
    (some #(= s (apply str (repeat % (subs s 0 (/ len %))))) divisors)))

(defn sum-invalid-ids' [aseq]
  (reduce + (filter invalid-id'? (flatten (map parse-range aseq)))))
