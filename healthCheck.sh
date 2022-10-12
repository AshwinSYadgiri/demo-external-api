#!/usr/bin/env bash
#
# this file exists as piper requires smoke test for blue-green deployment plugin. No way to avoid.
#
# this is simply testing if the application root returns HTTP STATUS_CODE
curl -so /dev/null -w '%{response_code}' https://$1/actuator/health | grep $STATUS_CODE