package no.aksjesimulator.application

import no.aksjesimulator.application.interfaces.AksjesimRepositoryInterface
import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.User

class Aksjesimulator(val aksjesimRepository: AksjesimRepositoryInterface) {

    fun getUser(id: Int): User? = aksjesimRepository.getUserById(id)

    fun getUserByUsername(username: String): User? = aksjesimRepository.getUserByUsername(username)

    fun createUser(userDto: UserDto): User = aksjesimRepository.createUser(userDto)

    fun deleteUser(userId: Int): Boolean = aksjesimRepository.deleteUser(userId)
}

data class UserDto(val username: String, val password: String, val email: String? = null, val accounts: List<Account> = emptyList())

