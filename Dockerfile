# Etapa 1 — Build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

COPY src ./src
RUN mvn -q -e -B package -DskipTests

# Etapa 2 — Imagem Final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Cria o diretório de configuração externo
RUN mkdir -p /app/config

# Copia o JAR gerado
COPY --from=build /app/target/crm_backend-0.0.1-SNAPSHOT.jar app.jar


# Expõe a porta do Spring Boot
EXPOSE 8080

# Permite que o Spring carregue configs externas em /app/config
ENV SPRING_CONFIG_LOCATION=classpath:/,file:/app/config/

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
