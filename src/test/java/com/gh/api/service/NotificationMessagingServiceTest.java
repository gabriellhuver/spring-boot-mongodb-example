package com.gh.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gh.api.dto.NotificationRequestDTO;
import com.gh.api.helpers.NotificationDummyBuilder;
import com.gh.api.messaging.connector.KafkaConnector;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = NotificationMessagingServiceTest.TOPIC)
class NotificationMessagingServiceTest {

	static final String TOPIC = "notification";

	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;

	private Consumer<String, String> consumer;
	
	@InjectMocks
	private NotificationMessagingService messagingService;

	@Mock
	private KafkaConnector kafkaConnector; 
	
	@Mock
	private KafkaProducer<String, Object> kafkaProducer;

	
	@BeforeEach
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group", "false", embeddedKafkaBroker));
        consumer = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new StringDeserializer()).createConsumer();
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, TOPIC);
        when(kafkaConnector.kafkaProducerDev()).thenReturn(kafkaProducer);
        
    }
 
    @AfterEach
    void tearDown() {
        consumer.close();
    }
    
    @Test
    void testNotification() throws InterruptedException {
    	NotificationRequestDTO createNotification = NotificationDummyBuilder.createNotification();
		Future<RecordMetadata> notificar = messagingService.notificar(createNotification);
    }
    
    @Test
    void testEnviarMensagem() throws InterruptedException {
        // Criar uma mensagem de teste
        String mensagem = "Mensagem de teste";
 
        // Enviar a mensagem para o tópico usando o KafkaProducer
        KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<>(getKafkaProperties());
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(TOPIC, mensagem);
        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
        kafkaProducer.flush();
 
        // Aguardar 1 segundo para garantir que a mensagem seja processada
        Thread.sleep(1000);
 
        // Verificar se a mensagem foi recebida corretamente pelo tópico
        ConsumerRecords<String, String> records = KafkaTestUtils.getRecords(consumer);
        assertThat(records.count()).isEqualTo(1);
        ConsumerRecord<String, String> record = records.iterator().next();
        assertThat(record.value()).isEqualTo(mensagem);
    }
 
    private Map<String, Object> getKafkaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

}
