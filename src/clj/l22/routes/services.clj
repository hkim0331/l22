(ns l22.routes.services
 (:require
  [l22.db.core :as db]
  [l22.middleware :as middleware]
  [ring.util.http-response :as response]
  [taoensso.timbre :as timbre]))

;; FIXME: erros
(defn user [{{:keys [login]} :path-params}]
  (timbre/debug "login" login)
  (try
    {:status 200
     :body (db/get-user {:login login})}
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn users [_]
  (try
    {:status 200
     :body {:users (db/list-users)}}
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn services-routes []
  ["/api"
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/user/:login" {:get user}]
   ["/users"       {:get users}]])

