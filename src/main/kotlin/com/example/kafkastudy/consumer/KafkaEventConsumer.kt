package com.example.kafkastudy.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import reactor.kafka.receiver.KafkaReceiver

@Component
class KafkaEventConsumer(
    @Qualifier("kafkaConsumer")
    private val KafkaReceiver: KafkaReceiver<String, String>
) {

    init {
        KafkaReceiver.receive()
            .doOnNext { println("receive Event : ${it}") }
            .subscribe()
    }
}
