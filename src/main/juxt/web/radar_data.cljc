;; Copyright © 2021, JUXT LTD.

(ns juxt.web.radar-data
  (:require [juxt.web.libraries :as libraries]))

(def points-by-year
  {:2016
   [ ;; ClojureScript
    {:id :om-next, :r 250, :t 140}
    {:id :om-tools, :r 80, :t 120}
    {:id :om, :r 80, :t 105}
    {:id :sablono, :r 100, :t 105}
    {:id :reagent, :r 100, :t 155}
    {:id :re-frame, :r 100, :t 140}
    {:id :quiescent, :r 145, :t 120}
    {:id :lein-cljsbuild, :r 45, :t 110}
    {:id :figwheel, :r 45, :t 140}
    {:id :datascript, :r 145, :t 155}

    ;; Platforms/Frameworks
    {:id :core-async, :r 50, :t 240}
    {:id :pedestal, :r 60, :t 210}
    {:id :onyx, :r 160, :t 210}
    {:id :storm, :r 300, :t 210}
    {:id :avout, :r 300, :t 230}
    {:id :compojure-api, :r 85, :t 210}
    {:id :liberator, :r 250, :t 210}
    {:id :riemann, :r 250, :t 190}
    {:id :kekkonen, :r 250, :t 230}
    {:id :yada, :r 250, :t 250}
    {:id :buddy, :r 160, :t 250}
    {:id :prone, :r 100, :t 230}
    {:id :ring, :r 110, :t 260}
    {:id :http-kit, :r 160, :t 230}
    {:id :aleph, :r 110, :t 210}
    {:id :mount, :r 160, :t 190}
    {:id :component, :r 75, :t 230}
    {:id :datomic, :r 110, :t 195}

    ;; Tools
    {:id :clojure-test, :r 70, :t 55}
    {:id :lein, :r 70, :t 25}
    {:id :joplin, :r 150, :t 50}
    {:id :paredit, :r 40, :t 45}
    {:id :cider, :r 100, :t 25}
    {:id :cursive, :r 100, :t 65}
    {:id :boot, :r 160, :t 25}
    {:id :clj-refactor, :r 160, :t 65}
    {:id :midje, :r 300, :t 65}
    {:id :gradle, :r 300, :t 35}

    ;; Libraries
    {:id :garden, :r 250, :t 75}
    {:id :honeysql, :r 50, :t 330}
    {:id :korma, :r 250, :t 330}
    {:id :yesql, :r 80, :t 330}
    {:id :gloss, :r 110, :t 305}
    {:id :schema, :r 50, :t 305}
    {:id :bidi, :r 110, :t 335}
    {:id :tripod, :r 250, :t 300}
    {:id :compojure, :r 250, :t 320}
    {:id :metrics-clojure, :r 110, :t 305}
    {:id :clj-time, :r 110, :t 285}
    {:id :instaparse, :r 160, :t 290}
    {:id :spectre, :r 160, :t 280}
    {:id :hiccup, :r 100, :t 345}
    {:id :stencil, :r 160, :t 345}
    {:id :selmer, :r 160, :t 335}
    {:id :enlive, :r 160, :t 325}
    {:id :timbre, :r 90, :t 290}
    {:id :nomad, :r 75, :t 345}
    {:id :aero, :r 250, :t 350}
    {:id :cheshire, :r 100, :t 320}
    {:id :environ, :r 160, :t 310}],

   :2021
   [ ;; ClojureScript
    {:id :reagent, :r 80, :t 105}
    {:id :re-frame, :r 95, :t 110}
    {:id :shadow-cljs, :r 60, :t 125}
    {:id :figwheel-main, :r 100, :t 125}
    {:id :devcards, :r 100, :t 155}

    {:id :cypress, :r 160, :t 155}
    {:id :re-frame-10x, :r 170, :t 120}
    {:id :kee-frame, :r 146, :t 130}
    {:id :oz, :r 174, :t 110}

    {:id :fulcro, :r 250, :t 160}
    {:id :krell, :r 260, :t 145}
    {:id :helix, :r 245, :t 125}

    {:id :figwheel, :r 300, :t 140}

    ;; Platforms/Frameworks
    {:id :integrant, :r 60, :t 210}
    {:id :xtdb, :r 75, :t 230}
    {:id :ring, :r 110, :t 260}

    {:id :site, :r 160, :t 210}

    {:id :polylith, :r 250, :t 210}
    {:id :holy-lambda, :r 250, :t 190}

    {:id :yada, :r 300, :t 210}
    {:id :mount, :r 300, :t 230}
    {:id :core-typed, :r 306, :t 220}
    {:id :core-async, :r 290, :t 240}

    ;; Tools
    {:id :paredit, :r 40, :t 45}
    {:id :babashka, :r 70, :t 25}
    {:id :ragtime, :r 85, :t 65}
    {:id :deps-edn, :r 100, :t 25}
    {:id :clj-kondo, :r 100, :t 65}
    {:id :jmh-clojure, :r 105, :t 50}

    {:id :kaocha, :r 150, :t 50}
    {:id :clojure-lsp, :r 160, :t 25}
    {:id :calva, :r 160, :t 65}

    {:id :babashka-tasks, :r 240, :t 65}

    {:id :boot, :r 300, :t 30}

    ;; Libraries
    {:id :reitit, :r 50, :t 330}
    {:id :aero, :r 110, :t 305}
    {:id :buddy, :r 50, :t 305}
    {:id :aws-api, :r 110, :t 335}
    {:id :lacinia, :r 110, :t 305}
    {:id :orchestra, :r 110, :t 285}
    {:id :next-jdbc, :r 80, :t 290}

    {:id :jsonista, :r 160, :t 290}
    {:id :tick, :r 160, :t 280}
    {:id :muuntaja, :r 160, :t 345}
    {:id :hato, :r 172, :t 330}

    {:id :malli, :r 250, :t 320}
    {:id :apex, :r 250, :t 330}

    {:id :schema, :r 310, :t 320}
    {:id :timbre, :r 300, :t 340}]})


