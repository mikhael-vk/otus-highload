FROM maven:3.9.6-eclipse-temurin-21-jammy as build_package
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21.0.3_9-jdk-ubi9-minimal as build_layers
WORKDIR /app
COPY --from=build_package /app/target/social_network.jar .
RUN java -Djarmode=layertools -jar social_network.jar extract

FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal
WORKDIR /app
COPY opentelemetry-javaagent-2.4.0.jar /app/opentelemetry-javaagent.jar
COPY --from=build_layers app/dependencies/ ./
COPY --from=build_layers app/spring-boot-loader/ ./
COPY --from=build_layers app/snapshot-dependencies/ ./
COPY --from=build_layers app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
