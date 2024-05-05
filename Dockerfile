# Use a Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven configuration file
COPY pom.xml .

# Copy the entire project
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use an OpenJDK base image
FROM maven:3.8.4-openjdk-17

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot application JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that your Spring Boot application uses
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
