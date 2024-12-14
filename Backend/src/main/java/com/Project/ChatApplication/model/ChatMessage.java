package com.Project.ChatApplication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class ChatMessage {
    @Id
    private String id;
    private String charRoomId;
    private String sender;
    private String receiver;
    private String message;
    private Date timestamp;


}
