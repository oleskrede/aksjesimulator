package no.aksjesimulator.application.models

data class Stock(
    val ticker: String,
    val name: String,
    var price: Double
)
