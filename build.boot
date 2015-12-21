(set-env!
 :source-paths #{"sass" "src" "resources"}
 :dependencies '[[dali "0.7.0-SNAPSHOT"]
                 [org.clojure/clojure "1.7.0"]
                 [thi.ng/geom "0.0.908"]])

#_(deftask dev
  "Simple alias to run application in development mode"
  []
  (set-env! :target-path "target/dev")
  (comp
   (serve :dir "target/dev")
   (watch)
   (speak)
   (sass :sass-file "app.scss"
         :output-dir "."
         :line-numbers true
         :source-maps true)
   (html)))
