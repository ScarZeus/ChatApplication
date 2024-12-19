package com.Project.ChatApplication.repository.ChatMessageRepo;

import com.Project.ChatApplication.model.ChatMessage;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepo extends MongoRepository<ChatMessage, String> {
 List<ChatMessage> findByChatId(String id);
}
