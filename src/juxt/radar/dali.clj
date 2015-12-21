(ns juxt.radar.dali
  (:require [dali.io :as io]))

(def radar-cfg {:arcs [{:radius 100, :name "Adopt"}
                       {:radius 200, :name "Trial"}
                       {:radius 300, :name "Assess"}
                       {:radius 400, :name "Hold"}]})

(defn radar-width [radar]
  (* 2 (apply max (map :radius (-> radar :arcs)))))

(defn radar-height [radar]
  (radar-width radar))

(defn radar [data]
  (let [width (radar-width data)
        height (radar-height data)]
    [:page {:stroke {:paint :black :width 2} }
     [:line {:stroke [10 5]} [0 (/ height 2)] [width (/ height 2)]] ;; Horizonal line
     [:line {:stroke [10 5]} [(/ width 2) 0] [(/ width 2) height]] ;; Vertical line
     (for [{:keys [radius]} (:arcs data)]
       [:circle
        {:stroke :indigo :stroke-width 1 :fill :none}
        [(/ width 2) (/ width 2)] radius])]))

(defn foo []
  (io/render-svg (radar radar-cfg) "foo.svg"))

(foo)
