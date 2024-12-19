package com.Project.ChatApplication.controller;

import lombok.RequiredArgsConstructor;
import com.Project.ChatApplication.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Project.ChatApplication.service.UserService;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MessageMapping("/app/connected")
    @SendTo("/user/public")
    public User connect(@Payload User user) {
        userService.updateUser(user);
        return user;
    }

    @MessageMapping("/app/disconnected")
    @SendTo("/user/public")
    public User disconnect(@Payload User user) {
        userService.disconnect(user);
        return user;
    }

    @PostMapping("/app/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<User> deletUser(@PathVariable("username") String senderId) {
        User deletUser = userService.getUser(senderId);
        if (deletUser != null) {
            userService.deletUser(senderId);
            return ResponseEntity.ok(deletUser);
        }
        return null;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(userService.getConnectedUsers());
    }

}
