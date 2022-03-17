(ns l22.register
 (:require
  [buddy.hashers :as hashers]
  [l22.db.core :as db]
  [l22.layout :as layout]
  [ring.util.http-response :as response]
  [struct.core :as st]))

(def user-schema
  [[:sid
    st/required
    st/string
    {:message "学生番号は数字３つの後に英大文字、続いて数字４つ。"
     :validate (fn [sid]
                 (re-matches #"^\d{3}[A-Z]\d{4}" sid))}]
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

(defn validate-user [params]
  (first (st/validate params user-schema)))

(defn register [{:keys [flash] :as request}]
  (layout/render [request] "register.html"
                 (select-keys flash [:name :message :errors])))

(defn register! [{:keys [params]}]
  (if-let [errors (validate-user params)]
    (-> (response/found "/register")
        (assoc :flash (assoc params :errors errors)))
    (do
      ;;(db/create-user! params)
      (db/create-user! (assoc (dissoc params :password)
                              :password (hashers/derive (:password params))))
      (response/found "/"))))