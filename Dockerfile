FROM openjdk:17-jdk-slim

WORKDIR /app

COPY src/ ./src/

RUN javac src/*.java

EXPOSE 8080

CMD ["java", "-cp", "src", "Main"]