package no.aksjesimulator.managers

import no.aksjesimulator.domain.models.User
import no.aksjesimulator.interfaces.repository.UserRepository

class UserManager(val userRepository: UserRepository) {

    fun getUser(id: Int): User? = userRepository.getUser(id)

    fun createUser(newUserDto: NewUserDto): User? {

    }
}

data class NewUserDto(val username: String, val password: String, val email: String? = null)
