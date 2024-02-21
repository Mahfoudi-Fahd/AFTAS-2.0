#Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /workspace
COPY . .
RUN mvn clean package -DskipTests

#Stage 2: Run the application
FROM openjdk:17-jdk-alpine3.13
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]