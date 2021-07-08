package no.aksjesimulator.infrastructure.repository.stubs

import no.aksjesimulator.application.interfaces.IAksjesimRepository
import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.AccountHolding
import no.aksjesimulator.application.models.Stock
import no.aksjesimulator.application.models.User
import no.aksjesimulator.application.models.dto.NewAccountDto
import no.aksjesimulator.application.models.dto.NewUserDto
import no.aksjesimulator.application.models.dto.Quote

class AksjesimRepositoryStub : IAksjesimRepository {
    private val users = mutableListOf<User>()

    private var userIdGenerator = 1
    private fun generateUserId() = userIdGenerator++

    private var accountIdGenerator = 1
    private fun generateAccountId() = accountIdGenerator++

    private val stocks = mutableMapOf<String, Stock>()

    init {
        val tel = Stock("TEL", "Telenor", 143.5500)
        val nhy = Stock("NHY", "Norsk Hydro", 53.9400)
        val dnb = Stock("DNB", "DNB", 188.6000)
        val sbank = Stock("SBANK", "Sbanken", 107.8000)
        stocks[tel.ticker] = tel
        stocks[nhy.ticker] = nhy
        stocks[dnb.ticker] = dnb
        stocks[sbank.ticker] = sbank

        val holdings = mutableListOf(
            AccountHolding(stocks["TEL"]!!, 10, 140.0),
            AccountHolding(stocks["DNB"]!!, 23, 153.0),
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

    override fun getUser(userId: Int): User? {
        return users.find { it.id == userId }
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

    override fun deleteUser(userId: Int): Boolean {
        return users.removeIf { it.id == userId }
    }

    override fun createAccount(userId: Int, newAccountDto: NewAccountDto): Boolean {
        val user = getUser(userId)!!
        val account = Account(
            id = generateAccountId(),
            name = newAccountDto.name,
            balance = newAccountDto.balance,
            minCommissionFee = newAccountDto.minCommissionFee,
            commissionFee = newAccountDto.commissionFee,
            currencySpread = newAccountDto.currencySpread
        )
        return user.accounts.add(account)
    }

    override fun getUserAccount(userId: Int, accountId: Int): Account? {
        return getUser(userId)?.accounts?.find { it.id == accountId }
    }

    override fun getStocks(): List<Stock> {
        return stocks.values.sortedBy { it.name }
    }

    override fun getStock(ticker: String): Stock? {
        return stocks[ticker]
    }

    override fun updateStocks(quotes: List<Quote>) {
        quotes.forEach {
            if (it.ticker in stocks) {
                stocks[it.ticker]?.price = it.price
            }
            else {
                stocks[it.ticker] = Stock(it.ticker, it.name, it.price)
            }
        }
    }
}
