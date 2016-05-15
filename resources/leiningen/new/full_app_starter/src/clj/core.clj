(ns {{name}}.core
    (:gen-class)
    (:require [compojure.core :refer :all]
              [compojure.route :refer [not-found]]))

(defroutes app
  (GET "/" [] "<h1>Hello world")
  (not-found "<h1>NOT FOUND</h1>"))

