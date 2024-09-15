(in-ns 'lucos-scenes.core)

;; Returns a keyword representing the rough time of day in London
(defn timeOfDayInLondon []
	(def currentHour (-> (jt/zoned-date-time (jt/zone-id "Europe/London")) .getHour))
	(condp > currentHour
		5  :night
		12 :morning
		18 :afternoon
		21 :evening
		24 :night
	)
)

;; Decides on an approprate collection to play in bedroom, based on time of day
(defn getCollectionForBedroom []
	(case (timeOfDayInLondon)
		:night "sleep"
		:morning "waking"
		:afternoon "chill"
		:evening "chill"
		"chill"
	)
)


;; Decides on approprate volume to play in bedroom, based on time of day
(defn getVolumeForBedroom []
	(case (timeOfDayInLondon)
		:night 0.1
		:morning 0.5
		:afternoon 0.8
		:evening 0.7
		0.5
	)
)

;; Decides on an approprate collection to play in shower, based on time of day
(defn getCollectionForShower []
	(case (timeOfDayInLondon)
		:night "night-shower"
		:morning "day-shower"
		:afternoon "day-shower"
		:evening "day-shower"
		"day-shower"
	)
)