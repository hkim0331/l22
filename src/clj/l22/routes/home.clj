(ns l22.routes.home
  (:require
   [l22.layout :as layout]
   [l22.db.core :as db]
   [clojure.java.io :as io]
   [l22.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn register [request]
  (layout/render [request] "register.html"))

(defn register! [{:keys [params] :as request}]
  (layout/render [request] "home.html" {:docs (str params)}))


(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/register" {:get  register
                 :post register!}]
   ["/" {:get home-page}]
   ["/about" {:get about-page}]])

