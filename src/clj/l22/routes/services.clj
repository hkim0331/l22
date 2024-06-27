(ns l22.routes.services
  (:require
   [clojure.tools.logging :as log]
   [l22.db.core :as db]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]
   [ring.middleware.cors :refer [wrap-cors origin]]))

;; FIXME: errors
(defn user
  [{{:keys [login]} :path-params :as request}]
  (log/info "login" login "from" (:remote-addr request))
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

(defn services-routes []
  ["/api" {:middleware [#(wrap-cors %
                                    :access-control-allow-origin
                                    [#".*\.melt\.kyutech\.ac\.jp.*"
                                     #".*localhost.*"]
                                    :access-control-allow-methods
                                    [:get :post])
                        middleware/wrap-csrf
                        middleware/wrap-formats]}
   ["/random/:uhour" {:get user-randomly}]
   ["/subj/:subj"   {:get subj}]
   ["/user/:login"  {:get user}]
   ["/users"        {:get users}]
   ["/users/:year"  {:get users-year}]])
