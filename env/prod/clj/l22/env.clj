(ns l22.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[l22 started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[l22 has shut down successfully]=-"))
   :middleware identity})
