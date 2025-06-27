# ----------- STAGE 1: Build WAR with Maven -----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .

# Build WAR
RUN mvn clean package -DskipTests

# ----------- STAGE 2: Setup WildFly and deploy WAR -----------
FROM openjdk:17-slim
LABEL maintainer="gregorgottschewski"

# install needed components
RUN apt-get update
RUN apt-get install -y unzip curl sed
RUN rm -rf /var/lib/apt/lists/*

# change workdir for wildfly installation
WORKDIR /

# Download and unzip WildFly
RUN curl -L -o wildfly.zip https://github.com/wildfly/wildfly/releases/download/36.0.1.Final/wildfly-36.0.1.Final.zip
RUN unzip wildfly.zip
RUN rm wildfly.zip
RUN mv wildfly-36.0.1.Final wildfly

# change security settings to work properly
RUN sed -i '/urn:jboss:domain:undertow:/,/application-security-domain name="other"/ s|<application-security-domain name="other" security-domain="ApplicationDomain"|<application-security-domain name="other" security-domain="ApplicationDomain" integrated-jaspi="false"|' wildfly/standalone/configuration/standalone.xml

# Copy built WAR from previous stage
COPY --from=builder /app/target/*.war wildfly/standalone/deployments/

# start widlfly
WORKDIR /wildfly/bin

RUN chmod +x standalone.sh
CMD ["./standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
