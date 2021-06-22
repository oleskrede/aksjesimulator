package no.aksjesimulator.application.interfaces

import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.Stock
import no.aksjesimulator.application.models.User
import no.aksjesimulator.application.models.dto.NewAccountDto
import no.aksjesimulator.application.models.dto.NewUserDto

interface IAksjesimRepository {
    fun getUser(userId: Int): User?
    fun getUserByUsername(username: String): User?
    fun createUser(userDto: NewUserDto): User
    fun deleteUser(userId: Int): Boolean
    fun createAccount(userId: Int, newAccountDto: NewAccountDto): Boolean
    fun getUserAccount(userId: Int, accountId: Int): Account?
    fun getStocks(): List<Stock>
    fun getStock(ticker: String): Stock?
}
