(ns l22.middleware
  (:require
    [l22.env :refer [defaults]]
    [clojure.tools.logging :as log]
    [l22.layout :refer [error-page]]
    [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
    [l22.middleware.formats :as formats]
    [muuntaja.middleware :refer [wrap-format wrap-params]]
    ;;[l22.config :refer [env]]
    [ring.middleware.flash :refer [wrap-flash]]
    [ring.adapter.undertow.middleware.session :refer [wrap-session]]
    [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
    [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
    [buddy.auth.accessrules :refer [restrict]]
    ;;[buddy.auth :refer [authenticated?]]
    [buddy.auth.backends.session :refer [session-backend]]
    [ring.util.http-response :as response]))
    ;;[taoensso.timbre :as timbre]))

(defn wrap-internal-error [handler]
  (fn [req]
    (try
      (handler req)
      (catch Throwable t
        (log/error t (.getMessage t))
        (error-page {:status 500
                     :title "Something very bad has happened!"
                     :message "We've dispatched a team of highly trained gnomes to take care of the problem."})))))

(defn wrap-csrf [handler]
  (wrap-anti-forgery
    handler
    {:error-response
     (error-page
       {:status 403
        :title "Reload and retry!"})}))

(defn wrap-formats [handler]
  (let [wrapped (-> handler wrap-params (wrap-format formats/instance))]
    (fn [request]
      ;; disable wrap-formats for websockets
      ;; since they're not compatible with this middleware
      ((if (:websocket? request) handler wrapped) request))))

;; (defn on-error [request response]
;;   (error-page
;;    {:status 403
;;     :title (str "Access to " (:uri request) " is not authorized")}))

(defn on-error [_ _]
  (-> (response/found "/login")
      identity))
      ;;(assoc :flash "admin only")))

(defn admin? [request]
  (let [identity (get-in request [:session :identity] nil)]
   ;;(timbre/debug identity)
   (boolean identity)))

(defn wrap-restricted [handler]
  (restrict handler {;;:handler authenticated?
                     :handler admin?
                     :on-error on-error}))

(defn wrap-auth [handler]
  (let [backend (session-backend)]
    (-> handler
        (wrap-authentication backend)
        (wrap-authorization backend))))

(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      ;; wrap-auth here? 2022-08-28
      wrap-auth
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))
      wrap-internal-error))
