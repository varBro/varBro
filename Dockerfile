FROM openjdk:11
ADD target/varBro.jar varBro.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "varBro.jar"]
