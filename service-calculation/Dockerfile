FROM azul/zulu-openjdk:11
COPY "./calculation-service-application/target/calculator-service-application-1.0.0.jar" "calculator-service-application-1.0.0.jar"
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","calculator-service-application-1.0.0.jar"]