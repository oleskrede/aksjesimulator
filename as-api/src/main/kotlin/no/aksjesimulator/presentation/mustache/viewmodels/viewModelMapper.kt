package no.aksjesimulator.presentation.mustache.viewmodels

import no.aksjesimulator.domain.models.Account

fun accountTOAccountVm(account: Account): AccountVM {
    return AccountVM(
        account.id,
        account.name,
        (account.balance / 100).toInt()
    )
}
