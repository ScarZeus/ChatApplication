package service;

import lombok.RequiredArgsConstructor;
import model.ChatMessage;
import org.springframework.stereotype.Service;
import repository.ChatMessageRepo.ChatMessageRepo;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
  private final ChatMessageRepo chatMessageRepo;
  private final ChatRoomService chatRoomService;

  public ChatMessage save(ChatMessage chatMessage) {
      var chatRoomId=chatRoomService.getChatRoomId(chatMessage.getSender(), chatMessage.getReceiver(),true).orElse(null);
      chatMessage.setCharRoomId(chatRoomId);
      chatMessageRepo.save(chatMessage);
      return chatMessage;
  }
}
