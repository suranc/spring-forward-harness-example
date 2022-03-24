FROM openjdk:11
COPY pom.xml /app/pom.xml
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
WORKDIR /app
RUN ./mvnw compile

COPY src /app/src
RUN ./mvnw install
