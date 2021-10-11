package hu.eloszott.iit.me.elosztott.kafkaproducer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

  private  String message;
  private String room;
  private String from;
}
