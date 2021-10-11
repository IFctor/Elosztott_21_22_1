package hu.eloszott.iit.me.elosztott.kafkaproducer;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {
  void sendMessage(MessageDto messageDto) throws JsonProcessingException;
}
