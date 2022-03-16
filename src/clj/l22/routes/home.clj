(ns l22.routes.home
  (:require
   [l22.layout :as layout]
   [l22.db.core :as db]
   [clojure.java.io :as io]
   [l22.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]
   [struct.core :as st]))

(def user-schema
  [[:sid
    st/required
    st/string
    {:message "学生番号は数字３つの後に英大文字、続いて数字４つ。"
     :validate (fn [sid] (re-matches #"^\d{3}[A-Z]\d{4}" sid))}]
   [:name
    st/required
    st/string]
   [:login
    st/required
    st/string
    {:message "同じユーザ名があります。"
     :validate (fn [login]
                  (let [ret (db/get-user {:login login})]
                      (empty? ret)))}]
   [:password
    st/required
    st/string]])

;; error here
(defn validate-user [params]
  (first (st/validate params user-schema)))

(defn register [{:keys [flash] :as request}]
  (layout/render [request] "register.html"
                 (select-keys flash [:name :message :errors])))

(defn register! [{:keys [params]}]
  (if-let [errors (validate-user [params])]
    (-> (response/found "/register")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/create-user! params)
      (response/found "/"))))

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
