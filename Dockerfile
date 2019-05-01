FROM openjdk:11-jre-slim

LABEL maintainer = "Maicon Mattos"

HEALTHCHECK --interval=30s --timeout=3s --retries=10 CMD </dev/tcp/localhost/8080

WORKDIR /app

ARG JAR_FILE
ADD target/${JAR_FILE} sample-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sample-service.jar"]