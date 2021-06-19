package no.aksjesimulator.presentation.mustache.viewmodels

import no.aksjesimulator.application.models.Account
import no.aksjesimulator.application.models.AccountHolding
import no.aksjesimulator.application.models.Ticker
import no.aksjesimulator.application.models.User

data class UserVM(val username: String, val accountOverviews: List<AccountOverviewVM>)

data class AccountOverviewVM(val id: Int, val name: String, val balance: Int)

data class AccountVM(val name: String, val balance: Int, val holdings: List<AccountHoldingVM>)

data class AccountHoldingVM(val ticker: TickerVM, val amount: Long, val gav: Double)

data class TickerVM(val ticker: String, val name: String, val price: Double)

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
        ticker.toTickerVM(), amount, gav
    )
}

fun Ticker.toTickerVM(): TickerVM {
    return TickerVM(ticker, name, price)
}
