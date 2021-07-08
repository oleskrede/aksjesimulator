package no.aksjesimulator.application.models

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Transaction(
    val ticker: String,
    val amount: Long, // number of stocks
    val price: Double, // price per stock, excluding fees
    val sumOfFees: Double,
    val timestamp: LocalDateTime = now()
)
