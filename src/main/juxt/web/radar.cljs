;; Copyright © 2021, JUXT LTD.

(ns juxt.web.radar
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [juxt.web.radar-data :as radar-data]))

(def clojure-logo-underlay
  [:g {:transform "translate(242,-33) scale(7.25,7.25)"}
   [:path {:d "m49.3,72.9c2.6-0.005,5.09-0.434,7.43-1.21-0.549-0.502-1.07-1.04-1.55-1.64-3.17-4.04-4.94-9.96-7.73-19.9-0.443,0.96-0.932,2.04-1.44,3.19-1.79,4.06-3.77,8.99-4.5,12.2-0.26,1.13-0.421,2.52-0.418,4.07,0,0.612,0.034,1.26,0.085,1.91,2.53,0.932,5.26,1.44,8.12,1.45z" :fill "#91dc47" :display "inline"}]
   [:path {:d "m25.4,19.8c5.18,0.015,9.25,1.62,11.2,2.72,0.472,0.272,0.919,0.565,1.36,0.866,3.49-1.54,7.34-2.4,11.4-2.4,15.6,0.002,28.3,12.7,28.3,28.3h-0.002c0,7.89-3.22,15-8.42,20.1,1.28,0.143,2.64,0.231,4.03,0.225,4.94,0.002,10.3-1.09,14.3-4.45,2.61-2.2,4.79-5.41,6-10.2,0.237-1.86,0.373-3.76,0.373-5.68,0-24.6-20-44.6-44.6-44.6-14.9,0-28.1,7.35-36.2,18.6,4.21-2.63,8.5-3.58,12.3-3.55z" :fill "#5881d8" :display "inline"}]

   [:path {:d "m73.6,76.6c-1.35,0-2.61-0.072-3.78-0.215-3.12-0.392-5.7-0.863-7.95-1.66-3.79,1.88-8.06,2.94-12.6,2.94-15.6,0-28.3-12.7-28.3-28.3,0.001-8.49,3.74-16.1,9.65-21.3-1.58-0.381-3.23-0.605-4.92-0.602-8.31,0.079-17.1,4.68-20.7,17.1-0.168,1.58-0.26,3.17-0.26,4.79,0,24.6,20,44.6,44.6,44.6,15.1,0,28.4-7.49,36.5-18.9-4.36,1.09-8.56,1.61-12.1,1.62z" :display "inline" :fill "#63b132"}]
   [:path {:d "m44.9,42.9c-1.5-3.78-3.68-8.17-5.62-10.2-0.988-1.04-2.19-1.94-3.52-2.7-6.05,4.26-10,11.3-10,19.3,0.015,7.85,3.85,14.8,9.75,19.1,1.45-6.02,5.07-11.5,10.5-22.6-0.322-0.887-0.69-1.86-1.1-2.88z" :display "inline" :fill "#91dc47"}]
   [:path {:d "m61.5,67.7c0.274,0.137,0.898,0.359,1.77,0.605,5.84-4.29,9.64-11.2,9.65-19h-0.002c-0.022-13-10.6-23.5-23.6-23.6-2.59,0.005-5.07,0.431-7.4,1.2,4.78,5.45,7.09,13.2,9.31,21.8,0.001,0.003,0.003,0.006,0.004,0.01,0.004,0.006,0.712,2.37,1.93,5.5,1.21,3.13,2.93,7,4.8,9.82,1.23,1.89,2.58,3.25,3.51,3.65z" :display "inline" :fill "#90b4fe"}]
   [:path {:d "m93.5,55c-1.21,4.82-3.4,8.04-6,10.2-4,3.36-9.34,4.45-14.3,4.45-1.39,0.007-2.75-0.082-4.03-0.225,5.2-5.13,8.42-12.3,8.42-20.1h0.002c-0.002-15.6-12.7-28.3-28.3-28.3-4.06,0-7.91,0.859-11.4,2.4-0.439-0.301-0.887-0.594-1.36-0.866-1.95-1.1-6.03-2.7-11.2-2.72-3.75-0.034-8.05,0.92-12.3,3.55-4.37,6.09-7.25,13.3-8.09,21.2,3.65-12.4,12.4-17,20.7-17.1,1.68-0.003,3.34,0.22,4.92,0.602-5.92,5.19-9.65,12.8-9.65,21.3,0.002,15.6,12.7,28.3,28.3,28.3,4.52,0,8.79-1.06,12.6-2.94,2.25,0.795,4.83,1.27,7.95,1.66,1.17,0.143,2.43,0.215,3.78,0.215,3.59-0.011,7.79-0.531,12.1-1.62,4.08-5.79,6.8-12.6,7.74-19.9zm-67.8-5.68c0.015-7.97,3.97-15,10-19.3,1.33,0.766,2.53,1.66,3.52,2.7,1.94,1.99,4.11,6.38,5.62,10.2,0.412,1.02,0.78,1.99,1.1,2.88-5.44,11.1-9.06,16.6-10.5,22.6-5.9-4.28-9.74-11.2-9.75-19.1zm23.6,23.6c-2.85-0.005-5.59-0.516-8.12-1.45-0.052-0.655-0.085-1.3-0.085-1.91-0.002-1.55,0.159-2.94,0.418-4.07,0.724-3.16,2.71-8.1,4.5-12.2,0.506-1.15,0.995-2.23,1.44-3.19,2.8,9.95,4.56,15.9,7.73,19.9,0.477,0.603,1,1.14,1.55,1.64-2.34,0.777-4.83,1.2-7.43,1.21zm13.9-4.57c-0.868-0.246-1.49-0.469-1.77-0.605-0.922-0.399-2.28-1.76-3.51-3.65-1.88-2.82-3.6-6.69-4.8-9.82-1.22-3.13-1.92-5.49-1.93-5.5-0.001-0.003-0.003-0.006-0.004-0.01-2.23-8.53-4.53-16.3-9.31-21.8,2.33-0.771,4.81-1.2,7.4-1.2,13,0.025,23.5,10.6,23.6,23.6h0.002c-0.014,7.8-3.81,14.7-9.65,19z" :display "inline" :fill "#FFF"}]])

