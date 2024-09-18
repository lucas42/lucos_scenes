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

(defn playOnDevice [deviceName]
	(def deviceUuid (getDeviceUuid deviceName))
	(log/info "Move playback to device" deviceName deviceUuid)
	(client/put (str MEDIA_MANAGER "/current-device") {:body deviceUuid :headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
	(client/put (str MEDIA_MANAGER "/is-playing") {:body "true" :headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
)

(defn pause []
	(log/info "Pause playback")
	(client/put (str MEDIA_MANAGER "/is-playing") {:body "false" :headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
)

(defn skipTrack []
	(log/info "Skip to next track")
	(client/post (str MEDIA_MANAGER "/skip-track"){:headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
)

(defn playCollection [collectionSlug]
	(log/info "Play Collection" collectionSlug)
	(client/put (str MEDIA_MANAGER "/current-collection") {:body collectionSlug :headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
)

(defn setVolume [volume]
	(log/info "Play Collection" volume)
	(client/put (str MEDIA_MANAGER "/volume") {:body (str volume) :headers{:Authorization (str "Key " MEDIA_MANAGER_API_KEY)} :unexceptional-status #{204}})
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