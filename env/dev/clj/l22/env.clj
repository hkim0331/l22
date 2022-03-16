(ns l22.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [l22.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[l22 started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[l22 has shut down successfully]=-"))
   :middleware wrap-dev})
