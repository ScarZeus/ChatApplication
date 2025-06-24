package com.kevin.ChatApi.controller;

import com.kevin.ChatApi.model.UserModel;
import com.kevin.ChatApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MessageMapping("/app/connected")
    @SendTo("/user/public")
    public UserModel connect(@Payload UserModel user) {
        userService.updateUser(user);
        return user;
    }

    @MessageMapping("/app/disconnected")
    @SendTo("/user/public")
    public UserModel disconnect(@Payload UserModel user) {
        userService.disconnect(user);
        return user;
    }

    @PostMapping("/app/addUser")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<UserModel> deletUser(@PathVariable("username") String senderId) {
        UserModel deletUser = userService.getUser(senderId);
        if (deletUser != null) {
            userService.deletUser(senderId);
            return ResponseEntity.ok(deletUser);
        }
        return null;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> users() {
        return ResponseEntity.ok(userService.getConnectedUsers());
    }

}
