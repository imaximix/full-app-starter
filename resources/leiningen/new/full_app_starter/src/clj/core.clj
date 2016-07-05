(ns {{name}}.core
    (:require [crypto.random :as random]
              [compojure.core :refer :all]
              [compojure.route :refer [not-found]]
              [ring.util.response :refer [resource-response]]
              [ring.middleware.resource :refer [wrap-resource]]
              [ring.middleware.session :refer [wrap-session]]
              [ring.middleware.session.cookie :refer [cookie-store]]
              [ring.middleware.stacktrace :refer [wrap-stacktrace]]
              [ring.logger :refer [wrap-with-logger]]

              [{{name}}.middleware.ssr :refer [wrap-ssr-routes]]))

(defn api-handler [request]
  (str (:query-string request)))

(defroutes middleware-routes
  (GET "/api" request api-handler)
  (not-found (resource-response "index.html" {:root "public"})))

(def app
  (-> middleware-routes
      (wrap-resource "public")
      (wrap-ssr-routes)
      (wrap-session {:store (cookie-store {:key (random/bytes 16)})})
      wrap-stacktrace
      wrap-with-logger))

