package hu.elosztott.iit.me.elastic;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatMessageDto {

  @NotBlank private String id;
  @NotBlank private String message;
  @NotBlank private String sender;
  @NotBlank private String roomId;
  @NotBlank private String roomName;

  public ChatMessage toDocument(){
    return ChatMessage.builder()
        .id(this.id)
        .message(this.message)
        .sender(this.sender)
        .roomId(this.roomId)
        .roomName(this.roomName)
        .build();
  }
}
