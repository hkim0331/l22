(ns l22.login
  (:require
   [buddy.hashers :as hashers]
   [l22.db.core :as db]
   [l22.layout :as layout]
   [ring.util.http-response :as response]
   [struct.core :as st]))

(def admin-schema
  [[:login
    st/required
    st/string
    {:message "Admin only! Your attempt was recorded."
     :validate (fn [login]
                 (let [ret (db/get-user {:login login})]
                   (and (seq ret) (:is_admin ret))))}]
   [:password
    st/required
    st/string]])

(defn validate-admin [params]
  (first (st/validate params admin-schema)))

(defn login [{:keys [flash] :as request}]
  (layout/render [request] "login.html"
                 (select-keys flash [:name :message :errors :bad])))

(defn login! [{:keys [params]}]
  (if-let [errors (validate-admin params)]
    (-> (response/found "/login")
        (assoc :flash (assoc params :errors errors)))
    (let [user (db/get-user {:login (:login params)})]
      ;;(timbre/debug user)
      (if (and (seq user)
               (:is_admin user)
               (hashers/check (:password params) (:password user)))
        (-> (response/found "/admin/users")
            (assoc-in [:session :identity] (keyword (:login params))))
        (-> (response/found "/login")
            (assoc :flash {:bad "bad password"}))))))

(defn logout! [_]
  (-> (response/found "/")
      (assoc :session nil)))