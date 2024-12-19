package com.Project.ChatApplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.Project.ChatApplication.model.ChatMessage;
import com.Project.ChatApplication.model.ChatNotification;
import com.Project.ChatApplication.service.ChatMessageService;


@Controller
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
