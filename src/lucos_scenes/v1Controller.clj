(in-ns 'lucos-scenes.core)

(load "utils")
(load "media_controls")

(defn v1Controller [request]
	(let [[input action] (string/split (string/replace-first (:uri request) #"/v1/", "") #"/")]
		(if (= (:request-method request) :post)
			(case input
				"music" (case action
					"click" (do
					 (playOnDevice "living-room")
					 (accepted)
					)
					"double-click" (do
					 (skipTrack)
					 (accepted)
					)
					"hold" (do
					 (pause)
					 (accepted)
					)
					(not-found "Action Not Found")
				)
				"bedside" (case action
					"click" (do
					 (playOnDevice "bedroom")
					 (accepted)
					)
					"double-click" (do
					 (skipTrack)
					 (accepted)
					)
					"hold" (do
					 (pause)
					 (accepted)
					)
					(not-found "Action Not Found")
				)
				(not-found "Input Not Found")
			)
			(notAllowed :post)
		)
	)
)
