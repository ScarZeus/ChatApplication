package model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document
@Builder
public class ChatRoom {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private String chatId;
}
