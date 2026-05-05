(ns lucos-scenes.loganne
	(:require
		[clojure.tools.logging :as log]
		[clj-http.client :as client]
		[cheshire.core :as json]
	))

(def ^:private LOGANNE_ENDPOINT (System/getenv "LOGANNE_ENDPOINT"))
(def ^:private SOURCE "lucos_scenes")

(defn post-event
	"Fire-and-forget Loganne event.
	Fails soft — any error is logged and scene handling continues.
	Loganne is observability infrastructure, never a critical path."
	[type human-readable extra-fields]
	(future
		(try
			(when LOGANNE_ENDPOINT
				(let [payload (merge {:source SOURCE :type type :humanReadable human-readable} extra-fields)]
					(client/post LOGANNE_ENDPOINT
						{:body (json/generate-string payload)
						 :content-type :json
						 :unexceptional-status #{200 201 204}})))
			(catch Exception e
				(log/warn "Failed to post to Loganne:" (.getMessage e))))))
