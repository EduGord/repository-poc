FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /target/repository_poc.jar ./repository_poc.jar
EXPOSE 8080
CMD ["java", "-jar", "repository_poc.jar"]
