FROM azul/zulu-openjdk:11
COPY "./target/architecture-api-gateway-1.0.0.jar" "architecture-api-gateway-1.0.0.jar"
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","architecture-api-gateway-1.0.0.jar"]