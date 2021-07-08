package no.aksjesimulator.application

import no.aksjesimulator.application.interfaces.IAksjesimRepository
import java.time.LocalDateTime
import java.time.LocalDateTime.now

const val SEVEN_DAYS_IN_SECONDS = 60 * 60 * 24 * 7L
const val TOKEN_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
const val TOKEN_LENGTH = 150

interface AuthInterface {
    fun login(username: String, password: String): SessionToken?
    fun logout(userId: Int): Boolean
    fun isLoggedIn(userId: Int, token: String): Boolean
}

class AuthStub : AuthInterface {
    override fun login(username: String, password: String): SessionToken? =
        SessionToken(1, "tokenDummy", now().plusDays(7))

    override fun logout(userId: Int): Boolean = true
    override fun isLoggedIn(userId: Int, token: String): Boolean = true
}

class Auth(
    val aksjesimRepository: IAksjesimRepository,
    val sessionDuration: Long = SEVEN_DAYS_IN_SECONDS
) :
    AuthInterface {
    private val sessions = mutableMapOf<Int, SessionToken>() // <userId, SessionToken>

    private fun generateSessionToken(userId: Int): SessionToken {
        val token = List(TOKEN_LENGTH) { TOKEN_CHARSET.random() }
            .joinToString("")
        val validUntil = now().plusSeconds(sessionDuration)
        return SessionToken(userId, token, validUntil)
    }

    override fun login(username: String, password: String): SessionToken? {
        val user = aksjesimRepository.getUserByUsername(username) ?: return null
        if (password != user.password) return null

        val sessionToken = generateSessionToken(user.id)
        sessions[user.id] = sessionToken

        return sessionToken
    }

    override fun logout(userId: Int): Boolean {
        return sessions.remove(userId) != null
    }

    override fun isLoggedIn(userId: Int, token: String): Boolean {
        val sessionIsExpired = sessions[userId]?.expiry?.isBefore(LocalDateTime.now()) ?: true
        if (sessionIsExpired) {
            logout(userId)
            return false
        }

        val tokenIsValid = sessions[userId]?.token == token
        return tokenIsValid
    }
}

data class SessionToken(val userId: Int, val token: String, val expiry: LocalDateTime)
