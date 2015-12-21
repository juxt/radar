(ns juxt.radar.radar)

(def radar-cfg {:arcs [{:radius 100, :label "Adopt"}
                       {:radius 200, :label "Trial"}
                       {:radius 300, :label "Assess"}
                       {:radius 400, :label "Hold"}]})

(defn width [radar]
  (* 2 (apply max (map :radius (-> radar :arcs)))))

(defn height [radar]
  (radar-width radar))
