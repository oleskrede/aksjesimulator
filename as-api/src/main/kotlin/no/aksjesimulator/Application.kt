package no.aksjesimulator

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.mustache.Mustache
import io.ktor.routing.routing
import no.aksjesimulator.interfaces.mustache.registerMustacheHomeRoutes

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson()
    }
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }

    // registerUserRoutes(UserRepository())
    // registerPriceRoutes(PriceRepository())

    routing {
        static("/static") {
            resources("files")
        }
    }
    registerMustacheHomeRoutes()
}
