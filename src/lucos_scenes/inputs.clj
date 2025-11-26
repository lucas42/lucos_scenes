(in-ns 'lucos-scenes.core)

(load "contextual")
(load "media_controls")

(def inputs {
	"music" {
		:actions {
			"click"        (fn [] (playCollectionOnDevice "all" :living-room))
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
	"hallway" {
		:actions {
			"big-click"   (fn [] (switchDevice :living-room)) ;; Only switch device, so will only play if already playing elsewhere (eg on phone)
			"small-click" pause ;; Pauses everywhere for now.  TODO: perhaps don't pause if playing on phone?
		}
	}
})
