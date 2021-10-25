package hu.elosztott.iit.me.elastic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ChatMessageRepository extends ElasticsearchRepository<ChatMessage, String> {

  Page<ChatMessage> findBySender(String sender, Pageable pageable);

  Page<ChatMessage> findBySenderOrMessageOrRoomName(
      String sender, String message, String roomName, Pageable pageable);

  Page<ChatMessage> findBySenderLikeOrMessageLikeOrRoomNameLike(
      String sender, String message, String roomName, Pageable pageable);
}
