(ns l22.routes.admin
  (:require
   [l22.layout :as layout]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]))

(defn admin-page [request]
  (try
    (layout/render request "admin.html"
     {:flash "login as admin"})
    (catch Exception _
     (response/found "/login"))))

(defn logout [_]
  (-> (response/found "/")
      (assoc :session nil)))

(defn admin-routes []
  ["/admin"
   {:middleware [middleware/wrap-restricted
                 middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/index" {:get admin-page}]
   ["/logout" {:post logout}]])

