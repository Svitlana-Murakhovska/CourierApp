package kafka;

import Cuirer.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaTemplateConfiguration {

    @Bean
    public KafkaTemplate<Long, Order> getKafkaTemplate(ProducerFactory<Long, Order> producerFactory) {
        return new KafkaTemplate<>(producerFactory);

    }

}
