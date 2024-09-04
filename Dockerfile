FROM clojure:temurin-22-lein-2.11.2-bookworm-slim
COPY project.clj /usr/src/app/
COPY src /usr/src/app/src
WORKDIR /usr/src/app
CMD ["lein", "run"]
