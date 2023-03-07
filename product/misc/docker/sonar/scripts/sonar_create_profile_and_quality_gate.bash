#!/usr/bin/env bash

SONARURL="http://localhost:9000/api"

wait_sonarqube_up() {
  sonar_status="DOWN"
  printf "INFO initiating connection with SonarQube.\n"
  apk add curl
  apk add jq
  sleep 15
  while [ "${sonar_status}" != "UP" ]; do
    sleep 5
    printf "INFO retrieving SonarQube's service status.\n"
    sonar_status=$(curl -s -X GET "localhost:9000/api/system/status" | jq -r '.status')
    printf "INFO SonarQube is ${sonar_status}, expecting it to be UP.\n"
  done
  curl -u admin:admin -X POST "${SONARURL}/users/change_password?login=admin&previousPassword=admin&password=dev!"
  printf "INFO SonarQube is ${sonar_status}."
}

add_condition_to_quality_gate() {
  gate_id=$1
  metric_key=$2
  metric_operator=$3
  metric_errors=$4

  printf "INFO adding quality gate condition: ${metric_key} ${metric_operator} ${metric_errors}.\n"

  threshold=()
  if [ "${metric_errors}" != "none" ]; then
    threshold=("--data-urlencode" "error=${metric_errors}")
  fi

  res=$(curl -su "admin:dev!" \
    --data-urlencode "gateId=${gate_id}" \
    --data-urlencode "metric=${metric_key}" \
    --data-urlencode "op=${metric_operator}" \
    "${threshold[@]}" \
    "${SONARURL}/qualitygates/create_condition")
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    printf "INFO metric ${metric_key} condition successfully added.\n"
  else
    printf "WARNING impossible to add ${metric_key} condition $(echo "${res}" | jq '.errors[].msg')\n"
  fi
}

create_quality_gate() {
  printf "INFO creating quality gate.\n"
  res=$(curl -su "admin:dev!" \
    --data-urlencode "name=SpringCloudNetflix" \
    "${SONARURL}/qualitygates/create")
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    printf "INFO successfully created quality gate... now configuring it.\n"
  else
    printf "WARNING impossible to create quality gate $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  # Retrieve quality gates ID
  printf "INFO retrieving quality gate ID."
  res=$(curl --request GET -G -su "admin:dev!" \
    --data-urlencode "name=SpringCloudNetflix" \
    "${SONARURL}/qualitygates/show")
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    GATEID=$(echo ${res} | jq '.id' | tail -c +2 | head -c -2)
    printf "INFO successfully retrieved quality gate ID (ID=$GATEID).\n"
  else
    printf "ERROR impossible to reach quality gate ID $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  ## Setting it as default quality gate
  printf "INFO setting quality gate as default gate.\n"
  printf ${GATEID}
  res=$(curl -su "admin:dev!" \
    --data-urlencode "id=${GATEID}" \
    "${SONARURL}/qualitygates/set_as_default")
  if [ -z "$res" ]; then
    printf "INFO successfully set quality gate as default gate.\n"
  else
    printf "WARNING impossible to set quality gate as default gate $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  ## Adding all conditions of the JSON file
  printf "INFO adding all conditions of cnes-quality-gate.json to the gate.\n"
  len=$(jq '(.conditions | length)' ./scripts/custom_quality_gate.json)
  quality_gate=$(jq '(.conditions)' ./scripts/custom_quality_gate.json)
  for i in $(seq 0 $((len - 1))); do
    metric=$(echo "$quality_gate" | jq -r '(.['"$i"'].metric)')
    op=$(echo "$quality_gate" | jq -r '(.['"$i"'].op)')
    error=$(echo "$quality_gate" | jq -r '(.['"$i"'].error)')
    add_condition_to_quality_gate "$GATEID" "$metric" "$op" "$error"
  done
}

create_quality_profile() {
  printf "INFO creating quality profile.\n"
  res=$(curl --location --request POST -u "admin:dev!" \
    --data-urlencode "language=java" \
    --data-urlencode "name=Sonar_way_and_Mutation" \
    "${SONARURL}/qualityprofiles/create")
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    printf "INFO successfully created quality profile... now configuring it.\n"
  else
    printf "WARNING impossible to create quality profile $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  printf "INFO change parent of quality profile Sonar_way_and_Mutation to Sonar way.\n"
  res=$(curl --location --request POST -u "admin:dev!" -w "%{http_code}\n" \
    --data-urlencode "language=java" \
    --data-urlencode "parentQualityProfile=Sonar way" \
    --data-urlencode "qualityProfile=Sonar_way_and_Mutation" \
    "${SONARURL}/qualityprofiles/change_parent")
  if [ "$(echo "${res}")" == "204" ]; then
    printf "INFO successfully change parent of quality profile Sonar_way_and_Mutation to Sonar way.\n"
  else
    printf "WARNING impossible change parent of quality profile Sonar_way_and_Mutation to Sonar way $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  printf "INFO set Sonar_way_and_Mutation as default quality profile.\n"
  res=$(curl --location --request POST -u "admin:dev!" -w "%{http_code}\n" \
    --data-urlencode "language=java" \
    --data-urlencode "qualityProfile=Sonar_way_and_Mutation" \
    "${SONARURL}/qualityprofiles/set_default")
  if [ "$(echo "${res}")" == "204" ]; then
    printf "INFO successfully set Sonar_way_and_Mutation as default quality profile.\n"
  else
    printf "WARNING impossible set Sonar_way_and_Mutation as default quality profile $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  printf "INFO retrieving target quality profile key."
  res=$(curl --location --request GET -G -u "admin:dev!" \
    --data-urlencode "defaults=true" \
    --data-urlencode "language=java" \
    --data-urlencode "qualityProfile=Sonar_way_and_Mutation" \
    "${SONARURL}/qualityprofiles/search")
  printf ${res}
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    TARGETKEY=$(echo ${res} | jq '.profiles[0].key' | tail -c +2 | head -c -2)
    printf "INFO successfully retrieved target quality profile key (KEY=$TARGETKEY).\n"
  else
    printf "ERROR impossible to reach target quality profile key $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  printf "INFO retrieving source quality profile key."
  res=$(curl --location --request GET -G -u "admin:dev!" \
    --data-urlencode "language=java" \
    --data-urlencode "qualityProfile=Mutation Analysis" \
    "${SONARURL}/qualityprofiles/search")
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    SOURCEKEY=$(echo ${res} | jq '.profiles[0].key' | tail -c +2 | head -c -2)
    printf "INFO successfully retrieved source quality profile key (KEY=$SOURCEKEY).\n"
  else
    printf "ERROR impossible to reach source quality profile key $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi

  printf "INFO active rules of Mutation Analysis on Sonar_way_and_Mutation profile.\n"
  res=$(curl -v --location --request POST -u "admin:dev!" \
    --data-urlencode "language=java" \
    --data-urlencode "tags=mutation-operator" \
    --data-urlencode "qprofile=${SOURCEKEY}" \
    --data-urlencode "targetKey=${TARGETKEY}" \
    "${SONARURL}/qualityprofiles/activate_rules")
  if [ "$(echo "${res}" | jq '(.errors | length)')" == "0" ]; then
    printf "INFO successfully active rules of Mutation Analysis on Sonar_way_and_Mutation profile.\n"
  else
    printf "WARNING impossible active rules of Mutation Analysis on Sonar_way_and_Mutation profile $(echo "${res}" | jq '.errors[].msg')\n"
    return
  fi
}

wait_sonarqube_up
create_quality_profile
create_quality_gate
