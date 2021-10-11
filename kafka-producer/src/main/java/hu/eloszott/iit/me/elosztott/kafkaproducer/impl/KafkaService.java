package hu.eloszott.iit.me.elosztott.kafkaproducer.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.eloszott.iit.me.elosztott.kafkaproducer.MessageDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class KafkaService implements hu.eloszott.iit.me.elosztott.kafkaproducer.KafkaService {

  private final KafkaTemplate<Long, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  private final String topicName = "chat-rooms";

  @Override
  public void sendMessage(MessageDto messageDto) throws JsonProcessingException {
    String message = objectMapper.writeValueAsString(messageDto);
    Long key = 0L;

    ProducerRecord<Long, String> producerRecord = buildProducerRecord(key, message, topicName);
    ListenableFuture<SendResult<Long, String>> listenableFuture = kafkaTemplate
        .send(producerRecord);

    listenableFuture.addCallback(new ListenableFutureCallback<>() {
                                   @Override
                                   public void onFailure(Throwable ex) {
                                     System.out.println(String.format("Failed message %s", ex.toString()));
                                   }

                                   @Override
                                   public void onSuccess(SendResult<Long, String> result) {
                                     System.out.println(String.format("Succes message %s", result.toString()));
                                   }
                                 }
    );
  }


  private ProducerRecord<Long, String> buildProducerRecord(Long key, String value,
      String kafkaTopic) {
    return new ProducerRecord<Long, String>(kafkaTopic, null, key, value, null);
  }
}