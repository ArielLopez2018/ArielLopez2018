#FROM openjdk:11-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]