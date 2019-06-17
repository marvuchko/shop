FROM openjdk:8-jdk-alpine
COPY /application/target/*.jar /app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]