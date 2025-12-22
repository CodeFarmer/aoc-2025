(ns aoc-2025.day-10
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))

(defn -parse-lights [astr]
  (into []
        (second (re-find #"\[(.+)\]" astr))))

(defn -parse-buttons [astr]
  (->> astr
       (re-seq #"\(([0-9,]+)\)")
       (map second)
       (map #(str/split % #","))
       (map intify-seq)))

(defn -parse-joltage [astr]
  (->> astr
       (re-find #"\{(.+)\}")
       (second)
       (#(str/split % #","))
       (intify-seq)))

(defn parse [astr]
  (map (fn [line]
         [(-parse-lights line)
          (-parse-buttons line)
          (-parse-joltage line)])
       (str/split astr #"\n")))

;; where lights and button are bitmasks
(defn lights-to-bits [lights]
  (reduce + (map-indexed (fn [i x] (if (= x \#) (bit-shift-left 1 i) 0)) lights)))

(defn button-to-bits [button]
  (reduce + (map #(bit-shift-left 1 %) button)))

(defn toggle [lights button]
  (bit-xor lights button))

;; this stlil takes a minute to run on my laptop
(defn minimum-presses
  ([target buttons _]
   (let [x (minimum-presses (conj clojure.lang.PersistentQueue/EMPTY
                                  [0 0])
                            #{}
                            (lights-to-bits target)
                            (map button-to-bits buttons))]
     (comment (println target ":" x))
     x))
  
  ([q seen-states target buttons]
   (if (empty? q)
     nil
     (let [[depth state] (peek q)]
       (if (= target state) depth
           (recur (if (seen-states state) (pop q)
                      (into (pop q)
                            (map (fn [b] [(inc depth) (toggle state b)])
                                 buttons)))
                  (conj seen-states state)
                  target
                  buttons))))))
