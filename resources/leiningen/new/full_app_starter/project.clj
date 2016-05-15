(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.5.0"]

                 [org.clojure/tools.namespace "0.2.11"]
                 
                 [figwheel-sidecar "0.5.2"]
                 [com.cemerick/piggieback "0.2.1"]]

  :source-paths ["src/clj" "src/cljs" "dev"]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs/{{sanitized}}"]
                :figwheel true
                :compiler {:main {{name}}.core
                           :asset-path "js/out"
                           :output-to "resources/public/js/{{name}}.js"
                           :output-dir "resources/public/js/out"}}]})

