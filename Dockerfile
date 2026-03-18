# ============================================================
# Stage 1 — Build : compile et package avec Maven + JDK
# ============================================================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /src

# Copier d'abord le pom.xml pour profiter du cache des dépendances
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -B 2>/dev/null || true

# Copier les sources et lancer le build
COPY src ./src
RUN mvn package -DskipTests -B

# ============================================================
# Stage 2 — Runtime : image légère avec JRE seul
# ============================================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copier le JAR depuis le stage de build (wildcard pour gérer -SNAPSHOT)
COPY --from=build /src/target/tp_devops-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
