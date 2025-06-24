package com.kevin.ChatApi.controller;

import com.kevin.ChatApi.model.ChatMessage;
import com.kevin.ChatApi.model.ChatNotification;
import com.kevin.ChatApi.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiver(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getChatId(),
                        savedMsg.getSender(),
                        savedMsg.getReceiver(),
                        savedMsg.getMessage()));
    }

    @GetMapping("/messages/{senderId}/{recieverId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
                                                              @PathVariable String recieverId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recieverId));
    }
}
