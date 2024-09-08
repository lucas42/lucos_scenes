(ns lucos-scenes.core
	(:gen-class)
	(:require
		[clojure.tools.logging :as log]
		[ring.adapter.jetty :as jetty]
		[ring.middleware.json :refer [wrap-json-response]]
		[ring.util.response :refer [not-found, resource-response, content-type]]
	)
)




(defn handler [request]
	(case (:uri request)
		"/" (content-type (resource-response "index.html") "text/html")
		"/icon.png" (content-type (resource-response "icon.png") "image/png")
		"/style.css" (content-type (resource-response "style.css") "text/css")
		"/lucos_navbar.js" (content-type (resource-response "lucos_navbar.js") "text/javascript")
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
  (wrap-json-response handler))

(defn -main
	[& args]
	(def PORT 8026)
	(log/info "Starting server on port" PORT)
	(jetty/run-jetty
		app
		{:port PORT}
	)
)
