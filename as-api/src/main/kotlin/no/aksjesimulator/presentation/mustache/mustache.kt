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
import io.ktor.request.receive
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import no.aksjesimulator.application.Aksjesimulator
import no.aksjesimulator.application.AuthInterface
import no.aksjesimulator.presentation.mustache.viewmodels.toUserVM

private val emptyMap = emptyMap<String, Any>()
private const val AUTH_FORM = "auth-form"
private const val AUTH_SESSION = "auth-session"
private const val USER_SESSION = "user_session"

data class UserSession(val username: String, val token: String) : Principal

fun Route.mustacheRouting(auth: AuthInterface, aksjesimulator: Aksjesimulator) {
    route("/simple") {
        get {
            call.respond(MustacheContent("login.hbs", emptyMap))
        }
    }

    route("/simple/faq") {
        get {
            val principal = call.sessions.get<UserSession>()
            val params = mapOf("username" to principal?.username)
            call.respond(MustacheContent("faq.hbs", params))
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

    route("/simple/logout") {
        authenticate(AUTH_SESSION) {
            get {
                val principal = call.principal<UserSession>()!!
                auth.logout(principal.username)
                call.sessions.clear(USER_SESSION)
                call.respondRedirect("/simple/login")
            }
        }
    }

    route("/simple/accounts") {
        authenticate(AUTH_SESSION) {
            get {
                val principal = call.principal<UserSession>()!!
                val user = aksjesimulator.getUserByUsername(principal.username)?.toUserVM()
                val params = mapOf("username" to principal.username, "user" to user)
                call.respond(MustacheContent("accounts.hbs", params))
            }

            post {
                val principal = call.principal<UserSession>()!!
                val accountParams = call.receiveParameters()
                aksjesimulator.createAccount(principal.username)
                call.respondRedirect("/simple/accounts")
            }
        }
    }
}

fun Application.registerMustacheRoutes(auth: AuthInterface, aksjesimulator: Aksjesimulator) {
    install(Sessions) {
        cookie<UserSession>(USER_SESSION)
    }
    install(Authentication) {
        session<UserSession>(AUTH_SESSION) {
            validate { session ->
                if (auth.isLoggedIn(session.username, session.token)) {
                    session
                } else {
                    null
                }
            }
            challenge { "/simple/login" }
        }
        form(AUTH_FORM) {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                val sessionToken = auth.login(credentials.name, credentials.password)
                if (sessionToken != null) {
                    UserSession(credentials.name, sessionToken.token)
                } else {
                    null
                }
            }
        }
    }
    routing {
        mustacheRouting(auth, aksjesimulator)
    }
}
