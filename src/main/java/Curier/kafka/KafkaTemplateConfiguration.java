package Curier.kafka;

import client.model.Notification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaTemplateConfiguration {


    @Bean
    public KafkaTemplate<Long, Notification> getKafkaTemplate(ProducerFactory<Long, Notification> producerFactory) {
        return new KafkaTemplate<>(producerFactory);

    }

}
