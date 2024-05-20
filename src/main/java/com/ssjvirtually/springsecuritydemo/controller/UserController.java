package com.ssjvirtually.springsecuritydemo.controller;

import com.ssjvirtually.springsecuritydemo.entity.User;
import com.ssjvirtually.springsecuritydemo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {


    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username).get();
    }


    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
