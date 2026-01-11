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
		[clojure.string :as string]
		[clj-http.client :as client]
		[java-time.api :as jt]
	)
)

(load "v1Controller")
(load "previewController")
(load "infoController")

(defn frontController [request]
	(cond
		(string/starts-with? (:uri request) "/v1/") (v1Controller request)
		(= (:uri request) "/") (previewController request)
		(= (:uri request) "/_info") (infoController request)
		:else (not-found "Page Not Found")
	)
)

(def app
	(-> frontController
		(wrap-json-response)
		(wrap-resource "public")
		(wrap-content-type)
		(wrap-not-modified)
	)
)

(defn -main
	[& args]
	(def PORT (Integer/valueOf (System/getenv "PORT")))
	(log/info "Starting server on port" PORT)
	(jetty/run-jetty
		app
		{:port PORT}
	)
)
