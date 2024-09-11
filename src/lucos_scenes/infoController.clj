(in-ns 'lucos-scenes.core)

(load "utils")

(defn infoController [request]
	(if (= (:request-method request) :get)
		{
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
		(notAllowed :get)
	)
)
