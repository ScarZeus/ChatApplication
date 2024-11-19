package repository.ChatRoomRepo;

import model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepo extends MongoRepository<ChatRoom,String> {
    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String recieverid);
}
