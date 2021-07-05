package no.aksjesimulator

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.Key
import com.natpryce.konfig.overriding
import com.natpryce.konfig.stringType
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

// TODO merge with Ktors application.conf somehow?
internal object Configuration {
    private val profile = System.getenv("PROFILE") ?: "LOCAL"
    private val resourceProperties = when (profile) {
        "LOCAL" -> ConfigurationProperties.fromResource("local.properties")
        "PROD" -> ConfigurationProperties.fromResource("prod.properties")
        else -> throw IllegalArgumentException("Unknown PROFILE: $profile")
    }
    private val config = systemProperties() overriding
            EnvironmentVariables() overriding
            resourceProperties

    fun initDB() {
        val hikariConfig = HikariConfig().apply {
            username = config[Key("db.user", stringType)]
            password = config[Key("db.password", stringType)]
            jdbcUrl = "jdbc:postgresql://${config[Key("db.host", stringType)]}:${config[Key("db.port", stringType)]}/${config[Key("db.database", stringType)]}"
        }

        val dataSource = HikariDataSource(hikariConfig)
        Database.connect(dataSource)
//        createTables()
        println("Database initialized")
    }

//    private fun createTables() = transaction {
//        SchemaUtils.create(
//            Books
//        )
//    }
}
