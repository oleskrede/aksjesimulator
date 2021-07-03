package no.aksjesimulator.application

import no.aksjesimulator.application.interfaces.IAksjesimRepository
import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.Stock
import no.aksjesimulator.application.models.User
import no.aksjesimulator.application.models.dto.NewAccountDto
import no.aksjesimulator.application.models.dto.NewUserDto
import no.aksjesimulator.application.models.dto.Quote

class Aksjesimulator(val aksjesimRepository: IAksjesimRepository) {

    fun getUser(userId: Int): User? =
        aksjesimRepository.getUser(userId)

    fun createUser(userDto: NewUserDto): User =
        aksjesimRepository.createUser(userDto)

    fun deleteUser(userId: Int): Boolean =
        aksjesimRepository.deleteUser(userId)

    fun createAccount(userId: Int, newAccountDto: NewAccountDto): Boolean =
        aksjesimRepository.createAccount(userId, newAccountDto)

    fun getUserAccount(userId: Int, accountId: Int): Account? =
        aksjesimRepository.getUserAccount(userId, accountId)

    fun getStocks(): List<Stock> =
        aksjesimRepository.getStocks().sortedBy { it.name }

    fun getStock(ticker: String): Stock? = aksjesimRepository.getStock(ticker)

    fun updateStockPrices(quotes: List<Quote>) = aksjesimRepository.updateStocks(quotes)
}

