package com.Project.ChatApplication.repository.UserRepo;

import com.Project.ChatApplication.model.Status;
import com.Project.ChatApplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    List<User> findAllByStatus(Status status);
}
