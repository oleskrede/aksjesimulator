package no.aksjesimulator

import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class PriceRoutesTest {

    @Test
    fun testGetPrice() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/price/TEL").apply {
                assertEquals(
                    """{"timestamp":{"dateTime":{"date":{"year":2021,"month":6,"day":7},"time":{"hour":15,"minute":30,"second":0,"nano":0}},"offset":{"totalSeconds":0}},"price":1435500,"ticker":"TEL"}""",
                    response.content
                )
            }
        }
    }
}
