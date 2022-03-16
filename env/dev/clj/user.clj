(ns user
  "Userspace functions you can run by default in your local REPL."
  (:require
   [l22.config :refer [env]]
   [clojure.pprint]
   [clojure.spec.alpha :as s]
   [expound.alpha :as expound]
   [mount.core :as mount]
   [l22.core :refer [start-app]]
   [l22.db.core]
   [conman.core :as conman]
   [luminus-migrations.core :as migrations]
   [taoensso.timbre :as timbre]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(add-tap (bound-fn* clojure.pprint/pprint))

(defn start
  "Starts application.
  You'll usually want to run this on startup."
  []
  (mount/start-without #'l22.core/repl-server))

(defn stop
  "Stops application."
  []
  (mount/stop-except #'l22.core/repl-server))

(defn restart
  "Restarts application."
  []
  (stop)
  (start))

(defn restart-db
  "Restarts database."
  []
  (mount/stop #'l22.db.core/*db*)
  (mount/start #'l22.db.core/*db*)
  (binding [*ns* (the-ns 'l22.db.core)]
    (conman/bind-connection l22.db.core/*db* "sql/queries.sql")))

(defn reset-db
  "Resets database."
  []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate
  "Migrates database up for all outstanding migrations."
  []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback
  "Rollback latest database migration."
  []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration
  "Create a new up and down migration file with a generated timestamp and `name`."
  [name]
  (migrations/create name (select-keys env [:database-url])))


