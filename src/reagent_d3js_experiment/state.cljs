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

(defonce line-data (r/atom [{:x 1 :y 5}
                            {:x 20 :y 20}
                            {:x 40 :y 10}
                            {:x 60 :y 40}
                            {:x 80 :y 5}
                            {:x 100 :y 60}]))
