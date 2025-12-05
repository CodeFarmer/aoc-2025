(ns aoc-2025.day-5
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(defn parse [astr]
  (let [[first-part second-part] (str/split astr #"\n\n")]
    [(map #(map parse-long (str/split % #"-")) (str/split first-part #"\n"))
     (map parse-long (str/split second-part #"\n"))]))

(defn fresh? [ranges i]
  (if (empty? ranges) false
      (let [[a b] (first ranges)]
        (if (<= a i b) true
            (recur (rest ranges) i)))))

