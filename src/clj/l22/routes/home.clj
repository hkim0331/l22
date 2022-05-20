(ns l22.routes.home
  (:require
   ;;[clj-http.client :as client]
   ;;[clojure.data.json :as json]
   [l22.layout :as layout]
   [l22.login :refer [login login! logout!]]
   [l22.middleware :as middleware]
   [l22.register :refer [register register!]]
   [l22.password :refer [password password!]]
   [ring.util.response]))

(def ^:private version "0.4.0")
(def ^:private updated_at "2022-05-20 10:11:31")
;; below only works in development, not in jar.
;; (def ^:private version
;;   (-> "project.clj" slurp read-string (nth 2)))

;; cancel by 0.2.14
;; the reason?
#_(defn home-page [{:keys [flash] :as request}]
    (let [body (-> (client/get "https://w.hkim.jp/loc")
                   :body
                   (json/read-str :key-fn keyword))]
      (layout/render request "home.html"
                     {:flash flash
                      :loc (:location body)
                      :ts (:timestamp body)})))

(defn home-page [{:keys [flash] :as request}]
  (layout/render request "home.html"
                 {:flash flash}))

(defn about-page [request]
  (layout/render request "about.html" {:version version
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
                 :post password!}]])
