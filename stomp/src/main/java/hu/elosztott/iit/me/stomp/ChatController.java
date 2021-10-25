package hu.elosztott.iit.me.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  @MessageMapping("/chat")
  @SendTo("/chat/message")
  public OutGoingChatMessage greeting(IncomingChatMessage incomingChatMessage)
      throws InterruptedException {
    Thread.sleep(1000L);
    return new OutGoingChatMessage(incomingChatMessage);
  }
}
