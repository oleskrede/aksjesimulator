/**
* Aksjesimulator model
* All prices are in 1/10000 NOK. I.e. a price of 10 000 = 1 NOK
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package no.aksjesimulator.domain.models

import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.AccountHolding
import no.aksjesimulator.domain.models.AccountVMAllOf
import no.aksjesimulator.domain.models.Transaction

import com.squareup.moshi.Json

/**
 * 
 * @param id 
 * @param name 
 * @param balance 
 * @param commissionFlat 
 * @param commissionPercentage 
 * @param currencyFeePercentage 
 * @param transactions 
 * @param holdings 
 * @param nokBalance 
 */

data class AccountVM (
    @Json(name = "id")
    val id: kotlin.Int,
    @Json(name = "name")
    val name: kotlin.String,
    @Json(name = "balance")
    val balance: kotlin.Long,
    @Json(name = "commissionFlat")
    val commissionFlat: kotlin.Int,
    @Json(name = "commissionPercentage")
    val commissionPercentage: kotlin.Int,
    @Json(name = "currencyFeePercentage")
    val currencyFeePercentage: kotlin.Int,
    @Json(name = "transactions")
    val transactions: kotlin.collections.List<Transaction>,
    @Json(name = "holdings")
    val holdings: kotlin.collections.List<AccountHolding>,
    @Json(name = "nokBalance")
    val nokBalance: kotlin.Int? = null
)

