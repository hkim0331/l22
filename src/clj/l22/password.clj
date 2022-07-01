(ns l22.password
 (:require
  [buddy.hashers :as hashers]
  [clojure.tools.logging :as log]
  [l22.db.core :as db]
  [l22.layout :as layout]
  [ring.util.http-response :as response]
  [struct.core :as st]))

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

(defn password! [{:keys [params remote-addr]}]
  (if-let [errors (validate-password params)]
    (do
      (log/info "password validation error" (:login params) remote-addr)
      (-> (response/found "/password")
          (assoc :flash (assoc params :errors errors))))
    (let [user (db/get-user {:login (:login params)})]
      (if (hashers/check (:password params) (:password user))
        (do
          (log/info "password changed" (:login params) remote-addr)
          (db/update-password!
           (assoc (dissoc params :password :updated_at)
                  :password (hashers/derive (:new-password params))
                  :updated_at (java.util.Date.)))
          (-> (response/found "/")
              (assoc :flash "password changed")))
        (do
          (log/info "password error" (:login params) remote-addr)
          #_(layout/render nil "error.html"
                           {:status 404
                            :title "error"
                            :message "パスワードが一致しない。"})
          (-> (response/found "/password")
              (assoc :flash (assoc params :errors {:password "パスワードが一致しない"}))))))))