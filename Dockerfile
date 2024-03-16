# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY resources ./resources
RUN mvn -B clean package -Dmaven.test.skip=true

# Stage 2: Create the final image with the compiled application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/resources ./resources
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]