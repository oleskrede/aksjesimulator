
# Account

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.Int** |  | 
**name** | **kotlin.String** |  | 
**balance** | **kotlin.Double** |  | 
**transactions** | [**kotlin.collections.List&lt;Transaction&gt;**](Transaction.md) |  | 
**holdings** | [**kotlin.collections.List&lt;AccountHolding&gt;**](AccountHolding.md) |  | 
**commissionFeeMinimum** | **kotlin.Float** | Minimum treshold for commissionFee. In NOK. |  [optional]
**commissionFee** | **kotlin.Float** | Percentage of each trade |  [optional]
**currencySpread** | **kotlin.Float** | Percentage of each trade |  [optional]



