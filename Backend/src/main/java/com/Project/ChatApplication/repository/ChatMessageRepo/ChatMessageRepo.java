package com.Project.ChatApplication.repository.ChatMessageRepo;

import com.Project.ChatApplication.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepo extends MongoRepository<ChatMessage, String> {

}
