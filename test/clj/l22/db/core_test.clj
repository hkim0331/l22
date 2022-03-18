(ns l22.db.core-test
  (:require
   [l22.db.core :refer [*db*] :as db]
   [java-time.pre-java8]
   [luminus-migrations.core :as migrations]
   [clojure.test :refer :all]
   [next.jdbc :as jdbc]
   [l22.config :refer [env]]
   [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
     #'l22.config/env
     #'l22.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))

(deftest test-users
  (jdbc/with-transaction [t-conn *db* {:rollback-only true}]
    (is (= 1 (db/create-user!
              t-conn
              {:sid        "000A0000"
               :name       "Sam"
               :login      "Smith"
               :password   "pass"}
              {})))
    (is (= {:sid        "000A0000"
            :name       "Sam"
            :login      "Smith"
            :password   "pass"
            :is_admin   false}
           (-> (db/get-user t-conn {:login "Smith"} {})
               (select-keys [:sid :name :login :password :is_admin]))))))

(deftest test-update-password
  (jdbc/with-transaction [t-conn *db* {:rollback-only true}]
    (is (= 1 (db/create-user!
              t-conn
              {:sid        "000A0000"
               :name       "Sam"
               :login      "Smith"
               :password   "pass"}
              {})))
    (is (= 1 (db/update-password!
              t-conn
              {:login    "Smith"
               :password "thanks"})))
    (is (= {:sid      "000A0000"
            :name     "Sam"
            :login    "Smith"
            :password "thanks"}
           (-> (db/get-user t-conn {:login "Smith"} {})
               (select-keys [:sid :name :login :password]))))))
