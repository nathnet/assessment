# stage-1 build artifact
FROM amazoncorretto:17.0.10-alpine3.19 as build
WORKDIR /app
ADD . .
RUN ["./gradlew", "bootJar"]

# stage-2 run application
FROM gcr.io/distroless/java17-debian12:latest
WORKDIR /app
COPY --from=build /app .
ENTRYPOINT [ "java", "-jar", "build/libs/lottery-application-0.0.1-SNAPSHOT.jar" ]