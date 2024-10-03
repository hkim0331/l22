(ns l22.routes.home
  (:require
   [l22.layout :as layout]
   [l22.login :refer [login login! logout!]]
   [l22.middleware :as middleware]
   [l22.register :refer [register register!]]
   [l22.password :refer [password password!]]
   [l22.profile :refer [profile-login profile-show]]
   [ring.util.response]))

(def ^:private version "v3.4.656")
(def ^:private updated_at "2024-10-03 21:55:31")

(defn home-page [{:keys [flash] :as request}]
  (layout/render request "home.html"
                 {:flash flash}))

(defn about-page [request]
  (layout/render request "about.html"
                 {:version version
                  :updated_at updated_at}))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/"         {:get home-page}]
   ["/about"    {:get about-page}]
   ["/login"    {:get login
                 :post login!}]
   ["/logout"   {:get logout!}]
   ["/register" {:get register
                 :post register!}]
   ["/password" {:get password
                 :post password!}]
   ["/profile" {:get  profile-login
                :post profile-show}]])
