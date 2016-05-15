(ns leiningen.new.full-app-starter
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "full-app-starter"))

(defn full-app-starter
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' full-app-starter project.")
    (->files data
             ["src/clj/{{sanitized}}/core.clj" (render "src/clj/core.clj" data)]

             ["resources/public/index.html" (render "resources/public/index.html" data)]
             ["src/cljs/{{sanitized}}/core.cljs" (render "src/cljs/core.cljs" data)]

             ["dev/user.clj" (render "dev/user.clj" data)]
             ["project.clj" (render "project.clj" data)])))

