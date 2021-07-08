package no.aksjesimulator.presentation.mustache

import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.AccountHolding
import no.aksjesimulator.application.models.Stock
import no.aksjesimulator.application.models.User

data class UserVM(val username: String, val accounts: List<AccountOverviewVM>)

data class AccountOverviewVM(val id: Int, val name: String, val balance: Int)

data class AccountVM(val name: String, val balance: Int, val holdings: List<AccountHoldingVM>)

data class AccountHoldingVM(val stock: StockVM, val amount: Long, val gav: Double)

data class StockVM(val ticker: String, val name: String, val price: String)

fun User.toUserVM(): UserVM {
    val accountOverviewVMs = accounts?.map { it.toAccountOverviewVM() }
    return UserVM(username, accountOverviewVMs)
}

fun Account.toAccountOverviewVM(): AccountOverviewVM {
    return AccountOverviewVM(
        id,
        name,
        balance.toInt()
    )
}

fun Account.toAccountVM(): AccountVM {
    return AccountVM(
        name,
        balance.toInt(),
        holdings.map { it.toAccountHoldingVM() }
    )
}

fun AccountHolding.toAccountHoldingVM(): AccountHoldingVM {
    return AccountHoldingVM(
        stock.toStockVM(), amount, gav
    )
}

fun Stock.toStockVM(): StockVM {
    return StockVM(ticker, name, "%.2".format(price))
}
