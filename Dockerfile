FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/secure-library-management-api-0.0.1-SNAPSHOT.jar"]