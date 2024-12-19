package com.Project.ChatApplication.repository.UserRepo;

import com.Project.ChatApplication.model.Status;
import com.Project.ChatApplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    List<User> findAllByStatus(Status status);
}
