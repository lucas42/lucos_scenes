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
							(def action (get (:actions input) action-key))
							(action)
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
