package no.aksjesimulator.application.interfaces

import no.aksjesimulator.domain.models.User
import no.aksjesimulator.application.UserDto

interface UserRepositoryInterface {
    fun getUser(id: Int): User?
    fun createUser(userDto: UserDto): User
    fun deleteUser(userId: Int): Boolean
}
