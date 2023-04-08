package com.gh.api.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gh.api.dto.NotificationRequestDTO;
import com.gh.api.messaging.connector.KafkaConnector;

@Service
public class NotificationMessagingService {
	
	
	private static final String TOPIC = "notification";
	
	@Autowired
	private KafkaConnector kafkaConnector;
	
	public void notificar(NotificationRequestDTO notificationRequestDTO) {
		ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(TOPIC, notificationRequestDTO.toString());
		kafkaConnector.kafkaProducerDev().send(producerRecord);	
	}

}
