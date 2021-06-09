package no.aksjesimulator.interfaces.mustache

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

val emptyMap = emptyMap<String, Any>()

fun Route.mustacheHomeRouting() {
    route("/") {
        get {
            call.respond(MustacheContent("index.hbs", emptyMap))
        }
    }
}

fun Application.registerMustacheHomeRoutes() {
    routing {
        mustacheHomeRouting()
    }
}

data class NewUserDto(
    val username: String,
    val password: String,
    val email: String? = null
)
