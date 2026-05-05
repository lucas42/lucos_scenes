(in-ns 'lucos-scenes.core)

(load "utils")
(load "inputs")

(defn v1Controller [request]
	(let [[input-key action-key] (string/split (string/replace-first (:uri request) #"/v1/", "") #"/")]
		(if (= (:request-method request) :post)
			(if (contains? inputs input-key)
				(do
					(def input (get inputs input-key))
					(if (contains? (:actions input) action-key)
						(do
							(def action-def (get (:actions input) action-key))
							(def action-fn (:fn action-def))
							(def extra-fields (or (when-let [ff (:fields-fn action-def)] (ff))
							                      (:fields action-def)
							                      {}))
							(action-fn)
							(loganne/post-event
								"sceneActivated"
								(str "Scene '" input-key "' activated by '" action-key "'")
								(merge {:scene input-key :action action-key} extra-fields))
							(accepted)
						)
						(not-found (str "Action \"" action-key "\" Not Found\n"))
					)
				)
				(not-found (str "Input \"" input-key "\" Not Found\n"))
			)
			(notAllowed :post)
		)
	)
)
