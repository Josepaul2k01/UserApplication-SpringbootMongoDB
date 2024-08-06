package com.project.one.projOne.repository;
import com.project.one.projOne.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
