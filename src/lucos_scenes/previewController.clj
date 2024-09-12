(in-ns 'lucos-scenes.core)

(load "utils")
(load "inputs")
(use 'selmer.parser)

(defn previewController [request]
	(if (= (:request-method request) :get)
		{
			:status 200
			:headers {"Content-Type" "text/html"}
			:body (render-file "index.html" {:inputs inputs})
		}
		(notAllowed :get)
	)
)