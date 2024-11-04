FROM openjdk:17

WORKDIR /app

RUN mkdir database

COPY target/db-docker-web-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]