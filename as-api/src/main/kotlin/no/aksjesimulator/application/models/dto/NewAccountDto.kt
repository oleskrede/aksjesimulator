package no.aksjesimulator.application.models.dto

data class NewAccountDto(
    val name: String,
    val balance: Double = 0.0,
    val minCommissionFee: Float = 0f,
    val commissionFee: Float = 0f,
    val currencySpread: Float = 0f
)
