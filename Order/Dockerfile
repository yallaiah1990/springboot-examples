FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 8081
ARG JAR_FILE=target/OrderDetails-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} order.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/order.jar"]
