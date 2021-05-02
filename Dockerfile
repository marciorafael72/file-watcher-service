FROM openjdk:11.0.11-jre

MAINTAINER Márcio Alves Rafael <marciorafael72@gmail.com>

ADD build/libs/file-watcher-service-0.0.1-SNAPSHOT.jar /

ENTRYPOINT java -jar /file-watcher-service-0.0.1-SNAPSHOT.jar