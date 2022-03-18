(ns l22.password
 (:require
  [buddy.hashers :as hashers]
  [l22.db.core :as db]
  [l22.layout :as layout]
  [ring.util.http-response :as response]
  [struct.core :as st]))

(def password-schema
  [[:login
    st/required
    st/string]
   {:message "login 名を確認してください。"
    :validate (fn [login]
               (let [ret (db/get-user {:login login})]
                (seq ret)))}
   [:password
    st/required
    st/string]
   [:new-password
    st/required
    st/string]])

(defn validate-user [params]
  (first (st/validate params password-schema)))

(defn password [{:keys [flash] :as request}]
  (layout/render [request] "register.html"
                 (select-keys flash [:name :message :errors])))

(defn password! [{:keys [params]}]
  (if-let [errors (validate-user params)]
    (-> (response/found "/password")
        (assoc :flash (assoc params :errors errors)))
    (let [user (db/get-user {:login (:login params)})
          old-pass (:password params)
          new-pass (hashers/derive (:new-password params))]
      (if (= old-pass (:password user))
        (do
          (db/update-user! (assoc (dissoc params :password)
                            :password new-pass))
          (response/found "/"))
        (layout/render nil "error.html" {:status 404
                                         :title "error"
                                         :message "パスワードが一致しない"})))))