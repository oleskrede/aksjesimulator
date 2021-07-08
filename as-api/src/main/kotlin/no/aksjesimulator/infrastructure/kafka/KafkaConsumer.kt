package no.aksjesimulator.infrastructure.kafka

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*

fun createConsumer(): Consumer<String, String> {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:29092"
    props["group.id"] = "as-api-consumer"
    props["key.deserializer"] = StringDeserializer::class.java
    props["value.deserializer"] = StringDeserializer::class.java
    return KafkaConsumer(props)
}

