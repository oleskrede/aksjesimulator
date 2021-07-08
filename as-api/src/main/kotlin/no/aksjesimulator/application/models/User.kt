package no.aksjesimulator.application.models

data class User(
    val id: Int,
    var username: String,
    var password: String,
    var email: String? = null,
    val accounts: MutableList<Account> = mutableListOf()
)
