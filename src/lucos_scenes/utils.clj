(in-ns 'lucos-scenes.core)

(defn notAllowed [& methods]
	{
		:status 405
		:headers {"Allow" (string/join ", " (map string/upper-case (map name methods)))}
	}
)

(defn accepted []
	{
		:status 202
	}
)