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

;; part 2


(defn overlapping? [[a1 a2] [b1 b2]]
  (or (<= b1 a1 b2)
      (<= b1 a2 b2)
      (<= a1 b1 a2)
      (<= a1 b2 a2)))

(defn merge-ranges [[a1 b1] [a2 b2]]
  [(min a1 a2) (max b1 b2)])

;; merge the range into the first overlapping range, otherwise cons it on the front
(defn merge-into
  ([aseq r]
   (merge-into aseq r []))
  ([tseq r hseq]
   (if (empty? tseq)
     (cons r hseq)
     (let [h (first tseq)]
       (if (overlapping? r h)
         (concat hseq (cons (merge-ranges r h) (rest tseq)))
         (recur (rest tseq) r (conj hseq h)))))))

(defn count-ranges [aseq]
  (reduce + (map (fn [[a b]] (inc (- b a))) aseq)))

(defn merge-all-ranges
  ([aseq]
   (merge-all-ranges [] aseq))
  ([hseq tseq]
   (if (empty? tseq)
     hseq
     (let [[r & rest] tseq
           merged (merge-into rest r)]
       (if (= (count tseq) (count merged)) ;; no merge
         (recur (conj hseq r) rest)
         (recur hseq merged))))))
