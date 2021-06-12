package no.aksjesimulator.application

import no.aksjesimulator.application.interfaces.AksjesimRepositoryInterface
import java.time.LocalDateTime

const val SEVEN_DAYS_IN_SECONDS = 60 * 60 * 24 * 7L
const val TOKEN_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
const val TOKEN_LENGTH = 150

class Login(val aksjesimRepository: AksjesimRepositoryInterface) {
    private val sessions = mutableMapOf<String, SessionToken>() // <username, SessionToken>

    private fun generateToken(length: Int = TOKEN_LENGTH): String {
        return List(length) { TOKEN_CHARSET.random() }
            .joinToString("")
    }

    private fun generateSessionToken(username: String, secondsValid: Long = SEVEN_DAYS_IN_SECONDS): SessionToken {
        return SessionToken(generateToken(), LocalDateTime.now().plusSeconds(SEVEN_DAYS_IN_SECONDS))
    }

    fun login(username: String, password: String): SessionToken? {
        val userPassword = aksjesimRepository.getUserByUsername(username)?.password ?: return null
        if (password != userPassword) return null

        val sessionToken = generateSessionToken(username)
        sessions[username] = sessionToken

        return sessionToken
    }

    fun logout(username: String): Boolean {
        return sessions.remove(username) != null
    }

    fun isLoggedIn(username: String, token: String): Boolean {
        if (username.isNullOrEmpty()) return false

        val sessionIsExpired = sessions[username]?.expiry?.isAfter(LocalDateTime.now()) ?: true
        if (sessionIsExpired) {
            logout(username)
            return false
        }

        val tokenIsValid = sessions[username]?.token == token
        return tokenIsValid
    }
}

data class SessionToken(val token: String, val expiry: LocalDateTime)