(ns aoc-2025.day-4
  (:require [aoc-2025.core :refer :all]
            [clojure.string :as str]))


(defn has-fewer-neighbours [n tmap point]
  (let [neighbours (tmap-find-neighbours-diagonals tmap point)]
    (< (count (filter #(= \@ (get-tile tmap %)) neighbours))
       n)))

(defn accessible-rolls [tmap]
  (let [rolls (tmap-find-locations tmap \@)]
    (filter (partial has-fewer-neighbours 4 tmap) rolls)))

;; part 2

(defn remove-rolls [tmap points]
  (reduce (fn [acc p] (tmap-update acc p \.)) tmap points))

(defn remove-rolls-iteratively [tmap]
  (let [removable (accessible-rolls tmap)]
    (if (empty? removable)
      tmap
      (recur (remove-rolls tmap removable)))))
