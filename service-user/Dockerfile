FROM azul/zulu-openjdk:11
COPY "./user-service-application/target/user-service-application-1.0.0.jar" "user-service-application-1.0.0.jar"
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","user-service-application-1.0.0.jar"]