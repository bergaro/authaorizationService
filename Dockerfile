FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/authService-0.0.1.jar authService.jar
ENTRYPOINT ["java", "-jar", "authService.jar"]