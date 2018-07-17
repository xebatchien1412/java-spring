package com.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.login.entites.User;

public interface UserRepository extends MongoRepository<User, String>{

}
