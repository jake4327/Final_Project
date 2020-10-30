# pulling official image of Java
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD target/sfia3.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# pulling offical image of Nexus 
FROM sonatype/nexus3
LABEL maintainer="rpscdevelopments@gmail.com"
# copy configuration file to docker container
COPY /Nexus/nexus.properties nexus-data/etc/nexus.properties