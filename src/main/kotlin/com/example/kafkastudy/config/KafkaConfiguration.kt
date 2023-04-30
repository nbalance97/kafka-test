package com.example.kafkastudy.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions


@Configuration
class KafkaConfiguration(
    private val objectMapper: ObjectMapper,
    private val kafkaProperties: KafkaProperties,
) {

    @Bean(name = ["kafkaConsumer"])
    fun kafkaReceiver(): KafkaReceiver<String, String> {
        val topic = "test"
        val consumerProperties = kafkaProperties.buildConsumerProperties()
        consumerProperties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        consumerProperties[ConsumerConfig.GROUP_ID_CONFIG] = "test-event"
        consumerProperties[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        val receiverOptions = ReceiverOptions.create<String, String>(consumerProperties).subscription(listOf(topic))

        return KafkaReceiver.create(receiverOptions)
    }

    @Bean(name = ["kafkaProducer"])
    fun kafkaProducer(): KafkaSender<String, String> {
        val producerProperties = kafkaProperties.buildProducerProperties()
        producerProperties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        producerProperties[ConsumerConfig.GROUP_ID_CONFIG] = "test-event"
        producerProperties[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        val senderOptions = SenderOptions.create<String, String>(producerProperties)

        return KafkaSender.create(senderOptions)
    }
}
