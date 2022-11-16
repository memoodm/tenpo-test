FROM azul/zulu-openjdk:11
COPY "./target/architecture-registry-eureka-1.0.0.jar" "architecture-registry-eureka-1.0.0.jar"
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","architecture-registry-eureka-1.0.0.jar"]