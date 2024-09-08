(ns lucos-scenes.core
	(:gen-class)
	(:require
		[clojure.tools.logging :as log]
		[ring.adapter.jetty :as jetty]
		[ring.middleware.json :refer [wrap-json-response]]
		[ring.middleware.resource :refer [wrap-resource]]
		[ring.middleware.content-type :refer [wrap-content-type]]
		[ring.middleware.not-modified :refer [wrap-not-modified]]
		[ring.util.response :refer [not-found, resource-response, content-type]]
	)
)




(defn handler [request]
	(case (:uri request)
		"/" (content-type (resource-response "index.html") "text/html")
		"/_info" {
			:status 200
			:body {
				:system "lucos_scenes"
				:title "Home Scenes"
				:icon "/icon.png"
				:ci {
					:circle "gh/lucas42/lucos_scenes"
				}
				:network_only true
				:show_on_homepage true
			}
		}
		(not-found "Page Not Found")
	)
)

(def app
	(-> handler
		(wrap-json-response)
		(wrap-resource "public")
		(wrap-content-type)
		(wrap-not-modified)
	)
)

(defn -main
	[& args]
	(def PORT 8026)
	(log/info "Starting server on port" PORT)
	(jetty/run-jetty
		app
		{:port PORT}
	)
)
