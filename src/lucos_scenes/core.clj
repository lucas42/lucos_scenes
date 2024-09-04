(ns lucos-scenes.core
	(:gen-class)
	(:require [ring.adapter.jetty :as jetty])
)

(defn -main
	[& args]
	(def PORT 8026)
	(println "Starting server on port" PORT)
	(jetty/run-jetty
		(fn [req] {:status 404 :body "Page Not Found" :headers {}})
		{:port PORT}
	)
)
