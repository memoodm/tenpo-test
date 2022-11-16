FROM azul/zulu-openjdk:11
COPY "./history-service-application/target/history-service-application-1.0.0.jar" "history-service-application-1.0.0.jar"
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","history-service-application-1.0.0.jar"]