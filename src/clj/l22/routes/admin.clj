(ns l22.routes.admin
  (:require
   [l22.layout :as layout]
   [l22.login :refer [login login!]]
   [l22.middleware :as middleware]
   [l22.register :refer [register register!]]
   [l22.password :refer [password password!]]
   [ring.util.response]))

(def ^:private version "0.2.3-SNAPSHOT")

(defn admin-page [{:keys [flash] :as request}]
  (layout/render request "home.html"
   {:flash flash}))

(defn admin-routes []
  ["/admin"
   {:middleware [middleware/wrap-restricted
                 middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/index" {:get admin-page}]])
