FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 8086
ARG JAR_FILE=target/ZuulService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} zuul-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/zuul-server.jar"]
