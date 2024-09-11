(in-ns 'lucos-scenes.core)

(load "utils")
(load "inputs")

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
				:metrics {
					:input-count {
						:techDetail "Number of input devices currently configured for setting scenes"
						:value (count inputs)
					}
				}
			}
		}
		(notAllowed :get)
	)
)
