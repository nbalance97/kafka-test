package com.example.kafkastudy

import com.example.kafkastudy.producer.KafkaEventPublisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val kafkaEventPublisher: KafkaEventPublisher
) {

    @GetMapping("/test")
    fun test(@RequestParam content: String): String {
        kafkaEventPublisher.produce(content).subscribe()
        return "test"
    }
}
