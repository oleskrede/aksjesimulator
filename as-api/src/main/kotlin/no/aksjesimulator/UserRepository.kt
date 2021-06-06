package no.aksjesimulator

import org.openapitools.client.models.Account
import org.openapitools.client.models.AccountHolding
import org.openapitools.client.models.User

class UserRepository {
    private val users = mutableListOf<User>()

    private var idGenerator = 1
    private fun generateId() =  idGenerator++

    init {
        val holding = AccountHolding("TEL", 10, 1000)
        val account = Account("ASK", 1000000, 0, 0, 0, emptyList(), listOf(holding))
        val user = User(1, "ole", "password", listOf(account))
        users.add(user)
    }

    fun getAllUsers(): List<User> {
        return users
    }

    fun getUser(id: Int): User? {
        return users.find { it.id == id }
    }

    fun add(newUserDto: NewUserDto) {
        val user = User(generateId(), newUserDto.username, newUserDto.password, mutableListOf(), newUserDto.email)
        users.add(user)
    }

    fun deleteUser(id: Int): Boolean {
        return users.removeIf {it.id == id}
    }
}
