# Primeiro estágio: Build
FROM maven:3.8.4-openjdk-17-slim AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e o diretório src para o container
COPY . .
# Execute o comando Maven para construir o arquivo .jar
RUN mvn clean package -DskipTests

# Segundo estágio: Execução
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo .jar gerado no estágio de build para o estágio de execução
COPY --from=build /app/infrastructure/target/api-seletivo.jar seletivo.jar

# Exponha a porta que o Spring Boot está ouvindo
EXPOSE 8083

# Comando para executar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/app/seletivo.jar"]
