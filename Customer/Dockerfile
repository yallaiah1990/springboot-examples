FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 8085
ARG JAR_FILE=target/Customer*.jar
COPY ${JAR_FILE} customer.jar/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/customer.jar"]