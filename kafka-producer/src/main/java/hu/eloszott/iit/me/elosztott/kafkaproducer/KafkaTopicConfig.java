package hu.eloszott.iit.me.elosztott.kafkaproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Bean
  public NewTopic createTopic() {
    return TopicBuilder.name("chat-rooms")
        .partitions(1)
        .replicas(1)
        .build();
  }
}
