FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /target/demo.jar ./demo.jar
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]
