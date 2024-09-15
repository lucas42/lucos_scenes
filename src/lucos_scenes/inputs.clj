(in-ns 'lucos-scenes.core)

(load "contextual")
(load "media_controls")

(def inputs {
	"music" {
		:actions {
			"click"        (fn [] (playOnDevice :living-room))
			"double-click" skipTrack
			"hold"         pause
		}
	}
	"bedside" {
		:actions {
			"click"        (fn [] (playCollectionAtVolumeOnDevice (getCollectionForBedroom) (getVolumeForBedroom) :bedroom))
			"double-click" skipTrack
			"hold"         pause
		}
	}
	"bathroom" {
		:actions {
			"click"        (fn [] (playCollectionOnDevice (getCollectionForShower) :phone))
			"double-click" skipTrack
			"hold"         (fn [] (playCollectionOnDevice "bath" :phone))
		}
	}
})
