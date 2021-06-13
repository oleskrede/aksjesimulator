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

import no.aksjesimulator.domain.models.AccountHolding
import no.aksjesimulator.domain.models.Transaction

import com.squareup.moshi.Json

/**
 * 
 * @param id 
 * @param name 
 * @param balance 
 * @param transactions 
 * @param holdings 
 * @param commissionFeeMinimum Minimum treshold for commissionFee. In NOK.
 * @param commissionFee Percentage of each trade
 * @param currencySpread Percentage of each trade
 */

data class Account (
    @Json(name = "id")
    val id: kotlin.Int,
    @Json(name = "name")
    val name: kotlin.String,
    @Json(name = "balance")
    val balance: kotlin.Double,
    @Json(name = "transactions")
    val transactions: kotlin.collections.List<Transaction>,
    @Json(name = "holdings")
    val holdings: kotlin.collections.List<AccountHolding>,
    /* Minimum treshold for commissionFee. In NOK. */
    @Json(name = "commissionFeeMinimum")
    val commissionFeeMinimum: kotlin.Float? = null,
    /* Percentage of each trade */
    @Json(name = "commissionFee")
    val commissionFee: kotlin.Float? = null,
    /* Percentage of each trade */
    @Json(name = "currencySpread")
    val currencySpread: kotlin.Float? = null
)

