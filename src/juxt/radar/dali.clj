(ns juxt.radar.dali
  (:require [dali.io :as io]
            [dali.layout.stack]
            [juxt.radar.radar :refer :all]))

(defn radar [data]
  (let [width (radar-width data)
        height (radar-height data)]
    [:page {:stroke {:paint :black :width 2} }
     [:line {:stroke [10 5]} [0 (/ height 2)] [width (/ height 2)]] ;; Horizonal line
     [:line {:stroke [10 5]} [(/ width 2) 0] [(/ width 2) height]] ;; Vertical line
     (for [{:keys [radius label]} (:arcs data)]
       [:dali/stack {}
        [:circle
         {:stroke :indigo :stroke-width 1 :fill :none}
         [(/ width 2) (/ width 2)] radius]]
       #_[:text
          {:font-family "Arial" :font-size 20} label])]))

(defn foo []
  (io/render-svg (radar radar-cfg) "foo.svg"))

(foo)
