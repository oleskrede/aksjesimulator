package no.aksjesimulator.application

import no.aksjesimulator.application.interfaces.IAksjesimRepository
import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.Stock
import no.aksjesimulator.application.models.User
import no.aksjesimulator.application.models.dto.NewAccountDto
import no.aksjesimulator.application.models.dto.NewUserDto

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

    fun getTickers(): List<Stock> =
        aksjesimRepository.getTickers()
}
