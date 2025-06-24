package com.kevin.ChatApi.service;

import com.kevin.ChatApi.model.ChatMessage;
import com.kevin.ChatApi.repo.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<ChatMessage> findChatMessages(String senderId, String recieverId)
    {
        var chatId = chatRoomService.getChatRoomId(senderId, recieverId, false);

        return chatId.map(chatMessageRepo::findByChatId).orElse(new ArrayList<>());
    }

}
