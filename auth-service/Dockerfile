FROM eclipse-temurin:17-jre
ARG JAR_FILE=target/auth-service-*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
