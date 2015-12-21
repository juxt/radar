(ns juxt.dali
  (:require [dali.io :as io]))

(defn radar []
  [{:filename "JUXT Clojure Radar"
    :document
    [:page {:width 60 :height 60}
                               #_[:cicle
      {:stroke :indigo :stroke-width 4 :fill :darkorange}
      [30 30] 20]]}])

(defn foo []
  (io/render-svg (radar) "foo.svn"))

(foo)
