FROM amazoncorretto:21
LABEL authors="marchesinPOS"
WORKDIR app
COPY target/cars-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]