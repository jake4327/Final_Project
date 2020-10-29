FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/sfia3.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]