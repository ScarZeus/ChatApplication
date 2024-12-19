package com.Project.ChatApplication.service;

import lombok.RequiredArgsConstructor;
import com.Project.ChatApplication.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.Project.ChatApplication.repository.ChatMessageRepo.ChatMessageRepo;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
  private final ChatMessageRepo chatMessageRepo;
  private final ChatRoomService chatRoomService;

  public ChatMessage save(ChatMessage chatMessage) {
      var chatRoomId=chatRoomService.getChatRoomId(chatMessage.getSender(), chatMessage.getReceiver(),true).orElse(null);
      chatMessage.setCharRoomId(chatRoomId);
      chatMessageRepo.save(chatMessage);
      return chatMessage;
  }
  public List<ChatMessage> findChatMessages(String senderId,String recieverId)
  {
    var chatId = chatRoomService.getChatRoomId(senderId, recieverId, false);

  return chatId.map(chatMessageRepo::findByChatId).orElse(new ArrayList<>());
  }
}
