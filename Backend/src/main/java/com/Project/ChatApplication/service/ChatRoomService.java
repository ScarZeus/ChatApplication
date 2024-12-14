package com.Project.ChatApplication.service;

import lombok.RequiredArgsConstructor;
import com.Project.ChatApplication.model.ChatRoom;
import org.springframework.stereotype.Service;
import com.Project.ChatApplication.repository.ChatRoomRepo.ChatRoomRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepo chatRoomRepo;


    public Optional<String> getChatRoomId(String senderId, String receiverId, boolean ifNotExists) {
        return chatRoomRepo.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (ifNotExists) {
                       var chatId= createChat(senderId,receiverId);
                      return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChat(String senderId, String receiverId) {
        var chatRoom = String.format("%s_%s", senderId, receiverId);
        ChatRoom recieverSender=ChatRoom.builder().chatId(chatRoom).senderId(receiverId).receiverId(senderId).build();
        ChatRoom senderReciever=ChatRoom.builder().chatId(chatRoom).senderId(senderId).receiverId(receiverId).build();
        chatRoomRepo.save(recieverSender);
        chatRoomRepo.save(senderReciever);

        return chatRoom;
    }


}

