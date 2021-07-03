package no.aksjesimulator

import com.beust.klaxon.Klaxon
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.routing.*
import no.aksjesimulator.application.models.dto.QuoteDto
import no.aksjesimulator.application.models.dto.toQuote
import no.aksjesimulator.infrastructure.kafka.createConsumer
import no.aksjesimulator.presentation.mustache.registerMustacheRoutes
import java.time.Duration
import java.util.*
import kotlin.concurrent.schedule

const val FIVE_SECONDS = 5*1000L
const val ONE_MINUTE = 60*1000L

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    val kafkaConsumer = createConsumer()
    kafkaConsumer.subscribe(listOf("quotes"))


    Timer().schedule(FIVE_SECONDS, ONE_MINUTE) {
        val records = kafkaConsumer.poll(Duration.ofSeconds(5))
        records.iterator().forEach {
            val quotesJson = it.value()
            val quotes = Klaxon().parseArray<QuoteDto>(quotesJson)
                ?.map { dto -> dto.toQuote() } ?: emptyList()
            Dependencies.aksjesimulator.updateStockPrices(quotes)
        }
    }

    install(IgnoreTrailingSlash)
    install(ContentNegotiation) { gson() }
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }

    // registerUserRoutes(UserRepository())
    // registerPriceRoutes(PriceRepository())

    routing {
        static("/static") {
            resources("files")
        }
    }
    registerMustacheRoutes(Dependencies.login, Dependencies.aksjesimulator)
}
