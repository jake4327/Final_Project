
# pulling official image of Java
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD target/sfia3.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# pulling offical image of Nexus 
#FROM sonatype/nexus3
#LABEL maintainer="rpscdevelopments@gmail.com"
# copy configuration file to docker container
#COPY /Nexus/nexus.properties nexus-data/etc/nexus.properties


FROM maven:latest AS build-stage
COPY . /build
WORKDIR /build
RUN mvn clean package

FROM java:8 AS runtime
WORKDIR /opt/fit
COPY --from=build-stage /build/target/*.jar app.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]

