FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
ENV PORT 8080
EXPOSE 8080
COPY ./target/bank-backend-0.0.1-SNAPSHOT-jar-with-dependencies.jar bank.jar
ENTRYPOINT ["java", "-jar", "/bank.jar"]