package no.aksjesimulator.presentation.mustache.viewmodels

import no.aksjesimulator.domain.models.Account
import no.aksjesimulator.domain.models.User

data class AccountVM(val id: Int, val name: String, val balance: Int)

data class UserVM(val username: String, val accounts: Array<AccountVM>)

fun Account.toAccountVm(): AccountVM {
    return AccountVM(
        id,
        name,
        balance.toInt()
    )
}

fun User.toUserVM(): UserVM {
    val accountVMs = accounts?.map { it.toAccountVm() }.toTypedArray()
    return UserVM(username, accountVMs)
}
