package no.aksjesimulator.infrastructure.repository.stubs

import no.aksjesimulator.application.UserDto
import no.aksjesimulator.application.interfaces.AksjesimRepositoryInterface
import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.AccountHolding
import no.aksjesimulator.domain.models.User

class AksjesimRepositoryStub : AksjesimRepositoryInterface {
    private val users = mutableListOf<User>()

    private var userIdGenerator = 1
    private fun generateUserId() = userIdGenerator++

    private var accountIdGenerator = 1
    private fun generateAccountId() = userIdGenerator++

    init {
        val holding = AccountHolding("TEL", 10, 1000)
        val account = Account(generateAccountId(), "ASK", 1000000, 0, 0, 0, emptyList(), listOf(holding))
        val user = User(generateUserId(), "ole", "123", listOf(account))
        users.add(user)
    }

    fun getAllUsers(): List<User> {
        return users
    }

    override fun getUserById(id: Int): User? {
        return users.find { it.id == id }
    }

    override fun getUserByUsername(username: String): User? {
        return users.find { it.username == username }
    }

    override fun createUser(userDto: UserDto): User {
        val user = User(generateUserId(), userDto.username, userDto.password, userDto.accounts, userDto.email)
        users.add(user)
        return user
    }

    override fun deleteUser(id: Int): Boolean {
        return users.removeIf { it.id == id }
    }
}
