package no.aksjesimulator.presentation.mustache

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.Principal
import io.ktor.auth.authenticate
import io.ktor.auth.form
import io.ktor.auth.principal
import io.ktor.auth.session
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import no.aksjesimulator.application.Aksjesimulator
import no.aksjesimulator.application.Login

private val emptyMap = emptyMap<String, Any>()
private const val AUTH_FORM = "auth-form"
private const val AUTH_SESSION = "auth-session"

data class UserSession(val username: String, val token: String) : Principal

fun Route.mustacheRouting(aksjesimulator: Aksjesimulator) {
    route("/simple") {
        get {
            call.respond(MustacheContent("login.hbs", emptyMap))
        }
    }

    route("/simple/login") {
        get {
            call.respond(MustacheContent("login.hbs", emptyMap))
        }
        authenticate(AUTH_FORM) {
            post {
                val principal = call.principal<UserSession>()!!

                call.sessions.set(principal)
                val params = mapOf("username" to principal.username)
                call.respond(MustacheContent("login-success.hbs", params))
            }
        }
    }

    route("/simple/accounts") {
        authenticate(AUTH_SESSION) {
            get {
                val principal = call.principal<UserSession>()!!
                val user = aksjesimulator.getUserByUsername(principal.username)
                val params = mapOf("username" to principal.username, "user" to user)
                call.respond(MustacheContent("accounts.hbs", params))
            }
        }
    }
}

fun Application.registerMustacheRoutes(login: Login, aksjesimulator: Aksjesimulator) {
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
    install(Authentication) {
        session<UserSession>(AUTH_SESSION) {
            validate { session ->
                if (login.isLoggedIn(session.username, session.token)) {
                    session
                } else {
                    null
                }
            }
            challenge("/simple/login")
        }
        form(AUTH_FORM) {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                val sessionToken = login.login(credentials.name, credentials.password)
                if (sessionToken != null) {
                    UserSession(credentials.name, sessionToken.token)
                } else {
                    null
                }
            }
        }
    }
    routing {
        mustacheRouting(aksjesimulator)
    }
}
