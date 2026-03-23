# Base image
FROM eclipse-temurin:21-jdk

# App directory
WORKDIR /app

# Copy project files
COPY . .

# Make gradlew executable
RUN chmod +x gradlew

# Build the project, skip tests
RUN ./gradlew build -x test

# Set environment variables for DB (Render automatically sets these if you use a DB)
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=""
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect

# Run the application
CMD ["java", "-jar", "build/libs/secure-library-management-api-0.0.1-SNAPSHOT.jar"]