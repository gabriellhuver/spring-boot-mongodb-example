package com.gh.api.messaging.connector;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class KafkaConnector {

	private KafkaProducer<String, Object> kafkaProducer;

	public KafkaProducer<String, Object> kafkaProducerDev() {
		if(kafkaProducer == null) {
			Properties props = new Properties();
			props.put("bootstrap.servers", "localhost:9092");
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			this.kafkaProducer = new KafkaProducer<>(props);
			return this.kafkaProducer;
		} else {
			return kafkaProducer;
		}
	}

}
