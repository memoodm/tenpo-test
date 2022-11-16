FROM azul/zulu-openjdk:11
COPY "./mock-service-application/target/mock-service-application-1.0.0.jar" "mock-service-application-1.0.0.jar"
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","mock-service-application-1.0.0.jar"]