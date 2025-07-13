FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y nginx
COPY src/*.java .
COPY frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/sites-available/default
RUN javac *.java
EXPOSE 80
EXPOSE 8080
CMD java -cp /app Main & sleep 5 && nginx -g 'daemon off;'