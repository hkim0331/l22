(ns l22.routes.home
  (:require
   [l22.layout :as layout]
   [l22.login :refer [login login!]]
   [l22.middleware :as middleware]
   [l22.register :refer [register register!]]
   [l22.password :refer [password password!]]
   [ring.util.response]))

(def ^:private version "0.2.3-SNAPSHOT")

(defn home-page [{:keys [flash] :as request}]
  (layout/render request "home.html"
   {:flash flash}))

(defn about-page [request]
  (layout/render request "about.html" {:version version}))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/login" {:get  login
              :post login!}]
   ["/register" {:get  register
                 :post register!}]
   ["/password" {:get  password
                 :post password!}]])
