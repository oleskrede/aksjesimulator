package no.aksjesimulator.application.models.dto

import no.aksjesimulator.application.models.Account

data class NewUserDto(
    val username: String,
    val password: String,
    val email: String? = null,
    val accounts: List<Account> = emptyList()
)
