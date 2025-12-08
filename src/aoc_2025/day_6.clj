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

;; part 2

(defn parse-columns
  ([astr]
   (let [lines (str/split astr #"\n")
         operators-line (last lines)
         numbers-lines (take-while #(not (= operators-line %)) lines)]
     ;; fairly sure this is criminally bad clojure style
     (map #(vector (symbol (str/trim %1)) %2)
          (str/split operators-line #"\s+")
          (parse-columns 0 [] (repeat (count numbers-lines) "") numbers-lines))))
  
  ([idx cols current-col lines]
   (cond (= idx (count (first lines))) (conj cols current-col)
         (every? #(= \space (nth % idx)) lines) (recur (inc idx)
                                                       (conj cols current-col)
                                                       (repeat (count current-col) "")
                                                       lines)
         :default (recur (inc idx)
                         cols (map #(str %1 (nth %2 idx)) current-col lines)
                         lines))))

(defn cephalopod-mangle [aseq]
  (loop [strings []
         i (dec (count (first aseq)))]
    (if (= -1 i)
      (map parse-long strings)
      (recur (conj strings (str/trim (apply str (map #(.charAt % i) aseq))))
             (dec i)))))

(defn cephalopod-solve [[operator operands]]
    (apply (resolve operator) (cephalopod-mangle operands)))
