(ns juxt.radar.thing
  (:require [thi.ng.geom.core :as g]
            [thi.ng.geom.circle :as c]
            [thi.ng.geom.svg.core :as svg]
            [thi.ng.geom.svg.adapter :as adapt]
            [thi.ng.color.core :as col]
            [juxt.radar.radar :as radar]))

(defn labeled-dot
  "Position and label"
  [p label]
  (list (c/circle p 3) (svg/text (g/+ p 10 0) label)))

(defn foo [data]
  (let [width (radar/width data)
        height (radar/height data)]
    (svg/svg
     {:width 800 :height 800}

     ;; Radiants
     (svg/group
      {:fill "none" :stroke "indigo" :stroke-width 1}
      (for [{:keys [radius]} (:arcs data)]
        (c/circle (/ width 2) (/ width 2) radius)))

     ;; Labels
     (svg/group
      {:fill "#000"
       :font-family "Arial, sans-serif"
       :font-size 10}
      (for [{:keys [radius label]} (:arcs data)]
        (labeled-dot (g/point-at 400 (+ 400 radius)) "asidjaisujd"))))))

(defn make []
  (->> radar/radar-cfg
       (foo)
       (adapt/all-as-svg)
       (svg/serialize)
       (spit "foo.svg")))
