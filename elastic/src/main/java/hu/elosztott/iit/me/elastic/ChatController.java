package hu.elosztott.iit.me.elastic;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController {
  private final ChatMessageRepository chatMessageRepository;

  @PostMapping()
  public void newData(@RequestBody @Valid ChatMessageDto chatMessageDto) {
    chatMessageRepository.save(chatMessageDto.toDocument());
  }

  @GetMapping(consumes = "application/json")
  public Page<ChatMessage> findBySender(@RequestBody @Valid FindByDto findByDto) {
    return chatMessageRepository.findBySender(findByDto.getQuery(), PageRequest.of(0, 10));
  }

  @GetMapping(consumes = "application/json",path = "/smr")
  public Page<ChatMessage> findBySenderOrMessageOrRoomName(@RequestBody @Valid FindByDto findByDto) {
    return chatMessageRepository.findBySenderOrMessageOrRoomName(findByDto.getQuery(),findByDto.getQuery(),findByDto.getQuery(), PageRequest.of(0, 10));
  }

  @GetMapping(consumes = "application/json",path = "/smrlike")
  public Page<ChatMessage> findBySenderLikeOrMessageLikeOrRoomNameLike(@RequestBody @Valid FindByDto findByDto) {
    return chatMessageRepository.findBySenderLikeOrMessageLikeOrRoomNameLike(findByDto.getQuery(),findByDto.getQuery(),findByDto.getQuery(), PageRequest.of(0, 10));
  }
}
