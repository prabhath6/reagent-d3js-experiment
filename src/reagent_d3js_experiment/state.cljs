(ns reagent-d3js-experiment.state
  (:require [reagent.core :as r]))

;; -------------------------
;; State
(defonce dimensions (r/atom {:height 600}))

(defonce data (r/atom [{:x 1}
	                     {:x 2}
	                     {:x 3}]))
