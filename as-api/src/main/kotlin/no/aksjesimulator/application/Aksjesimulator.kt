package no.aksjesimulator.application

import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.User
import no.aksjesimulator.application.interfaces.UserRepositoryInterface

class Aksjesimulator(val userRepository: UserRepositoryInterface) {

    fun getUser(id: Int): User? = userRepository.getUser(id)

    fun createUser(userDto: UserDto): User = userRepository.createUser(userDto)

    fun deleteUser(userId: Int): Boolean = userRepository.deleteUser(userId)
}

data class UserDto(val username: String, val password: String, val email: String? = null, val accounts: List<Account> = emptyList())

