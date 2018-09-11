(ns reagent-d3js-experiment.core
    (:require
     [reagent.core :as r]
     [reagent-d3js-experiment.state :as st]
     [reagent-d3js-experiment.tadpole :as t]
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
;; Handlers

;; tadpole stuff
(defn svg-data []
  [:circle {:cx "50"
            :cy "50"
            :r "40"
            :stroke "black"
            :stroke-width "3"
            :fill "red"}])

(defn set-svg-tadpole []
  (let [height (get-height)
        width (get-width)]
    (-> js/d3
        (.select "#tadpole")
        (.append "svg")
        (.attr "height" height)
        (.attr "width" width)
        (.attr "id" "svg-tadpole"))))

(defn tadpole-handler [id value]
  [:input {:type "text"
           :value value
           :class "controller-input"
           :id id
           :on-change (fn [e]
                        (let [input-value (.. e -target -value)
                              converted-value (string->int input-value)]
                          (swap! st/controllers assoc id converted-value)))}])

;; -------------------------
;; Components

(defn form-component []
  (set-svg-tadpole)
  (-> js/d3
      (.select "#svg-tadpole")
      (.append "circle")
      (.attr "cx" 50)
      (.attr "cy" 50)
      (.attr "r" 40)
      (.attr "stroke" "black")
      (.attr "stroke-width" 3)
      (.attr "fill" "red"))
  [:form {:class :form}
   [:ul
    [:li
     [:label {:class :m} "m: "]
     [tadpole-handler :m (get @st/controllers :m)]]
    [:li
     [:label {:class :n} "n: "]
     [tadpole-handler :n (get @st/controllers :n)]]
    [:li
     [:label {:class :v} "v: "]
     [tadpole-handler :v (get @st/controllers :v)]]]])

;; -------------------------
;; Views

(defn home-page []
  [:div
   [:h2 "Welcome to Reagent"]
   [form-component]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
