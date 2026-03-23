# Use Eclipse Temurin 21 JDK
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy everything to container
COPY . .

# Make gradlew executable
RUN chmod +x gradlew

# Build the app without running tests
RUN ./gradlew build -x test

# Expose the port your Spring Boot app runs on (optional, default 8080)
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "build/libs/secure-library-management-api-0.0.1-SNAPSHOT.jar"]