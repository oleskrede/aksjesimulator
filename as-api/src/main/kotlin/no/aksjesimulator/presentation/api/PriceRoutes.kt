package no.aksjesimulator

import io.ktor.application.Application
import io.ktor.routing.Route

fun Route.priceRouting() {
//    get("/price") {
//        call.respond(priceRepository.getAllPrices())
//    }
//    get("/price/{ticker}") {
//        val ticker =
//            call.parameters["ticker"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
//        val price = priceRepository.getPrice(ticker) ?: return@get call.respondText(
//            "Not Found",
//            status = HttpStatusCode.NotFound
//        )
//        call.respond(price)
//    }
}

fun Application.registerPriceRoutes() {
//    routing {
//        priceRouting(priceRepository)
//    }
}
