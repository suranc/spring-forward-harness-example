FROM openjdk:11 AS builder
COPY pom.xml /app/pom.xml
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
WORKDIR /app

# build-arg to pass in FF API key, used by unit tests.  Can be invalid but test is more complete if valid.
ARG FF_API_KEY="your-api-key"
ENV FF_API_KEY=${FF_API_KEY}

RUN ./mvnw compile

COPY src /app/src
RUN ./mvnw install

FROM openjdk:11
COPY application.yml /app/application.yml
COPY --from=builder /app/target/spring-forward-0.0.1-SNAPSHOT.jar /app/spring-forward-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD java -jar /app/spring-forward-0.0.1-SNAPSHOT.jar