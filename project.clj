(defproject lucos_scenes "0.1.0-SNAPSHOT"
	:description "Configuration for Smarthome Scenes"
	:url "https://github.com/lucas42/lucos_scenes"
	:dependencies [
		[org.clojure/clojure "1.11.1"]
		[ring/ring-jetty-adapter "1.12.2"]
		[ring/ring-json "0.5.1"]
		[org.clojure/tools.logging "1.3.0"]
		[org.slf4j/slf4j-simple "2.0.16"]
		[clj-http "3.13.0"]
		[selmer "1.12.61"]
	]
	:main ^:skip-aot lucos-scenes.core
	:target-path "target/%s"
	:uberjar-name "standalone.jar"
	:profiles {:uberjar {:aot :all
											 :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
