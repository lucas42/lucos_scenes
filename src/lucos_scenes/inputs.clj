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
			"click"        (fn [] (playCollection (getCollectionForBedroom)) (playOnDevice :bedroom))
			"double-click" skipTrack
			"hold"         pause
		}
	}
	"bathroom" {
		:actions {
			"click"        (fn [] (playCollection (getCollectionForShower)) (playOnDevice :phone))
			"double-click" skipTrack
			"hold"         (fn [] (playCollection "bath") (playOnDevice :phone))
		}
	}
})
