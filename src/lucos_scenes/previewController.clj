(in-ns 'lucos-scenes.core)

(load "utils")

(defn previewController [request]
	(if (= (:request-method request) :get)
		(content-type (resource-response "index.html") "text/html")
		(notAllowed :get)
	)
)