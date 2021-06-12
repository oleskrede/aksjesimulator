package no.aksjesimulator.presentation.mustache.viewmodels

data class AccountVM(val id: Int, val name: String, val balance: Int)

data class UserVM(val username: String, val accounts: List<AccountVM>)
