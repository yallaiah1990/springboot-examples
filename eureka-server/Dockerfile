FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 9091
ARG JAR_FILE=target/OrderEurekaServer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server.jar"]
