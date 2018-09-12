(ns reagent-d3js-experiment.core
    (:require
     [reagent.core :as r]
     [reagent-d3js-experiment.state :as st]
     [cljsjs.d3]))

;; -------------------------
;; Helpers

(defn string->int [value]
  (if (clojure.string/blank? value)
    0
    (js/parseInt value)))

(defn get-height []
  (get @st/dimensions :height))

(defn get-width []
  (* 0.8 (get-height)))

;; -------------------------
;; Components
(defn rect-viz []
  (let [height (get-height)
        width (get-width)]
    (-> js/d3
        (.select "#first")
        (.append "svg")
        (.attr "height" height)
        (.attr "width" width)
        (.attr "id" "first-chart"))))

(defn rext-viz-implement []
  (prn @st/data)
  (let [test-data (clj->js @st/data)]
    (-> js/d3
        (.select "#first-chart")
        (.selectAll "rect")
        (.data test-data)
        .enter
        (.append "rect")
        (.attr "height" (fn [d i]
                          (* 10 (string->int (aget d "x")))))
        (.attr "width" 70)
        (.attr "x" (fn [d i]
                     (* 80 i)))
        (.attr "y" (fn [d i]
                     120)))))

;; -------------------------
;; Views

(defn home-page []
  (rect-viz)
  (rext-viz-implement)
  [:div
   [:h2 "Charts D3"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
