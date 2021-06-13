package no.aksjesimulator.application

import no.aksjesimulator.application.interfaces.AksjesimRepositoryInterface
import java.time.LocalDateTime

const val SEVEN_DAYS_IN_SECONDS = 60 * 60 * 24 * 7L
const val TOKEN_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
const val TOKEN_LENGTH = 150

interface AuthInterface {
    fun login(username: String, password: String): SessionToken?
    fun logout(username: String): Boolean
    fun isLoggedIn(username: String, token: String): Boolean
}

class AuthStub : AuthInterface {
    override fun login(username: String, password: String): SessionToken? =
        SessionToken("tokenDummy", LocalDateTime.now().plusDays(7))

    override fun logout(username: String): Boolean = true
    override fun isLoggedIn(username: String, token: String): Boolean = true
}

class Auth(
    val aksjesimRepository: AksjesimRepositoryInterface,
    val sessionDuration: Long = SEVEN_DAYS_IN_SECONDS
) :
    AuthInterface {
    private val sessions = mutableMapOf<String, SessionToken>() // <username, SessionToken>

    private fun generateSessionToken(): SessionToken {
        val token = List(TOKEN_LENGTH) { TOKEN_CHARSET.random() }
            .joinToString("")
        val validUntil = LocalDateTime.now().plusSeconds(sessionDuration)
        return SessionToken(token, validUntil)
    }

    override fun login(username: String, password: String): SessionToken? {
        val userPassword = aksjesimRepository.getUserByUsername(username)?.password ?: return null
        if (password != userPassword) return null

        val sessionToken = generateSessionToken()
        sessions[username] = sessionToken

        return sessionToken
    }

    override fun logout(username: String): Boolean {
        return sessions.remove(username) != null
    }

    override fun isLoggedIn(username: String, token: String): Boolean {
        if (username.isNullOrEmpty()) return false

        val sessionIsExpired = sessions[username]?.expiry?.isBefore(LocalDateTime.now()) ?: true
        if (sessionIsExpired) {
            logout(username)
            return false
        }

        val tokenIsValid = sessions[username]?.token == token
        return tokenIsValid
    }
}

data class SessionToken(val token: String, val expiry: LocalDateTime)
