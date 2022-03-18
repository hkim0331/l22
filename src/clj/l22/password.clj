(ns l22.password
 (:require
  [buddy.hashers :as hashers]
  [l22.db.core :as db]
  [l22.layout :as layout]
  [ring.util.http-response :as response]
  [struct.core :as st]
  [taoensso.timbre :as timbre]))

(def password-schema
  [[:login
    st/required
    st/string
    {:message "login 名を確認してください。"
     :validate (fn [login]
                (let [ret (db/get-user {:login login})]
                 (seq ret)))}]
   [:password
    st/required
    st/string]
   [:new-password
    st/required
    st/string]])

(defn validate-password [params]
  (first (st/validate params password-schema)))

(defn password [{:keys [flash] :as request}]
  (layout/render [request] "password.html"
                 (select-keys flash [:name :message :errors])))

(defn password! [{:keys [params]}]
  (if-let [errors (validate-password params)]
    (-> (response/found "/password")
        (assoc :flash (assoc params :errors errors)))
    (let [user (db/get-user {:login (:login params)})]
      (if (hashers/check (:password params) (:password user))
        (do
          (db/update-password!
           (assoc (dissoc params :password)
                  :password (hashers/derive (:new-password params))))
          (-> (response/found "/")
              (assoc :flash "password changed")))
        (layout/render nil "error.html"
                       {:status 404
                        :title "error"
                        :message "パスワードが一致しない。"})))))
