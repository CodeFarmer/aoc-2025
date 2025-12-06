(ns aoc-2025.day-6
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(defn parse
  ([astr]
   (parse [] (map #(str/split (str/trim %) #"\s+") (str/split astr #"\n"))))
  ([acc [row & rows]]
   (if (#{"*" "+"} (first row))
     (map #(vector (symbol %1) %2) row acc)
     (recur (if (empty? acc)
              (map #(vector (parse-long %)) row)
              (map #(conj %2 (parse-long %1)) row acc))
            rows))))

(defn solve [[operator operands]]
  (apply (resolve operator) operands))
