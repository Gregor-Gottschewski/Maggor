FROM openjdk:17
LABEL authors="gregorgottschewski"

WORKDIR /

COPY ../wildfly-35.0.1.Final wildfly
COPY maggor-0.0.1.war wildfly/standalone/deployments/maggor-0.0.1.war

WORKDIR /wildfly/bin

RUN chmod +x standalone.sh
CMD ["./standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
