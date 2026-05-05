(in-ns 'lucos-scenes.core)

(load "contextual")
(load "media_controls")

(def inputs {
	"music" {
		:actions {
			"click"        {:fn (fn [] (playCollectionOnDevice "all" :living-room))
			               :fields {:targetCollection "all" :targetDevice "living-room"}}
			"double-click" {:fn skipTrack}
			"hold"         {:fn pause}
		}
	}
	"bedside" {
		:actions {
			"click"        {:fn (fn [] (playCollectionAtVolumeOnDevice (getCollectionForBedroom) (getVolumeForBedroom) :bedroom))
			               :fields-fn (fn [] {:targetCollection (getCollectionForBedroom) :targetDevice "bedroom"})}
			"double-click" {:fn skipTrack}
			"hold"         {:fn pause}
		}
	}
	"bathroom" {
		:actions {
			"click"        {:fn (fn [] (playCollectionOnDevice (getCollectionForShower) :phone))
			               :fields-fn (fn [] {:targetCollection (getCollectionForShower) :targetDevice "phone"})}
			"double-click" {:fn skipTrack}
			"hold"         {:fn (fn [] (playCollectionOnDevice "bath" :phone))
			               :fields {:targetCollection "bath" :targetDevice "phone"}}
		}
	}
	"hallway" {
		:actions {
			"big-click"   {:fn (fn [] (switchDevice :living-room))
			              :fields {:targetDevice "living-room"}}
			"small-click" {:fn pause}
		}
	}
})
