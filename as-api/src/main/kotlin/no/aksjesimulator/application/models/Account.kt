package no.aksjesimulator.application.models

data class Account(
    val id: Int,
    var name: String,
    var balance: Double = 0.0,
    var minCommissionFee: Float = 0F,
    var commissionFee: Float = 0F,
    var currencySpread: Float = 0F,
    val transactions: MutableList<Transaction> = mutableListOf(),
    val holdings: MutableList<AccountHolding> = mutableListOf()
)
