(ns l22.profile
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
    st/string]])

(defn validate-password [params]
  (first (st/validate params password-schema)))

(defn profile-login [{:keys [flash] :as request}]
  (layout/render [request] "profile-login.html"
                 (select-keys flash [:name :message :errors])))

(defn profile-show [{:keys [params] :as request}]
  (let [user (db/profile {:login (:login params)})]
    (if (hashers/check (:password params) (:password user))
      (do
        #_(log/info "user" user)
        (layout/render [request] "profile-show.html" user))
      (-> (response/found "/profile")
          (assoc
           :flash
           (assoc params
                  :errors {:password "パスワードが一致しない"}))))))
