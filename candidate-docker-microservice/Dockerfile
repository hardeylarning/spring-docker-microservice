#FROM maven:3.8.6-eclipse-temurin-17 AS consume
#
#WORKDIR build
#
#COPY src src
#COPY pom.xml pom.xml
#
#RUN mvn clean package



FROM bellsoft/liberica-openjdk-alpine:17.0.4.1-1

WORKDIR /usr/share/app

#COPY --from=consume /build/target/*.jar app.jar
COPY target/*.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]
