FROM alpine:latest
WORKDIR /root/
RUN apk update && apk add ca-certificates wget && update-ca-certificates && apk add curl

RUN LATEST_RELEASE=$(curl -L -s -H 'Accept: application/json' https://github.com/devcon5io/mutation-analysis-plugin/releases/latest); \
    LATEST_VERSION=$(echo $LATEST_RELEASE | sed -e 's/.*"tag_name":"\([^"]*\)".*/\1/'); \
    ARTIFACT_URL="https://github.com/devcon5io/mutation-analysis-plugin/releases/download/$LATEST_VERSION/mutation-analysis-plugin-${LATEST_VERSION/v/""}.jar"; \
    wget -O mutation-analysis-plugin.jar "$ARTIFACT_URL" -P ./;

FROM sonarqube:9.8.0-community
COPY --from=0 /root/mutation-analysis-plugin.jar /opt/sonarqube/extensions/plugins
