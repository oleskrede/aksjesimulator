package no.aksjesimulator

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing

fun Route.priceRouting(priceRepository: PriceRepository) {
    get("/price") {
        call.respond(priceRepository.getAllPrices())
    }
    get("/price/{ticker}") {
        val ticker =
            call.parameters["ticker"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
        val price = priceRepository.getPrice(ticker) ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        call.respond(price)
    }
}

fun Application.registerPriceRoutes(priceRepository: PriceRepository) {
    routing {
        priceRouting(priceRepository)
    }
}