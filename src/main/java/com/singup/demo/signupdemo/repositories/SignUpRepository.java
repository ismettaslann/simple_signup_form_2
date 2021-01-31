package com.singup.demo.signupdemo.repositories;

import com.singup.demo.signupdemo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends ReactiveMongoRepository<User, String>{
}