(defn- add-previous
  "We add the previous radius to the each radius, to help with applying the in-radar quadrant legend."
  [radiants]
  (zipmap (keys radiants)
          (for [[id radiant] radiants
                :let [previous-radiants (not-empty (sort (filter #(> id %) (keys radiants))))
                      previous-id (or (and previous-radiants (apply max previous-radiants)) 0)]]
            (assoc radiant :previous (or (-> previous-id radiants :radius) 0)))))

(def radar-base-data
  (let [base-data {:radiants    {1 {:radius 125 :label "Adopt" :fill "rgba(185,188,187,0.1)"}
                                 2 {:radius 206 :label "Trial" :fill "rgba(198,201,200,0.2)"}
                                 3 {:radius 275 :label "Assess" :fill "rgba(211,212,211,0.3)"}
                                 4 {:radius 325 :label "Hold" :fill "rgba(227,228,226,0.7)"}}
                   :width       650
                   :height      650
                   :total-width 1200
                   :viewBox     "0 0 1200 690"
                   :quadrants   {1 {:fill  "rgb(58,143,163)"
                                    :label "ClojureScript"}
                                 2 {:fill  "rgb(234,125,40)"
                                    :label "Infrastructure"}
                                 3 {:fill  "rgb(107,151,89"
                                    :label "Tools"}
                                 4 {:fill  "rgb(159,30,69)"
                                    :label "Libraries"}}}]
    (-> base-data
        (assoc :radar-radius (/ (:width base-data) 2))
        (update-in [:radiants] add-previous))))

(defn- polar-to-cartesian
  "Radians to degrees, requires the t*pi/180."
  [r t]
  [(* r (Math/cos (* t (/ Math/PI 180))))
   (* r (Math/sin (* t (/ Math/PI 180))))])

(defn- radiant-for [radiants radius]
  (ffirst (filter (fn [[_ v]] (<= (:previous v) radius (:radius v))) radiants)))

(defn- enrich-points [points radiants]
  (->> points
       (mapv (fn [point] (merge (-> point :id libraries/libraries) point)))
       (map (fn [{:keys [r t] :as blip}]
              (let [[x y] (polar-to-cartesian r t)]
                (assoc blip
                       :coords [x y]
                       :quadrant (cond (and (pos? x) (neg? y)) 4
                                       (and (pos? x) (pos? y)) 3
                                       (and (neg? x) (neg? y)) 2
                                       :else 1)))))
       (sort-by (fn [{:keys [quadrant coords]}]
                  [quadrant (Math/abs (* (first coords) (second coords)))]))
       (map-indexed #(assoc %2 :index (inc %1)))
       (map #(assoc % :radiant (radiant-for radiants (:r %))))))

(def radars
  (zipmap (keys points-by-year)
          (map (fn [points]
                 (-> radar-base-data
                     (assoc :points (enrich-points points (:radiants radar-base-data)))))
               (vals points-by-year))))
