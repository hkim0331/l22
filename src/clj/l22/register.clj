(ns l22.register
 (:require
  [buddy.hashers :as hashers]
  [clojure.string :as str]
  [clojure.tools.logging :as log]
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
   [:sid
    st/required
    st/string
    {:message "学生番号はすでに登録済みです。"
     :validate (fn [sid]
                 (let [user (db/get-user-by-sid {:sid sid})]
                   (empty? user)))}]
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
   [:login
    st/required
    st/string
    {:message "長すぎます。10 文字以内で。"
     :validate (fn [login] (<= (count login) 10))}]

   [:login
    st/required
    st/string
    {:message "先頭に - は使えない。"
     :validate (fn [login] (not (re-find #"^-" login)))}]

   [:login
    st/required
    st/string
    {:message "数字で始まるアカウントは不可。"
     :validate (fn [login] (not (re-find #"^[0-9]" login)))}]

   [:login
    st/required
    st/string
    {:message "大文字はいけません。"
     :validate (fn [login] (not (re-find #"[A-Z]" login)))}]

   ;; FIXME: this field is mandatory を理解できない学生はいる。
   ;;        :messages "msg" を書いても、"msg" が表示されない。
   [:password
    st/required
    st/string]

   [:uhour
    st/required
    st/string
    {:message "受講クラスを選んでください。"
     :validate (fn [uhour]
                 ;; (log/info "uhour" uhour)
                 (not (= uhour "none")))}]])

(defn validate-user [params]
  (first (st/validate params user-schema)))

(defn register [{:keys [flash] :as request}]
  (layout/render [request] "register.html"
                 (select-keys
                  flash
                  [:sid :name :login :password :message :errors])))

(defn register! [{:keys [params]}]
  (log/info "register!" params)
  (if-let [errors (validate-user params)]
    (-> (response/found "/register")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/create-user! (assoc (dissoc params :password)
                              :password (hashers/derive (:password params))))
      (-> (response/found "/")
          (assoc :flash "registered")))))
