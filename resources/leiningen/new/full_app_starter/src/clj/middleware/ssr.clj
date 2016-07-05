(ns {{name}}.middleware.ssr
  (:require [ring.util.request :refer [path-info]]
            [bidi.bidi :as bidi]))

;; TODO: Move these out of here
(def redirect-url "/index.html")
(def routes ["/" {"" :index
                  "home" :index}])

(defn should-ssr [request]
  (let [url (path-info request)]
    (boolean (bidi/match-route routes url))))

(defn promote-to-ssr [request]
  (assoc request :uri redirect-url))


(defn wrap-ssr-routes [handler]
  (fn [request]
    (if (should-ssr request)
      (handler (promote-to-ssr request))
      (handler request))))
