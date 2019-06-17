FROM openjdk:8-jdk-alpine
WORKDIR /shopp-app
COPY /application/target/*.jar /shop-app/app.jar
RUN bash -c 'cd /shop-app'
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]