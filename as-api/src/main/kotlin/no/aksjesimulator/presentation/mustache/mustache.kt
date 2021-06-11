package no.aksjesimulator.presentation.mustache

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

private val emptyMap = emptyMap<String, Any>()

fun Route.mustacheRouting() {
    route("/simple") {
        get {
            call.respond(MustacheContent("login.hbs", emptyMap))
        }
    }

    route("/simple/login") {
        get {
            call.respond(MustacheContent("login.hbs", emptyMap))
        }
    }
}

fun Application.registerMustacheHomeRoutes() {
    routing {
        mustacheRouting()
    }
}
