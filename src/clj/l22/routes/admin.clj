(ns l22.routes.admin
  (:require
   [l22.db.core :as db]
   [l22.layout :as layout]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]))

(defn admin-page [request]
  (try
    (layout/render request "admin.html"
                   {:flash "login as admin"})
    (catch Exception _
      (response/found "/login"))))

(defn users-page [request]
  (let [users (db/list-users)]
    (layout/render request "users.html" {:users users})))

(defn delete-user! [{:keys [params]}]
  (try
    (db/delete-user! params)
    (response/found "/admin/users")
    (catch Exception e
      (layout/render nil "error.html" {:message (.getMessage e)}))))

(defn admin-routes []
  ["/admin"
   {:middleware [middleware/wrap-restricted ; only for this.
                 middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/index"  {:get  admin-page}]
   ["/users"  {:get  users-page}]
   ["/delete" {:post delete-user!}]])
