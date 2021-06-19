package no.aksjesimulator.application.models

data class AccountHolding(
    val ticker: Ticker,
    var amount: Long, // Number of stocks
    var gav: Double // Average price paid per stock, including fees
)
