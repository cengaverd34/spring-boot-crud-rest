FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/spring-boot-crud-rest.jar spring-boot-crud-rest.jar
ENTRYPOINT ["java","-jar","/app.jar"]

