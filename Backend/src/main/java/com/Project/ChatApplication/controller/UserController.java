package com.Project.ChatApplication.controller;

import lombok.RequiredArgsConstructor;
import com.Project.ChatApplication.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.Project.ChatApplication.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

    @MessageMapping("/app/chatNow")
    @SendTo("/Global/Online")
    public User user(@Payload  User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/app/chatDone")
    @SendTo("/Global/Online")
    public User disconnect(@Payload  User user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(userService.getConnectedUsers());
    }

}
