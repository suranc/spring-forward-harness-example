FROM openjdk:11 AS builder
COPY pom.xml /app/pom.xml
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
WORKDIR /app
RUN ./mvnw compile

COPY src /app/src
RUN ./mvnw install

FROM openjdk:11
COPY --from=builder /app/target/spring-forward-0.0.1-SNAPSHOT.jar /app/spring-forward-0.0.1-SNAPSHOT.jar
CMD java -jar /app/spring-forward-0.0.1-SNAPSHOT.jar