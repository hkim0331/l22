(ns l22.routes.services
  (:require
   [l22.db.core :as db]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]
   [ring.middleware.cors :refer [wrap-cors]]
   [taoensso.timbre :as timbre]))

;; FIXME: erros
(defn user [{{:keys [login]} :path-params :as request}]
  (timbre/debug "login" login "from" (:remote-addr request))
  (try
    (response/ok (db/get-user {:login login}))
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn users [_]
  (try
    (response/ok {:users (db/list-users)})
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn my-probe [handler]
 (fn [request]
  (timbre/info "origin" (get-in request [:headers "origin"]))
  (handler request)))

;; curl/httpie からだとファイアしない。
(defn my-wrap-cors [handler]
  (timbre/debug "my-wrap-cors called")
  (-> handler
    (wrap-cors :access-control-allow-origin [#"http://localhost"]
               :access-control-allow-methods [:get])))

(defn services-routes []
  ["/api"
   {:middleware [my-probe
                 my-wrap-cors
                 middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/user/:login" {:get user}]
   ["/users"       {:get users}]])

