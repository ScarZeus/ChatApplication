package com.kevin.ChatApi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChatMessage {
    @Id
    private String chatId;
    private String charRoomId;
    private String sender;
    private String receiver;
    private String message;
    private Date timestamp;
}
