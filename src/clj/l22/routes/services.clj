(ns l22.routes.services
  (:require
   [clojure.tools.logging :as log]
   [l22.db.core :as db]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]
   [ring.middleware.cors :refer [wrap-cors]]))

;; FIXME: errors
(defn user [{{:keys [login]} :path-params}]
  ;;(log/debug "login" login "from" (:remote-addr request))
  (try
    (response/ok (db/get-user {:login login}))
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

(defn users [_]
  (try
    (response/ok {:users (db/list-users)})
    (catch Exception e {:status 404
                        :body (.getMessage e)})))

;; curl/httpie からだとファイアしない。
;; origin は名乗られたのを信用するのか。
;; 本番チェックが必要。
(defn my-wrap-cors [handler]
  (-> handler
      (wrap-cors :access-control-allow-origin  [#"http://localhost.*"]
                 :access-control-allow-methods [:get])))

(defn my-probe [handler]
  (fn [request]
    (log/info "origin:" (get-in request [:headers "origin"]))
    (handler request)))

(defn services-routes []
  ["/api" {:middleware [my-probe
                        my-wrap-cors
                        middleware/wrap-csrf
                        middleware/wrap-formats]}
   ["/user/:login" {:get user}]
   ["/users"       {:get users}]])
