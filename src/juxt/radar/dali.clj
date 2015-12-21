(ns juxt.radar.dali
  (:require [dali.io :as io]))

(defn radar []
  [:page
   [:rect
    {:width 100 :height 100 :x 0 :y 0 :fill :white}
    [:circle
     {:stroke :indigo :stroke-width 4 :fill :darkorange}
     [30 30] 20]]])

(defn foo []
  (io/render-svg (radar) "foo.svg"))

(foo)
