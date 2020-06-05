FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 8082
ARG JAR_FILE=target/ProductDetails-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/product.jar"]
