FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 9411
ARG JAR_FILE=target/zipkin-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} zipkin-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/zipkin-server.jar"]
