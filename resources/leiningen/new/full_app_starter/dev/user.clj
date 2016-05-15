(ns user
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [{{name}}.core :refer [app]]))

(def system nil)

(defn make-system []
  {:handler app})

(defn start [system]
  (alter-var-root #'system (fn [system]
                             {:server (run-jetty app {:port 3000
                                                      :join? false})})))

(defn stop [system]
  (let [{:keys [server]} system]
    (.stop server)))

(defn reset []
  (stop system)
  (refresh :after 'user/start))
