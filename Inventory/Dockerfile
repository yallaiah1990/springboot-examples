FROM maven:3.5.4-jdk-8
LABEL maintainer="yallaiahm@gmail.com"
EXPOSE 8083
ARG JAR_FILE=target/InventoryDetails-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} inventory.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/inventory.jar"]
