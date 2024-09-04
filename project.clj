(defproject lucos_scenes "0.1.0-SNAPSHOT"
	:description "Configuration for Smarthome Scenes"
	:url "https://github.com/lucas42/lucos_scenes"
	:dependencies [
		[org.clojure/clojure "1.11.1"]
		[ring/ring-jetty-adapter "1.12.2"]
	]
	:main ^:skip-aot lucos-scenes.core
	:target-path "target/%s"
	:uberjar-name "standalone.jar"
	:profiles {:uberjar {:aot :all
											 :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
