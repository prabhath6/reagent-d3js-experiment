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

(defn ellipse-viz []
  (let [height (get-height)
        width (get-width)]
    (-> js/d3
        (.select "#thrid")
        (.append "svg")
        (.attr "height" height)
        (.attr "width" width)
        (.attr "id" "ellipse-chart"))))

(defn line-viz []
  (let [height (get-height)
        width (get-width)]
    (-> js/d3
        (.select "#four")
        (.append "svg")
        (.attr "height" height)
        (.attr "width" width)
        (.attr "id" "line-chart"))))

(defn path-viz []
  (let [height (get-height)
        width (get-width)]
    (-> js/d3
        (.select "#five")
        (.append "svg")
        (.attr "height" height)
        (.attr "width" width)
        (.attr "id" "path-chart"))))

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
  (let [test-data (clj->js @st/data)]
    (-> js/d3
        (.select "#circle-chart")
        (.selectAll "circle.groupa")
        (.data test-data)
        .enter
        (.append "circle")
        (.attr "class" "groupa")
        (.attr "cx" (fn [d i]
                      (* (+ i 1) (* (+ i 1) 15))))
        (.attr "cy" 100)
        (.attr "r" (fn [d i]
                     (string->int (aget d "x"))))
        (.attr "fill" "red"))
    (-> js/d3
        (.select "#circle-chart")
        (.selectAll "circle.groupb")
        (.data test-data)
        .enter
        (.append "circle")
        (.attr "class" "groupb")
        (.attr "cx" (fn [d i]
                      (* (+ i 1) (* (+ i 1) 15))))
        (.attr "cy" 250)
        (.attr "r" (fn [d i]
                     (string->int (aget d "x"))))
        (.attr "fill" "green"))))

(defn ellipse-viz-implement []
  (let [test-data (clj->js @st/data)]
    (-> js/d3
        (.select "#ellipse-chart")
        (.selectAll "ellipse.groupa")
        (.data test-data)
        .enter
        (.append "ellipse")
        (.attr "class" "groupa")
        (.attr "cx" (fn [d i]
                      (* (+ i 1) (* (+ i 1) 15))))
        (.attr "cy" 100)
        (.attr "rx" (fn [d i]
                     (string->int (aget d "x"))))
        (.attr "ry" 20)
        (.attr "fill" "yellow"))))

(defn line-viz-implement []
  (let [test-data (clj->js @st/data)]
    (-> js/d3
        (.select "#line-chart")
        (.selectAll "line.groupa")
        (.data test-data)
        .enter
        (.append "line")
        (.attr "stroke" "orange")
        (.attr "stroke-width" "4")
        (.attr "class" "groupa")
        (.attr "x1" 0)
        (.attr "y1" (fn [d i]
                      (+ 40 (* i 20))))
        (.attr "x2" (fn [d]
                      (* (string->int (aget d "x")) 20)))
        (.attr "y2" (fn [d i]
                      (+ 40 (* i 20))))
        (.attr "fill" "yellow"))))

(defn path-viz-implement []
  (let [test-data (clj->js @st/line-data)
        line-function (-> js/d3
                          .line
                          (.x (fn [d i]
                                (* (string->int (aget d "x")) 8)))
                          (.y (fn [d i]
                                (* (string->int (aget d "y")) 10))))]
    (-> js/d3
        (.select "#path-chart")
        (.append "path")
        (.attr "d" (line-function test-data))
        (.attr "stroke" "blue")
        (.attr "stroke-width" 4)
        (.attr "fill" "none"))))

;; -------------------------
;; Views

(defn home-page []
  (rect-viz)
  (rext-viz-implement)
  (circle-viz)
  (circle-viz-implement)
  (ellipse-viz)
  (ellipse-viz-implement)
  (line-viz)
  (line-viz-implement)
  (path-viz)
  (path-viz-implement)
  [:div
   [:h2 "Charts D3"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
