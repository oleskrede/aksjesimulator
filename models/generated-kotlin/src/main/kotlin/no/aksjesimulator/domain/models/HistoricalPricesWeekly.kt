/**
* Aksjesimulator model
* No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package no.aksjesimulator.domain.models


import com.squareup.moshi.Json

/**
 * 
 * @param price 
 * @param week Date of wednesday in the week
 */

data class HistoricalPricesWeekly (
    @Json(name = "price")
    val price: kotlin.Double,
    /* Date of wednesday in the week */
    @Json(name = "week")
    val week: java.time.LocalDate
)
