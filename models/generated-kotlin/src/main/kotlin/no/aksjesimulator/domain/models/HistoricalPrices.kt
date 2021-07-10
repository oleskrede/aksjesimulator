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

import no.aksjesimulator.domain.models.HistoricalPricesDaily
import no.aksjesimulator.domain.models.HistoricalPricesMonthly
import no.aksjesimulator.domain.models.HistoricalPricesWeekly

import com.squareup.moshi.Json

/**
 * 
 * @param ticker 
 * @param daily 
 * @param weekly 
 * @param monthly 
 */

data class HistoricalPrices (
    @Json(name = "ticker")
    val ticker: kotlin.String,
    @Json(name = "daily")
    val daily: kotlin.collections.List<HistoricalPricesDaily>,
    @Json(name = "weekly")
    val weekly: kotlin.collections.List<HistoricalPricesWeekly>,
    @Json(name = "monthly")
    val monthly: kotlin.collections.List<HistoricalPricesMonthly>
)
