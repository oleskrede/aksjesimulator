#!/bin/bash

rm -rf ../as-api/src/main/kotlin/org/openapitools
openapi-generator-cli generate \
    -g kotlin \
    -i models.json \
    -o ../as-api \
    --global-property models
