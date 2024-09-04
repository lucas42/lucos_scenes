FROM clojure:temurin-22-lein-2.11.2-bookworm-slim AS build
COPY project.clj /usr/src/app/
COPY src /usr/src/app/src
WORKDIR /usr/src/app

RUN lein uberjar

FROM eclipse-temurin:22-jdk-alpine
COPY --from=build /usr/src/app/target/uberjar/standalone.jar standalone.jar
CMD ["java", "-jar", "standalone.jar"]