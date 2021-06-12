package no.aksjesimulator.presentation.mustache.viewmodels

import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.User

data class AccountVM(val id: Int, val name: String, val balance: Int)

data class UserVM(val username: String, val accounts: List<AccountVM>)

fun accountTOAccountVm(account: Account): AccountVM {
    return AccountVM(
        account.id,
        account.name,
        (account.balance).toInt()
    )
}

fun userToUserVM(user: User): UserVM {
    val accountVMs = user.accounts?.map { accountTOAccountVm(it) } ?: emptyList()
    return UserVM(user.username, accountVMs)
}
