FROM openjdk:8-jdk-alpine
VOLUME /shop-app
ADD /application/target/*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]