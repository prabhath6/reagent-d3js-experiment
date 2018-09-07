(ns reagent-d3js-experiment.prod
  (:require
    [reagent-d3js-experiment.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
