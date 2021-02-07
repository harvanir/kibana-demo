FROM azul/zulu-openjdk-alpine:11.0.8-11.41.23-jre-headless as builder
WORKDIR application
ARG JAR_FILE=app/java/spring-boot-web/target/*.jar
COPY ${JAR_FILE} app.jar
COPY apm/elastic-apm-agent-1.20.0.jar dependencies/elastic-apm-agent-1.20.0.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM azul/zulu-openjdk-alpine:11.0.8-11.41.23-jre-headless
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
ENV JVM_OPT " "
ENTRYPOINT ["sh", "-c", "java -server $JVM_OPT org.springframework.boot.loader.JarLauncher"]