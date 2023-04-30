package com.example.kafkastudy.producer

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import java.util.*

@Component
class KafkaEventPublisher(
    @Qualifier("kafkaProducer")
    private val kafkaProducer: KafkaSender<String, String>
) {

    fun produce(content: String): Mono<SenderResult<Void>> {
        val topic = "test"
        val key = "1"

        val producerRecord = ProducerRecord(topic, key, content)
        val eventId = UUID.randomUUID().toString()

        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, eventId.toByteArray())
        val senderRecord: SenderRecord<String, String, Void> = SenderRecord.create(producerRecord, null)

        return kafkaProducer.send(Mono.just(senderRecord)).single()
    }
}
