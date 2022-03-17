(ns l22.routes.home
  (:require
   [l22.layout :as layout]
   [l22.middleware :as middleware]
   [l22.register :refer [register register!]]
   [ring.util.response]))

(def ^:private version "0.2.0")

(defn home-page [request]
  (layout/render request "home.html"))

(defn about-page [request]
  (layout/render request "about.html" {:version version}))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/register" {:get  register
                 :post register!}]
   ["/" {:get home-page}]
   ["/about" {:get about-page}]])
