(ns reagent-d3js-experiment.state
  (:require [reagent.core :as r]))

;; -------------------------
;; State
(defonce dimensions (r/atom {:height 600}))

(defonce data (r/atom [{:x 10}
	                     {:x 20}
	                     {:x 30}
                       {:x 50}
                       {:x 60}]))
