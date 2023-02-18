#!/usr/bin/env bash

# This file is the entrypoint of the image
# It must be executed as an unprivileged user

# Fail on error
exec ./scripts/sonar_create_profile_and_quality_gate.bash &
exec ./bin/run.sh
