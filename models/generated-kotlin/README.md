# org.openapitools.client - Kotlin client library for Aksjesimulator model

## Requires

* Kotlin 1.4.30
* Gradle 6.8.3

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**dummyGet**](docs/DefaultApi.md#dummyget) | **GET** /dummy | Because paths section is required?


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.Account](docs/Account.md)
 - [org.openapitools.client.models.AccountHolding](docs/AccountHolding.md)
 - [org.openapitools.client.models.HistoricalPrices](docs/HistoricalPrices.md)
 - [org.openapitools.client.models.HistoricalPricesDaily](docs/HistoricalPricesDaily.md)
 - [org.openapitools.client.models.HistoricalPricesMonthly](docs/HistoricalPricesMonthly.md)
 - [org.openapitools.client.models.HistoricalPricesWeekly](docs/HistoricalPricesWeekly.md)
 - [org.openapitools.client.models.StockPrice](docs/StockPrice.md)
 - [org.openapitools.client.models.Ticker](docs/Ticker.md)
 - [org.openapitools.client.models.Transaction](docs/Transaction.md)
 - [org.openapitools.client.models.User](docs/User.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
