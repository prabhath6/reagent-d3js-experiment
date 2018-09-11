(ns reagent-d3js-experiment.state
  (:require [reagent.core :as r]))

;; -------------------------
;; State

(defonce controllers (r/atom {:m 12 :n 100 :v 2}))

(defonce dimensions (r/atom {:height 600}))
