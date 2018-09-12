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
  (* 0.9 (get-height)))

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

(defn circle-viz []
  (let [height (get-height)
        width (get-width)]
    (-> js/d3
        (.select "#second")
        (.append "svg")
        (.attr "height" height)
        (.attr "width" width)
        (.attr "id" "circle-chart"))))

(defn rext-viz-implement []
  (let [test-data (clj->js @st/data)]
    (-> js/d3
        (.select "#first-chart")
        (.selectAll "rect")
        (.data test-data)
        .enter
        (.append "rect")
        (.attr "fill" "darkred")
        (.attr "height" (fn [d i]
                          (* 10 (string->int (aget d "x")))))
        (.attr "width" 70)
        (.attr "x" (fn [d i]
                     (* 80 i)))
        (.attr "y" (fn [d i]
                     (- 350 (* 10 (string->int (aget d "x")))))))))

(defn circle-viz-implement []
  (let [test-data (clj->js @st/data)
        fixed 10]
    (-> js/d3
        (.select "#circle-chart")
        (.selectAll "circle")
        (.data test-data)
        .enter
        (.append "circle")
        (.attr "cx" (fn [d i]
                      (* (+ i 1) (* (+ i 1) 15))))
        (.attr "cy" 100)
        (.attr "r" (fn [d i]
                     (string->int (aget d "x"))))
        (.attr "fill" "red"))))

;; -------------------------
;; Views

(defn home-page []
  (rect-viz)
  (rext-viz-implement)
  (circle-viz)
  (circle-viz-implement)
  [:div
   [:h2 "Charts D3"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
