(ns ^:figwheel-no-load reagent-d3js-experiment.dev
  (:require
    [reagent-d3js-experiment.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
