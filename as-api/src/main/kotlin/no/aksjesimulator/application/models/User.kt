package no.aksjesimulator.application.models

data class User(
    val id: Int,
    var password: String,
    var username: String,
    var email: String? = null,
    val accounts: MutableList<Account> = mutableListOf()
)
