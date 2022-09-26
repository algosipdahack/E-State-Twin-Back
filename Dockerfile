FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.config.location=classpath:/application.yml,classpath:/application-s3.properties,classpath:/application-oauth.yml,classpath:/application-oauth.properties","-Dspring.profiles.active=prod","/app.jar"]
