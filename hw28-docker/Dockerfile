FROM oracle/openjdk:8
VOLUME /tmp
COPY target/hw28-docker-0.0.1-SNAPSHOT.jar /opt/hw28-docker-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
EXPOSE 8080
CMD ["bash", "-c", "java -jar -jar -Dspring.profiles.active=docker /opt/hw28-docker-0.0.1-SNAPSHOT.jar"]