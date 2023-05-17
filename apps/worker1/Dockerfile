FROM sbtscala/scala-sbt:openjdk-11.0.16_1.8.1_3.2.1 as builder
WORKDIR /app
COPY . /app
RUN sbt "; project worker1; stage"

FROM openjdk:11-slim
WORKDIR /app
EXPOSE 8090
COPY --from=builder /app/apps/worker1/target/universal/ .
CMD ./stage/bin/worker1