(defn hide-all-but [but-index {:keys [points] :as m}]
  (assoc m :points (mapv (fn [{:keys [index] :as point}]
                          (assoc point :hide (not (= index but-index))))
                        points)))

(defn show-all [{:keys [points] :as m}]
  (assoc m :points (mapv (fn [point]
                           (assoc point :hide false))
                         points)))

(def legend-text-height 16)

(defn- raster-to-cartesian [[rx ry] [w h]]
  [(+ w rx)
   (- h ry)])

(defn- radiant-listing [radar-data label points-for-quadrant]
  (cons
   [:text {:x 0
           :y 0
           :text-anchor "left"
           :fill "black"
           :font-size 12
           :key (str "quandrant-" label)}
    label]
   (for [{:keys [quadrant-index label index id]} (map-indexed #(assoc %2 :quadrant-index %1) points-for-quadrant)]
     [:a {:xlinkHref (str "#" (name id)) :key (str "radiant-link-" index)}
      [:text {:key (str "radiant-" index)
              :x 0
              :y (+ 0 (* (inc quadrant-index) legend-text-height))
              :text-anchor "left"
              :on-mouse-over #(swap! radar-data (partial hide-all-but index))
              :on-mouse-out #(swap! radar-data show-all)
              :class "linkable"}
       index]
      [:text {:key (str "radiant-label-" index)
              :x 25
              :y (+ 0 (* (inc quadrant-index) legend-text-height))
              :text-anchor "left"
              :on-mouse-over #(swap! radar-data (partial hide-all-but index))
              :on-mouse-out #(swap! radar-data show-all)
              :class "linkable"}
       label]])))

(defn- legend [radar-data]
  [:g {:fill "#000" :font-family "Arial, sans-serif" :font-size 12 :style {:font-weight "bold"}}
   (let [{:keys [quadrants points total-width radiants]} @radar-data]
     (for [[id {:keys [fill label]}] quadrants
           :let [start-y (if (= 0 (mod id 2)) 340 15)
                 start-x (if (> id 2) (- total-width 200) 70)
                 radiant-groups (->> points
                                     (filter #(= id (:quadrant %)))
                                     (group-by :radiant)
                                     (sort-by first))]]
       [:g {:transform (str "translate(" start-x "," start-y ")") :key (str "quadrant-legend-" id)}
        [:text {:x 0
                :y 0
                :text-anchor "left"
                :fill fill
                :font-size 15}
         label]
        (for [[radiant-id radiant-points] radiant-groups
              :let [previous-groups (remove (fn [[k v]] (>= k radiant-id)) radiant-groups)
                    start-y (+ 20
                               (* (dec radiant-id) 5)
                               (* legend-text-height (count previous-groups))
                               (* legend-text-height (->> previous-groups vals flatten count)))
                    radiant-label (:label (get radiants radiant-id))]
              :when (not-empty radiant-points)]
          [:g {:transform (str "translate(" 0 "," start-y ")") :key (str "legend-transform-" radiant-label)}
           (radiant-listing radar-data radiant-label radiant-points)])]))])

(defn- blip
  [[x y] hide id]
  (let [w 20
        h (* w (Math/sin (/ Math/PI 3)))
        w (* 0.7 w)
        translate (str "translate(0," (/ h 2) ")")
        opacity (if hide "0" "1")]
    [:g
     {:key (str "blip-" id)}
     [:polygon {:points [[(- x w) (- y h)] [(+ x w) (- y h)] [x y]]
                :transform translate
                :fill-opacity opacity}]
     [:text {:x x
             :y (+ 1 y)
             :text-anchor "middle"
             :stroke-opacity opacity
             :fill-opacity opacity
             :stroke-width 0
             :fill :white
             :font-family "Arial, sans-serif" :font-size "10" :stroke "white"}
      id]]))

(defn- radar-component
  [year]
  (let [radar-data (r/atom (get radar-data/radars year))]
    (fn []
      (let [{:keys [radiants width height points quadrants total-width radar-radius viewBox]} @radar-data
            center [(+ (/ (- total-width width) 2) (/ width 2)) (/ height 2)]]
        [:object {:style {:width "100%"}}
         [:svg {:viewBox viewBox :xmlns "http://www.w3.org/2000/svg" :xmlnsXlink "http://www.w3.org/1999/xlink"}

          ;; Clojure logo underlay
          clojure-logo-underlay

          ;; The RADAR Structure:

          [:g {:stroke-width "2" :stroke "white"}
           (for [[id {:keys [radius] :as c}] (reverse radiants)]
             [:circle (merge {:key (str "radiant-" id) :cx (first center) :cy (second center) :r radius}
                             (select-keys c [:fill]))])]

          [:g {:stroke-width "20" :stroke "rgb(219,220,219)"}
           [:line {:x1 (- (first center) radar-radius) :x2 (+ (first center) radar-radius) :y1 (second center) :y2 (second center)}]
           [:line {:x1 (first center) :x2 (first center) :y1 0 :y2 height}]]
          [:g {:stroke-width "1" :stroke "black"}
           [:line {:x1 (first center) :x2 (first center) :y1 0 :y2 height}]]

          ;; Axis legends:
          [:g {:fill "#000" :font-family "Arial, sans-serif" :font-size 12 :font-weight "bold"}
           (for [{:keys [radius label previous]} (vals radiants)]
             [:text {:key (str "axis-left-" label)
                     :x (+ (first center) previous (/ (- radius previous) 2))
                     :y (+ 4 (second center))
                     :text-anchor "middle"}
              label])
           (for [{:keys [radius label previous]} (vals radiants)]
             [:text {:key (str "axis-right-" label)
                     :x (+ (/ (- radius previous) 2) (- (first center) radius))
                     :y (+ 4 (second center))
                     :text-anchor "middle"}
              label])]

          ;; The Blips:

          (for [[quadrant points] (group-by :quadrant points)]
            [:g {:fill (-> quadrant quadrants :fill) :key (str "blip-group-" quadrant)}
             (for [{:keys [coords hide index]} points]
               (blip (raster-to-cartesian coords center) hide index))])

          (legend radar-data)]]))))

(defn ^:dev/after-load start []
  (rdom/render [radar-component :2021]
               (. js/document (getElementById "radar"))))

(defn init []
  (start))
