(ns lucos-scenes.core
	(:gen-class)
	(:require
		[ring.adapter.jetty :as jetty]
		[ring.middleware.json :refer [wrap-json-response]]
	)
)

(defn handler [request]
	(case (:uri request)
		"/_info" {
			:status 200
			:body {
				:system "lucos_scenes"
				:ci {
					:circle "gh/lucas42/lucos_scenes"
				}
				:network_only true
			}
		}
		{:status 404 :body "Page Not Found" :headers {}}
	)
)

(def app
  (wrap-json-response handler))

(defn -main
	[& args]
	(def PORT 8026)
	(println "Starting server on port" PORT)
	(jetty/run-jetty
		app
		{:port PORT}
	)
)
