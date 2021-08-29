FROM adoptopenjdk/openjdk11

MAINTAINER Vladyslav Yemelianov <emelyanov.vladyslav@gmail.com>

ADD ./target/user-service.jar /app/
USER root
CMD ["java", "-Xmx512m", "-jar", "/app/user-service.jar"]

EXPOSE 9000