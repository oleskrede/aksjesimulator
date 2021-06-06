# Aksjesimulator models

Specified with OpenAPI

## Usage

### Install 

Install the [OpenAPI Generator CLI](https://openapi-generator.tech/docs/installation/#bash-launcher-script)

### Generate models

```
rm -rf generated/kotlin/src/ && \
openapi-generator-cli generate \
    -g kotlin \
    -i models.json \
    -o ../as-api/src/main/kotlin \
    --global-property models
```