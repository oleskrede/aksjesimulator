package no.aksjesimulator.application.interfaces

import no.aksjesimulator.application.UserDto
import no.aksjesimulator.domain.models.User

interface AksjesimRepositoryInterface {
    fun getUserById(id: Int): User?
    fun getUserByUsername(username: String): User?
    fun createUser(userDto: UserDto): User
    fun deleteUser(userId: Int): Boolean

    fun createAccount(
        userId: Int,
        name: String,
        initialBalance: Double,
        brokerageFee: Float,
        brokerageSpread: Float,
        currencySpread: Float
    )
}
