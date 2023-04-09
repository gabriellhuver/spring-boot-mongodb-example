package com.gh.api.messaging.connector;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class KafkaConnectorTest {

	@MockBean
	private KafkaConnector kafkaConnector;

	@BeforeEach
	public void setUp() {
		kafkaConnector = new KafkaConnector();
	}

	@Test
	public void testKafkaProducerDev() {
		KafkaProducer<String, Object> kafkaProducer = kafkaConnector.kafkaProducerDev();

		assertNotNull(kafkaProducer);
	}


}
