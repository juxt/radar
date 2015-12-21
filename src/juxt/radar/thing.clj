(ns juxt.radar.thing
  (:require [thi.ng.geom.core :as g]
            [thi.ng.geom.circle :as c]
            [thi.ng.geom.svg.core :as svg]
            [thi.ng.geom.svg.adapter :as adapt]
            [thi.ng.color.core :as col]
            [thi.ng.geom.viz.core :as viz]
            [thi.ng.geom.triangle :as tri]
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

     ;; Quadrants
     (svg/group
      {:stroke "indigo" :stroke-width 1}
      (svg/line [0 (/ height 2)] [width (/ height 2)])
      (svg/line [(/ width 2) 0] [(/ width 2) height])

      ((viz/svg-triangle-down 30) [[100 100]]))

     ;; Radiants
     (svg/group
      {:fill "none" :stroke "indigo" :stroke-width 1}
      (for [{:keys [radius]} (:arcs data)]
        (with-meta
          (c/circle (/ width 2) (/ width 2) radius)
          {:label "ASdc"})))

     ;; Labels
     (svg/group
      {:fill "#000"
       :font-family "Arial, sans-serif"
       :font-size 10}
      (for [{:keys [radius label]} (:arcs data)]
        (svg/text [403 (+ 15 (- 400 radius))] label))))))

(defn make []
  (->> radar/radar-cfg
       (foo)
       (adapt/all-as-svg)
       (svg/serialize)
       (spit "foo.svg")))

(make)


;; maybe: http://liebke.github.io/analemma/?
