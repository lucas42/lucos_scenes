(in-ns 'lucos-scenes.core)

(defn getDeviceUuid [deviceName]
	(case deviceName
		"bedroom" "bc828821-649a-46bd-9624-7ef668022549"
		"living-room" "02db18a0-b29d-4eb1-be6d-e7242de6496e"
	)
)

(defn playOnDevice [deviceName]
	(def deviceUuid (getDeviceUuid deviceName))
	(log/info "Move playback to device" deviceName deviceUuid)
	(client/put "https://ceol.l42.eu/v3/current-device" {:body deviceUuid})
	(client/put "https://ceol.l42.eu/v3/is-playing" {:body "true"})
)

(defn pause []
	(log/info "Pause playback")
	(client/put "https://ceol.l42.eu/v3/is-playing" {:body "false"})
)

(defn skipTrack []
	(log/info "Skip to next track")
	(client/post "https://ceol.l42.eu/v3/skip-track")
)