(in-ns 'lucos-scenes.core)

(load "media_controls")

(def inputs {
	"music" {
		:actions {
			"click"        (fn [] (playOnDevice "living-room"))
			"double-click" skipTrack
			"hold"         pause
		}
	}
	"bedside" {
		:actions {
			"click"        (fn [] (playOnDevice "bedroom"))
			"double-click" skipTrack
			"hold"         pause
		}

	}
})