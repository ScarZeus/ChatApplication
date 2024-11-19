package repository.ChatMessageRepo;

import model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepo extends MongoRepository<ChatMessage, String> {

}
