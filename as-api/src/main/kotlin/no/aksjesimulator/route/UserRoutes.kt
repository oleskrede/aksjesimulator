package no.aksjesimulator.route

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import no.aksjesimulator.repository.UserRepository

fun Route.userRouting(userRepository: UserRepository = UserRepository()) {
    route("/user") {
        get {
            val allUsers = userRepository.getAllUsers()
            if (allUsers.isNotEmpty()) {
                call.respond(allUsers)
            } else {
                call.respondText("No users found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val user =
                userRepository.getUser(id.toInt()) ?: return@get call.respondText(
                    "No user with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(user)
        }
        post {
            val newUser = call.receive<NewUserDto>()
            userRepository.add(newUser)
            call.respondText("User created", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (userRepository.deleteUser(id.toInt())) {
                call.respondText("User deleted", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun Application.registerUserRoutes(userRepository: UserRepository) {
    routing {
        userRouting(userRepository)
    }
}

data class NewUserDto(
    val username: String,
    val password: String,
    val email: String? = null
)
