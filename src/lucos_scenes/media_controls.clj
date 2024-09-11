(in-ns 'lucos-scenes.core)

(defn getDeviceUuid [deviceName]
	(case deviceName
		"bedroom" "bc828821-649a-46bd-9624-7ef668022549"
		"living-room" "02db18a0-b29d-4eb1-be6d-e7242de6496e"
	)
)

(defn playOnDevice [deviceName]
	(def deviceUuid (getDeviceUuid deviceName))
	(log/info "//TODO: move playback to device" deviceUuid)
)

(defn pause []
	(log/info "//TODO: pause playback")
)

(defn skipTrack []
	(log/info "//TODO: skip to next track")
)