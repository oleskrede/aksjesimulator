package no.aksjesimulator

import org.openapitools.client.models.Account
import org.openapitools.client.models.AccountHolding
import org.openapitools.client.models.User

class UserRepository {
    private val users = mutableListOf<User>()

    init {
        val holding = AccountHolding("TEL", 10, 1000)
        val account = Account("ASK", 1000000, 0, 0, 0, emptyList(), listOf(holding))
        val user = User("ole", "password", listOf(account))
        users.add(user)
    }

    fun getAllUsers(): List<User> {
        return users
    }

    fun getUser(id: String): User? {
        return users.getOrNull(0)
    }
}
