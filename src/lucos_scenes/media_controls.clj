(in-ns 'lucos-scenes.core)

(defn getDeviceUuid [deviceName]
	(case deviceName
		:bedroom "bc828821-649a-46bd-9624-7ef668022549"
		:living-room "02db18a0-b29d-4eb1-be6d-e7242de6496e"
		:phone "9131f2f9-4db7-4471-ad81-f5cd06c2c3ca"
	)
)

(def MEDIA_MANAGER (System/getenv "MEDIA_MANAGER"))
(def MEDIA_MANAGER_API_KEY (System/getenv "KEY_LUCOS_MEDIA_MANAGER"))

(defn manager_put [path body]
	(client/put (str MEDIA_MANAGER path) {:body body :headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
)
(defn manager_post [path]
	(client/post (str MEDIA_MANAGER path) {:headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
)

(defn playOnDevice [deviceName]
	(def deviceUuid (getDeviceUuid deviceName))
	(log/info "Move playback to device" deviceName deviceUuid)
	(manager_put "/current-device" deviceUuid)
	(manager_put "/is-playing" "true")
)

(defn pause []
	(log/info "Pause playback")
	(manager_put "/is-playing" "false")
)

(defn skipTrack []
	(log/info "Skip to next track")
	(manager_post "/skip-track")
)

(defn playCollection [collectionSlug]
	(log/info "Play Collection" collectionSlug)
	(manager_put "/current-collection" collectionSlug)
)

(defn setVolume [volume]
	(log/info "Play Collection" volume)
	(manager_put "/volume" (str volume))
)



;; Convenience Methods
(defn playCollectionOnDevice [collectionSlug deviceName]
	(playCollection collectionSlug)
	(playOnDevice deviceName)
)

(defn playCollectionAtVolumeOnDevice [collectionSlug volume deviceName]
	(playCollection collectionSlug)
	(setVolume volume)
	(playOnDevice deviceName)
)