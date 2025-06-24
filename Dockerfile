# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /home/app

# Copiar solo el pom.xml para cachear las dependencias
COPY pom.xml ./

# Descargar las dependencias sin construir aún (acelera rebuilds si no cambian deps)
RUN mvn dependency:go-offline

# Copiar el código fuente completo
COPY src ./src

# Construir el proyecto, saltando tests para acelerar
RUN mvn clean package -DskipTests

# Stage 2: Imagen final
FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8000

# Copiar el JAR generado del stage build
COPY --from=build /home/app/target/*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]