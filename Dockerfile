FROM openjdk:11
MAINTAINER Dongmin Kang <riyenas0925@gmail.com>

ARG JAR_FILE=build/libs/gajiquiz-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} gaji-quiz.jar

ENV DOCKERIZE_VERSION v0.2.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

ADD /scripts/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 8080