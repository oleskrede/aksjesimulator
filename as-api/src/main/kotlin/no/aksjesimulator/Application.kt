package no.aksjesimulator

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson()
    }
    registerUserRoutes(UserRepository())
    registerPriceRoutes(PriceRepository())
}
