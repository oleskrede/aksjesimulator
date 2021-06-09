package no.aksjesimulator.interfaces.repository

import org.openapitools.client.models.StockPrice
import java.time.OffsetDateTime
import java.time.ZoneOffset

class PriceRepository {

    private val prices = mutableMapOf<String, StockPrice>()

    init {
        val now = OffsetDateTime.of(2021, 6, 7, 15, 30, 0, 0, ZoneOffset.UTC)
        val tel = StockPrice(now, 1435500, "TEL")
        val nhy = StockPrice(now, 539400, "NHY")
        val dnb = StockPrice(now, 1886000, "DNB")
        val sbank = StockPrice(now, 1078000, "SBANK")
        prices[tel.ticker] = tel
        prices[nhy.ticker] = nhy
        prices[dnb.ticker] = dnb
        prices[sbank.ticker] = sbank
    }

    fun getAllPrices(): Set<StockPrice> {
        return prices.values.toSet()
    }

    fun getPrice(ticker: String): StockPrice? {
        return prices[ticker]
    }
}
