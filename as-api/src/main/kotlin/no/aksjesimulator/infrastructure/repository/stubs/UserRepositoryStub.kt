package no.aksjesimulator.infrastructure.repository.stubs

import no.aksjesimulator.application.UserDto
import no.aksjesimulator.application.interfaces.UserRepositoryInterface
import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.AccountHolding
import no.aksjesimulator.domain.models.User

class UserRepositoryStub : UserRepositoryInterface {
    private val users = mutableListOf<User>()

    private var idGenerator = 1
    private fun generateId() = idGenerator++

    init {
        val holding = AccountHolding("TEL", 10, 1000)
        val account = Account("ASK", 1000000, 0, 0, 0, emptyList(), listOf(holding))
        val user = User(generateId(), "ole", "123", listOf(account))
        users.add(user)
    }

    fun getAllUsers(): List<User> {
        return users
    }

    override fun getUser(id: Int): User? {
        return users.find { it.id == id }
    }

    override fun createUser(userDto: UserDto): User {
        val user = User(generateId(), userDto.username, userDto.password, userDto.accounts, userDto.email)
        users.add(user)
        return user
    }

    override fun deleteUser(id: Int): Boolean {
        return users.removeIf { it.id == id }
    }
}
