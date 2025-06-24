package com.kevin.ChatApi.model;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotification {
    private String id;
    private String senderId;
    private String recieverId;
    private String content;
}
