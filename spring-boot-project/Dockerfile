FROM openjdk:11-jre-slim
COPY target/spring-boot-project.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]