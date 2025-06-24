package com.kevin.ChatApi.service;

import com.kevin.ChatApi.model.Status;
import com.kevin.ChatApi.model.UserModel;
import com.kevin.ChatApi.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepo userRepository;
    public UserModel getUser(String username) {
        return userRepository.findById(username).orElse(null);
    }
    public void updateUser(UserModel user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }
    public void disconnect(UserModel user) {
        UserModel storedUser = userRepository.findById(user.getUsername()).orElse(null);
        if (storedUser != null) {
            user.setStatus(Status.OFFLINE);
            userRepository.save(user);
        }

    }
    public UserModel addUser(UserModel user){
        UserModel existeduser = getUser(user.getUsername());
        if(existeduser!=null){
            return existeduser;
        }
        return userRepository.save(user);
    }



    public void deletUser(String username){
        userRepository.deleteById(username);
    }

    public List<UserModel> getConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
