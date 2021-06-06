package no.aksjesimulator

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.userRouting(userRepository: UserRepository) {
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
                userRepository.getUser(id) ?: return@get call.respondText(
                    "No user with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(user)
        }
        post {
        }
        delete("{id}") {
        }
    }
}
