(ns l22.routes.services
  (:require
   [clojure.tools.logging :as log]
   [l22.db.core :as db]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]
   [ring.middleware.cors :refer [wrap-cors origin]]))

(defn- remote-addr [req]
  (or
   (get-in req [:headers "cf-connecting-ip"])
   (get-in req [:headers "x-real-ip"])
   (get req :remote-addr)))

(defn user
  [{{:keys [login]} :path-params :as request}]
  (log/info "user" login "from" (remote-addr request))
  (try
    (response/ok (db/get-user {:login login}))
    (catch Exception e
      (log/info "exception" (.getMessage e))
      {:status 404
       :body (.getMessage e)})))

(defn users [_]
  (try
    (response/ok {:users (db/list-users)})
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn users-year [{{:keys [year]} :path-params}]
  (try
    (response/ok {:users (db/list-users-year {:year (Integer/parseInt year)})})
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn subj
  [{{:keys [subj]} :path-params}]
  (log/info "subj" subj)
  (response/ok {:users (db/subj {:subj subj})}))

(defn user-randomly [{{:keys [uhour]} :path-params}]
  (response/ok {:user (:login (db/user-randomly {:uhour uhour}))}))

(defn sid->login [{{:keys [sid]} :path-params}]
  (response/ok (db/login {:sid sid})))

(defn login->sid [{{:keys [login]} :path-params}]
  (response/ok (db/sid {:login login})))

(defn users-year-subj-uhour
  [{{:keys [year subj uhour]} :path-params}]
  (response/ok {:users (db/list-users-year-subj-uhour
                        {:year (parse-long year)
                         :subj subj
                         :uhour uhour})}))

(defn services-routes []
  ["/api" {:middleware
           [#(wrap-cors
              %
              :access-control-allow-origin  [#".*\.melt\.kyutech\.ac\.jp.*"
                                             #".*localhost.*"]
              :access-control-allow-methods [:get :post])
            middleware/wrap-csrf
            middleware/wrap-formats]}
   ["/login/:sid" {:get sid->login}]
   ["/sid/:login" {:get login->sid}]
   ;;
   ["/subj/:subj"  {:get subj}]
   ["/user/:uhour/randomly" {:get user-randomly}]
   ["/user/:login" {:get user}]
   ["/users"       {:get users}]
   ["/users/:year" {:get users-year}]
   ["/users/:year/:subj/:uhour" {:get users-year-subj-uhour}]])
