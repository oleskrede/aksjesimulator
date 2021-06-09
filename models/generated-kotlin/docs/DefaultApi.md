# DefaultApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**dummyGet**](DefaultApi.md#dummyGet) | **GET** /dummy | Because paths section is required?


<a name="dummyGet"></a>
# **dummyGet**
> dummyGet()

Because paths section is required?

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
try {
    apiInstance.dummyGet()
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#dummyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#dummyGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

