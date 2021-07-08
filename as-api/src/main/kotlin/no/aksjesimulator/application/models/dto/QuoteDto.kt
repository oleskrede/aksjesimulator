package no.aksjesimulator.application.models.dto

import kotlin.random.Random

// E.g.:
// {"ticker": "2020", "exchange": "OSE", "name": "2020 BULKERS", "price": "111,00", "timestamp": 1625229161}
data class QuoteDto(
    val ticker: String,
    val exchange: String,
    val name: String,
    val price: String,
    val timestamp: Long
)

data class Quote(
    val ticker: String,
    val exchange: String,
    val name: String,
    val price: Double,
    val timestamp: Long
)

fun QuoteDto.toQuote(): Quote {
    return Quote(
        ticker,
        exchange,
        name,
        price.replace(',', '.').toDouble() + Random.nextDouble(-0.5, 1.0),
        timestamp
    )
}