(ns reagent-d3js-experiment.core
    (:require
     [reagent.core :as r]
     [reagent-d3js-experiment.state :as st]
     [reagent-d3js-experiment.tadpole :as t]))

;; -------------------------
;; Helpers
(defn string->int [value]
  (if (clojure.string/blank? value)
    0
    (js/parseInt value)))

;; -------------------------
;; Handlers

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
