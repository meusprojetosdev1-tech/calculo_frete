FROM maven:3.8.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/calculo-frete-1.0.0.jar app.jar
EXPOSE 10000
ENTRYPOINT ["sh", "-c", "java -Xmx256m -Dserver.port=${PORT:-10000} -Dserver.address=0.0.0.0 -jar app.jar"]
