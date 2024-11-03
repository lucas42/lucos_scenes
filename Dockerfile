FROM lucas42/lucos_navbar:latest AS navbar

FROM clojure:temurin-22-lein-2.11.2-bookworm-slim AS build
WORKDIR /usr/src/app
COPY project.clj .
COPY src src
COPY resources resources
COPY --from=navbar lucos_navbar.js resources/public/

RUN lein uberjar

FROM eclipse-temurin:22-jdk-alpine
COPY --from=build /usr/src/app/target/uberjar/standalone.jar standalone.jar
CMD ["java", "-jar", "standalone.jar"]