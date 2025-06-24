package com.kevin.ChatApi.repo;

import com.kevin.ChatApi.model.Status;
import com.kevin.ChatApi.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<UserModel,String> {

    List<UserModel> findAllByStatus(Status status);
}
