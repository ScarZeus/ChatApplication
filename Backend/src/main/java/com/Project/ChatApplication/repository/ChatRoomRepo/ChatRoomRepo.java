package com.Project.ChatApplication.repository.ChatRoomRepo;

import com.Project.ChatApplication.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepo extends MongoRepository<ChatRoom,String> {
    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String recieverid);
}
