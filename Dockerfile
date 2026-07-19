# STAGE 1
FROM gradle:8.14-jdk21 AS builder
WORKDIR /app

COPY ./build.gradle .
COPY ./settings.gradle .

COPY src ./src

RUN gradle build --no-daemon

# STAGE 2
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

COPY --from=builder /app/build/libs/discografia-1.war discografia-1.war

EXPOSE 443

CMD ["java", "-jar", "discografia-1.war"]