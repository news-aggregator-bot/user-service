FROM adoptopenjdk/openjdk11

MAINTAINER Vladyslav Yemelianov <emelyanov.vladyslav@gmail.com>

ADD ./target/user-service.jar /app/
USER root
CMD ["java", "-Xmx500m", "-jar", "/app/user-service.jar"]

EXPOSE 9050