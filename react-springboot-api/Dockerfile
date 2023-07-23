#FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
#VOLUME /tmp
#RUN apt-get update; apt-get install -y fontconfig libfreetype6
#ARG JAVA_OPTS
#ENV JAVA_OPTS=$JAVA_OPTS
#COPY target/react-springboot-1.0.3-RELEASE emp-managent-service.jar
#EXPOSE 9002
#ENTRYPOINT exec java $JAVA_OPTS -jar authservice.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar authservice.jar

#FROM maven:3.8.3-openjdk-17 AS build
#COPY ./src /usr/src/app/src
#COPY ./pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
VOLUME /tmp
COPY target/react-springboot-1.0.3-RELEASE.jar emp-management-service.jar
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8080
ENTRYPOINT ["java","-jar","emp-management-service.jar"]