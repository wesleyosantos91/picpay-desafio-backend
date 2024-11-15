FROM openjdk:21-jdk-oracle
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx512m", "-XX:+PrintFlagsFinal", "-XX:+UseZGC", "-XX:MinRAMPercentage=60", "-XX:MaxRAMPercentage=60", "/app.jar"]