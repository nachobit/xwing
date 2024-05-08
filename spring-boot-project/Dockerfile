FROM openjdk:17-jdk-slim
COPY target/spring-boot-project-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]