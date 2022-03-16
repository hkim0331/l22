FROM openjdk:8-alpine

COPY target/uberjar/l22.jar /l22/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/l22/app.jar"]
