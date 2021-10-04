package hu.eloszott.iit.me.elosztott.kafkaproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequiredArgsConstructor
public class MainCtrl {
  private final KafkaService kafkaService;

  @PostMapping("/send-message")
  void main(@RequestBody MessageDto message) throws JsonProcessingException {
    kafkaService.sendMessage(message);
  }
}
