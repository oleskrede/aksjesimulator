package no.aksjesimulator.presentation.api

import io.ktor.application.Application
import io.ktor.routing.Route
import no.aksjesimulator.infrastructure.repository.stubs.AksjesimRepositoryStub

fun Route.userRouting(userRepository: AksjesimRepositoryStub = AksjesimRepositoryStub()) {
//    route("/user") {
//        get {
//            val allUsers = userRepository.getAllUsers()
//            if (allUsers.isNotEmpty()) {
//                call.respond(allUsers)
//            } else {
//                call.respondText("No users found", status = HttpStatusCode.NotFound)
//            }
//        }
//        get("{id}") {
//            val id = call.parameters["id"] ?: return@get call.respondText(
//                "Missing or malformed id",
//                status = HttpStatusCode.BadRequest
//            )
//            val user =
//                userRepository.getUser(id.toInt()) ?: return@get call.respondText(
//                    "No user with id $id",
//                    status = HttpStatusCode.NotFound
//                )
//            call.respond(user)
//        }
//        post {
//            val newUser = call.receive<NewUserDto>()
//            userRepository.add(newUser)
//            call.respondText("User created", status = HttpStatusCode.Created)
//        }
//        delete("{id}") {
//            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
//            if (userRepository.deleteUser(id.toInt())) {
//                call.respondText("User deleted", status = HttpStatusCode.Accepted)
//            } else {
//                call.respondText("Not Found", status = HttpStatusCode.NotFound)
//            }
//        }
//    }
}

fun Application.registerUserRoutes(userRepository: AksjesimRepositoryStub) {
//    routing {
//        userRouting(userRepository)
//    }
}
