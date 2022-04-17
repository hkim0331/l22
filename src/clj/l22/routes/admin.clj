(ns l22.routes.admin
  (:require
   [l22.db.core :as db]
   [l22.layout :as layout]
   [l22.middleware :as middleware]
   [ring.util.http-response :as response]))

(defn admin-page [request]
  (try
    (layout/render request "users.html"
                   {:flash "login as admin"})
    (catch Exception _
      (response/found "/login"))))

(defn users-page [request]
  (let [users (db/list-users)]
    (layout/render request "users.html" {:users users})))

(defn user-page [{{:keys [id]} :path-params :as request}]
  (let [user (db/user {:id id})]
   (layout/render request "user.html" {:user user})))

(defn update-user [request]
  (layout/render request "under-construction.html"))

(defn delete-user! [{:keys [params]}]
  (try
    (db/delete-user! params)
    (response/found "/admin/users")
    (catch Exception e
      (layout/render nil "error.html" {:message (.getMessage e)}))))

(defn admin-routes []
  ["/admin"
   {:middleware [middleware/wrap-restricted ; only for /admin routes.
                 middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/users"  {:get  users-page}]
   ["/user/:id"] {:get user-page
                  :post update-user}
   ["/delete" {:post delete-user!}]])

