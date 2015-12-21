(ns juxt.radar.dali
  (:require [dali.io :as io]))

(defn radar []
  [:page
   [:rect
    {:width 500 :height 500 :x 0 :y 0 :fill :none :stroke {:paint :black :width 2}}
    [:circle
     {:stroke :indigo :stroke-width 4 :fill :darkorange}
     [30 30] 20]]])

(defn foo []
  (io/render-svg (radar) "foo.svg"))

(foo)
