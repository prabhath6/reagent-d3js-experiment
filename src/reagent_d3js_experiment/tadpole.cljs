(ns reagent-d3js-experiment.tadpole
  (:require [reagent.core :as r]
            [reagent-d3js-experiment.state :as st]))

;; -------------------------
;; helpers

(defn create-velocity-data [v]
  (* (- (js/Math.random) 0.5) v))

(defn create-position-data [m dim]
  (take m (repeat (* (js/Math.random) dim))))

(defn create-tadpole-data [v m]
  {:vx (create-velocity-data v)
   :vy (create-velocity-data v)
   :px (create-position-data m (get @st/dimensions :width))
   :py (create-position-data m (get @st/dimensions :height))
   :count 0})

(defn create-tadpoles [n v m]
  (take n (repeat (create-tadpole-data v m))))
