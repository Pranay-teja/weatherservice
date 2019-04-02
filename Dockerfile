FROM openjdk:8
ADD build/libs/weatherapp-0.0.1-SNAPSHOT.jar weatherapp-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "weatherapp-0.0.1-SNAPSHOT.jar"]