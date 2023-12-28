FROM maven:3.9.5-eclipse-temurin-21 as build
WORKDIR /build
COPY . .
RUN mvn -B package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar
HEALTHCHECK --interval=5s --timeout=5s CMD curl -f http://localhost:8080/actuator/health || exit 1
EXPOSE 8080
ENTRYPOINT java -jar app.jar