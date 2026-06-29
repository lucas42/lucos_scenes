FROM lucas42/lucos_navbar:2.3.2 AS navbar

FROM clojure:temurin-26-lein-2.13.0-bookworm-slim AS build
WORKDIR /usr/src/app
COPY project.clj .
COPY src src
COPY resources resources
COPY --from=navbar lucos_navbar.js resources/public/

RUN lein uberjar

FROM eclipse-temurin:25-jdk-alpine
ARG VERSION
ENV VERSION=$VERSION
COPY --from=build /usr/src/app/target/uberjar/standalone.jar standalone.jar
CMD ["java", "-jar", "standalone.jar"]