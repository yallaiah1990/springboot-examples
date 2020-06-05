FROM maven:3.5.4-jdk-8

LABEL maintainer="subbareddygangala@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

EXPOSE 8081

COPY target/employee-management-*.jar employee_management.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/employee_management.jar"]
