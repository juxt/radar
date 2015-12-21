(ns juxt.radar.thing
  (:require [thi.ng.geom.core :as g]
            [thi.ng.geom.circle :as c]
            [thi.ng.geom.svg.core :as svg]
            [thi.ng.geom.svg.adapter :as adapt]
            [thi.ng.color.core :as col]
            [juxt.radar.radar :as radar]))

(defn foo [data]
  (let [width (radar/width data)
        height (radar/height data)]
    (svg/svg
     {:width 800 :height 800}
     (svg/group
      {:fill "none" :stroke "indigo" :stroke-width 1}
      (for [{:keys [radius]} (:arcs data)]
        (c/circle (/ width 2) (/ width 2) radius))))))

(defn make []
  (->> radar/radar-cfg
       (foo)
       (adapt/all-as-svg)
       (svg/serialize)
       (spit "foo.svg")))

(make)
