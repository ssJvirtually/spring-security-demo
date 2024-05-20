package com.ssjvirtually.springsecuritydemo.service;
import org.springframework.stereotype.Component;

import com.ssjvirtually.springsecuritydemo.entity.User;
import com.ssjvirtually.springsecuritydemo.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    


    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public Optional<User> getUser(String username) {
        return userRepo.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }


    public List<User> getAllUsers() {
        return  userRepo.findAll();
    }
}
