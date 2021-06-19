package no.aksjesimulator.infrastructure.repository.stubs

import no.aksjesimulator.application.interfaces.IAksjesimRepository
import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.AccountHolding
import no.aksjesimulator.application.models.Ticker
import no.aksjesimulator.application.models.User
import no.aksjesimulator.application.models.dto.NewAccountDto
import no.aksjesimulator.application.models.dto.NewUserDto

class AksjesimRepositoryStub : IAksjesimRepository {
    private val users = mutableListOf<User>()

    private var userIdGenerator = 0
    private fun generateUserId() = userIdGenerator++

    private var accountIdGenerator = 0
    private fun generateAccountId() = userIdGenerator++

    private val prices = mutableMapOf<String, Ticker>()

    init {
        val tel = Ticker("TEL", "Telenor", 143.5500)
        val nhy = Ticker("NHY", "Norsk Hydro", 53.9400)
        val dnb = Ticker("DNB", "DNB", 188.6000)
        val sbank = Ticker("SBANK", "Sbanken", 107.8000)
        prices[tel.ticker] = tel
        prices[nhy.ticker] = nhy
        prices[dnb.ticker] = dnb
        prices[sbank.ticker] = sbank

        val holdings = mutableListOf(
            AccountHolding(prices["TEL"]!!, 10, 140.0),
            AccountHolding(prices["DNB"]!!, 23, 153.0),
        )
        val account = Account(
            id = generateAccountId(),
            name = "ASK",
            balance = 100000.0,
            holdings = holdings
        )
        val user = User(
            generateUserId(),
            "ole",
            "123",
            accounts = mutableListOf(account)
        )
        users.add(user)
    }

    override fun getUser(id: Int): User? {
        return users.find { it.id == id }
    }

    override fun getUserByUsername(username: String): User? {
        return users.find { it.username == username }
    }

    override fun createUser(userDto: NewUserDto): User {
        val user =
            User(generateUserId(), userDto.username, userDto.password, userDto.email, userDto.accounts.toMutableList())
        users.add(user)
        return user
    }

    override fun deleteUser(id: Int): Boolean {
        return users.removeIf { it.id == id }
    }

    override fun createAccount(userId: Int, account: NewAccountDto): Boolean {
        val user = getUser(userId)!!
        val account = Account(
            id = generateAccountId(),
            name = account.name,
            balance = account.balance,
            minCommissionFee = account.minCommissionFee,
            commissionFee = account.commissionFee,
            currencySpread = account.currencySpread
        )
        return user.accounts.add(account)
    }

    override fun getUserAccount(userId: Int, accountId: Int): Account? {
        return getUser(userId)?.accounts?.find { it.id == accountId } ?: null
    }

    fun getAllPrices(): Set<Ticker> {
        return prices.values.toSet()
    }

    fun getPrice(ticker: String): Ticker? {
        return prices[ticker]
    }
}
